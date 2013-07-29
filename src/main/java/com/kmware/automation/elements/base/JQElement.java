package com.kmware.automation.elements.base;

import java.lang.annotation.*;

/**
 * Created with IntelliJ IDEA.
 * User: Oleg
 * Date: 26.07.13 13:37
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface JQElement {
    String extraSelector();
}
