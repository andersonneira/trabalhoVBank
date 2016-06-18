package br.com.rp.repository.impl;

import br.com.rp.domain.Regiao;
import br.com.rp.repository.RegiaoRepository;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

@Stateless
public class RegiaoRepositoryImpl extends AbstractRepositoryImpl<Regiao> implements RegiaoRepository {

    public RegiaoRepositoryImpl() {
        super(Regiao.class);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public Regiao save(Regiao object) {
        return super.save(object);
    }
}
