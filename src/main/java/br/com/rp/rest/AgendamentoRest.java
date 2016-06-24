package br.com.rp.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import br.com.rp.domain.Agendamento;
import br.com.rp.services.AgendamentoService;

@Path("/transaction")
@Produces("application/json")
public class AgendamentoRest {

	@EJB
	private AgendamentoService service;
	
	@GET
	public List<Agendamento> getAllTransactions() {
		return service.getAll();
	}
}
