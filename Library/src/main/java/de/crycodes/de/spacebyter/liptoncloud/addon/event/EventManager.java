package de.crycodes.de.spacebyter.liptoncloud.addon.event;

import com.google.common.base.Preconditions;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.eventbus.Subscribe;
import com.google.common.reflect.ClassPath;
import de.crycodes.de.spacebyter.liptoncloud.addon.event.abstracts.Event;
import de.crycodes.de.spacebyter.liptoncloud.addon.event.interfaces.Listener;

import java.lang.reflect.Method;
import java.util.LinkedHashMap;

/**
 * Coded By CryCodes
 * Class: EventManager
 * Date : 19.07.2020
 * Time : 21:11
 * Project: LiptonCloud
 */

public final class EventManager {

    private final EventBus eventBus;

    public EventManager() {
        this.eventBus = new EventBus();
    }

    public <T extends Event> T callEvent(T event) {
        Preconditions.checkNotNull(event, "event");
        this.eventBus.post(event);
        event.postCall();

        return event;
    }

    public void registerListener(Listener listener) {
        for (Method method : listener.getClass().getDeclaredMethods())
            Preconditions.checkArgument(
                    !method.isAnnotationPresent(Subscribe.class),
                    "Listener %s has registered using deprecated subscribe annotation! Please update to @EventHandler."
                    , listener
            );

        this.eventBus.register(listener);
    }

    public void registerListener(Listener... listeners){
        for (Listener listener : listeners)
            this.registerListener(listener);
    }

    public void unregisterListener(Listener listener) {
        this.eventBus.unregister(listener);
    }

    public void registerListener(String classpath, Class main) {
        try {
            for (UnmodifiableIterator unmodifiableIterator = ClassPath.from(main.getClassLoader()).getTopLevelClasses(classpath).iterator(); unmodifiableIterator.hasNext(); ) {
                ClassPath.ClassInfo classInfo = (ClassPath.ClassInfo)unmodifiableIterator.next();
                Object obj = Class.forName(classInfo.getName(), true, main.getClassLoader()).newInstance();
                if (obj instanceof Listener) {
                    this.registerListener((Listener) obj);
                }
            }
        } catch (ClassNotFoundException|IllegalAccessException|InstantiationException|java.io.IOException e) {
            e.printStackTrace();
        }
    }


}
