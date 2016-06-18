package br.com.rp.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import br.com.rp.domain.Cliente;
import br.com.rp.services.ClienteService;

@Path("/cliente")
public class ClienteRest {

	@EJB
	private ClienteService service;
	
	@GET
	public List<Cliente> consultarClientes() {
		return service.getAll();
	}
}
