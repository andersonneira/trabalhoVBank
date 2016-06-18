package br.com.rp.repository.impl;

import br.com.rp.domain.ContaCorrente;
import br.com.rp.repository.ContaCorrenteRepository;
import javax.ejb.Stateless;

@Stateless
public class ContaCorrenteRepositotyImpl extends AbstractRepositoryImpl<ContaCorrente> implements ContaCorrenteRepository {

    public ContaCorrenteRepositotyImpl() {
        super(ContaCorrente.class);
    }

    public ContaCorrente save(ContaCorrente object) {
        return super.save(object);
    }
}
