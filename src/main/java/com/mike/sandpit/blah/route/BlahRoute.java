package com.mike.sandpit.blah.route;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mike.sandpit.blah.json.BlahResponse;
import com.mike.sandpit.blah.service.BlahService;
import com.mike.sandpit.route.ExternalRoute;

@Component
public class BlahRoute extends ExternalRoute {
	private static final Logger logger = LogManager.getLogger(BlahRoute.class);

	public static String exchangePropertyRLP = "RouteLoggingProperties";

	@Autowired
	private BlahService blahService;

    @Override
    public void configure() throws Exception {
		super.configure();
        // uncomment to see if the route runs
    		//from("timer:foo")
        //  .to("log:bar");

        rest("blah")
        	.get("v1").outType(BlahResponse.class).to("direct:blah");

        from("direct:blah")
          .process(startExternalRoute("Blah"))
          .setBody(constant("back at you"))

          .setBody(method(blahService, "create"))
          .marshal().json(JsonLibrary.Jackson)

          .process(endExternalRoute())

          .log("in direct blah");
    }

}