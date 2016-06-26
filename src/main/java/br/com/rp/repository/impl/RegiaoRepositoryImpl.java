package br.com.rp.repository.impl;

import java.util.List;

import br.com.rp.domain.Regiao;
import br.com.rp.repository.RegiaoRepository;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.Query;

@Stateless
public class RegiaoRepositoryImpl extends AbstractRepositoryImpl<Regiao> implements RegiaoRepository {

    public RegiaoRepositoryImpl() {
        super(Regiao.class);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public Regiao save(Regiao object) {
        return super.save(object);
    }
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public Regiao findByid(Long id) {
        return super.findById(id);
    }
	@Override
	public List<Regiao> buscarClientesPorRegiao(Regiao regiao) {
		Query q = em
				.createQuery(" from Cliente obj "
						+ " where cast( substring(obj.cep, 1 , 5) as long ) between :cepInicial and :cepFinal ");
		q.setParameter("cepInicial",
				regiao.transformaStringCepEmLong(regiao.getCepInicial()));
		q.setParameter("cepFinal",
				regiao.transformaStringCepEmLong(regiao.getCepFinal()));
		return q.getResultList();

	}
}
