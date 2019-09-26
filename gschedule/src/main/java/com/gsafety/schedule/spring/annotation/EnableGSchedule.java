package com.gsafety.schedule.spring.annotation;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 开启调度任务
 *
 * @author: haitao.han
 * @since 2019-09-25 16:41
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(GScheduleConfigurationSelector.class)
public @interface EnableGSchedule {

    String exportPort() default "9999";

}
