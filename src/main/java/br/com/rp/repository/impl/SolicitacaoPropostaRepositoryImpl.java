package br.com.rp.repository.impl;

import br.com.rp.domain.Regiao;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import br.com.rp.domain.SolicitacaoProposta;
import br.com.rp.repository.SolicitacaoPropostaRepository;

import java.util.List;

import javax.persistence.Query;

@Stateless
public class SolicitacaoPropostaRepositoryImpl extends
		AbstractRepositoryImpl<SolicitacaoProposta> implements
		SolicitacaoPropostaRepository {

	public SolicitacaoPropostaRepositoryImpl() {
		super(SolicitacaoProposta.class);
	}

	public SolicitacaoProposta save(SolicitacaoProposta object) {
		return super.save(object);
	}

	/**
	 * Este método busca todas as solicitações por regiao.
	 *
	 * @param regiao
	 *            a ser pesquisada
	 *
	 * @return retornar as solicitações por regiao.
	 */
	@Override
	public List<Regiao> buscarSolicitacoesPorRegiao(Regiao regiao) {
		Query q = em
				.createQuery(" from SolicitacaoProposta obj "
						+ " where cast( substring(obj.cep, 1 , 5) as long ) between :cepInicial and :cepFinal ");
		q.setParameter("cepInicial",
				regiao.transformaStringCepEmLong(regiao.getCepInicial()));
		q.setParameter("cepFinal",
				regiao.transformaStringCepEmLong(regiao.getCepFinal()));
		return q.getResultList();

	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public SolicitacaoProposta findById(Long id) {
		return super.findById(id);
	}

}
