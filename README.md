# yuki-spring
Manual rewrite spring

spring ioc详细设计（以ClassPathXmlApplicationContext 为例）

1.获取xml配置文件的路径, 解析xml文件获取到bean的信息（id, name, 类名等）, 添加到beanFactory的beanDefinitionMap中，k-v为name-BeanDefinition 

2.获取 BeanPostProcessor 类型, 添加到 beanFactory 中

3.调用onRefresh() 

3.1执行初始化bean的方法, 获取beanDefinitionMap中的beanDefinition 通过反射生成 bean


ioc容器的实现

Resource

以 Resource 接口为核心的几个类都是处理配置文件从哪里读取、配置文件如何读取 的问题

类名	说明

Resource	接口，标识一个外部资源。通过 getInputStream() 方法 获取资源的输入流 

UrlResource	实现 Resource 接口的资源类，通过 URL 获取资源

ResourceLoader	资源加载类，通过 getResource(String) 方法获取一个 Resouce 对象，是 获取 Resouce 的主要途径 

BeanDefinition

以 BeanDefinition 类为核心的几个类，都是用于解决 Bean 的具体定义问题，包括 Bean 的名字是什么、它的类型是什么，

它的属性赋予了哪些值或者引用，也就是 如何在 IoC 容器中定义一个 Bean，使得 IoC 容器可以根据这个定义来生成实例 的问题

类名	说明

BeanDefinition	该类保存了 Bean 定义。包括 Bean 的 名字 String beanClassName、类型 Class beanClass、属性 PropertyValues propertyValues。根据其 类型 可以生成一个类实例，然后可以把 属性 注入进去。propertyValues 里面包含了一个个 PropertyValue 条目，每个条目都是键值对 String - Object，分别对应要生成实例的属性的名字与类型。在 Spring 的 XML 中的 property 中，键是 key ，值是 value 或者 ref。对于 value 只要直接注入属性就行了，但是 ref 要先进行解析。Object 如果是 BeanReference 类型，则说明其是一个引用，其中保存了引用的名字，需要用先进行解析，转化为对应的实际 Object。

BeanDefinitionReader	解析 BeanDefinition 的接口。通过 loadBeanDefinitions(String) 来从一个地址加载类定义。

AbstractBeanDefinitionReader	实现 BeanDefinitionReader 接口的抽象类（未具体实现 loadBeanDefinitions，而是规范了 BeanDefinitionReader 的基本结构）。内置一个 HashMap rigistry，用于保存 String - beanDefinition 的键值对。内置一个 ResourceLoader resourceLoader，用于保存类加载器。用意在于，使用时，只需要向其 loadBeanDefinitions() 传入一个资源地址，就可以自动调用其类加载器，并把解析到的 BeanDefinition 保存到 registry 中去。

XmlBeanDefinitionReader	具体实现了 loadBeanDefinitions() 方法，从 XML 文件中读取类定义。

BeanFactory

以 BeanFactory 接口为核心的几个类，都是用于解决 IoC 容器在 已经获取 Bean 的定义的情况下，如何装配、获取 Bean 实例 的问题

类名	说明

BeanFactory	接口，标识一个 IoC 容器。通过 getBean(String) 方法来 获取一个对象

AbstractBeanFactory	BeanFactory 的一种抽象类实现，规范了 IoC 容器的基本结构，但是把生成 Bean 的具体实现方式留给子类实现。IoC 容器的结构：AbstractBeanFactory 维护一个 beanDefinitionMap 哈希表用于保存类的定义信息（BeanDefinition）。获取 Bean 时，如果 Bean 已经存在于容器中，则返回之，否则则调用 doCreateBean 方法装配一个 Bean。（所谓存在于容器中，是指容器可以通过 beanDefinitionMap 获取 BeanDefinition 进而通过其 getBean() 方法获取 Bean。）

AutowireCapableBeanFactory	可以实现自动装配的 BeanFactory。在这个工厂中，实现了 doCreateBean 方法，该方法分三步：1，通过 BeanDefinition 中保存的类信息实例化一个对象；2，把对象保存在 BeanDefinition 中，以备下次获取；3，为其装配属性。装配属性时，通过 BeanDefinition 中维护的 PropertyValues 集合类，把 String - Value 键值对注入到 Bean 的属性中去。如果 Value 的类型是 BeanReference 则说明其是一个引用（对应于 XML 中的 ref），通过 getBean 对其进行获取，然后注入到属性中。

ApplicationContext

以 ApplicationContext 接口为核心的几个类，主要是对前面 Resource 、 BeanFactory、BeanDefinition 进行了功能的封装，解决 根据地址获取 IoC 容器并使用 的问题

类名	说明

ApplicationContext	标记接口，继承了 BeanFactory。通常，要实现一个 IoC 容器时，需要先通过 ResourceLoader 获取一个 Resource，其中包括了容器的配置、Bean 的定义信息。接着，使用 BeanDefinitionReader 读取该 Resource 中的 BeanDefinition 信息。最后，把 BeanDefinition 保存在 BeanFactory 中，容器配置完毕可以使用。注意到 BeanFactory 只实现了 Bean 的 装配、获取，并未说明 Bean 的 来源 也就是 BeanDefinition 是如何 加载 的。该接口把 BeanFactory 和 BeanDefinitionReader 结合在了一起。

AbstractApplicationContext	ApplicationContext 的抽象实现，内部包含一个 BeanFactory 类。主要方法有 getBean() 和 refresh() 方法。getBean() 直接调用了内置 BeanFactory 的 getBean() 方法，refresh() 则用于实现 BeanFactory 的刷新，也就是告诉 BeanFactory 该使用哪个资源（Resource）加载类定义（BeanDefinition）信息，该方法留给子类实现，用以实现 从不同来源的不同类型的资源加载类定义 的效果。

ClassPathXmlApplicationContext	从类路径加载资源的具体实现类。内部通过 XmlBeanDefinitionReader 解析 UrlResourceLoader 读取到的 Resource，获取 BeanDefinition 信息，然后将其保存到内置的 BeanFactory 中。

在yuki-spring，先用 BeanDefinitionReader 读取 BeanDefiniton 后，保存在内置的 registry （键值对为 String - BeanDefinition 的哈希表，通过 getRigistry() 获取）中，然后由 ApplicationContext 把 BeanDefinitionReader 中 registry 的键值对一个个赋值给 BeanFactory 中保存的 beanDefinitionMap。而在 Spring 的实现中，BeanDefinitionReader 直接操作 BeanDefinition ，它的 getRegistry() 获取的不是内置的 registry，而是 BeanFactory 的实例。如何实现呢？以 DefaultListableBeanFactory 为例，它实现了一个 BeanDefinitonRigistry 接口，该接口把 BeanDefinition 的 注册 、获取 等方法都暴露了出来，这样，BeanDefinitionReader 可以直接通过这些方法把 BeanDefiniton 直接加载到 BeanFactory 中去


设计模式

模板方法模式

该模式大量使用，例如在 BeanFactory 中，把 getBean() 交给子类实现，不同的子类 **BeanFactory 对其可以采取不同的实现。

代理模式

在 yuki-spring 中（Spring 中也有类似但不完全相同的实现方式），ApplicationContext 继承了 BeanFactory 接口，具备了 getBean() 功能，但是又内置了一个 BeanFactory 实例，getBean() 直接调用 BeanFactory 的 getBean() 。但是ApplicationContext 加强了 BeanFactory，它把类定义的加载也包含进去了。