package com.hikari.spring.aop;

import com.hikari.spring.HelloWorldService;
import com.hikari.spring.HelloWorldServiceImpl;
import org.junit.Assert;
import org.junit.Test;

public class AspectJExpressionPointcutTest {

    @Test
    public void testClassFilter() throws Exception {
        String expression = "execution(* us.codecraft.tinyioc.*.*(..))";
        AspectJExpressionPointcut aspectJExpressionPointcut = new AspectJExpressionPointcut();
        aspectJExpressionPointcut.setExpression(expression);
        boolean matches = aspectJExpressionPointcut.getClassFilter().matches(HelloWorldService.class);
        Assert.assertTrue(matches);
    }

    @Test
    public void testMethodInterceptor() throws Exception {
        String expression = "execution(* us.codecraft.tinyioc.*.*(..))";
        AspectJExpressionPointcut aspectJExpressionPointcut = new AspectJExpressionPointcut();
        aspectJExpressionPointcut.setExpression(expression);
        boolean matches = aspectJExpressionPointcut.getMethodMatcher().matches(HelloWorldServiceImpl.class.getDeclaredMethod("helloWorld"), HelloWorldServiceImpl.class);
        Assert.assertTrue(matches);
    }
}
