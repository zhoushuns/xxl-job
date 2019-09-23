package com.xxl.job.executor.mvc.service;

import com.xxl.job.executor.mvc.dao.JsonWebmagicDao;
import com.xxl.job.executor.mvc.po.JsonEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * @program: xxl-job
 * @Description:
 * @author: xxinfeng
 * @date: 2019-09-20 14:58
 **/
@Service
public class JsonWebmagicService {

    /*@Autowired
    private JsonWebmagicDao jsonWebmagicDao;*/

    @Transactional
    public int insertJson(List<JsonEntity> jsonList, String tableName){

        return batchInsert(jsonList,tableName);
    }

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
