package de.crycodes.de.spacebyter.liptoncloud.setup;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface SetupPart {

    int id();

    String[] forbiddenAnswers() default {};

    String question();

}
