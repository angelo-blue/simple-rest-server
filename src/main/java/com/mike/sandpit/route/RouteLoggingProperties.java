package com.mike.sandpit.route;

import java.time.ZonedDateTime;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mike.sandpit.util.SandpitDateUtils;

/**
 * Use this class to log the standard route calls.  
 * @author mike
 *
 */
public class RouteLoggingProperties {
	private static final Logger routeLogger = LogManager.getLogger("RouteLogger");

	public ZonedDateTime start;
	public ZonedDateTime end;
	public String routeName;
	public String exception;
	private String logPattern = "route:%s start:%s end:%s";
	private String logPatternWithException = "route:%s start:%s end:%s error:%s";
	
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
		if (exception == null) {
			routeLogger.info(String.format(logPattern, routeName, sdu.getISOString(start), sdu.getISOString(end)));
		} else {
			routeLogger.info(String.format(logPatternWithException, routeName, sdu.getISOString(start), sdu.getISOString(end), exception));
		}
	}

}
