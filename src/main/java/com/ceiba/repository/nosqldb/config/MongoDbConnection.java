package com.ceiba.repository.nosqldb.config;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;

import com.ceiba.repository.nosqldb.model.DataSourceNoSql;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

public abstract class MongoDbConnection extends AbstractMongoConfiguration {
  
  private final String DEFAULT_MONGO_DATABASE = "admin";
  

  public abstract DataSourceNoSql getDataSourceNoSql();

  @Override
  protected String getDatabaseName() {
    return getDataSourceNoSql().getDataBase();
  }

  @Override
  public Mongo mongo() throws Exception {
    return new MongoClient(serverAddress(), mongoCredentials());
  }

  @Override
  @Bean
  public MongoTemplate mongoTemplate() throws Exception {
    MongoTemplate mongoTemplate;
    mongoTemplate = new MongoTemplate(mongoDbFactory());
    return mongoTemplate;
  }

  public MongoClient mongoClient() throws UnknownHostException {
    return new MongoClient(serverAddress(), mongoCredentials());
  }

  public GridFsTemplate gridFsTemplate() throws Exception {
    return new GridFsTemplate(mongoDbFactory(), mappingMongoConverter());
  }

  @Override
  public MongoDbFactory mongoDbFactory() throws Exception {
    return new SimpleMongoDbFactory(mongoClient(), getDatabaseName());
  }

  private List<MongoCredential> mongoCredentials() {

    List<MongoCredential> credentials = new ArrayList<MongoCredential>();
    String user = getDataSourceNoSql().getUser();
    String password = getDataSourceNoSql().getPassword();
    if ((user != null && user != "") && (password != null && password != "")) {
      credentials.add(MongoCredential.createCredential(user, getDataSourceNoSql().getDataBase(),
          password.toCharArray()));
    }
    return credentials;
  }

  private ServerAddress serverAddress() throws UnknownHostException {
    return new ServerAddress(getDataSourceNoSql().getHost(), getDataSourceNoSql().getPort());
  }
}
