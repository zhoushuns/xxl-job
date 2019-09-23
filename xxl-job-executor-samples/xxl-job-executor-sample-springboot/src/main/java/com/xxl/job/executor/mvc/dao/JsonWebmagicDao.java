package com.xxl.job.executor.mvc.dao;

import com.xxl.job.executor.mvc.po.JsonEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * @program: xxl-job
 * @Description:
 * @author: xxinfeng
 * @date: 2019-09-20 14:48
 **/
public class JsonWebmagicDao {

    @PersistenceContext
    private EntityManager entityManager;

    public int batchInsert(List<JsonEntity> jsonList, String tableName) {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO ");
        sb.append(tableName).append("(id, content, publish_time) VALUES ");
        for(JsonEntity jsonEntity : jsonList) {
            sb.append("(?, ?, ?),");
        }
        String sql = sb.toString().substring(0, sb.length() - 1);
        Query query = entityManager.createNativeQuery(sql);
        int paramIndex = 1;
        for(JsonEntity jsonEntity : jsonList) {
            query.setParameter(paramIndex++, jsonEntity.getId());
            query.setParameter(paramIndex++, jsonEntity.getContent());
            query.setParameter(paramIndex++, jsonEntity.getPublish_time());
        }
        return query.executeUpdate();
    }
}
