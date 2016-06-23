package br.com.rp.services;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.rp.domain.Transacao;
import br.com.rp.repository.TransacaoRepository;

@Stateless
public class TransacaoService {

	@EJB
	private TransacaoRepository repository;
	
	public List<Transacao> getAll() {
		return repository.getAll();
	}
}
