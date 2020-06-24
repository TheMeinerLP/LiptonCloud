package de.crycodes.de.spacebyter.liptoncloud.utils.annotiations;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.CLASS)
@Target(
    {
        ElementType.METHOD,
        ElementType.FIELD,
        ElementType.PARAMETER,
        ElementType.LOCAL_VARIABLE,
        ElementType.CONSTRUCTOR
    }
)
public @interface ForRemoval {

}
