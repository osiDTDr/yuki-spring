package com.hikari.spring.aop;

/**
 * Core Spring pointcut abstraction.
 */
public interface Pointcut {

    /**
     * Return the ClassFilter for this pointcut.
     *
     * @return the ClassFilter (never {@code null})
     */
    ClassFilter getClassFilter();

    /**
     * Return the MethodMatcher for this pointcut.
     *
     * @return the MethodMatcher (never {@code null})
     */
    MethodMatcher getMethodMatcher();
}
