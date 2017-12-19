package com.ceiba.repository.nosqldb.connection;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

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
  
  private DataSourceNoSql dataSourceNoSql;

  public abstract void setDataSourceNoSql(DataSourceNoSql dataSourceNoSql);

  @Override
  protected String getDatabaseName() {
    return dataSourceNoSql.getDataBase();
  }

  @Override
  public Mongo mongo() throws Exception {
    return new MongoClient(serverAddress(), mongoCredentials());
  }

  @Override
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
    String user = dataSourceNoSql.getUser();
    String password = dataSourceNoSql.getPassword();
    if ((user != null && user != "") && (password != null && password != "")) {
      credentials.add(MongoCredential.createCredential(user, DEFAULT_MONGO_DATABASE,
          password.toCharArray()));
    }
    return credentials;
  }

  private ServerAddress serverAddress() throws UnknownHostException {
    return new ServerAddress(dataSourceNoSql.getHost(), dataSourceNoSql.getPort());
  }
}
