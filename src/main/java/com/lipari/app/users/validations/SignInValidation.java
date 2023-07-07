package com.lipari.app.users.validations;


import com.lipari.app.commons.validations.GeneralValidation;
import org.springframework.stereotype.Component;

/**
 * The type Sign in validation.
 */
@Component
public class SignInValidation extends GeneralValidation {

    /**
     * Validation.
     *
     * @param username the username
     * @param password the password
     */
    public void validation(String username, String password) {
		stringNotBlank(username,password);
		stringNoSpace(username,password);
		//minimumLenght8(username,password);
	}
}
