package com.lipari.app.commons.validations;

import org.springframework.stereotype.Component;

import com.lipari.app.commons.exception.utils.InvalidDataException;

@Component
public class GeneralValidation {

	public void stringNotBlank(String... strings) {

		for (String s : strings) {
			if (s.isBlank())
				throw new InvalidDataException("i campi non possono essere vuoti");
		}

	}

	public void positiveInt(Integer... integers) {
		for (Integer i : integers) {
			if (i == null || i < 0)
				throw new InvalidDataException("il numero è nullo o minore di zero");
		}
	}

	public void stringNoSpace(String... strings) {

		for (String s : strings) {
			if (s.contains(" "))
				throw new InvalidDataException("i campi non possono contenere spazi");
		}
	}

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

	public void equalStrings(String s1, String s2) {

		if (s1.equals(s2))
			throw new InvalidDataException("i campi devondo essere uguali");

	}

}
