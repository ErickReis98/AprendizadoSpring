package com.dosreis.rest;

import java.util.Arrays;
import java.util.List;

public class ApiErrors {

	private List<String> errors;

	public ApiErrors(String msgErro) {
		this.errors = Arrays.asList(msgErro);
	}

	public List<String> getErrors() {
		return errors;
	}

	

}
