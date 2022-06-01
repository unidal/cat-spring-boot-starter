package org.unidal.cat.annotation;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Map;
import java.util.Objects;

class CatRegistrar implements ImportBeanDefinitionRegistrar {
   @Override
   public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
      Map<String, Object> attributes = importingClassMetadata.getAnnotationAttributes(EnableCat.class.getName());
      Object enabled = attributes.get("value");

      if (enabled instanceof Boolean) {
         if (((Boolean) enabled).booleanValue()) {
            registerIfNotExists(registry, CatAnnotationProcessor.class);
         } else {
            System.out.println("CAT is DISABLED explicitly by @EnabledCat(false). ");
         }
      }
   }

   private boolean registerIfNotExists(BeanDefinitionRegistry registry, Class<?> beanClass) {
      String beanName = beanClass.getName();

      if (registry.containsBeanDefinition(beanName)) {
         return false;
      }

      String[] candidates = registry.getBeanDefinitionNames();

      for (String candidate : candidates) {
         BeanDefinition beanDefinition = registry.getBeanDefinition(candidate);

         if (Objects.equals(beanDefinition.getBeanClassName(), beanClass.getName())) {
            return false;
         }
      }

      BeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition(beanClass).getBeanDefinition();

      registry.registerBeanDefinition(beanName, beanDefinition);
      return true;
   }
}
