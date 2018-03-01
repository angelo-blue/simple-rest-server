package com.mike.sandpit.route;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class BackendRoute extends RouteBuilder {
	private static final Logger logger = LogManager.getLogger(BackendRoute.class);

	@Override
    public void configure() throws Exception {
		from("direct:backendCall")
			.process(checkCache)

			.choice()
				.when(header("cacheNotFound"))
					.to("direct:backendThreadPool")
				.end()
		;

    		from("direct:backendThreadPool")
    			.threads(10, 10, "backendthreadpool")
    			.process(setupBackendCall)
    			// just simulating an external service.  in reality will be a URI
    			.to("direct:backendStub");
    }
    
	private Processor checkCache = new Processor() {
		@Override
		public void process(Exchange exchange) throws Exception {
			BackendCall backendCall = exchange.getProperty("backendCall", BackendCall.class);
			if (backendCall.useCache) {
				logger.info("checking cache... not implemented yet!");
			}
			exchange.getIn().setHeader("cacheNotFound", true);
		}
	};
	
	private Processor setupBackendCall = new Processor() {
		@Override
		public void process(Exchange exchange) throws Exception {
			BackendCall backendCall = exchange.getProperty("backendCall", BackendCall.class);
			exchange.getIn().setHeader(Exchange.HTTP_PATH, backendCall.httpPath);
		}
	};
}