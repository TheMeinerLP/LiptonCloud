package de.crycodes.de.spacebyter.liptoncloud.addon.event.annotiations;

import de.crycodes.de.spacebyter.liptoncloud.addon.event.enums.EventTarget;
import de.crycodes.de.spacebyter.liptoncloud.setup.SetupPart;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

/**
 * Coded By CryCodes
 * Class: EventType
 * Date : 19.07.2020
 * Time : 22:01
 * Project: LiptonCloud
 */
@Documented
@Retention(RetentionPolicy.SOURCE)
@Target(value={ METHOD })
public @interface EventType {

    EventTarget eventTarget();

}
