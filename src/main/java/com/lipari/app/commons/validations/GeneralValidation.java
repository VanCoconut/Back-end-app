package com.lipari.app.commons.validations;

import org.springframework.stereotype.Component;

import com.lipari.app.commons.exception.utils.InvalidDataException;

@Component
public class GeneralValidation {

	public void stringNotBlank(String ...strings) {
		
		for (String s : strings) {
			if (s.isBlank())
				throw new InvalidDataException("i campi non possono essere vuoti");
		}
	
	}
	
	public void positiveInt(Integer ...integers) {
		for (Integer i : integers) {
			if (i==null || i<0)
				throw new InvalidDataException("il numero è nullo o minore di zero");
		}
	}

	public void stringNoSpace(String ...strings) {

		for (String s : strings) {
			if (s.contains(" "))
				throw new InvalidDataException("i campi non possono contenere spazi");
		}
	}
	

	public void minimumLenght8(String ...strings) {

		for (String s : strings) {
			if (s.length()<8)
				throw new InvalidDataException("i campi non possono contenere meno di 8 caratteri");
		}
	}


}
