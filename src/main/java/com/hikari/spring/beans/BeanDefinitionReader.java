package com.hikari.spring.beans;

/**
 * Read BeanDefinition from configuration
 */
public interface BeanDefinitionReader {

    void loadBeanDefinitions(String location) throws Exception;
}
