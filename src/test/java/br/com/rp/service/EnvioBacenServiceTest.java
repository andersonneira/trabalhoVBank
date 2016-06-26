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
import br.com.rp.services.EnvioBacenService;

@CleanupUsingScript(value="db/cleanup.sql", phase=TestExecutionPhase.AFTER)
public class EnvioBacenServiceTest extends AbstractTest {

	@EJB
	private EnvioBacenService service;
	
	@EJB
	private AgendamentoRepository agendamentoRepository;
	
	@Test
	@UsingDataSet({"db/regiao.xml", "db/cliente.xml", "db/conta.xml", "db/transacao.xml", "db/agendamento.xml"})
	public void deveRealizarEnvioInformacoes() throws Exception {
		List<Agendamento> lst = agendamentoRepository.findAgendamentosRealizadosNaoEnviadosAoBacenByDataFinal(Calendar.getInstance().getTime());
		Assert.assertNotEquals(0, lst.size());
		service.envioInformacoes();
		List<Agendamento> lstAtualizado = agendamentoRepository.findAgendamentosRealizadosNaoEnviadosAoBacenByDataFinal(Calendar.getInstance().getTime());
		Assert.assertEquals(0, lstAtualizado.size());
	}
	
	@Test
	@UsingDataSet({"db/regiao.xml", "db/cliente.xml", "db/conta.xml", "db/transacao.xml", "db/agendamento.xml"})
	public void deveRealizarEnvioInformacaoUnico() throws Exception {
		Agendamento agendamento = agendamentoRepository.findById(1000L);
		Assert.assertNull(agendamento.getTransacao().getEnvioBacen());
		service.envioInformacao(1000L);
		Agendamento agendamentoAtualizado = agendamentoRepository.findById(1000L);
		Assert.assertNotNull(agendamentoAtualizado.getTransacao().getEnvioBacen());
	}
}
