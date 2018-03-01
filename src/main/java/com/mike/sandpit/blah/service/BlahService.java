package com.mike.sandpit.blah.service;

import org.apache.camel.Body;
import org.springframework.stereotype.Service;

import com.mike.sandpit.blah.json.BlahResponse;

@Service
public class BlahService {

	/**
	 * @param backendReponse
	 * @return front-end BlahResponse
	 */
	public BlahResponse create(@Body String backendReponse) {
		BlahResponse result = new BlahResponse();
		result.message = backendReponse;
		return result;
	}
}
