package com.lipari.app.users.validations;

import com.lipari.app.commons.exception.utils.InvalidDataException;
import com.lipari.app.commons.validations.GeneralValidation;
import com.lipari.app.users.entities.AppUser;
import org.springframework.stereotype.Component;

/**
 * The type Sign up validation.
 */
@Component
public class SignUpValidation extends GeneralValidation {

    /**
     * Validation.
     *
     * @param appUser the app user
     */
    public void validation(AppUser appUser) {
		stringNotBlank(appUser.getNome(), appUser.getCognome(),
				appUser.getUsername(), appUser.getPassword(), appUser.getEmail());
		stringNoSpace(appUser.getUsername(), appUser.getPassword(), appUser.getEmail());
		passwordNotContainUsername(appUser.getUsername(), appUser.getPassword());
		minimumLenght8(appUser.getUsername(), appUser.getPassword());
		//positiveLong(user.getRole().getId());
	}

    /**
     * Password not contain username.
     *
     * @param username the username
     * @param password the password
     */
    private void passwordNotContainUsername(String username, String password) {

		if (password.contains("" + username))
			throw new InvalidDataException("password non puo contenere username");
	}

}
