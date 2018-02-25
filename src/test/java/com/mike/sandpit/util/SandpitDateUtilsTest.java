package com.mike.sandpit.util;

import static org.junit.Assert.*;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.junit.Test;

public class SandpitDateUtilsTest {

	SandpitDateUtils sdu = new SandpitDateUtils();

	@Test
	public void getISOString() {
		ZonedDateTime z = ZonedDateTime.of(2018, 02, 01, 01, 02, 03, 4000000, ZoneId.of("NZ"));
		String s = sdu.getISOString(z);
		assertEquals(s, "2018-02-01T01:02:03.004+13:00[NZ]");
	}

	@Test
	public void getShortDateString() {
		ZonedDateTime z = ZonedDateTime.of(2018, 02, 01, 01, 02, 03, 4000000, ZoneId.of("NZ"));
		String s = sdu.getShortDateString(z);
		assertEquals(s, "2018-02-01");
	}

	@Test
	public void getDateFromISOString() {
		ZonedDateTime z = ZonedDateTime.of(2018, 02, 01, 01, 02, 03, 4000000, ZoneId.of("NZ"));
		String s = "2018-02-01T01:02:03.004+13:00[NZ]";
		ZonedDateTime z2 = sdu.getDateFromISOString(s);
		assertEquals(z2, z);
	}

	@Test
	public void getDateFromShortString() {
		ZonedDateTime z = ZonedDateTime.of(2018, 02, 01, 00, 00, 00, 00, ZoneId.of("NZ"));
		String s = "2018-02-01";
		ZonedDateTime z2 = sdu.getDateFromShortString(s);
		assertEquals(z2, z);
	}
}
