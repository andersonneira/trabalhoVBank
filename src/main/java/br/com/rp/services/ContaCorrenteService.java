package br.com.rp.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.concurrent.ManagedExecutorService;

import br.com.rp.domain.Agendamento;
import br.com.rp.domain.ContaCorrente;
import br.com.rp.dto.ContaCorrenteMovimentacaoDTO;
import br.com.rp.dto.ContaCorrenteResumoDTO;
import br.com.rp.repository.AgendamentoRepository;
import br.com.rp.repository.ContaCorrenteRepository;

@Stateless
public class ContaCorrenteService {

	@EJB
	private ContaCorrenteRepository repository;
	
	@EJB
	private AgendamentoRepository agendamentoRepository;

	@Resource(name = "DefaultManagedExecutorService")
	private ManagedExecutorService mes;

	public List<ContaCorrente> getAllRegioes() {
		return repository.getAll();
	}
	
	public ContaCorrente getContaCorrenteById(Long id) {
		return repository.findById(id);
	}
	
	public List<ContaCorrenteMovimentacaoDTO> getMovimentacaoBancariaPorContaCorrenteIdPorIntervaloData(Long contaCorrenteId, Date dataInicial, Date dataFinal) {
		List<ContaCorrenteMovimentacaoDTO> lstResult = new ArrayList<ContaCorrenteMovimentacaoDTO>();
		List<Agendamento> lstAgendamentos = agendamentoRepository.findMovimentacaoRealizadaByContaCorrenteIdByDataInicialByDataFinal(contaCorrenteId, dataInicial, dataFinal);
		lstAgendamentos.forEach(agendamento -> {
			lstResult.add(new ContaCorrenteMovimentacaoDTO(agendamento.getConta().getId(), agendamento.getDataRealizacao(), agendamento.getTransacao().getValor()));
		});		
		return lstResult;
	}

	public List<ContaCorrenteResumoDTO> getResumoContasPorCiente(Long clienteId) {
		try {
			List<ContaCorrente> lstContas = repository.findByClienteId(clienteId);
			List<java.util.concurrent.Callable<ContaCorrenteResumoDTO>> result = new ArrayList<>();
			lstContas.forEach(conta -> {
				result.add(new ResumoContas(conta));
			});
			List<Future<ContaCorrenteResumoDTO>> invoke = mes.invokeAll(result);
			return invoke.stream().map(conta -> {
				try {
					return conta.get();
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}).collect(Collectors.toList());
		} catch (Throwable e) {
			e.printStackTrace();
			return null;
		}
	}

	private class ResumoContas implements java.util.concurrent.Callable<ContaCorrenteResumoDTO> {
		private ContaCorrente contaCorrente;

		public ResumoContas(ContaCorrente contaCorrente) {
			this.contaCorrente = contaCorrente;
		}

		@Override
		public ContaCorrenteResumoDTO call() throws Exception {
			List<Agendamento> lstAgendamnetos = agendamentoRepository
					.findMovimentacaoRealizadaByContaCorrenteId(this.contaCorrente.getId());
			ContaCorrenteResumoDTO resumo = new ContaCorrenteResumoDTO(this.contaCorrente, lstAgendamnetos);
			return resumo;
		}

	}
}
