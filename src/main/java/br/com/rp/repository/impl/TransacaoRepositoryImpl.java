package br.com.rp.repository.impl;

import javax.ejb.Stateless;

import br.com.rp.domain.Transacao;
import br.com.rp.repository.TransacaoRepository;

@Stateless
public class TransacaoRepositoryImpl extends AbstractRepositoryImpl<Transacao> implements TransacaoRepository {

	public TransacaoRepositoryImpl() {
		super(Transacao.class);
	}
	

}
