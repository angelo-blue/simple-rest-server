package com.mike.sandpit.blah.service;

import org.springframework.stereotype.Service;

import com.mike.sandpit.blah.json.BlahResponse;

@Service
public class BlahService {

	public BlahResponse create() {
		BlahResponse result = new BlahResponse();
		result.message = "Back at you!";
		return result;
	}
}
