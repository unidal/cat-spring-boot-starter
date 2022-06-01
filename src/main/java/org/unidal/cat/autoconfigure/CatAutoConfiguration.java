package org.unidal.cat.autoconfigure;

import org.apiguardian.api.API;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.apiguardian.api.API.Status.INTERNAL;

@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(CatProperties.class)
@ConditionalOnClass(name = "com.dianping.cat.Cat")
@API(status = INTERNAL, since = "1.0")
public class CatAutoConfiguration {
   private final CatProperties m_properties;

   public CatAutoConfiguration(CatProperties properties) {
      m_properties = properties;
   }

   @Bean
   @ConditionalOnMissingBean(CatInitializer.class)
   @API(status = INTERNAL, since = "1.0")
   public CatInitializer catInitializer() {
      return new CatInitializer(m_properties);
   }
}
