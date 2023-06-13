package com.lipari.app.users.validations;

import org.springframework.stereotype.Component;

import com.lipari.app.users.model.vo.User;
@Component
public class SignUpValidation extends GeneralValidation {

	public void validation(User user) {
		stringNotBlank(user.getNome(), user.getCognome(), 
				user.getUsername(), user.getPassword(), user.getEmail());
		stringNoSpace(user.getUsername(), user.getPassword());
		passwordNotContainUsername(user.getUsername(), user.getPassword());
		minimumLenght8(user.getUsername(), user.getPassword());
		positiveInt(user.getRole());
	}
	
	private void passwordNotContainUsername(String username, String password) {

		if (password.contains("" + username))
			throw new RuntimeException("password non puo contenere username");
	}

}
