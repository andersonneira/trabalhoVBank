package br.com.rp.domain;

import org.junit.Assert;
import org.junit.Test;

import br.com.rp.AbstractTest;

public class FuncionarioTest extends AbstractTest {

	@Test
	public void nomeNotNull() {
		Funcionario funcionario = new Funcionario("marcos","123marcos","009.794.029-13");
		Assert.assertNotNull(funcionario.getNome());
	}
	@Test
	public void senhaNotNull() {
		Funcionario funcionario = new Funcionario("marcos","123marcos","009.794.029-13");
		Assert.assertNotNull(funcionario.getSenha());
	}
	@Test
	public void validaTamanhoSenhaTrue() {
		Funcionario funcionario = new Funcionario("marcos","123marcos","009.794.029-13");
		Assert.assertTrue(funcionario.getSenha().length()< 11 && funcionario.getSenha().length() > 6);
	}
	@Test
	public void validaTamanhoNomeTrue() {
		Funcionario funcionario = new Funcionario("marcos","123marcos","009.794.029-13");
		Assert.assertTrue(funcionario.getNome().length()< 101 && funcionario.getNome().length()> 2);
	}
	@Test
	public void validaTamanhoMaiorSenhaFalse() {
		Funcionario funcionario = new Funcionario("marcos","123456marcos","009.794.029-13");
		Assert.assertFalse(funcionario.getSenha().length()< 11 && funcionario.getSenha().length() > 6);
	}
	@Test
	public void validaTamanhoMenorSenhaFalse() {
		Funcionario funcionario = new Funcionario("marcos","arcos","009.794.029-13");
		Assert.assertFalse(funcionario.getSenha().length()< 11 && funcionario.getSenha().length() > 6);
	}
	@Test
	public void validaTamanhoMenorNomeFalse() {
		Funcionario funcionario = new Funcionario("ze","123marcos","009.794.029-13");
		Assert.assertFalse(funcionario.getNome().length()< 101 && funcionario.getNome().length()> 2);
	}
	@Test
	public void validaTamanhoMaiorNomeFalse() {
		String nome = new String();
		for (int i = 0; i < 110; i++) {
			nome+="a";	
		}
		Funcionario funcionario = new Funcionario(nome,"123marcos","009.794.029-13");
		Assert.assertFalse(funcionario.getNome().length()< 101 && funcionario.getNome().length()> 2);
	}
	
	

}