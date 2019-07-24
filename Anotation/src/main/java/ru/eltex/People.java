package ru.eltex;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface People {
    int massa() default 60;
    int age() default 30;
    int sex() default 1;// 1 - male; 2 - female
}
