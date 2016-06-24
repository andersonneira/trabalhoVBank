package br.com.rp.repository.impl;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import br.com.rp.domain.Configuracao;
import br.com.rp.repository.ConfiguracaoRepository;

@Stateless
public class ConfiguracaoRepositoryImpl extends
		AbstractRepositoryImpl<Configuracao> implements ConfiguracaoRepository {
	public ConfiguracaoRepositoryImpl() {
		super(Configuracao.class);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Configuracao save(Configuracao object) {
		return super.save(object);
	}
}
