package br.com.rp.services;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.rp.domain.Agendamento;
import br.com.rp.domain.ContaCorrente;
import br.com.rp.domain.Pagamento;
import br.com.rp.domain.Transacao;
import br.com.rp.enums.EstadoAgendamento;
import br.com.rp.enums.TipoPagamento;
import br.com.rp.repository.AgendamentoRepository;
import br.com.rp.repository.PagamentoRepository;
import br.com.rp.repository.TransacaoRepository;

@Stateless
public class PagamentoService {

	@EJB
	private PagamentoRepository repository;
	
	@EJB
	private ContaCorrenteService contaCorrenteService;
	
	@EJB
	private AgendamentoRepository agendamentoRepository;
	
	@EJB
	private TransacaoRepository transacaoRepository;

	public void realizarPagamentoTitulo(Long contaId, String linhaDigitavel, BigDecimal valor, String favorecido) throws Exception {
		realizarPagamento(contaId, linhaDigitavel, TipoPagamento.TITULO, valor, favorecido);
	}

	public void realizarPagamentoAgua(Long contaId, String linhaDigitavel, BigDecimal valor, String favorecido) throws Exception {
		realizarPagamento(contaId, linhaDigitavel, TipoPagamento.AGUA, valor, favorecido);
	}
	
	public void realizarPagamentoLuz(Long contaId, String linhaDigitavel, BigDecimal valor, String favorecido) throws Exception {
		realizarPagamento(contaId, linhaDigitavel, TipoPagamento.LUZ, valor, favorecido);
	}
	
	public void realizarPagamentoImposto(Long contaId, String linhaDigitavel, BigDecimal valor, String favorecido) throws Exception {
		realizarPagamento(contaId, linhaDigitavel, TipoPagamento.IMPOSTO, valor, favorecido);
	}

	private void realizarPagamento(Long contaId, String linhaDigitavel, TipoPagamento tipoPagamento, BigDecimal valor, String favorecido) throws Exception {
		contaCorrenteService.validaSaldoTransacao(contaId, valor);
		ContaCorrente conta = contaCorrenteService.getContaCorrenteById(contaId);
		
		Pagamento pagamento = new Pagamento(linhaDigitavel, tipoPagamento, favorecido);
		repository.save(pagamento);
		
		Transacao tr = new Transacao();
		tr.setNumeroContaDestino(conta.getNumero());
		tr.setNumeroContaDestinoDigitoVerificador(conta.getDigitoVerificador());
		tr.setAgenciaDestino("009");
		tr.setAgenciaDestinoDigitoVerificador("1");
		tr.setValor(valor.multiply(new BigDecimal(-1)));
		tr.setPagamento(pagamento);
		transacaoRepository.save(tr);

		Agendamento ag = new Agendamento();
		ag.setConta(conta);
		ag.setDataInclusao(Calendar.getInstance().getTime());
		ag.setDataRealizacao(Calendar.getInstance().getTime());
		ag.setEstado(EstadoAgendamento.REALIZADO);
		ag.setTransacao(tr);
		agendamentoRepository.save(ag);
	}
}
