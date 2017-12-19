package com.ceiba.repository.nosqldb;

import java.io.InputStream;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Interface with basic operations to interact with NoSql Database.
 * 
 * <p>
 * This interface allows to abstract basic operartions for interacting with NoSql database using
 * Spring-Data-Repositories. For more information check Spring Framework Reference Documentation.
 * 
 * @see <a href=
 *      "https://docs.spring.io/spring-data/data-commons/docs/1.6.1.RELEASE/reference/html/repositories.html">
 *      https://docs.spring.io/spring-data/data-commons/docs/1.6.1.RELEASE/reference/html/repositories.html
 *      </a>
 */
public interface IDbNoSql {

  /**
   * Stores an entity on the NoSql DataBase by the object of the entity to save.
   * 
   * @param <T>
   * 
   * @param entity the object to store on the NoSql DataBase, must not be {@code null}.
   * @throws AppException when the object Id to store already exists on the Nosql DataBase.
   */
  public <T> Boolean save(T entity) throws Exception;

  /**
   * Retrieves an entity on the NoSql DataBase by its identifier and entity class.
   * 
   * @param <T>
   * 
   * @param primaryKey identifier of the entity to retrieve.
   * @param entityClass the entity class of the returned object, must not be {@code null}.
   * @return the entity with the given identifier or null if none found.
   * @throws AppException when the entityClass is null.
   */
  public <T> T findOne(Serializable primaryKey, Class<T> entityClass);

  /**
   * Deletes an entity on the Nosql DataBase by its identifier and entity class.
   * 
   * @param <T>
   * 
   * @param primaryKey identifier of the entity to delete.
   * @param entityClass the entity class, must not be {@code null}.
   * @throws AppException when the entityClass is null.
   */
  public <T> void delete(Serializable primaryKey, Class<T> entityClass);

  /**
   * informs if exists an entity on the NoSql DataBase by its identifier and entity class.
   * 
   * @param <T>
   * 
   * @param primaryKey identifier of the entity to delete.
   * @param entityClass the entity class, must not be {@code null}.
   * @return {@code true} if the entity exist on the Nosql DataBase, {@code false} otherwise.
   * @throws AppException when the entityClass is null.
   */
  public <T> boolean exists(Serializable primaryKey, Class<T> entityClass);

  /**
   * Retrieves all the entities on the NoSql DataBase by its entity class.
   * 
   * @param <T>
   * 
   * @param entityClass the entity class, must not be {@code null}.
   * @return All the entities of a entity class saved on the NoSql DataBase.
   * @throws AppException when the entityClass is null.
   */
  public <T> List<T> getAllObjects(Class<T> entityClass);

  /**
   * Stores the given content into a file with the given name and content type using the given
   * metadata on the NoSql DataBase.
   * 
   * @param inStream file like stream, must not be {@code null}.
   * @param fileName File name, must not be {@code null}.
   * @param contentType file type content
   * @param metaData additional information about the file.
   * @throws AppException when inStream is null or fileName is null .
   */
  public void storeFile(InputStream inStream, String fileName, String contentType, Object metaData);

  /**
   * Deletes a file saved on NoSql Database.
   * 
   * @param fileName File name.
   */
  public void deleteFile(String fileName);

  /**
   * Finds one entity by filtering by its field value and entity class
   * 
   * @param <T>
   * 
   * @param field Name of the field for filter
   * @param value Value of the field
   * @param entityClass the entity class, must not be {@code null}.
   * @return A entity or null if none found.
   * @throws AppException when the entityClass is null.
   */
  public <T> T findOneByFieldValue(String field, String value, Class<T> entityClass);

  /**
   * Finds entities by filtering by their field value and entity class. the search is performed
   * using regular language.
   * 
   * @param <T>
   * 
   * @param fields Map key values, where key is the entity field and value is the field value, must
   *        not be {@code null}.
   * @param entityClass the entity class, must not be {@code null}.
   * @return A list with the entities found or a empty list.
   * @throws AppException when the entityClass is null or fields is null or empty.
   */
  public <T> List<T> findByFieldValues(Map<String, String> fields, Class<T> entityClass);

}
