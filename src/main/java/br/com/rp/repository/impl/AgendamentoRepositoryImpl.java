package br.com.rp.repository.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.TemporalType;

import br.com.rp.domain.Agendamento;
import br.com.rp.repository.AgendamentoRepository;

@Stateless
public class AgendamentoRepositoryImpl extends AbstractRepositoryImpl<Agendamento> implements AgendamentoRepository {

	public AgendamentoRepositoryImpl() {
		super(Agendamento.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Agendamento> findMovimentacaoRealizadaByContaCorrenteId(Long contaCorrenteId) {
		Calendar cal = Calendar.getInstance();
		Query qry = em.createQuery("from Agendamento ag where ag.conta.id = :contaCorrenteId and ag.dataRealizacao <= :dataRealizacao and ag.estado = 'REALIZADO'", Agendamento.class);
		qry.setParameter("contaCorrenteId", contaCorrenteId);
		qry.setParameter("dataRealizacao", cal.getTime(), TemporalType.TIMESTAMP);
		return qry.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Agendamento> findMovimentacaoRealizadaByContaCorrenteIdByDataInicialByDataFinal(Long contaCorrenteId,
			Date dataInicial, Date dataFinal) {
		Query qry = em.createQuery("from Agendamento ag where ag.conta.id = :contaCorrenteId and ag.dataRealizacao >= :dataInicial and ag.dataRealizacao <= :dataFinal and ag.estado = 'REALIZADO'", Agendamento.class);
		qry.setParameter("contaCorrenteId", contaCorrenteId);
		qry.setParameter("dataInicial", dataInicial);
		qry.setParameter("dataFinal", dataFinal);
		return qry.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Agendamento> findAgendamentosPendentesByContaCorrenteId(Long contaCorrenteId) {
		Query qry = em.createQuery("from Agendamento ag where ag.conta.id = :contaCorrenteId and ag.estado = 'PENDENTE'", Agendamento.class);
		qry.setParameter("contaCorrenteId", contaCorrenteId);
		return qry.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Agendamento> findAgendamentosPendentesByContaCorrenteIdByDataFinal(Long contaCorrenteId,
			Date dataFinal) {
		Query qry = em.createQuery("from Agendamento ag where ag.conta.id = :contaCorrenteId and ag.dataRealizacao <= :dataFinal and ag.estado = 'PENDENTE'", Agendamento.class);
		qry.setParameter("contaCorrenteId", contaCorrenteId);
		qry.setParameter("dataFinal", dataFinal);
		return qry.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Agendamento> findAgendamentosPendentesByDataFinal(Date dataFinal) {
		Query qry = em.createQuery("from Agendamento ag where ag.dataRealizacao <= :dataFinal and ag.estado = 'PENDENTE'", Agendamento.class);
		qry.setParameter("dataFinal", dataFinal);
		return qry.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Agendamento> findAgendamentosRealizadosNaoEnviadosAoBacenByDataFinal(Date dataFinal) {
		Query qry = em.createQuery("from Agendamento ag where ag.dataRealizacao <= :dataFinal and ag.estado = 'REALIZADO' and ag.transacao.envioBacen is null", Agendamento.class);
		qry.setParameter("dataFinal", dataFinal);
		return qry.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Agendamento> findAgendamentosRealizadosNaoEnviadosAMatrizByDataFinal(Date dataFinal) {
		Query qry = em.createQuery("from Agendamento ag where ag.dataRealizacao <= :dataFinal and ag.estado = 'REALIZADO' and ag.transacao.envioMatriz is null", Agendamento.class);
		qry.setParameter("dataFinal", dataFinal);
		return qry.getResultList();
	}
	
}
