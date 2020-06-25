package de.crycodes.de.spacebyter.liptoncloud.utils.annotiations;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ShouldRunAsync { }
