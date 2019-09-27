package com.hikari.spring.beans.factory;

/**
 * The root interface for accessing a Spring bean container.
 */
public interface BeanFactory {

    /**
     * Return an instance, which may be shared or independent, of the specified bean.
     *
     * @param name the name of the bean to retrieve
     * @return an instance of the bean
     * @throws Exception Exception
     */
    Object getBean(String name) throws Exception;
}
