package br.com.rp.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import br.com.rp.domain.Funcionario;
import br.com.rp.repository.FuncionarioRepository;

@Path("/employee")
@Produces("application/json")
public class FuncionarioRest {

	@EJB
	private FuncionarioRepository repository;

	@GET
	public List<Funcionario> consultarFuncionarios() {
		return repository.getAll();
	}
	
	@Path("/{id}")
	@GET
	public Funcionario consultarFuncionarioById(@PathParam("id")String id) {
		return repository.findById(Long.valueOf(id));
	}
}
