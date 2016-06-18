package br.com.rp.anotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import br.com.rp.anotations.validators.DocumentoValidator;

@Constraint(validatedBy = DocumentoValidator.class)
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Documento {

	String message() default "Cep inválido";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
