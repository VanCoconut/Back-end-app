package com.lipari.app.users.validations;


import org.springframework.stereotype.Component;

import com.lipari.app.users.model.vo.User;

@Component
public class SignInValidation extends GeneralValidation {

	public void validation(String username, String password) {
		stringNotBlank(username,password);
		stringNoSpace(username,password);
		minimumLenght8(username,password);
	}
}
