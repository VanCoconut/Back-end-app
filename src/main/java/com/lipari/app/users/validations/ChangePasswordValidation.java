package com.lipari.app.users.validations;

import org.springframework.stereotype.Component;

import com.lipari.app.commons.exception.utils.InvalidDataException;
import com.lipari.app.commons.validations.GeneralValidation;

@Component
public class ChangePasswordValidation extends GeneralValidation {

	public void validation(String username, String password, String confPassword) {
		stringNotBlank(password, confPassword);
		stringNoSpace(password, confPassword);
		minimumLenght8(password, confPassword);
		passwordNotContainUsername(username, password);
		passwordNotContainUsername(username, confPassword);
		//equalStrings(password,confPassword);
	}
	
	private void passwordNotContainUsername(String username, String password) {

		if (password.contains("" + username))
			throw new InvalidDataException("password non puo contenere username");
	}

}
