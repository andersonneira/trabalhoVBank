package br.com.rp.repository;

import java.math.BigDecimal;

import javax.ejb.EJB;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.rp.AbstractTest;
import br.com.rp.domain.Transacao;

public class TransacaoRepositoryTest extends AbstractTest {

	private static final BigDecimal _VALOR_VALIDO = new BigDecimal(30);
	private static final String _CONTA_DESTINO_VALIDA = "000003";
	private static final String _CONTA_DESTINO_DV_VALIDO = "3";
	private static final String _AGENCIA_DESTINO_DV_VALIDO = "2";
	private static final String _AGENDCIA_DESTINO_VALIDA = "001";

	@EJB
	private TransacaoRepository repository;
	
	private Transacao transacao;
	@Before
	public void before() {
		transacao = new Transacao();
		transacao.setAgenciaDestino(_AGENDCIA_DESTINO_VALIDA);
		transacao.setAgenciaDestinoDigitoVerificador(_AGENCIA_DESTINO_DV_VALIDO);
		transacao.setNumeroContaDestino(_CONTA_DESTINO_VALIDA);
		transacao.setNumeroContaDestinoDigitoVerificador(_CONTA_DESTINO_DV_VALIDO);
		transacao.setValor(_VALOR_VALIDO);
	}
	
	@After
	public void after() {
		repository.remove(transacao.getId());
	}
	
	@Test
	public void deveInserirComSucesso() {
		repository.save(transacao);
		Assert.assertNotNull(transacao.getId());
	}
	
	@Test(expected = Exception.class)
	public void naoDeveInserirPorFaltaDeValor() {
		transacao.setValor(null);
		repository.save(transacao);
		Assert.assertNotNull(transacao.getId());
	}
	
	@Test(expected = Exception.class)
	public void naoDeveInserirPorFaltaDeAgenciaDestino() {
		transacao.setAgenciaDestino(null);
		repository.save(transacao);
		Assert.assertNotNull(transacao.getId());
	}
	
	@Test(expected = Exception.class)
	public void naoDeveInserirPorFaltaDeNumeroContaDestino() {
		transacao.setNumeroContaDestino(null);
		repository.save(transacao);
		Assert.assertNotNull(transacao.getId());
	}
	
	@Test(expected = Exception.class)
	public void naoDeveInserirPorFaltaDeAgenciaDestinoDigitoVerificador() {
		transacao.setAgenciaDestinoDigitoVerificador(null);
		repository.save(transacao);
		Assert.assertNotNull(transacao.getId());
	}
	
	@Test(expected = Exception.class)
	public void naoDeveInserirPorFaltaDeNumeroContaDestinoDigitoVerificador() {
		transacao.setNumeroContaDestinoDigitoVerificador(null);
		repository.save(transacao);
		Assert.assertNotNull(transacao.getId());
	}
}
