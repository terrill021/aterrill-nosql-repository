package com.ceiba.repository.dbnosql.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockingDetails;
import org.mockito.Mockito;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.gridfs.GridFsOperations;

import com.ceiba.repository.dbnosql.model.Tester;
import com.ceiba.repository.nosqldb.impl.MongoDb;



/**
 * Class with unit tests.
 */
public class MongoDbTest {

  @Mock
  private MongoTemplate mongoTemplate;
  @Mock
  private GridFsOperations grOperations;
  @InjectMocks
  private MongoDb mongoDb;
  
  private Tester tester;

  /**
   * This method makes all the previous configuration necessary to realize the tests.
   */
  @Before
  public void sepUp() {
    tester = new Tester();
    mongoDb = new MongoDb();
    mongoTemplate = Mockito.mock(MongoTemplate.class);
    mongoDb.setMongoTemplate(mongoTemplate);
  }
  
  @Test
  public void saveTest() throws Exception {	  
	  //arrange			  
	  Mockito.doNothing().when(mongoTemplate).insert(tester);
	  // act
	  Boolean res = mongoDb.save(tester);
	  //assert		  
	  Assert.assertTrue(res);
  }
	
  @Test(expected = Exception.class)
  public void saveFailTest() throws Exception {	  
	  //arrange		
	  Mockito.doThrow(Exception.class).when(mongoTemplate).insert(null);
	  // act
	  mongoDb.save(null);
	  //assert		  
	}
  
  
}
