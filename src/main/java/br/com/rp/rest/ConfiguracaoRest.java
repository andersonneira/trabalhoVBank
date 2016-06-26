package br.com.rp.rest;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import br.com.rp.domain.Configuracao;
import br.com.rp.services.ConfiguracaoService;

@Path("/configuration")
@Produces("application/json")
public class ConfiguracaoRest {
	
	@EJB
	private ConfiguracaoService service;

	@GET
	public List<Configuracao> consultarConfiguracoes() {
		return service.getAllConfiguracoes();
	}
	
	@POST
	@Path("/save")
	public String salvar(@FormParam("initialhour") String initialhour, @FormParam("finalhour") String finalhour, @FormParam("limit") BigDecimal limit) {
		try {
			service.salvarConfiguracao(initialhour, finalhour, limit);
			return "ok";
		} catch (Exception e) {
			return e.getMessage();
		}
	}

}
