package com.mike.sandpit.blah.route;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mike.sandpit.blah.json.BlahResponse;
import com.mike.sandpit.blah.service.BlahService;
import com.mike.sandpit.route.BackendCall;
import com.mike.sandpit.route.ExternalRoute;

/**
 * Custom route to handle the blah function.
 * @author mike
 */
@Component
public class BlahRoute extends ExternalRoute {
	private static final Logger logger = LogManager.getLogger(BlahRoute.class);

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

          // setup rest call to backend
          .setProperty("backendCall", method(BlahRoute.class, "createBackendCall"))
          .to("direct:backendCall")

          // transform to client response
          .setBody(method(blahService, "createClientResponse"))
          .marshal().json(JsonLibrary.Jackson)

          .process(endExternalRoute())
          ;
    }

	public BackendCall createBackendCall() {
		BackendCall bc = new BackendCall();
		bc.httpPath = "blah";
		bc.useCache = true;
		return bc;
	}

}