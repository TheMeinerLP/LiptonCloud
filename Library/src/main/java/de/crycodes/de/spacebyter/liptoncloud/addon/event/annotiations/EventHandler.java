package de.crycodes.de.spacebyter.liptoncloud.addon.event.annotiations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Coded By CryCodes
 * Class: EventHandler
 * Date : 19.07.2020
 * Time : 20:59
 * Project: LiptonCloud
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface EventHandler {
    byte priority() default 0;
}