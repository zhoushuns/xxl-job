package com.xxl.job.executor.mvc.po;

/**
 * @program: xxl-job
 * @Description:
 * @author: xxinfeng
 * @date: 2019-09-20 10:42
 **/
public class JsonEntity {

    private String id;

    private String content;

    private String publish_time;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPublish_time() {
        return publish_time;
    }

    public void setPublish_time(String publish_time) {
        this.publish_time = publish_time;
    }
}
