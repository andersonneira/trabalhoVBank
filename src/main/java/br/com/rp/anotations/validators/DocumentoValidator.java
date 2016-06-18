package br.com.rp.anotations.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.rp.anotations.Documento;

public class DocumentoValidator implements ConstraintValidator<Documento, String> {

	private Pattern patternCpf = Pattern.compile("[0-9]{3}.[0-9]{3}.[0-9]{3}-[0-9]{2}");
	
	private Pattern patternCnpj = Pattern.compile("[0-9]{2}.[0-9]{3}.[0-9]{3}/[0-9]{4}-[0-9]{2}");

	@Override 
	public void initialize(Documento constraintAnnotation) {
		
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value == null) {
			return true; 
		}
		Matcher m = patternCpf.matcher(value);
		if (m.matches()) {
//			TODO: Validar se cpf é valido	
			return true;
		}
		
		m = patternCnpj.matcher(value);
		if (m.matches()) {
//			TODO:Validar se cnpj é valido
			return true;
		}
		
		return false;
	}

}
