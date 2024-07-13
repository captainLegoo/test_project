package cn.dcy.jsapi.annotation;

import java.lang.annotation.*;

/**
 * @author Kyle
 * @date 2024/07/12
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogAnnotation {
    String value() default "";
}
