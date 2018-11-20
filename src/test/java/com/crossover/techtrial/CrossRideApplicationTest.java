/**
 * 
 */
package com.crossover.techtrial;

import com.crossover.techtrial.controller.PersonController;
import com.crossover.techtrial.controller.RideController;
import com.crossover.techtrial.dto.TopDriverDTO;
import com.crossover.techtrial.model.Person;
import com.crossover.techtrial.repositories.PersonRepository;
import com.crossover.techtrial.repositories.RideRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * @author crossover
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CrossRideApplicationTest {
    
  MockMvc mockMvc;
  
  @Mock
  private RideController rideController;
  
  @Autowired
  private TestRestTemplate template;
  
  @Autowired
  RideRepository rideRepository;
  
  @Before
  public void setup() throws Exception {
    mockMvc = MockMvcBuilders.standaloneSetup(rideController).build();
  }
  
  @Test
  public void testGetTopDriversAreReturned() throws Exception {
      Map<String, Object> urlVariables = new HashMap();
      urlVariables.put("count", 5L);
      urlVariables.put("startTime", "2018-08-08T00:00:00");
      urlVariables.put("endTime", "2018-08-08T23:59:00");
    ResponseEntity<List> response = template.getForEntity(
        "/api/top-rides",List.class, urlVariables);
    
    Assert.assertEquals("Did not return 5", 5, ((List)response.getBody().get(0)).size());
    Assert.assertEquals(200,response.getStatusCode().value());
  }
}
