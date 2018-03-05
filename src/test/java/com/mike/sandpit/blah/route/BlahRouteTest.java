package com.mike.sandpit.blah.route;

import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.CamelSpringBootRunner;
import org.apache.camel.test.spring.MockEndpointsAndSkip;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mike.sandpit.route.BackendCall;

/**
 * Tests the BlahRoute (only) and mocks the producers.  
 * @author mike
 */
@RunWith(CamelSpringBootRunner.class)
@SpringBootTest
// need skip otherwise it mocks 'as well as running', rather than 'instead of running'.    
@MockEndpointsAndSkip("direct:backendCall")
public class BlahRouteTest {

	  @Autowired
	  private ProducerTemplate producerTemplate;

	  @EndpointInject(uri = "mock:direct:backendCall")
	  private MockEndpoint mockCamel;

	  @Test
	  public void testMessageReceivedByBackendRoute() throws InterruptedException {
	    BackendCall bc = new BackendCall();
	    bc.httpPath = "blah";
	    bc.useCache = true;
	    mockCamel.expectedMessageCount(1);
	    mockCamel.expectedPropertyReceived("backendCall", bc);
	    
	    // call the route
	    producerTemplate.sendBody("direct:blah", null);
	    
	    // test
	    mockCamel.assertIsSatisfied();
	  }
}
