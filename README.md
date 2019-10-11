# yuki-spring
Manual rewrite spring

spring ioc详细设计（以ClassPathXmlApplicationContext 为例）

1.获取xml配置文件的路径, 解析xml文件获取到bean的信息（id, name, 类名等）, 添加到beanFactory的beanDefinitionMap中，k-v为name-BeanDefinition 
2.获取 BeanPostProcessor 类型, 添加到 beanFactory 中
3.调用onRefresh() 
3.1执行初始化bean的方法, 获取beanDefinitionMap中的beanDefinition 通过反射生成 bean
