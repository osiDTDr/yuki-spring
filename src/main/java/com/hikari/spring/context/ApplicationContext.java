package com.hikari.spring.context;

import com.hikari.spring.beans.factory.BeanFactory;

/**
 * Central interface to provide configuration for an application.
 * This is read-only while the application is running, but may be
 * reloaded if the implementation supports this.
 */
public interface ApplicationContext extends BeanFactory {
}
