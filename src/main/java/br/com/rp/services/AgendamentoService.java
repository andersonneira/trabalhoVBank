package br.com.rp.services;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.rp.domain.Agendamento;
import br.com.rp.repository.AgendamentoRepository;

@Stateless
public class AgendamentoService {

	@EJB
	private AgendamentoRepository repository;
	
	public List<Agendamento> getAll() {
		return repository.getAll();
	}
}
