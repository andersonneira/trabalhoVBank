package br.com.rp.repository.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

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
		cal.add(Calendar.MINUTE, 1);
		Query qry = em.createQuery("from Agendamento ag where ag.conta.id = :contaCorrenteId and ag.dataRealizacao <= :dataRealizacao", Agendamento.class);
		qry.setParameter("contaCorrenteId", contaCorrenteId);
		qry.setParameter("dataRealizacao", cal.getTime());
		return qry.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Agendamento> findMovimentacaoRealizadaByContaCorrenteIdByDataInicialByDataFinal(Long contaCorrenteId,
			Date dataInicial, Date dataFinal) {
		Query qry = em.createQuery("from Agendamento ag where ag.conta.id = :contaCorrenteId and ag.dataRealizacao >= :dataInicial and ag.dataRealizacao <= :dataFinal", Agendamento.class);
		qry.setParameter("contaCorrenteId", contaCorrenteId);
		qry.setParameter("dataInicial", dataInicial);
		qry.setParameter("dataFinal", dataFinal);
		return qry.getResultList();
	}
	
}
