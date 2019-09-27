package com.hikari.spring.aop;

import java.lang.reflect.Method;

/**
 * Part of a {@link Pointcut}: Checks whether the target method is eligible for advice.
 */
public interface MethodMatcher {

    /**
     * Perform static checking whether the given method matches.
     *
     * @param method      the candidate method
     * @param targetClass the target class (may be {@code null}, in which case
     *                    the candidate class must be taken to be the method's declaring class)
     * @return whether or not this method matches statically
     */
    boolean matches(Method method, Class targetClass);
}
