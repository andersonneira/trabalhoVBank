package br.com.rp.repository.impl;

import javax.ejb.Stateless;

import br.com.rp.domain.SolicitacaoProposta;
import br.com.rp.repository.SolicitacaoPropostaRepository;

@Stateless
public class SolicitacaoPropostaRepositoryImpl extends AbstractRepositoryImpl<SolicitacaoProposta> implements SolicitacaoPropostaRepository {

    public SolicitacaoPropostaRepositoryImpl() {
        super(SolicitacaoProposta.class);
    }
   
    public SolicitacaoProposta save(SolicitacaoProposta object) {
        return super.save(object);
    }
}
