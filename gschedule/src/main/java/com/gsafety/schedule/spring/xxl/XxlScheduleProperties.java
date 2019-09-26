package com.gsafety.schedule.spring.xxl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.StringUtils;

/**
 * XxlScheduleProperties
 *
 * @author: haitao.han
 * @since 2019-09-26 15:10
 */
@ConfigurationProperties
public class XxlScheduleProperties {

    @Value("${spring.application.name}")
    private String appName;

    @Value("${g.schedule.addresses}")
    private String addresses;

    @Value("${server.port}")
    private Integer port;

    @Value("${g.schedule.logPath}")
    private String logPath;

    @Value("${g.schedule.logRetentionDays}")
    private int logRetentionDays;

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAddresses() {
        return addresses;
    }

    public void setAddresses(String addresses) {
        this.addresses = addresses;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getLogPath() {
        if (StringUtils.hasLength(logPath)){
            return logPath;
        }
        return "../".concat(appName).concat("/log");
    }

    public void setLogPath(String logPath) {
        this.logPath = logPath;
    }

    public int getLogRetentionDays() {
        if (StringUtils.hasLength(logPath)){
            return logRetentionDays;
        }
        return 15;
    }

    public void setLogRetentionDays(int logRetentionDays) {
        this.logRetentionDays = logRetentionDays;
    }
}
