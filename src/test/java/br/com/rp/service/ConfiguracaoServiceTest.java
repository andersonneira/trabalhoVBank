package br.com.rp.service;

import java.math.BigDecimal;
import java.util.List;
import javax.ejb.EJB;

import org.jboss.arquillian.persistence.CleanupUsingScript;
import org.jboss.arquillian.persistence.TestExecutionPhase;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.junit.Assert;
import org.junit.Test;
import br.com.rp.AbstractTest;
import br.com.rp.domain.Configuracao;
import br.com.rp.services.ConfiguracaoService;

@CleanupUsingScript(value="db/cleanup.sql", phase=TestExecutionPhase.BEFORE)
public class ConfiguracaoServiceTest extends AbstractTest {
	@EJB
	private ConfiguracaoService service;

	@Test
	@UsingDataSet("db/configuracao.xml")
	public void deveRetornar2Logs() {
		List<Configuracao> configuracoes = service.getAllConfiguracoes();
		Assert.assertEquals(1, configuracoes.size());
	}
	
	@Test
	public void deveSalvar() throws Exception {
		service.salvarConfiguracao("06:00:00", "23:30:00", new BigDecimal(1000));
		List<Configuracao> configuracoes = service.getAllConfiguracoes();
		Assert.assertEquals(1, configuracoes.size());
	}
}
