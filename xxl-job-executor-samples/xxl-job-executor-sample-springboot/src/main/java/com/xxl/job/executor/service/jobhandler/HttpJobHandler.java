package com.xxl.job.executor.service.jobhandler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import com.xxl.job.core.log.XxlJobLogger;
import com.xxl.job.executor.mvc.po.JsonEntity;
import com.xxl.job.executor.mvc.service.JsonWebmagicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 跨平台Http任务
 *
 * @author xuxueli 2018-09-16 03:48:34
 */
@JobHandler(value = "httpJobHandler")
@Component
public class HttpJobHandler extends IJobHandler {

    @Autowired
    private JsonWebmagicService jsonWebmagicService;
    @Override
    public ReturnT<String> execute(String param) throws Exception {

        JSONObject jsonParam = JSON.parseObject(param);
        String parameterList = jsonParam.getString("parameterList");
        List<JsonEntity> listEntity = new ArrayList<>();

        //根据参数拼接请求url
        if(parameterList.equals("false")){

            // request
            HttpURLConnection connection = null;
            BufferedReader bufferedReader = null;
            try {
                // connection
                URL realUrl = new URL(jsonParam.getString("url"));
                connection = (HttpURLConnection) realUrl.openConnection();

                // connection setting
                connection.setRequestMethod(jsonParam.getString("requestMethod"));
                connection.setDoOutput(true);
                connection.setDoInput(true);
                connection.setUseCaches(false);
                connection.setReadTimeout(5 * 1000);
                connection.setConnectTimeout(3 * 1000);
                connection.setRequestProperty("connection", "Keep-Alive");
                connection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                connection.setRequestProperty("Accept-Charset", "application/json;charset=UTF-8");

                // do connection
                connection.connect();

                //Map<String, List<String>> map = connection.getHeaderFields();

                // valid StatusCode
                int statusCode = connection.getResponseCode();
                if (statusCode != 200) {
                    throw new RuntimeException("Http Request StatusCode(" + statusCode + ") Invalid.");
                }

                // result
                bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
                StringBuilder result = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    result.append(line);
                }
                //数据插入list
                String uuid =  UUID.randomUUID().toString().replaceAll("-", "");
                JsonEntity jsonEntity = new JsonEntity();
                jsonEntity.setId(uuid);
                jsonEntity.setContent(result.toString());
                jsonEntity.setPublish_time(formatDate());

                listEntity.add(jsonEntity);

                jsonWebmagicService.insertJson(listEntity,jsonParam.getString("tableName"));
                XxlJobLogger.log(JSONArray.parseArray(JSONObject.toJSONString(listEntity)).toString());
                return SUCCESS;
            } catch (Exception e) {
                XxlJobLogger.log(e);
                return FAIL;
            } finally {
                try {
                    if (bufferedReader != null) {
                        bufferedReader.close();
                    }
                    if (connection != null) {
                        connection.disconnect();
                    }
                } catch (Exception e2) {
                    XxlJobLogger.log(e2);
                }
            }
        }
        XxlJobLogger.log("参数parameterList输入有误，检查是否为false！！！！");
        return FAIL;

    }

    public String formatDate(){
        Date date =new Date();
        SimpleDateFormat simdate=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str=simdate.format(date);
        return str;
    }

}
