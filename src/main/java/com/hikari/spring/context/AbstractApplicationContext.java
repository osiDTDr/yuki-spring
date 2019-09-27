package com.hikari.spring.context;

import com.hikari.spring.beans.factory.AbstractBeanFactory;
import com.hikari.spring.beans.BeanPostProcessor;

import java.util.List;

/**
 * Central interface to provide configuration for an application.
 * This is read-only while the application is running, but may be
 * reloaded if the implementation supports this.
 */
public abstract class AbstractApplicationContext implements ApplicationContext {
    protected AbstractBeanFactory beanFactory;

    /**
     * Create a new AbstractApplicationContext with the given parent context.
     *
     * @param beanFactory the parent context
     */
    public AbstractApplicationContext(AbstractBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    /**
     * Load or refresh the persistent representation of the configuration,
     * which might an XML file, properties file, or relational database schema.
     *
     * @throws Exception Exception
     */
    public void refresh() throws Exception {
        loadBeanDefinitions(beanFactory);
        registerBeanPostProcessors(beanFactory);
        onRefresh();
    }

    /**
     * Load bean definitions into the given bean factory, typically through
     * delegating to one or more bean definition readers.
     *
     * @param beanFactory the bean factory to load bean definitions into
     * @throws Exception Exception
     */
    protected abstract void loadBeanDefinitions(AbstractBeanFactory beanFactory) throws Exception;

    /**
     * Instantiate and invoke all registered BeanPostProcessor beans,
     * respecting explicit order if given.
     * <p>Must be called before any instantiation of application beans.
     */
    protected void registerBeanPostProcessors(AbstractBeanFactory beanFactory) throws Exception {
        List beanPostProcessors = beanFactory.getBeansForType(BeanPostProcessor.class);
        for (Object beanPostProcessor : beanPostProcessors) {
            beanFactory.addBeanPostProcessor((BeanPostProcessor) beanPostProcessor);
        }
    }

    /**
     * Called on initialization of special beans, before instantiation of singletons
     *
     * @throws Exception Exception
     */
    protected void onRefresh() throws Exception {
        beanFactory.preInstantiateSingletons();
    }

    /**
     * Return an instance, which may be shared or independent, of the specified bean.
     *
     * @param name the name of the bean to retrieve
     * @return an instance of the bean
     * @throws Exception Exception
     */
    @Override
    public Object getBean(String name) throws Exception {
        return beanFactory.getBean(name);
    }
}
