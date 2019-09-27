package com.hikari.spring.aop;

import org.aopalliance.aop.Advice;

/**
 * Spring AOP Advisor that can be used for any AspectJ pointcut expression.
 */
public class AspectJExpressionPointcutAdvisor implements PointcutAdvisor {

    private AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();

    private Advice advice;

    public void setAdvice(Advice advice) {
        this.advice = advice;
    }

    public void setExpression(String expression) {
        this.pointcut.setExpression(expression);
    }

    @Override
    public Advice getAdvice() {
        return advice;
    }

    @Override
    public Pointcut getPointcut() {
        return pointcut;
    }
}
