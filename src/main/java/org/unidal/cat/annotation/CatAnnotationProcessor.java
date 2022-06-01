package org.unidal.cat.annotation;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class CatAnnotationProcessor implements BeanPostProcessor {
   @Override
   public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
      return bean;
   }
}
