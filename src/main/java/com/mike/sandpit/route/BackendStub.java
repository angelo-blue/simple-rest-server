package com.mike.sandpit.route;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * Simulate a backend provider
 * @author mike
 */
@Component
public class BackendStub extends RouteBuilder {
	private static final Logger logger = LogManager.getLogger(BackendStub.class);

    @Override
    public void configure() throws Exception {

        from("direct:backendStub")
        		.choice()
        		.when(header(Exchange.HTTP_PATH).isEqualTo("blah"))
        			.setBody(constant("back at you!"))
        		.otherwise()
        			.setBody(constant("something else!"))
        		;
    }

}