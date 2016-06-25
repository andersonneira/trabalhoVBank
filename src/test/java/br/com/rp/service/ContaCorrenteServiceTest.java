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
import br.com.rp.domain.ContaCorrente;
import br.com.rp.dto.ContaCorrenteMovimentacaoDTO;
import br.com.rp.dto.ContaCorrenteResumoDTO;
import br.com.rp.services.ContaCorrenteService;

@CleanupUsingScript(value="db/cleanup.sql", phase=TestExecutionPhase.AFTER)
public class ContaCorrenteServiceTest extends AbstractTest {

	@EJB
	private ContaCorrenteService service;
	
	@Test
	@UsingDataSet({"db/regiao.xml", "db/cliente.xml", "db/conta.xml", "db/transacao.xml", "db/agendamento.xml"})
	public void deveBuscarMovimentacaoBancariaPorContaCorrenteIdPorIntervaloData() {
		ContaCorrente cc = service.getContaCorrenteById(1000L);
		Assert.assertNotNull(cc);
		Calendar calInicial = Calendar.getInstance();
		calInicial.set(Calendar.DAY_OF_MONTH, 1);
		calInicial.set(Calendar.MONTH, 0);
		calInicial.set(Calendar.YEAR, 2016);
		calInicial.set(Calendar.HOUR, 23);
		calInicial.set(Calendar.MINUTE, 59);
		calInicial.set(Calendar.SECOND, 59);
		
		Calendar calFinal = Calendar.getInstance();
		calFinal.set(Calendar.DAY_OF_MONTH, 24);
		calFinal.set(Calendar.MONTH, 5);
		calFinal.set(Calendar.YEAR, 2016);
		calFinal.set(Calendar.HOUR, 23);
		calFinal.set(Calendar.MINUTE, 59);
		calFinal.set(Calendar.SECOND, 59);
		
		List<ContaCorrenteMovimentacaoDTO> lst = service.getMovimentacaoBancariaPorContaCorrenteIdPorIntervaloData(cc.getId(), calInicial.getTime(), calFinal.getTime());
		Assert.assertNotEquals(0, lst.size());
	}
	
	@Test
	@UsingDataSet({"db/regiao.xml", "db/cliente.xml", "db/conta.xml", "db/transacao.xml", "db/agendamento.xml"})
	public void deveResumoContasPorCiente() {
		ContaCorrente cc = service.getContaCorrenteById(1000L);
		Assert.assertNotNull(cc);
		
		List<ContaCorrenteResumoDTO> lst = service.getResumoContasPorCiente(cc.getCliente().getId());
		System.out.println(lst.get(0));
		Assert.assertNotEquals(0, lst.size());
	}
	
}
