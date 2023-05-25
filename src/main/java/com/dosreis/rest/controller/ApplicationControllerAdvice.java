package com.dosreis.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.dosreis.exception.RegraNegocioException;
import com.dosreis.rest.ApiErrors;

@RestControllerAdvice
public class ApplicationControllerAdvice {
	
	@ExceptionHandler(RegraNegocioException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	private ApiErrors handleRegraNegocioException(RegraNegocioException er) {
		String mensagemErro = er.getMessage();
		return new ApiErrors(mensagemErro);
	}
}
