package com.mike.sandpit.routeHelpers;

import java.time.ZonedDateTime;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mike.sandpit.util.SandpitDateUtils;

public class RouteLoggingProperties {
	private static final Logger logger = LogManager.getLogger(RouteLoggingProperties.class);

	public ZonedDateTime start;
	public ZonedDateTime end;
	public String routeName;
	public String exception;
	private String logPattern = "route:%s start:%s end:%s";

	public static RouteLoggingProperties start(String routeName) {
		SandpitDateUtils sdu = new SandpitDateUtils();
		RouteLoggingProperties rlp = new RouteLoggingProperties();
		rlp.start = sdu.getNow();
		rlp.routeName = routeName;
		return rlp;
	}

	public void finish() {
		SandpitDateUtils sdu = new SandpitDateUtils();
		end = sdu.getNow();
		logger.info(String.format(logPattern, routeName, sdu.getISOString(start), sdu.getISOString(end)));
	}

}
