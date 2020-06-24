package de.crycodes.de.spacebyter.liptoncloud.utils.annotiations;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.TYPE, ElementType.METHOD})
public @interface ReplacedBy {

    String value() default "";
}
