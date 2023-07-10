package com.dosreis.exception.validation.constraintValidation;

import java.util.List;

import com.dosreis.exception.validation.NotEmptyList;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;



public class NotEmptyListValidator implements ConstraintValidator<NotEmptyList, List>{


	@Override
	public boolean isValid(List value, ConstraintValidatorContext context) {
		
		return value != null && !value.isEmpty();
	}

	@Override
	public void initialize(NotEmptyList constraintAnnotation) {
		
		ConstraintValidator.super.initialize(constraintAnnotation);
	}
	
	

	

}


