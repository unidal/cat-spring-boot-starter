package org.unidal.cat.annotation;

import org.apiguardian.api.API;
import org.springframework.context.annotation.Import;
import org.unidal.cat.autoconfigure.CatAutoConfiguration;

import java.lang.annotation.*;

import static org.apiguardian.api.API.Status.STABLE;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(CatAutoConfiguration.class)
@API(status = STABLE, since = "1.0")
public @interface EnableCat {
   boolean value() default true;
}
