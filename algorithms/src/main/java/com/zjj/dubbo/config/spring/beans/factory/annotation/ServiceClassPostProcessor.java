package com.zjj.dubbo.config.spring.beans.factory.annotation;

import com.zjj.dubbo.common.utils.RpcAnnotationUtils;
import com.zjj.dubbo.common.utils.StringUtils;
import com.zjj.dubbo.config.annotation.RpcService;
import com.zjj.dubbo.config.spring.ServiceBean;
import com.zjj.dubbo.config.spring.context.annotation.RpcClassPathBeanDefinitionScanner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.SingletonBeanRegistry;
import org.springframework.beans.factory.support.*;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.util.ClassUtils;
import org.springframework.util.CollectionUtils;

import java.lang.annotation.Annotation;
import java.util.*;

import static org.springframework.context.annotation.AnnotationConfigUtils.CONFIGURATION_BEAN_NAME_GENERATOR;

@Slf4j
public class ServiceClassPostProcessor implements BeanDefinitionRegistryPostProcessor, EnvironmentAware,
        ResourceLoaderAware, BeanClassLoaderAware {

    private final static List<Class<? extends Annotation>> serviceAnnotationTypes =
            Arrays.asList(RpcService.class);
    protected final Set<String> packagesToScan;
    private Environment environment;
    private ResourceLoader resourceLoader;
    private ClassLoader classLoader;

    public ServiceClassPostProcessor(String... packagesToScan) {
        this(Arrays.asList(packagesToScan));
    }

    public ServiceClassPostProcessor(Collection<String> packagesToScan) {
        this(new LinkedHashSet<>(packagesToScan));
    }

    public ServiceClassPostProcessor(Set<String> packagesToScan) {
        this.packagesToScan = packagesToScan;
    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {

        Set<String> resolvedPackagesToScan = resolvePackagesToScan();
        if (CollectionUtils.isEmpty(resolvedPackagesToScan)) {
            log.warn("packagesToScan is empty , ServiceBean registry will be ignored!");
        } else {
            registerServiceBeans(resolvedPackagesToScan, registry);
        }
    }

    private void registerServiceBeans(Set<String> packagesToScan, BeanDefinitionRegistry registry) {
        RpcClassPathBeanDefinitionScanner scanner =
                new RpcClassPathBeanDefinitionScanner(registry, environment, resourceLoader);
        BeanNameGenerator beanNameGenerator = resolveBeanNameGenerator(registry);
        scanner.setBeanNameGenerator(beanNameGenerator);
        serviceAnnotationTypes.forEach(annotationType -> scanner.addIncludeFilter(new AnnotationTypeFilter(annotationType)));
        for (String packageToScan : packagesToScan) {
            scanner.scan(packageToScan);
            Set<BeanDefinitionHolder> beanDefinitionHolders =
                    findServiceBeanDefinitionHolders(scanner, packageToScan, registry, beanNameGenerator);
            if (!CollectionUtils.isEmpty(beanDefinitionHolders)) {
                for (BeanDefinitionHolder beanDefinitionHolder : beanDefinitionHolders) {
                    registerServiceBean(beanDefinitionHolder, registry, scanner);
                }
            } else {
                log.warn("No Spring Bean annotating @RpcService was found under package [{}].", packagesToScan);
            }
        }
    }

    private void registerServiceBean(BeanDefinitionHolder beanDefinitionHolder,
                                     BeanDefinitionRegistry registry, RpcClassPathBeanDefinitionScanner scanner) {
        Class<?> beanClass = resolveClass(beanDefinitionHolder);
        Annotation service = findServiceAnnotation(beanClass);
        AnnotationAttributes serviceAnnotationAttributes = AnnotationUtils.getAnnotationAttributes(service, false, false);
        Class<?> interfaceClass = RpcAnnotationUtils.resolveServiceInterfaceClass(serviceAnnotationAttributes, beanClass);
        String annotatedServiceBeanName = beanDefinitionHolder.getBeanName();
        AbstractBeanDefinition serviceBeanDefinition =
                buildServiceBeanDefinition(service, serviceAnnotationAttributes, interfaceClass, annotatedServiceBeanName);
        String beanName = generateServiceBeanName(serviceAnnotationAttributes, interfaceClass);
        if (scanner.checkCandidate(beanName, serviceBeanDefinition)) {
            registry.registerBeanDefinition(beanName, serviceBeanDefinition);
            log.debug("The BeanDefinition [{}] of ServiceBean has been registered with name : [{}]", serviceBeanDefinition, beanName);
        } else {
            log.warn("The Duplicated BeanDefinition [{}]  of ServiceBean[ bean name : {} was be found , Did @RpcComponentScan scan to same package in many times?",
                    serviceBeanDefinition, beanName);
        }
    }

    /**
     * 生成该类的bean name
     *
     * @param serviceAnnotationAttributes serviceAnnotationAttributes
     * @param interfaceClass              interfaceClass
     * @return beanName
     */
    private String generateServiceBeanName(AnnotationAttributes serviceAnnotationAttributes, Class<?> interfaceClass) {
        ServiceBeanNameBuilder builder = ServiceBeanNameBuilder.create(interfaceClass, environment)
                .group(serviceAnnotationAttributes.getString("group"))
                .version(serviceAnnotationAttributes.getString("version"));
        return builder.build();
    }

    private AbstractBeanDefinition buildServiceBeanDefinition(Annotation serviceAnnotation,
                                                              AnnotationAttributes serviceAnnotationAttributes,
                                                              Class<?> interfaceClass,
                                                              String annotatedServiceBeanName) {
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.rootBeanDefinition(ServiceBean.class);
        AbstractBeanDefinition beanDefinition = builder.getBeanDefinition();

        return beanDefinition;
    }

    private Class<?> resolveClass(BeanDefinitionHolder beanDefinitionHolder) {
        BeanDefinition beanDefinition = beanDefinitionHolder.getBeanDefinition();
        String beanClassName = beanDefinition.getBeanClassName();
        Class<?> beanClass = ClassUtils.resolveClassName(beanClassName, classLoader);
        return beanClass;
    }

    private Annotation findServiceAnnotation(Class<?> beanClass) {
        return serviceAnnotationTypes.stream()
                .map(annotationType -> AnnotatedElementUtils.findMergedAnnotation(beanClass, annotationType))
                .filter(Objects::nonNull)
                .findFirst()
                .orElse(null);
    }

    private Set<BeanDefinitionHolder> findServiceBeanDefinitionHolders(ClassPathBeanDefinitionScanner scanner,
                                                                       String packageToScan,
                                                                       BeanDefinitionRegistry registry,
                                                                       BeanNameGenerator beanNameGenerator) {
        Set<BeanDefinition> beanDefinitions = scanner.findCandidateComponents(packageToScan);
        Set<BeanDefinitionHolder> beanDefinitionHolders = new LinkedHashSet<>(beanDefinitions.size());
        for (BeanDefinition beanDefinition : beanDefinitions) {
            String beanName = beanNameGenerator.generateBeanName(beanDefinition, registry);
            BeanDefinitionHolder beanDefinitionHolder = new BeanDefinitionHolder(beanDefinition, beanName);
            beanDefinitionHolders.add(beanDefinitionHolder);
        }
        return beanDefinitionHolders;
    }

    private BeanNameGenerator resolveBeanNameGenerator(BeanDefinitionRegistry registry) {
        BeanNameGenerator beanNameGenerator = null;
        if (registry instanceof SingletonBeanRegistry) {
            SingletonBeanRegistry singletonBeanRegistry = (SingletonBeanRegistry) registry;
            beanNameGenerator = (BeanNameGenerator) singletonBeanRegistry.getSingleton(CONFIGURATION_BEAN_NAME_GENERATOR);
        }

        if (beanNameGenerator == null) {
            log.debug("BeanNameGenerator bean can't be found in BeanFactory with name [{}].", CONFIGURATION_BEAN_NAME_GENERATOR);
            log.debug("BeanNameGenerator will be a instance of {} , it maybe a potential problem on bean name generation.", AnnotationBeanNameGenerator.class.getName());
            beanNameGenerator = new AnnotationBeanNameGenerator();
        }

        return beanNameGenerator;
    }

    private Set<String> resolvePackagesToScan() {
        Set<String> resolvedPackagesToScan = new LinkedHashSet<>(packagesToScan.size());
        for (String packageToScan : packagesToScan) {
            if (StringUtils.hasText(packageToScan)) {
                String resolvePlaceholders = environment.resolvePlaceholders(packageToScan.trim());
                resolvedPackagesToScan.add(resolvePlaceholders);
            }
        }
        return resolvedPackagesToScan;
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }
}
