package br.com.rp.services;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.rp.domain.Agendamento;
import br.com.rp.domain.ContaCorrente;
import br.com.rp.domain.DepositoCheque;
import br.com.rp.domain.Transacao;
import br.com.rp.enums.EstadoAgendamento;
import br.com.rp.repository.AgendamentoRepository;
import br.com.rp.repository.ContaCorrenteRepository;
import br.com.rp.repository.DepositoChequeRepository;
import br.com.rp.repository.TransacaoRepository;

@Stateless
public class DepositoChequeService {

	@EJB
	private DepositoChequeRepository repository;
	
	@EJB
	private ContaCorrenteRepository contaCorrenteRepository;
	
	@EJB
	private AgendamentoRepository agendamentoRepository;
	
	@EJB
	private TransacaoRepository transacaoRepository;
	
	public void realizarDepositoChequeEmConta(Long contaId, String cmc7, String imagem, BigDecimal valor) throws Exception {
		ContaCorrente conta = contaCorrenteRepository.findById(contaId);
		if (conta == null) {
			throw new Exception("Conta Corrente não localizada");
		}
		
		DepositoCheque cheque = new DepositoCheque(cmc7, imagem);
		repository.save(cheque);

		Transacao tr = new Transacao();
		tr.setNumeroContaDestino(conta.getNumero());
		tr.setNumeroContaDestinoDigitoVerificador(conta.getDigitoVerificador());
		tr.setAgenciaDestino("009");
		tr.setAgenciaDestinoDigitoVerificador("1");
		tr.setValor(valor);
		tr.setDepositoCheque(cheque);
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
