package com.mike.sandpit.route;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class SomeRoute extends RouteBuilder {
 
    @Override
    public void configure() throws Exception {
        // uncomment to see if the route runs
    		//from("timer:foo")
        //  .to("log:bar");

        rest("blah")
        	.get("v1").outType(String.class).to("direct:blah");

        from("direct:blah")
        .setBody(constant("back at you"))
        .log("in direct blah");
    }
}