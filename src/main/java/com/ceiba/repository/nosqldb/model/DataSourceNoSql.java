package com.ceiba.repository.nosqldb.model;

/**
 * class to connect to a nosql database.
 * 
 */
public class DataSourceNoSql {

  private String host;
  private int port;
  private String dataBase;
  private String user;
  private String password;

  public DataSourceNoSql() {}

  public String getHost() {
    return host;
  }

  public void setHost(String host) {
    this.host = host;
  }

  public int getPort() {
    return port;
  }

  public void setPort(int port) {
    this.port = port;
  }

  public String getDataBase() {
    return dataBase;
  }

  public void setDataBase(String dataBase) {
    this.dataBase = dataBase;
  }

  public String getUser() {
    return user;
  }

  public void setUser(String user) {
    this.user = user;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
