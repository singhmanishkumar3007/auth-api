package com.cloudcompilerr.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.cloudcompilerr.domain.LoginForm;

@Component
public class LoginValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return clazz.isAssignableFrom(LoginForm.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		LoginForm loginForm = (LoginForm) target;
		// ValidationUtils.rejectIfEmptyOrWhitespace(errors, loginForm.getPassword(),
		// "", "Empty Login Password");
		// ValidationUtils.rejectIfEmptyOrWhitespace(errors, loginForm.getUserName(),
		// "", "UserName is empty");

		if (null == loginForm.getUsername()) {
			errors.rejectValue("userName", "", "Username can not be blank");
		}
		if (null == loginForm.getPassword()) {
			errors.rejectValue("userName", "", "Password  can not be blank");
		}
		if (loginForm.getUsername().length() < 5) {
			errors.rejectValue("userName", "", "Username length is less than 5");
		}

	}

}
