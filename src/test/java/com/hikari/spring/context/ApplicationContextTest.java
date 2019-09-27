package com.hikari.spring.context;

import com.hikari.spring.HelloWorldService;
import org.junit.Test;


public class ApplicationContextTest {

    @Test
    public void test() throws Exception {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("tinyioc.xml");
        HelloWorldService helloWorldService = (HelloWorldService) applicationContext.getBean("helloWorldService");
        helloWorldService.helloWorld();
    }

    @Test
    public void testPostBeanProcessor() throws Exception {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("tinyioc-postbeanprocessor.xml");
        HelloWorldService helloWorldService = (HelloWorldService) applicationContext.getBean("helloWorldService");
        helloWorldService.helloWorld();
    }
}
