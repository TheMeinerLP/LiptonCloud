package de.crycodes.de.spacebyter.liptoncloud.addon.event;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Coded By CryCodes
 * Class: EventHandlerMethod
 * Date : 19.07.2020
 * Time : 20:59
 * Project: LiptonCloud
 */

public class EventHandlerMethod {
    private final Object listener;

    private final Method method;

    public EventHandlerMethod(Object listener, Method method) {
        this.listener = listener;
        this.method = method;
    }

    public Object getListener() {
        return this.listener;
    }

    public Method getMethod() {
        return this.method;
    }

    public void invoke(Object event)  {
        try {
            this.method.invoke(this.listener, event);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}