package com.hikari.spring.context;

import com.hikari.spring.beans.BeanDefinition;
import com.hikari.spring.beans.factory.AbstractBeanFactory;
import com.hikari.spring.beans.factory.AutowireCapableBeanFactory;
import com.hikari.spring.beans.io.ResourceLoader;
import com.hikari.spring.beans.xml.XmlBeanDefinitionReader;

import java.util.Map;

/**
 * Standalone XML application context, taking the context definition files
 * from the class path, interpreting plain paths as class path resource names
 * that include the package path (e.g. "mypackage/myresource.txt"). Useful for
 * test harnesses as well as for application contexts embedded within JARs.
 */
public class ClassPathXmlApplicationContext extends AbstractApplicationContext {

    private String configLocation;

    /**
     * Create a new ClassPathXmlApplicationContext, loading the definitions
     * from the given XML file and automatically refreshing the context.
     *
     * @param configLocation resource location
     * @throws Exception Exception
     */
    public ClassPathXmlApplicationContext(String configLocation) throws Exception {
        this(configLocation, new AutowireCapableBeanFactory());
    }

    /**
     * Create a new ClassPathXmlApplicationContext with the given parent,
     * loading the definitions from the given XML files.
     *
     * @param configLocation array of resource locations
     * @param beanFactory    the parent context
     * @throws Exception Exception
     */
    public ClassPathXmlApplicationContext(String configLocation, AbstractBeanFactory beanFactory) throws Exception {
        super(beanFactory);
        this.configLocation = configLocation;
        refresh();
    }

    /**
     * Load bean definitions into the given bean factory, typically through
     * delegating to one or more bean definition readers.
     *
     * @param beanFactory the bean factory to load bean definitions into
     * @throws Exception Exception
     */
    @Override
    protected void loadBeanDefinitions(AbstractBeanFactory beanFactory) throws Exception {
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(new ResourceLoader());
        xmlBeanDefinitionReader.loadBeanDefinitions(configLocation);
        for (Map.Entry<String, BeanDefinition> beanDefinitionEntry : xmlBeanDefinitionReader.getRegistry().entrySet()) {
            beanFactory.registerBeanDefinition(beanDefinitionEntry.getKey(), beanDefinitionEntry.getValue());
        }
    }
}
