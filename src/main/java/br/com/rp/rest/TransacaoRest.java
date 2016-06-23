package br.com.rp.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import br.com.rp.domain.Transacao;
import br.com.rp.services.TransacaoService;

@Path("/transaction")
@Produces("application/json")
public class TransacaoRest {

	@EJB
	private TransacaoService service;

	@GET
	public List<Transacao> getAllTransactions() {
		return service.getAll();
	}
}
