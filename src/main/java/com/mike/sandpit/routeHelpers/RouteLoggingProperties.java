package com.mike.sandpit.routeHelpers;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RouteLoggingProperties {
	private static final Logger logger = LogManager.getLogger(RouteLoggingProperties.class);

	public ZonedDateTime start;
	public ZonedDateTime end;
	public String routeName;
	public String exception;
	private String logPattern = "route:%s start:%s end:%s";

	public static RouteLoggingProperties start(String routeName) {
		RouteLoggingProperties rlp = new RouteLoggingProperties();
		rlp.start = ZonedDateTime.now(ZoneId.of("NZ"));
		rlp.routeName = routeName;
		return rlp;
	}

	public void finish() {
		end = ZonedDateTime.now(ZoneId.of("NZ"));
		logger.info(String.format(logPattern, routeName, start.toString(), end.toString()));
	}

}
