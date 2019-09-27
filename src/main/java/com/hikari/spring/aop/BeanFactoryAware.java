package com.hikari.spring.aop;

import com.hikari.spring.beans.factory.BeanFactory;

/**
 * Interface to be implemented by beans that wish to be aware of their
 * owning {@link BeanFactory}.
 */
public interface BeanFactoryAware {

    /**
     * Callback that supplies the owning factory to a bean instance.
     * <p>Invoked after the population of normal bean properties
     * but before an initialization callback such as
     * afterPropertiesSet() or a custom init-method.
     *
     * @param beanFactory owning BeanFactory (never {@code null}).
     *                    * The bean can immediately call methods on the factory.
     * @throws Exception Exception
     */
    void setBeanFactory(BeanFactory beanFactory) throws Exception;
}
