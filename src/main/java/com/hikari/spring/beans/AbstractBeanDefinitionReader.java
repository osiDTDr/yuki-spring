package com.hikari.spring.beans;

import com.hikari.spring.beans.io.ResourceLoader;

import java.util.HashMap;
import java.util.Map;

/**
 * Abstract base class for bean definition readers which implement
 * the {@link BeanDefinitionReader} interface.
 */
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader {

    private Map<String, BeanDefinition> registry;

    private ResourceLoader resourceLoader;

    protected AbstractBeanDefinitionReader(ResourceLoader resourceLoader) {
        this.registry = new HashMap<>();
        this.resourceLoader = resourceLoader;
    }

    public Map<String, BeanDefinition> getRegistry() {
        return registry;
    }

    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }
}
