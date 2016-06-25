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
	
	@Test
	@UsingDataSet({"db/regiao.xml", "db/cliente.xml", "db/conta.xml", "db/transacao.xml", "db/agendamento.xml"})
	public void deveResumoContasPorConta() {
		ContaCorrente cc = service.getContaCorrenteById(1000L);
		Assert.assertNotNull(cc);
		
		ContaCorrenteResumoDTO resumo = service.getResumoConta(cc.getId());
		System.out.println(resumo);
		Assert.assertNotNull(resumo);
	}
	
	@Test
	@UsingDataSet({"db/regiao.xml", "db/cliente.xml", "db/conta.xml", "db/transacao.xml", "db/agendamento.xml"})
	public void deveTransferirParaClienteDoMesmoBanco() throws Exception {
		ContaCorrente cc = service.getContaCorrenteById(1000L);
		ContaCorrente ccDestino = service.getContaCorrenteById(1001L);
		Assert.assertNotNull(cc);
		Assert.assertNotNull(ccDestino);
		
		int tr = service.getMovimentacaoBancariaPorContaCorrenteIdPorIntervaloData(cc.getId(), getHoraInicial(), getHoraFinal()).size();
		int trDestino = service.getMovimentacaoBancariaPorContaCorrenteIdPorIntervaloData(ccDestino.getId(), getHoraInicial(), getHoraFinal()).size();
		service.realizarTransferenciaEntreContas(cc.getId(), new BigDecimal(1000), ccDestino.getNumero(), ccDestino.getDigitoVerificador());
		int trAtualizado = service.getMovimentacaoBancariaPorContaCorrenteIdPorIntervaloData(cc.getId(), getHoraInicial(), getHoraFinal()).size();
		int trDestinoAtualizado = service.getMovimentacaoBancariaPorContaCorrenteIdPorIntervaloData(ccDestino.getId(), getHoraInicial(), getHoraFinal()).size();
		
		Assert.assertTrue(tr < trAtualizado);
		Assert.assertTrue(trDestino < trDestinoAtualizado);
	}
	
	@Test(expected=Exception.class)
	@UsingDataSet({"db/regiao.xml", "db/cliente.xml", "db/conta.xml", "db/transacao.xml", "db/agendamento.xml"})
	public void naoDeveTransferirParaClienteDoMesmoBancoPorFaltaDeLimite() throws Exception {
		ContaCorrente cc = service.getContaCorrenteById(1000L);
		ContaCorrente ccDestino = service.getContaCorrenteById(1001L);
		Assert.assertNotNull(cc);
		Assert.assertNotNull(ccDestino);
		
		service.realizarTransferenciaEntreContas(cc.getId(), new BigDecimal(10000), ccDestino.getNumero(), ccDestino.getDigitoVerificador());
	}
	
	@Test(expected=Exception.class)
	@UsingDataSet({"db/regiao.xml", "db/cliente.xml", "db/conta.xml", "db/transacao.xml", "db/agendamento.xml"})
	public void naoDeveTransferirParaClienteDoMesmoBancoPorContaInvalida() throws Exception {
		ContaCorrente ccDestino = service.getContaCorrenteById(1001L);
		Assert.assertNotNull(ccDestino);
		
		service.realizarTransferenciaEntreContas(3331L, new BigDecimal(1000), ccDestino.getNumero(), ccDestino.getDigitoVerificador());
	}
	
	@Test(expected=Exception.class)
	@UsingDataSet({"db/regiao.xml", "db/cliente.xml", "db/conta.xml", "db/transacao.xml", "db/agendamento.xml"})
	public void naoDeveTransferirParaClienteDoMesmoBancoPorContaDestinoInvalida() throws Exception {
		service.realizarTransferenciaEntreContas(3331L, new BigDecimal(1000), "831", "0");
	}
	
	@Test
	@UsingDataSet({"db/regiao.xml", "db/cliente.xml", "db/conta.xml", "db/transacao.xml", "db/agendamento.xml"})
	public void deveTransferirParaOutrosBancos() throws Exception {
		ContaCorrente cc = service.getContaCorrenteById(1000L);
		Assert.assertNotNull(cc);
		
		int tr = service.getMovimentacaoBancariaPorContaCorrenteIdPorIntervaloData(cc.getId(), getHoraInicial(), getHoraFinal()).size();
		service.realizarTransferenciaOutrosBancos(cc.getId(), new BigDecimal(1000), "001", "9", "091233", "2");
		int trAtualizado = service.getMovimentacaoBancariaPorContaCorrenteIdPorIntervaloData(cc.getId(), getHoraInicial(), getHoraFinal()).size();
		
		Assert.assertTrue(tr < trAtualizado);
	}
	
	@Test(expected=Exception.class)
	@UsingDataSet({"db/regiao.xml", "db/cliente.xml", "db/conta.xml", "db/transacao.xml", "db/agendamento.xml"})
	public void naoDeveTransferirParaOutrosBancosPorContaInvalida() throws Exception {
		service.realizarTransferenciaOutrosBancos(342342L, new BigDecimal(1000), "001", "9", "091233", "2");
	}
	
	@Test(expected=Exception.class)
	@UsingDataSet({"db/regiao.xml", "db/cliente.xml", "db/conta.xml", "db/transacao.xml", "db/agendamento.xml"})
	public void naoDeveTransferirParaOutrosBancosPorContaDestinoInvalida() throws Exception {
		service.realizarTransferenciaOutrosBancos(1000L, new BigDecimal(1000), null, "", "", null);
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
		calFinal.set(Calendar.MONTH, 5);
		calFinal.set(Calendar.YEAR, 2016);
		calFinal.set(Calendar.HOUR, 25);
		calFinal.set(Calendar.MINUTE, 59);
		calFinal.set(Calendar.SECOND, 59);
		return calFinal.getTime();
	}
	
}
