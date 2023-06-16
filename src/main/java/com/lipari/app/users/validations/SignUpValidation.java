package com.lipari.app.users.validations;

import org.springframework.stereotype.Component;

import com.lipari.app.commons.exception.utils.InvalidDataException;
import com.lipari.app.commons.validations.GeneralValidation;
import com.lipari.app.users.entities.User;
@Component
public class SignUpValidation extends GeneralValidation {

	public void validation(User user) {
		stringNotBlank(user.getNome(), user.getCognome(), 
				user.getUsername(), user.getPassword(), user.getEmail());
		stringNoSpace(user.getUsername(), user.getPassword(), user.getEmail());
		passwordNotContainUsername(user.getUsername(), user.getPassword());
		minimumLenght8(user.getUsername(), user.getPassword());
		positiveLong(user.getRole().getId());
	}
	
	private void passwordNotContainUsername(String username, String password) {

		if (password.contains("" + username))
			throw new InvalidDataException("password non puo contenere username");
	}

}
