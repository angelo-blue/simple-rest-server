package com.mike.sandpit.route;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class SomeRoute extends ExternalRoute {
	private static final Logger logger = LogManager.getLogger(SomeRoute.class);

	public static String exchangePropertyRLP = "RouteLoggingProperties";

    @Override
    public void configure() throws Exception {
		super.configure();
        // uncomment to see if the route runs
    		//from("timer:foo")
        //  .to("log:bar");

        rest("blah")
        	.get("v1").outType(String.class).to("direct:blah");

        from("direct:blah")
          .process(startExternalRoute("Blah"))
          .setBody(constant("back at you"))
        
          .process(endExternalRoute())
          .log("in direct blah");
    }

}