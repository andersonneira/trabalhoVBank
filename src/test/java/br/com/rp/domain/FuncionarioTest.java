package br.com.rp.domain;

import org.junit.Assert;
import org.junit.Test;

import br.com.rp.AbstractTest;

public class FuncionarioTest extends AbstractTest {

	@Test
	public void nomeNotNull() {
		Funcionario funcionario = new Funcionario("marcos","123marcos");
		Assert.assertNotNull(funcionario.getNome());
	}
	@Test
	public void senhaNotNull() {
		Funcionario funcionario = new Funcionario("marcos","123marcos");
		Assert.assertNotNull(funcionario.getSenha());
	}
	@Test
	public void validaTamanhoSenhaTrue() {
		Funcionario funcionario = new Funcionario("marcos","123marcos");
		Assert.assertTrue(funcionario.getSenha().length()< 11 && funcionario.getSenha().length() > 6);
	}
	@Test
	public void validaTamanhoNomeTrue() {
		Funcionario funcionario = new Funcionario("marcos","123marcos");
		Assert.assertTrue(funcionario.getNome().length()< 101 && funcionario.getNome().length()> 2);
	}
	@Test
	public void validaTamanhoMaiorSenhaFalse() {
		Funcionario funcionario = new Funcionario("marcos","123456marcos");
		Assert.assertFalse(funcionario.getSenha().length()< 11 && funcionario.getSenha().length() > 6);
	}
	@Test
	public void validaTamanhoMenorSenhaFalse() {
		Funcionario funcionario = new Funcionario("marcos","arcos");
		Assert.assertFalse(funcionario.getSenha().length()< 11 && funcionario.getSenha().length() > 6);
	}
	@Test
	public void validaTamanhoMenorNomeFalse() {
		Funcionario funcionario = new Funcionario("ze","123marcos");
		Assert.assertFalse(funcionario.getNome().length()< 101 && funcionario.getNome().length()> 2);
	}
	@Test
	public void validaTamanhoMaiorNomeFalse() {
		String nome = new String();
		for (int i = 0; i < 110; i++) {
			nome+="a";	
		}
		Funcionario funcionario = new Funcionario(nome,"123marcos");
		Assert.assertFalse(funcionario.getNome().length()< 101 && funcionario.getNome().length()> 2);
	}
	
	

}