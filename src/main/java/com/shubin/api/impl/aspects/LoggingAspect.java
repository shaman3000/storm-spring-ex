package com.shubin.api.impl.aspects;

import com.shubin.api.LoggingSpec;
import com.shubin.api.impl.AbstractAspect;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

import java.lang.reflect.Method;

/**
 * Created by sshubin on 14.11.2016.
 */

public class LoggingAspect extends AbstractAspect implements MethodInterceptor {

    private MethodMatcher methodMatcher =

            new MethodMatcher() {

                @Override
                public boolean matches(Method method, Class<?> targetClass) {
                    return method.isAnnotationPresent(LoggingSpec.class);
                }

                @Override
                public boolean isRuntime() {
                    return false;
                }

                @Override
                public boolean matches(Method method, Class<?> targetClass, Object... args) {
                    return false;
                }
            };

    private ClassFilter classFilter =
            new ClassFilter() {
                @Override
                public boolean matches(Class<?> clazz) {
                    return true;
                }
            };

    private Pointcut poincut =

            new Pointcut() {
                @Override
                public ClassFilter getClassFilter() {
                    return classFilter;
                }

                @Override
                public MethodMatcher getMethodMatcher() {
                    return methodMatcher;
                }
            };


    private DefaultPointcutAdvisor advice = new DefaultPointcutAdvisor();

    public LoggingAspect() {
        advice.setPointcut(poincut);
        advice.setAdvice(this);
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        long ms = System.currentTimeMillis();
        System.out.println("Proceed method -> START: " + invocation.getMethod().getName());
        try {
            return invocation.proceed() ;
        } finally {
            System.out.println("Proceed method -> FINISH: " + invocation.getMethod().getName() +
                    " [" + String.valueOf(System.currentTimeMillis() - ms) + "ms]");
        }
    }

    @Override
    public void apply(Object bean, ProxyFactory factory) {
        factory.addAdvisor(advice);
    }

}
