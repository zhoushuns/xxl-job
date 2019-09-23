package com.xxl.job.executor.mvc.untils;

import com.xxl.job.core.log.XxlJobLogger;

import java.sql.*;

/**
 * @program: xxl-job
 * @Description:
 * @author: xxinfeng
 * @date: 2019-09-20 09:55
 **/
public class CreateTable {

    private static final String CLASS_DRIVER = "com.mysql.jdbc.Driver";

    private static final String USER = "root";

    private static final String PASSWORD = "P@ssw0rd";

    private static final String URL = "jdbc:mysql://localhost:3306/xxf_job?useUnicode=true&characterEncoding=utf-8";


    public static void createTb(String tableAQL,String tableName){

        Connection conn = null;
        Statement stat = null;
        ResultSet result = null;

        try {

            //连接数据库
            Class.forName(CLASS_DRIVER);
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            stat = conn.createStatement();

            //查询所有表，若不存在就创建
            result = stat.executeQuery("select table_name from information_schema.tables where table_schema='xxf_job'");
            while (result.next()) {
                System.out.println(result.getString("table_name"));

            }

            //创建表test
            //stat.executeUpdate("create table test(id int, name varchar(80))");
            stat.executeUpdate(tableAQL);
        }catch (Exception e){
            e.printStackTrace();
            XxlJobLogger.log("创建数据表失败！！");
        }finally {

            //关闭数据库
            try {
                result.close();
                stat.close();
                conn.close();

            }catch (SQLException e){
                e.printStackTrace();
            }
        }

    }
}
