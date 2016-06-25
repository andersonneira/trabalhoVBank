package br.com.rp.repository;

import javax.ejb.EJB;

import org.jboss.arquillian.persistence.CleanupUsingScript;
import org.jboss.arquillian.persistence.TestExecutionPhase;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.rp.AbstractTest;
import br.com.rp.domain.Pagamento;
import br.com.rp.enums.TipoPagamento;

@CleanupUsingScript(value="db/cleanup.sql", phase=TestExecutionPhase.AFTER)
public class PagamentoRepositoryImplTest extends AbstractTest {

	@EJB
	private PagamentoRepository repository;
	
	private Pagamento pagamento;
	
	@Before
	public void before() {
		pagamento = new Pagamento("00000.00000.00000.000000.00000.000000.0.51980000000000", TipoPagamento.TITULO, "Teste");
	}
	
	@Test
	@UsingDataSet({"db/regiao.xml", "db/cliente.xml", "db/conta.xml", "db/transacao.xml", "db/agendamento.xml"})
    public void deveSalvar() {
		repository.save(pagamento);
		Assert.assertNotNull(pagamento.getId());
	}
	
	@Test(expected=AssertionError.class)
	@UsingDataSet({"db/regiao.xml", "db/cliente.xml", "db/conta.xml", "db/transacao.xml", "db/agendamento.xml"})
	public void naoDeveSalvarPorFaltaLinhaDigitavel() {
		pagamento.setLinhaDigitavel(null);
		repository.save(pagamento);
	}
	
	@Test(expected=Exception.class)
	@UsingDataSet({"db/regiao.xml", "db/cliente.xml", "db/conta.xml", "db/transacao.xml", "db/agendamento.xml"})
	public void naoDeveSalvarPorLinhaDigitavelCurta() {
		pagamento.setLinhaDigitavel("000");
		repository.save(pagamento);
	}
	
	@Test(expected=AssertionError.class)
	@UsingDataSet({"db/regiao.xml", "db/cliente.xml", "db/conta.xml", "db/transacao.xml", "db/agendamento.xml"})
	public void naoDeveSalvarPorFaltaFavorecido() {
		pagamento.setFavorecido(null);
		repository.save(pagamento);
	}
	
	@Test(expected=AssertionError.class)
	@UsingDataSet({"db/regiao.xml", "db/cliente.xml", "db/conta.xml", "db/transacao.xml", "db/agendamento.xml"})
	public void naoDeveSalvarPorFaltaTipoPagamento() {
		pagamento.setTipoPagamento(null);
		repository.save(pagamento);
	}
	
}
