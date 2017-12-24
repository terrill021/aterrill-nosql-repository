package com.ceiba.repository.nosqldb.impl;

import java.io.InputStream;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.stereotype.Service;

import com.ceiba.repository.nosqldb.IDbNoSql;

@Service
public class MongoDb implements IDbNoSql {

  @Autowired
  private MongoTemplate mongoTemplate;

  public void setMongoTemplate(MongoTemplate mongoTemplate) {
    this.mongoTemplate = mongoTemplate;
  }

  public <T> Boolean save(T entity) throws Exception {
	  
      mongoTemplate.insert(entity);
      return Boolean.TRUE;
  }
  
  public <T> Boolean saveOrUpdate(T entity) throws Exception {
	  
      mongoTemplate.save(entity);
      return Boolean.TRUE;
  }

  public <T> List<T> getAllObjects(Class<T> type) {
    try {
      return (List<T>) mongoTemplate.findAll(type);
    } catch (Exception e) {
    	throw e;
    }
  }

  public <T> T findOne(Serializable primaryKey, Class<T> type) {
    try {
      return (T) mongoTemplate.findOne(new Query(Criteria.where("_id").is(primaryKey)), type);
    } catch (Exception e) {
    	throw e;
    }
  }

  public <T> void delete(Serializable primaryKey, Class<T> type) {
    try {
      mongoTemplate.remove(new Query(Criteria.where("_id").is(primaryKey)), type);
    } catch (Exception e) {

    }
  }

  public <T> boolean exists(Serializable primaryKey, Class<T> type) {
    try {
      return mongoTemplate.exists(new Query(Criteria.where("_id").is(primaryKey)), type);
    } catch (Exception e) {
    	throw e;
    }

  }

  public <T> T findOneByFieldValue(String field, String value, Class<T> type) {
    try {
      return (T) mongoTemplate.findOne(new Query(Criteria.where(field).is(value)), type);
    } catch (Exception e) {
    	throw e;
    }
  }

  public <T> List<T> findByFieldValues(Map<String, String> fields, Class<T> type) {

    Criteria queryTemp = new Criteria();

    if (fields == null || fields.isEmpty()) {
      return null;
    }

    Iterator<Map.Entry<String, String>> it = fields.entrySet().iterator();
    Map.Entry<String, String> pair = it.next();
    queryTemp = Criteria.where(pair.getKey()).regex(pair.getValue());

    // Recorrer el resto
    while (it.hasNext()) {
      pair = it.next();
      queryTemp = queryTemp.and(pair.getKey()).regex(pair.getValue());
    }
    try {
      return mongoTemplate.find(new Query(queryTemp), type);
    } catch (Exception e) {
    	throw e;
    }
  }  

}
