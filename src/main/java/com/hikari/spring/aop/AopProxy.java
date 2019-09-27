package com.hikari.spring.aop;

/**
 * Delegate interface for a configured AOP proxy, allowing for the creation
 * of actual proxy objects.
 */
public interface AopProxy {

    /**
     * Create a new proxy object.
     *
     * @return the new proxy object (never {@code null})
     */
    Object getProxy();
}
