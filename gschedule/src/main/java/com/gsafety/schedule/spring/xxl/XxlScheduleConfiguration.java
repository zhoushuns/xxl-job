package com.gsafety.schedule.spring.xxl;

import com.xxl.job.core.executor.impl.XxlJobSpringExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * XxlScheduleConfiguration
 *
 * @author: haitao.han
 * @since 2019-09-25 18:09
 */
@Configuration
@EnableConfigurationProperties(XxlScheduleProperties.class)
public class XxlScheduleConfiguration{

    private static final Logger logger = LoggerFactory.getLogger(XxlScheduleConfiguration.class);

    @Resource
    XxlScheduleProperties properties;

    @Bean(initMethod = "start", destroyMethod = "destroy")
    public XxlJobSpringExecutor xxlJobExecutor() {
        logger.info("   xxl-job config init     ");
        XxlJobSpringExecutor xxlJobSpringExecutor = new XxlJobSpringExecutor();
        xxlJobSpringExecutor.setAdminAddresses(properties.getAddresses());
        xxlJobSpringExecutor.setAppName(properties.getAppName());
        xxlJobSpringExecutor.setPort(properties.getPort());
        xxlJobSpringExecutor.setLogPath(properties.getLogPath());
        xxlJobSpringExecutor.setLogRetentionDays(properties.getLogRetentionDays());
        return xxlJobSpringExecutor;
    }
}
