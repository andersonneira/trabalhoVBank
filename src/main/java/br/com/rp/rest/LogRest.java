package br.com.rp.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import br.com.rp.domain.Log;
import br.com.rp.repository.LogRepository;
import javax.ws.rs.Produces;

@Path("/log")
@Produces("application/json")
public class LogRest {

	@EJB
	private LogRepository repository;

	@GET
	public List<Log> consultarLogs() {
		return repository.getAll();
	}

}
