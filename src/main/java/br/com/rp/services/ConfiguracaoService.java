package br.com.rp.services;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.rp.domain.Configuracao;
import br.com.rp.repository.ConfiguracaoRepository;

@Stateless
public class ConfiguracaoService {

	@EJB
	private ConfiguracaoRepository repository;

	public List<Configuracao> getAllConfiguracoes() {
		return repository.getAll();
	}
}
