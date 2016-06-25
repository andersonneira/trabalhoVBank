package br.com.rp.service;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;

import org.jboss.arquillian.persistence.CleanupUsingScript;
import org.jboss.arquillian.persistence.TestExecutionPhase;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.junit.Assert;
import org.junit.Test;

import br.com.rp.AbstractTest;
import br.com.rp.dto.ContaCorrenteMovimentacaoDTO;
import br.com.rp.services.ContaCorrenteService;
import br.com.rp.services.PagamentoService;

@CleanupUsingScript(value="db/cleanup.sql", phase=TestExecutionPhase.AFTER)
public class PagamentoServiceTest extends AbstractTest {

	@EJB
	private PagamentoService service;
	
	@EJB
	private ContaCorrenteService contaCorrenteService;
	
	@Test
	@UsingDataSet({"db/regiao.xml", "db/cliente.xml", "db/conta.xml", "db/transacao.xml", "db/agendamento.xml"})
	public void deveRealizarPagamentoTitulo() throws Exception {
		List<ContaCorrenteMovimentacaoDTO> lst = contaCorrenteService.getMovimentacaoBancariaPorContaCorrenteIdPorIntervaloData(1000L, getHoraInicial(), getHoraFinal());
		Assert.assertNotEquals(0, lst.size());
		service.realizarPagamentoTitulo(1000L, "00000.00000.00000.000000.00000.000000.0.51980000000000", new BigDecimal(200), "Teste");
		List<ContaCorrenteMovimentacaoDTO> lstAtualizado = contaCorrenteService.getMovimentacaoBancariaPorContaCorrenteIdPorIntervaloData(1000L, getHoraInicial(), getHoraFinal());
		Assert.assertNotEquals(lst.size(), lstAtualizado.size());
	}
	
	@Test
	@UsingDataSet({"db/regiao.xml", "db/cliente.xml", "db/conta.xml", "db/transacao.xml", "db/agendamento.xml"})
	public void deveRealizarPagamentoAgua() throws Exception {
		List<ContaCorrenteMovimentacaoDTO> lst = contaCorrenteService.getMovimentacaoBancariaPorContaCorrenteIdPorIntervaloData(1000L, getHoraInicial(), getHoraFinal());
		Assert.assertNotEquals(0, lst.size());
		service.realizarPagamentoAgua(1000L, "0000000.0.51980000000000", new BigDecimal(200), "Sanepar");
		List<ContaCorrenteMovimentacaoDTO> lstAtualizado = contaCorrenteService.getMovimentacaoBancariaPorContaCorrenteIdPorIntervaloData(1000L, getHoraInicial(), getHoraFinal());
		Assert.assertNotEquals(lst.size(), lstAtualizado.size());
	}
	
	@Test
	@UsingDataSet({"db/regiao.xml", "db/cliente.xml", "db/conta.xml", "db/transacao.xml", "db/agendamento.xml"})
	public void deveRealizarPagamentoLuz() throws Exception {
		List<ContaCorrenteMovimentacaoDTO> lst = contaCorrenteService.getMovimentacaoBancariaPorContaCorrenteIdPorIntervaloData(1000L, getHoraInicial(), getHoraFinal());
		Assert.assertNotEquals(0, lst.size());
		service.realizarPagamentoLuz(1000L, "000000.88888888880.0.51980000000000", new BigDecimal(200), "Copel");
		List<ContaCorrenteMovimentacaoDTO> lstAtualizado = contaCorrenteService.getMovimentacaoBancariaPorContaCorrenteIdPorIntervaloData(1000L, getHoraInicial(), getHoraFinal());
		Assert.assertNotEquals(lst.size(), lstAtualizado.size());
	}
	
	@Test
	@UsingDataSet({"db/regiao.xml", "db/cliente.xml", "db/conta.xml", "db/transacao.xml", "db/agendamento.xml"})
	public void deveRealizarPagamentoImposto() throws Exception {
		List<ContaCorrenteMovimentacaoDTO> lst = contaCorrenteService.getMovimentacaoBancariaPorContaCorrenteIdPorIntervaloData(1000L, getHoraInicial(), getHoraFinal());
		Assert.assertNotEquals(0, lst.size());
		service.realizarPagamentoImposto(1000L, "0000.0.51980000", new BigDecimal(200), "Secretaria do Estado do Paraná");
		List<ContaCorrenteMovimentacaoDTO> lstAtualizado = contaCorrenteService.getMovimentacaoBancariaPorContaCorrenteIdPorIntervaloData(1000L, getHoraInicial(), getHoraFinal());
		Assert.assertNotEquals(lst.size(), lstAtualizado.size());
	}
	
	private Date getHoraInicial() {
		Calendar calInicial = Calendar.getInstance();
		calInicial.set(Calendar.DAY_OF_MONTH, 1);
		calInicial.set(Calendar.MONTH, 0);
		calInicial.set(Calendar.YEAR, 2016);
		calInicial.set(Calendar.HOUR, 23);
		calInicial.set(Calendar.MINUTE, 59);
		calInicial.set(Calendar.SECOND, 59);
		return calInicial.getTime();
	}
	
	private Date getHoraFinal() {
		Calendar calFinal = Calendar.getInstance();
		calFinal.set(Calendar.DAY_OF_MONTH, 24);
		calFinal.set(Calendar.MONTH, 12);
		calFinal.set(Calendar.YEAR, 2016);
		calFinal.set(Calendar.HOUR, 25);
		calFinal.set(Calendar.MINUTE, 59);
		calFinal.set(Calendar.SECOND, 59);
		return calFinal.getTime();
	}
	
}
