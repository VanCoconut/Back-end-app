package com.lipari.app.users.validations;


import com.lipari.app.commons.validations.GeneralValidation;
import org.springframework.stereotype.Component;

@Component
public class SignInValidation extends GeneralValidation {

	public void validation(String username, String password) {
		stringNotBlank(username,password);
		stringNoSpace(username,password);
		minimumLenght8(username,password);
	}
}
