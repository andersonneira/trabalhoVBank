package br.com.rp.services;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.rp.domain.Funcionario;
import br.com.rp.repository.FuncionarioRepository;

@Stateless
public class FuncionarioService {
	
	@EJB
	private FuncionarioRepository repository;

	public List<Funcionario> getAllFuncionarios() {
		return repository.getAll();
	}
	public Funcionario getFuncionarioById(Long id) {
		return repository.findById(id);
	}
}
