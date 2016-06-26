package br.com.rp.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
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
import br.com.rp.domain.Transacao;
import br.com.rp.dto.ContaCorrenteMovimentacaoDTO;
import br.com.rp.dto.ContaCorrenteResumoDTO;
import br.com.rp.enums.EstadoAgendamento;
import br.com.rp.repository.AgendamentoRepository;
import br.com.rp.repository.ContaCorrenteRepository;
import br.com.rp.repository.TransacaoRepository;

@Stateless
public class ContaCorrenteService {

	@EJB
	private ContaCorrenteRepository repository;

	@EJB
	private AgendamentoRepository agendamentoRepository;

	@EJB
	private TransacaoRepository transacaoRepository;

	@Resource(name = "DefaultManagedExecutorService")
	private ManagedExecutorService mes;

	public List<ContaCorrente> getAllRegioes() {
		return repository.getAll();
	}

	public ContaCorrente getContaCorrenteById(Long id) {
		return repository.findById(id);
	}

	public ContaCorrente save(ContaCorrente contaCorrente) {
		return repository.save(contaCorrente);
	}

	public List<ContaCorrenteMovimentacaoDTO> getMovimentacaoBancariaPorContaCorrenteIdPorIntervaloData(
			Long contaCorrenteId, Date dataInicial, Date dataFinal) {
		List<ContaCorrenteMovimentacaoDTO> lstResult = new ArrayList<ContaCorrenteMovimentacaoDTO>();
		List<Agendamento> lstAgendamentos = agendamentoRepository
				.findMovimentacaoRealizadaByContaCorrenteIdByDataInicialByDataFinal(
						contaCorrenteId, dataInicial, dataFinal);
		lstAgendamentos.forEach(agendamento -> {
			lstResult.add(new ContaCorrenteMovimentacaoDTO(agendamento
					.getConta().getId(), agendamento.getDataRealizacao(),
					agendamento.getTransacao().getValor()));
		});
		return lstResult;
	}

	public List<ContaCorrenteResumoDTO> getResumoContasPorCiente(Long clienteId) {
		try {
			List<ContaCorrente> lstContas = repository
					.findByClienteId(clienteId);
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

	public void validaSaldoTransacao(Long contaId, BigDecimal valor)
			throws Exception {
		ContaCorrente conta = repository.findById(contaId);
		if (conta == null) {
			throw new Exception("Conta Corrente não localizada");
		}
		ContaCorrenteResumoDTO ccResumo = getResumoConta(contaId);
		if (ccResumo == null) {
			throw new Exception(
					"Operação indisponivél no momento, entre em contato com seu gerente...");
		}

		if (valor.compareTo(ccResumo.getSaldo()) > 0) {
			throw new Exception("Saldo insuficiente, operação cancelada!");
		}
	}

	public ContaCorrenteResumoDTO getResumoConta(Long id) {
		try {
			ContaCorrente contaCorrente = repository.findById(id);
			List<java.util.concurrent.Callable<ContaCorrenteResumoDTO>> result = new ArrayList<>();
			result.add(new ResumoContas(contaCorrente));
			List<Future<ContaCorrenteResumoDTO>> invoke = mes.invokeAll(result);
			List<ContaCorrenteResumoDTO> resultado = invoke.stream()
					.map(conta -> {
						try {
							return conta.get();
						} catch (Exception e) {
							e.printStackTrace();
						}
						return null;
					}).collect(Collectors.toList());
			return !resultado.isEmpty() ? resultado.get(0) : null;
		} catch (Throwable e) {
			e.printStackTrace();
			return null;
		}
	}

	public void realizarTransferenciaEntreContas(Long contaId,
			BigDecimal valor, String contaDestinoNumero,
			String contaDestinoDigitoVerificador) throws Exception {
		ContaCorrente conta = repository.findById(contaId);
		if (conta == null) {
			throw new Exception("Conta Corrente não localizada");
		}
		ContaCorrenteResumoDTO ccResumo = getResumoConta(contaId);
		if (ccResumo == null) {
			throw new Exception(
					"Operação indisponivél no momento, entre em contato com seu gerente...");
		}

		if (valor.compareTo(ccResumo.getSaldo()) > 0) {
			throw new Exception("Saldo insuficiente, operação cancelada!");
		}

		ContaCorrente ccDestino = repository.findByContaByDigitoVerificador(
				contaDestinoNumero, contaDestinoDigitoVerificador);
		if (ccDestino == null) {
			throw new Exception("Conta Corrente de destino não localizada");
		}

		Transacao tr = new Transacao();
		tr.setNumeroContaDestino(ccDestino.getNumero());
		tr.setNumeroContaDestinoDigitoVerificador(ccDestino
				.getDigitoVerificador());
		tr.setAgenciaDestino("009");
		tr.setAgenciaDestinoDigitoVerificador("1");
		tr.setValor(valor.multiply(new BigDecimal(-1)));
		transacaoRepository.save(tr);

		Transacao trDestino = new Transacao();
		trDestino.setNumeroContaDestino(conta.getNumero());
		trDestino.setNumeroContaDestinoDigitoVerificador(conta
				.getDigitoVerificador());
		trDestino.setAgenciaDestino("009");
		trDestino.setAgenciaDestinoDigitoVerificador("1");
		trDestino.setValor(valor);
		transacaoRepository.save(trDestino);

		Agendamento ag = new Agendamento();
		ag.setConta(conta);
		ag.setDataInclusao(Calendar.getInstance().getTime());
		ag.setDataRealizacao(Calendar.getInstance().getTime());
		ag.setEstado(EstadoAgendamento.REALIZADO);
		ag.setTransacao(tr);
		agendamentoRepository.save(ag);

		Agendamento agDestino = new Agendamento();
		agDestino.setConta(ccDestino);
		agDestino.setDataInclusao(Calendar.getInstance().getTime());
		agDestino.setDataRealizacao(Calendar.getInstance().getTime());
		agDestino.setEstado(EstadoAgendamento.REALIZADO);
		agDestino.setTransacao(trDestino);
		agendamentoRepository.save(agDestino);
	}

	public void realizarTransferenciaOutrosBancos(Long contaId,
			BigDecimal valor, String agenciaDestinoNumero,
			String agenciaDestinoDigitoVerificador, String contaDestinoNumero,
			String contaDestinoDigitoVerificador) throws Exception {
		ContaCorrente conta = repository.findById(contaId);
		if (conta == null) {
			throw new Exception("Conta Corrente não localizada");
		}
		if (!hasValue(agenciaDestinoNumero, agenciaDestinoDigitoVerificador,
				contaDestinoNumero, contaDestinoDigitoVerificador)) {
			throw new Exception("Conta Corrente destino inválida");
		}
		ContaCorrenteResumoDTO ccResumo = getResumoConta(contaId);
		if (ccResumo == null) {
			throw new Exception(
					"Operação indisponivél no momento, entre em contato com seu gerente...");
		}

		if (valor.compareTo(ccResumo.getSaldo()) > 0) {
			throw new Exception("Saldo insuficiente, operação cancelada!");
		}

		Transacao tr = new Transacao();
		tr.setNumeroContaDestino(contaDestinoNumero);
		tr.setNumeroContaDestinoDigitoVerificador(contaDestinoDigitoVerificador);
		tr.setAgenciaDestino(agenciaDestinoNumero);
		tr.setAgenciaDestinoDigitoVerificador(agenciaDestinoDigitoVerificador);
		tr.setValor(valor.multiply(new BigDecimal(-1)));
		transacaoRepository.save(tr);

		Agendamento ag = new Agendamento();
		ag.setConta(conta);
		ag.setDataInclusao(Calendar.getInstance().getTime());
		ag.setDataRealizacao(Calendar.getInstance().getTime());
		ag.setEstado(EstadoAgendamento.REALIZADO);
		ag.setTransacao(tr);
		agendamentoRepository.save(ag);
	}

	private boolean hasValue(String... valor) {
		if (valor == null) {
			return false;
		}
		for (String string : valor) {
			if (string == null || string.trim().equals("")) {
				return false;
			}
		}
		return true;
	}

	private class ResumoContas implements
			java.util.concurrent.Callable<ContaCorrenteResumoDTO> {

		private ContaCorrente contaCorrente;

		public ResumoContas(ContaCorrente contaCorrente) {
			this.contaCorrente = contaCorrente;
		}

		@Override
		public ContaCorrenteResumoDTO call() throws Exception {
			List<Agendamento> lstAgendamnetos = agendamentoRepository
					.findMovimentacaoRealizadaByContaCorrenteId(this.contaCorrente
							.getId());
			ContaCorrenteResumoDTO resumo = new ContaCorrenteResumoDTO(
					this.contaCorrente, lstAgendamnetos);
			return resumo;
		}
	}

	public ContaCorrente cadastrarSenha(ContaCorrente contaCorrente,
			String senha) {
		contaCorrente.setSenha(senha);
		return repository.save(contaCorrente);
	}
}
