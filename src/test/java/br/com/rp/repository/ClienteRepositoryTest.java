package br.com.rp.repository;

import java.util.Calendar;

import javax.ejb.EJB;

import org.junit.Assert;
import org.junit.Test;

import br.com.rp.AbstractTest;
import br.com.rp.domain.Cliente;

public class ClienteRepositoryTest extends AbstractTest {

	private static final String _DOCUMENTO_VALIDO = "000.000.000-0";
	private static final String _NOME_VALIDO = "Teste";
	private static final String _EMAIL_VALIDO = "teste@teste.com.br";
	private static final String _CEP_VALIDO = "86800-005";
	@EJB
	private ClienteRepository repository;
	
	@Test
	public void deveInserirComSucesso() {
		Cliente cli = new Cliente();
		cli.setEmail(_EMAIL_VALIDO);
		cli.setCep(_CEP_VALIDO);
		cli.setNome(_NOME_VALIDO);
		cli.setDataCadastro(Calendar.getInstance().getTime());
		cli.setDocumento(_DOCUMENTO_VALIDO+"0");
		repository.save(cli);
		Assert.assertNotNull(cli.getId());
	}
	
	@Test(expected = Exception.class)
	public void naoDeveInserirPorFaltaDeEmail() {
		Cliente cli = new Cliente();
		cli.setCep(_CEP_VALIDO);
		cli.setNome(_NOME_VALIDO);
		cli.setDataCadastro(Calendar.getInstance().getTime());
		cli.setDocumento(_DOCUMENTO_VALIDO);
		repository.save(cli);
	}
	
	@Test(expected = Exception.class)
	public void naoDeveInserirPorFormatoIncorretoEmail() {
		Cliente cli = new Cliente();
		cli.setCep(_CEP_VALIDO);
		cli.setEmail("testebr");
		cli.setNome(_NOME_VALIDO);
		cli.setDocumento(_DOCUMENTO_VALIDO+"1");
		repository.save(cli);
	}
	
	@Test(expected = Exception.class)
	public void naoDeveInserirPorFaltaDeNome() {
		Cliente cli = new Cliente();
		cli.setCep(_CEP_VALIDO);
		cli.setEmail(_EMAIL_VALIDO);
		cli.setDataCadastro(Calendar.getInstance().getTime());
		cli.setDocumento(_DOCUMENTO_VALIDO);
		repository.save(cli);
	}
	
	@Test(expected = Exception.class)
	public void naoDeveInserirPorNomeMuitoCurto() {
		Cliente cli = new Cliente();
		cli.setNome("T");
		cli.setCep(_CEP_VALIDO);
		cli.setEmail(_EMAIL_VALIDO);
		cli.setDocumento(_DOCUMENTO_VALIDO+"2");
		repository.save(cli);
	}
	
	@Test(expected = Exception.class)
	public void naoDeveInserirPorNomeMuitoLongo() {
		Cliente cli = new Cliente();
		String nome = "";
		for (int i = 0; i < 50; i++) {
			nome+= "T";
		}
		cli.setNome(nome);
		cli.setCep(_CEP_VALIDO);
		cli.setEmail(_EMAIL_VALIDO);
		cli.setDocumento(_DOCUMENTO_VALIDO+"3");
		repository.save(cli);
	}
	
	@Test(expected = Exception.class)
	public void naoDeveInserirPorFaltaDeCep() {
		Cliente cli = new Cliente();
		cli.setNome(_NOME_VALIDO);
		cli.setEmail(_EMAIL_VALIDO);
		cli.setDocumento(_DOCUMENTO_VALIDO+"4");
		repository.save(cli);
	}
	
	@Test(expected = Exception.class)
	public void naoDeveInserirPorFormatoCepInvalido() {
		Cliente cli = new Cliente();
		cli.setNome(_NOME_VALIDO);
		cli.setCep("86800005");
		cli.setEmail(_EMAIL_VALIDO);
		cli.setDocumento(_DOCUMENTO_VALIDO+"5");
		repository.save(cli);
	}
	
	@Test(expected = Exception.class)
	public void naoDeveInserirPorFaltaDeDocumento() {
		Cliente cli = new Cliente();
		cli.setNome(_NOME_VALIDO);
		cli.setEmail(_EMAIL_VALIDO);
		cli.setCep(_CEP_VALIDO);
		repository.save(cli);
	}
}
