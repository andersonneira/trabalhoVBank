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
		System.out.println("[CPF Informado]"+value);
		Matcher m = patternCpf.matcher(value);
		if (m.matches()) {
//			TODO: Validar se cpf é valido	
			return isValidCPF(value);
		}
		
		m = patternCnpj.matcher(value);
		if (m.matches()) {
//			TODO:Validar se cnpj é valido
			return true;
		}
		
		return false;
	}
	
	private static final int[] pesoCPF = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};
	private int calcularDigito(String str, int[] peso) {
		int soma = 0;
		for (int indice=str.length()-1, digito; indice >= 0; indice-- ) {
			digito = Integer.parseInt(str.substring(indice,indice+1));
			soma += digito*peso[peso.length-str.length()+indice];
		}
		soma = 11 - soma % 11;
		return soma > 9 ? 0 : soma;
	}
	
	private Boolean isValidCPF(String cpf){
		
		if (cpf==null){
			return false;
		} 
		
		cpf = cpf.replaceAll("[.-]", "").trim();
		
		if(cpf.length() != 11){
			return false;
		} 
		
		cpf = cpf.replaceAll("[.-]", "").trim();
		System.out.println("[CPF Informado para validar]"+cpf);
		if(cpf.length() != 11){
			return false;
		}
			
		
		if (cpf.equals("00000000000") || cpf.equals("11111111111") || cpf.equals("22222222222")
				|| cpf.equals("33333333333") || cpf.equals("44444444444") || cpf.equals("55555555555")
				|| cpf.equals("66666666666") || cpf.equals("77777777777") || cpf.equals("88888888888") || cpf.equals("99999999999"))
			return false;

		Integer digito1 = calcularDigito(cpf.substring(0,9), pesoCPF);
		Integer digito2 = calcularDigito(cpf.substring(0,9) + digito1, pesoCPF);
		return cpf.equals(cpf.substring(0,9) + digito1.toString() + digito2.toString());
	}

}
