package br.com.rp.services;

import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.jms.Destination;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.ObjectMessage;

import br.com.rp.domain.Agendamento;
import br.com.rp.domain.Transacao;
import br.com.rp.dto.DepositoChequeDTO;
import br.com.rp.dto.PagamentosDTO;
import br.com.rp.dto.TransferenciaBacenDTO;
import br.com.rp.repository.AgendamentoRepository;
import br.com.rp.repository.TransacaoRepository;

@Singleton
public class EnvioMatrizService {

	@Inject
	@JMSConnectionFactory("java:jboss/DefaultJMSConnectionFactory")
	private JMSContext context;

	@Resource(name = "java:/jms/queue/TransferenciasMatrizQueue")
	private Destination destinationTransferencias;

	@Resource(name = "java:/jms/queue/PagamentosMatrizQueue")
	private Destination destinationPagamentos;

	@Resource(name = "java:/jms/queue/ChequesMatrizQueue")
	private Destination destinationCheques;

	@EJB
	private AgendamentoRepository agendamentoRepository;

	@EJB
	private TransacaoRepository transacaoRepository;

	public void envioInformacoes() {
		List<Agendamento> lstAgendamentos = agendamentoRepository
				.findAgendamentosRealizadosNaoEnviadosAoBacenByDataFinal(Calendar.getInstance().getTime());
		lstAgendamentos.forEach(agendamento -> {
			if (agendamento.getTransacao().getPagamento() != null) {
				enviarPagamento(agendamento);
			} else if (agendamento.getTransacao().getDepositoCheque() != null) {
				enviarDepositoCheque(agendamento);
			} else {
				enviarTransferencia(agendamento);
			}
		});
	}

	private void enviarTransferencia(Agendamento agendamento) {
		try {
			ObjectMessage obj = context.createObjectMessage();
			obj.setObject(new TransferenciaBacenDTO(agendamento.getConta().getNumero(),
													agendamento.getConta().getDigitoVerificador(), 
													agendamento.getDataRealizacao(),
													agendamento.getTransacao().getAgenciaDestino(),
													agendamento.getTransacao().getAgenciaDestinoDigitoVerificador(),
													agendamento.getTransacao().getNumeroContaDestino(),
													agendamento.getTransacao().getNumeroContaDestinoDigitoVerificador(),
													agendamento.getTransacao().getValor()));
			JMSProducer produtor = context.createProducer();
			produtor.send(destinationTransferencias, obj);
			Transacao tr = transacaoRepository.findById(agendamento.getTransacao().getId());
			tr.setEnvioMatriz(Calendar.getInstance().getTime());
			transacaoRepository.save(tr);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	
	private void enviarPagamento(Agendamento agendamento) {
		try {
			ObjectMessage obj = context.createObjectMessage();
			obj.setObject(
					new PagamentosDTO(agendamento.getConta().getNumero(), 
									  agendamento.getConta().getDigitoVerificador(),
									  agendamento.getDataRealizacao(), 
									  agendamento.getTransacao().getValor(),
									  agendamento.getTransacao().getPagamento().getLinhaDigitavel(),
									  agendamento.getTransacao().getPagamento().getTipoPagamento(),
									  agendamento.getTransacao().getPagamento().getFavorecido()));
			JMSProducer produtor = context.createProducer();
			produtor.send(destinationTransferencias, obj);
			Transacao tr = transacaoRepository.findById(agendamento.getTransacao().getId());
			tr.setEnvioMatriz(Calendar.getInstance().getTime());
			transacaoRepository.save(tr);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	
	private void enviarDepositoCheque(Agendamento agendamento) {
		try {
			ObjectMessage obj = context.createObjectMessage();
			obj.setObject(
					new DepositoChequeDTO(agendamento.getConta().getNumero(), 
							agendamento.getConta().getDigitoVerificador(),
							agendamento.getDataRealizacao(), 
							agendamento.getTransacao().getValor(),
							agendamento.getTransacao().getDepositoCheque().getCmc7(),
							agendamento.getTransacao().getDepositoCheque().getImagem()));
			JMSProducer produtor = context.createProducer();
			produtor.send(destinationTransferencias, obj);
			Transacao tr = transacaoRepository.findById(agendamento.getTransacao().getId());
			tr.setEnvioMatriz(Calendar.getInstance().getTime());
			transacaoRepository.save(tr);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}
