package br.com.rp.service;

import java.util.List;
import javax.ejb.EJB;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.junit.Assert;
import org.junit.Test;
import br.com.rp.AbstractTest;
import br.com.rp.domain.Configuracao;
import br.com.rp.services.ConfiguracaoService;

public class ConfiguracaoServiceTest extends AbstractTest {
	@EJB
	private ConfiguracaoService service;

	@Test
	@UsingDataSet("db/configuracao.xml")
	public void deveRetornar2Logs() {
		List<Configuracao> configuracoes = service.getAllConfiguracoes();
		Assert.assertEquals(1, configuracoes.size());
	}
}
