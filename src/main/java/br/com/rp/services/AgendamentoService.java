package br.com.rp.services;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.rp.domain.Agendamento;
import br.com.rp.enums.EstadoAgendamento;
import br.com.rp.repository.AgendamentoRepository;

@Stateless
public class AgendamentoService {

	@EJB
	private AgendamentoRepository repository;
	
	@EJB
	private ContaCorrenteService contaCorrenteService;
	
	public List<Agendamento> getAll() {
		return repository.getAll();
	}
	
	public void realizarAgendamentoPendentes() {
		List<Agendamento> lstAgendamentos = repository.findAgendamentosPendentesByDataFinal(Calendar.getInstance().getTime());
		lstAgendamentos.forEach(agendamento -> {
			try {
				contaCorrenteService.validaSaldoTransacao(agendamento.getConta().getId(), agendamento.getTransacao().getValor());
				agendamento.setEstado(EstadoAgendamento.REALIZADO);
				repository.save(agendamento);
			} catch (Exception e) {
				agendamento.setEstado(EstadoAgendamento.CANCELADO_SALDO_INSUFICIENTE);
				repository.save(agendamento);
			}
		});
	}
	
	public List<Agendamento> getAgendamentosRealizadosNaoEnviadosAMatrizByDataFinal(Date dataFinal) {
		return repository.findAgendamentosRealizadosNaoEnviadosAMatrizByDataFinal(dataFinal);
	}
	
	public List<Agendamento> getAgendamentosRealizadosNaoEnviadosAoBacenByDataFinal(Date dataFinal) {
		return repository.findAgendamentosRealizadosNaoEnviadosAoBacenByDataFinal(dataFinal);
	}
}
