package com.lipari.app.users.validations;

import org.springframework.stereotype.Component;

import com.lipari.app.exception.ValidationException;

@Component
public class GeneralValidation {

	public void stringNotBlank(String ...strings) {
		
		for (String s : strings) {
			if (s.isBlank())
				throw new RuntimeException("i campi non possono essere vuoti");
		}
	
	}
	
	public void positiveInt(Integer ...integers) {
		for (Integer i : integers) {
			if (i==null || i<0)
				throw new RuntimeException("il numero è nullo o minore di zero");
		}
	}

	public void stringNoSpace(String ...strings) {

		for (String s : strings) {
			if (s.isBlank())
				throw new RuntimeException("i campi non possono contenere spazi");
		}
	}
	

	public void minimumLenght8(String ...strings) {

		for (String s : strings) {
			if (s.isBlank())
				throw new RuntimeException("i campi non possono contenere meno di 8 caratteri");
		}
	}


}
