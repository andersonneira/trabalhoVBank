package br.com.rp.repository;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import javax.ejb.EJB;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.rp.AbstractTest;
import br.com.rp.domain.Agendamento;
import br.com.rp.domain.Cliente;
import br.com.rp.domain.ContaCorrente;
import br.com.rp.domain.Regiao;
import br.com.rp.domain.Transacao;
import br.com.rp.enums.EstadoAgendamento;
import br.com.rp.enums.StatusConta;
import br.com.rp.enums.TipoConta;

public class AgendamentoRepositoryTest extends AbstractTest {

	private static final EstadoAgendamento _ESTADO_VALIDO = EstadoAgendamento.PENDENTE;
	private static final Date _DATA_INCLUSAO_VALIDA = Calendar.getInstance().getTime();
	private static final Date _DATA_REALIZACAO_VALIDA = Calendar.getInstance().getTime();
	
	private static final String _NOME_VALIDO = "Teste";
	private static final String _EMAIL_VALIDO = "teste@gmail.com";
	private static final String _CEP_VALIDO = "86800-005";
	
	private static final BigDecimal _LIMITE_CONTA_VALIDO = new BigDecimal(300);
	private static final StatusConta _STATUS_CONTA_VALIDO = StatusConta.APROVADA;
	private static final TipoConta _TIPO_CONTA_VALIDO = TipoConta.CORRENTE;
	
	private static final BigDecimal _VALOR_VALIDO = new BigDecimal(30);
	private static final String _CONTA_DESTINO_VALIDA = "000003";
	private static final String _CONTA_DESTINO_DV_VALIDO = "3";
	private static final String _AGENCIA_DESTINO_DV_VALIDO = "2";
	private static final String _AGENDCIA_DESTINO_VALIDA = "001";

	@EJB
	private AgendamentoRepository repository;
	
	@EJB
	private TransacaoRepository transacaoRepository;
	
	@EJB
	private ContaCorrenteRepository contaCorrenteRepository;
	
	@EJB
	private RegiaoRepository regiaoRepository;
	
	@EJB
	private ClienteRepository clienteRepository;
	
	private Agendamento agendamento;
	private ContaCorrente conta;
	private Regiao regiao;
	private Cliente cliente;
	
	@Before
	public void before() {
		if (agendamento != null && agendamento.getId() != null) 
			repository.remove(agendamento.getId());
		
		if (conta != null && conta.getId() != null) 
		contaCorrenteRepository.remove(conta.getId());
		
		if (cliente != null && cliente.getId() != null) 
		clienteRepository.remove(cliente.getId());
		
		if (regiao != null && regiao.getId() != null) 
		regiaoRepository.remove(regiao.getId());
		
		Transacao transacao = new Transacao();
		transacao.setAgenciaDestino(_AGENDCIA_DESTINO_VALIDA);
		transacao.setAgenciaDestinoDigitoVerificador(_AGENCIA_DESTINO_DV_VALIDO);
		transacao.setNumeroContaDestino(_CONTA_DESTINO_VALIDA);
		transacao.setNumeroContaDestinoDigitoVerificador(_CONTA_DESTINO_DV_VALIDO);
		transacao.setValor(_VALOR_VALIDO);
		transacaoRepository.save(transacao);
		
		regiao = new Regiao();
		regiao.setNome("Teste");
		regiao.setCepFinal("86800-005");
		regiao.setCepInicial("86800-005");
		regiaoRepository.save(regiao);
		
		cliente = new Cliente();
		cliente.setEmail(_EMAIL_VALIDO);
		cliente.setCep(_CEP_VALIDO);
		cliente.setNome(_NOME_VALIDO);
		cliente.setDataCadastro(Calendar.getInstance().getTime());
		cliente.setDocumento(new GerarCPF().geraCPF()); //+new Random().nextInt(99)
		cliente.setRegiao(regiaoRepository.findById(regiao.getId()));
		clienteRepository.save(cliente);
		
		conta = new ContaCorrente();
		conta.setLimite(_LIMITE_CONTA_VALIDO);
		conta.setStatusConta(_STATUS_CONTA_VALIDO);
		conta.setTipoConta(_TIPO_CONTA_VALIDO);
		conta.setCliente(cliente);
		contaCorrenteRepository.save(conta);
		
		agendamento = new Agendamento();
		agendamento.setDataInclusao(_DATA_INCLUSAO_VALIDA);
		agendamento.setDataRealizacao(_DATA_REALIZACAO_VALIDA);
		agendamento.setEstado(_ESTADO_VALIDO);
		agendamento.setConta(conta);
		agendamento.setTransacao(transacao);
	}
	
	
	@Test
	public void deveInserirComSucesso() {
		repository.save(agendamento);
		Assert.assertNotNull(agendamento.getId());
		Assert.assertNotNull(agendamento.getTransacao().getId());
	}
	
	@Test(expected = Exception.class)
	public void naoDeveInserirPorFaltaDeDataInclusao() {
		agendamento.setDataInclusao(null);
		repository.save(agendamento);
		Assert.assertNotNull(agendamento.getId());
	}
	
	@Test(expected = Exception.class)
	public void naoDeveInserirPorFaltaDeDataRealizacao() {
		agendamento.setDataRealizacao(null);
		repository.save(agendamento);
		Assert.assertNotNull(agendamento.getId());
	}
	
	@Test(expected = Exception.class)
	public void naoDeveInserirPorFaltaDeEstado() {
		agendamento.setEstado(null);
		repository.save(agendamento);
		Assert.assertNotNull(agendamento.getId());
	}
	
	@Test(expected = Exception.class)
	public void naoDeveInserirPorFaltaDeTransacao() {
		agendamento.setTransacao(null);
		repository.save(agendamento);
		Assert.assertNotNull(agendamento.getId());
	}
	
	@Test(expected = Exception.class)
	public void naoDeveInserirPorFaltaDeConta() {
		agendamento.setConta(null);
		repository.save(agendamento);
		Assert.assertNotNull(agendamento.getId());
	}
}
