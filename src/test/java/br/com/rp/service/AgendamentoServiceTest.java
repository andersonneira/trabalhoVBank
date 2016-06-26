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
import br.com.rp.services.AgendamentoService;

@CleanupUsingScript(value="db/cleanup.sql", phase=TestExecutionPhase.AFTER)
public class AgendamentoServiceTest extends AbstractTest {

	@EJB
	private AgendamentoService service;
	
	@EJB
	private AgendamentoRepository repository;
	
	@Test
	@UsingDataSet({"db/regiao.xml", "db/cliente.xml", "db/conta.xml", "db/transacao.xml", "db/agendamento.xml"})
	public void realizarAgendamentoPendentes() {
		List<Agendamento> lstAgendamentos = repository.findAgendamentosPendentesByDataFinal(Calendar.getInstance().getTime());
		Assert.assertNotEquals(0, lstAgendamentos.size());
		service.realizarAgendamentoPendentes();
	}
}
