package com.gsafety.schedule.spring.annotation;

import com.xxl.job.core.spring.xxl.XxlScheduleConfiguration;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * TODO completion javadoc.
 *
 * @author: haitao.han
 * @since 2019-09-25 16:43
 */
public class GScheduleConfigurationSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        return new String[]{XxlScheduleConfiguration.class.getName()};
    }
}
