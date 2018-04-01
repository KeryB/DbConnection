package ru.connection.db.annotation;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Repeatable(value = JfxBuilder.class)
public @interface Builder {
    String name();
    JfxBuilderImpl.Type type();
    double layOutX ()default 0;
    double layOutY ()default 0;
    double prefWidth() default 150;
}
