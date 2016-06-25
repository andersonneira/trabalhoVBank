package br.com.rp.service;

import java.util.List;

import javax.ejb.EJB;

import org.jboss.arquillian.persistence.UsingDataSet;
import org.junit.Assert;
import org.junit.Test;

import br.com.rp.AbstractTest;
import br.com.rp.domain.Regiao;
import br.com.rp.domain.SolicitacaoProposta;
import br.com.rp.services.EmailService;
import br.com.rp.services.SolicitacaoPropostaService;

public class SolicitaPropostaTest extends AbstractTest {

	@EJB
	private SolicitacaoPropostaService service;
	
	@EJB
	private EmailService emailService;
	

	@Test
	@UsingDataSet("db/solicitaproposta.xml")
	public void deveRetornarDuasSolicitacoesPrposta() {
		List<SolicitacaoProposta> sp = service.getAllSolicitacoesPropostas();
		Assert.assertEquals(2, sp.size());
	}

	@Test
	@UsingDataSet("db/solicitaproposta.xml")
	public void deveRetornarUmaListaVazia() {
		Regiao regiao = new Regiao();
		regiao.setCepFinal("81000-000");
		regiao.setCepInicial("80000-000");
		regiao.setNome("nome de teste");
		Assert.assertEquals(0, service.buscarSolicitacoesPorRegiao(regiao)
				.size());
	}

	@Test
	@UsingDataSet("db/solicitaproposta.xml")
	public void deveRetornarUmaListaComDuasSolicitacaoProposta() {
		Regiao regiao = new Regiao();
		regiao.setCepFinal("89000-000");
		regiao.setCepInicial("80000-000");
		regiao.setNome("nome de teste");
		Assert.assertEquals(2, service.buscarSolicitacoesPorRegiao(regiao)
				.size());
	}

	@Test
	@UsingDataSet("db/solicitaproposta.xml")
	public void deveRetornarUmaSolicitacaoProposta() {
                Long id = new Long(1);
		SolicitacaoProposta sp = service.findById(id);
		String anderson = new String("anderson");
		Assert.assertEquals(anderson, sp.getNome());
	}
	@Test
	@UsingDataSet("db/solicitaproposta.xml")
	public void deveSetarMotivoRejeicaoRejeicaoProposta() {
                Long id = new Long(3);
                String motivoRejeicao = new String("Proposta rejeitada");
		SolicitacaoProposta sp = service.findById(id);
		sp.setMotivoRejeicao(motivoRejeicao);
		service.rejeitarProposta(id, motivoRejeicao);
		Assert.assertEquals(motivoRejeicao, sp.getMotivoRejeicao());
		
	}
}
