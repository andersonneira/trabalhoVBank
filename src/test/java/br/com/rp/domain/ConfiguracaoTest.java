package br.com.rp.domain;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import javax.ejb.EJB;

import org.junit.Assert;
import org.junit.Test;

import br.com.rp.AbstractTest;

public class ConfiguracaoTest extends AbstractTest {

	@Test
	public void horaAberturaOperacaoNotNull() {
		Configuracao configuracao = new Configuracao();
		Date data = new Date();
		Calendar calendar = Calendar.getInstance();
		data = calendar.getTime();
		configuracao.setHoraAberturaOperacao(data);
		data = configuracao.getHoraAberturaOperacao();
		Assert.assertNotNull(data);
	}

	@Test
	public void horaFechamentoOperacaoNotNull() {
		Configuracao configuracao = new Configuracao();
		Date data = new Date();
		Calendar calendar = Calendar.getInstance();
		data = calendar.getTime();
		configuracao.setHoraFechamentoOperacao(data);
		data = configuracao.getHoraFechamentoOperacao();
		Assert.assertNotNull(data);
	}

	@Test
	public void limitePadraoNotNull() {
		Configuracao configuracao = new Configuracao();
		BigDecimal bigDecimal = new BigDecimal(10);
		configuracao.setLimitePadrao(bigDecimal);
		Assert.assertNotNull(configuracao.getLimitePadrao());
	}

}
