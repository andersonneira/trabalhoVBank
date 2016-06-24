package br.com.rp.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import br.com.rp.domain.Configuracao;
import br.com.rp.repository.ConfiguracaoRepository;

@Path("/configuration")
@Produces("application/json")
public class ConfiguracaoRest {
	
	@EJB
	private ConfiguracaoRepository repository;

	@GET
	public List<Configuracao> consultarConfiguracoes() {
		return repository.getAll();
	}

}
