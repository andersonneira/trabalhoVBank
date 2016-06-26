package br.com.rp.service;

import java.util.Calendar;
import java.util.List;

import javax.ejb.EJB;

import org.jboss.arquillian.persistence.CleanupUsingScript;
import org.jboss.arquillian.persistence.TestExecutionPhase;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.junit.Assert;
import org.junit.Test;

import br.com.rp.AbstractTest;
import br.com.rp.domain.Agendamento;
import br.com.rp.repository.AgendamentoRepository;
import br.com.rp.services.EnvioMatrizService;

@CleanupUsingScript(value="db/cleanup.sql", phase=TestExecutionPhase.AFTER)
public class EnvioMatrizServiceTest extends AbstractTest {

	@EJB
	private EnvioMatrizService service;
	
	@EJB
	private AgendamentoRepository agendamentoRepository;
	
	@Test
	@UsingDataSet({"db/regiao.xml", "db/cliente.xml", "db/conta.xml", "db/transacao.xml", "db/agendamento.xml"})
	public void deveRealizarEnvioInformacoes() throws Exception {
		List<Agendamento> lst = agendamentoRepository.findAgendamentosRealizadosNaoEnviadosAMatrizByDataFinal(Calendar.getInstance().getTime());
		Assert.assertNotEquals(0, lst.size());
		service.envioInformacoes();
		List<Agendamento> lstAtualizado = agendamentoRepository.findAgendamentosRealizadosNaoEnviadosAMatrizByDataFinal(Calendar.getInstance().getTime());
		Assert.assertEquals(0, lstAtualizado.size());
	}
}
