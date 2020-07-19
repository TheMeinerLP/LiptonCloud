package de.crycodes.de.spacebyter.liptoncloud.addon.event;

import de.crycodes.de.spacebyter.liptoncloud.addon.event.abstracts.Event;
import de.crycodes.de.spacebyter.liptoncloud.addon.event.annotiations.EventHandler;
import de.crycodes.de.spacebyter.liptoncloud.addon.event.exceptions.EventAnnotatedNoArgument;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Coded By CryCodes
 * Class: EventBus
 * Date : 19.07.2020
 * Time : 20:56
 * Project: LiptonCloud
 */

public class EventBus {

    private final Map<Class<?>, Map<Byte, Map<Object, Method[]>>> byListenerAndPriority;
    private final Map<Class<?>, EventHandlerMethod[]> byEventBaked;
    private final Lock lock;

    public EventBus() {
        byEventBaked = new ConcurrentHashMap<>();
        byListenerAndPriority = new HashMap<>();
        lock = new ReentrantLock();
    }

    public void post(Object event) {

        EventHandlerMethod[] handlers = this.byEventBaked.get(event.getClass());

        if (handlers != null)

            for (EventHandlerMethod method : handlers) {

                try {

                    method.invoke(event);

                } catch (IllegalArgumentException ex) {

                    throw new Error("Method rejected target/argument: " + event, ex);

                }

            }
    }

    private Map<Class<?>, Map<Byte, Set<Method>>> findHandlers(Object listener) {

        Map<Class<?>, Map<Byte, Set<Method>>> handler = new HashMap<>();

        for (Method m : listener.getClass().getDeclaredMethods()) {

            EventHandler annotation = m.getAnnotation(EventHandler.class);

            if (annotation != null) {

                Class<?>[] params = m.getParameterTypes();

                if (params.length != 1) {

                    try {

                        throw new EventAnnotatedNoArgument();

                    } catch (EventAnnotatedNoArgument eventAnnotatedNoArgument) {

                        eventAnnotatedNoArgument.printStackTrace();

                    }

                } else {

                    Map<Byte, Set<Method>> prioritiesMap = handler.computeIfAbsent(params[0], k -> new HashMap<>());

                    Set<Method> priority = prioritiesMap.computeIfAbsent(annotation.priority(), k -> new HashSet<>());

                    priority.add(m);

                }

            }

        }

        return handler;

    }

    public void register(Object listener) {

        Map<Class<?>, Map<Byte, Set<Method>>> handler = findHandlers(listener);

        this.lock.lock();

        try {

            for (Map.Entry<Class<?>, Map<Byte, Set<Method>>> e : handler.entrySet()) {

                Map<Byte, Map<Object, Method[]>> prioritiesMap = this.byListenerAndPriority.computeIfAbsent(e.getKey(), k -> new HashMap<>());

                for (Map.Entry<Byte, Set<Method>> entry : e.getValue().entrySet()) {

                    Map currentPriorityMap = prioritiesMap.computeIfAbsent(entry.getKey(), k -> new HashMap<>());

                    Method[] baked = new Method[(entry.getValue()).size()];

                    currentPriorityMap.put(listener, (entry.getValue()).toArray(baked));

                }

                bakeHandlers(e.getKey());

            }

        } finally {

            this.lock.unlock();

        }

    }

    public void unregister(Object listener) {

        Map<Class<?>, Map<Byte, Set<Method>>> handler = findHandlers(listener);

        this.lock.lock();

        try {

            for (Map.Entry<Class<?>, Map<Byte, Set<Method>>> e : handler.entrySet()) {

                Map<Byte, Map<Object, Method[]>> prioritiesMap = this.byListenerAndPriority.get(e.getKey());

                if (prioritiesMap != null) {

                    for (Byte priority : e.getValue().keySet()) {

                        Map<Object, Method[]> currentPriority = prioritiesMap.get(priority);

                        if (currentPriority != null) {

                            currentPriority.remove(listener);

                            if (currentPriority.isEmpty())

                                prioritiesMap.remove(priority);

                        }

                    }

                    if (prioritiesMap.isEmpty())

                        this.byListenerAndPriority.remove(e.getKey());

                }

                bakeHandlers(e.getKey());
            }

        } finally {

            this.lock.unlock();

        }

    }

    private void bakeHandlers(Class<?> eventClass) {

        Map<Byte, Map<Object, Method[]>> handlersByPriority = this.byListenerAndPriority.get(eventClass);

        if (handlersByPriority != null) {

            List<EventHandlerMethod> handlersList = new ArrayList<>(handlersByPriority.size() * 2);

            byte value = Byte.MIN_VALUE;

            while (true) {

                Map<Object, Method[]> handlersByListener = handlersByPriority.get(value);

                if (handlersByListener != null)

                    for (Map.Entry<Object, Method[]> listenerHandlers : handlersByListener.entrySet()) {

                        for (Method method : listenerHandlers.getValue()) {

                            EventHandlerMethod ehm = new EventHandlerMethod(listenerHandlers.getKey(), method);

                            handlersList.add(ehm);

                        }

                    }

                value = (byte)(value + 1);

                if (value >= Byte.MAX_VALUE) {

                    this.byEventBaked.put(eventClass, handlersList.toArray(new EventHandlerMethod[0]));

                    return;

                }

            }

        }

        this.byEventBaked.remove(eventClass);

    }

}
