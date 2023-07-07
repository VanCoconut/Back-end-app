package com.lipari.app.commons.validations;

import org.springframework.stereotype.Component;

import com.lipari.app.commons.exception.utils.InvalidDataException;

/**
 * The type General validation.
 */
@Component
public class GeneralValidation {

    /**
     * String not blank.
     *
     * @param strings the strings
     */
    public void stringNotBlank(String... strings) {

		for (String s : strings) {
			if (s.isBlank())
				throw new InvalidDataException("i campi non possono essere vuoti");
		}

	}

    /**
     * Positive long.
     *
     * @param longs the longs
     */
    public void positiveLong(Long... longs) {
		for (Long i : longs) {
			if (i == null || i < 0)
				throw new InvalidDataException("il numero è nullo o minore di zero");
		}
	}

    /**
     * String no space.
     *
     * @param strings the strings
     */
    public void stringNoSpace(String... strings) {

		for (String s : strings) {
			if (s.contains(" "))
				throw new InvalidDataException("i campi non possono contenere spazi");
		}
	}

    /**
     * Minimum lenght 8.
     *
     * @param strings the strings
     */
    public void minimumLenght8(String... strings) {

		for (String s : strings) {
			if (s.length() < 8)
				throw new InvalidDataException("i campi non possono contenere meno di 8 caratteri");
		}
	}

	/*
	 * public void equalStrings(String... strings) {
	 * 
	 * for (int i = 0; i < strings.length - 1; i++) { if
	 * (strings[i].equals(strings[i + 1])) throw new
	 * InvalidDataException("i campi devondo essere uguali"); } }
	 */

    /**
     * Equal strings.
     *
     * @param s1 the s 1
     * @param s2 the s 2
     */
    public void equalStrings(String s1, String s2) {

		if (s1.equals(s2))
			throw new InvalidDataException("i campi devondo essere uguali");

	}

}
