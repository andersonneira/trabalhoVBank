package br.com.rp.anotations.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.rp.anotations.Email;


public class EmailValidator implements ConstraintValidator<Email, String> {

	private static final String EMAIL_PATTERN = 
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	private Pattern pattern = Pattern.compile(EMAIL_PATTERN);

	@Override
	public void initialize(Email constraintAnnotation) {

	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value == null)
			return true;
		Matcher m = pattern.matcher(value);
		System.out.println("Email validadtor ("+value+") = "+m.matches());
		return m.matches();
	}
}
