package br.com.rp.rest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import br.com.rp.domain.Agendamento;
import br.com.rp.services.AgendamentoService;
import br.com.rp.services.EnvioBacenService;
import br.com.rp.services.EnvioMatrizService;

@Path("/schedule")
@Produces("application/json")
public class AgendamentoRest {

	@EJB
	private AgendamentoService service;
	
	@EJB
	private EnvioBacenService bacenService;
	
	@EJB
	private EnvioMatrizService matrizService;
	

	@GET
	@Path("/findnotsendtobacen/{dataFinal}")
	public List<Agendamento> buscarAgendamentosRealizadosNaoEnviadosAoBacenPorDataFinal(
			@PathParam("dataFinal") String dataFinal) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return service.getAgendamentosRealizadosNaoEnviadosAoBacenByDataFinal(sdf.parse(dataFinal));
	}

	@GET
	@Path("/findnotsendtoheadquarters/{dataFinal}")
	public List<Agendamento> buscarAgendamentosRealizadosNaoEnviadosAMatrizPorDataFinal(
			@PathParam("dataFinal") String dataFinal) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return service.getAgendamentosRealizadosNaoEnviadosAMatrizByDataFinal(sdf.parse(dataFinal));
		} catch (ParseException e) {
			return null;
		}
	}
	
	@POST
	@Path("/sendscheduletobacen")
	public String enviarAgendamentoParaBacen(@FormParam("agendamentoId") Long agendamentoId) {
		try {
			bacenService.envioInformacao(agendamentoId);
			return "ok";
		} catch (Exception e) {
			return e.getMessage();
		}
	}
	
	@POST
	@Path("/sendscheduletoheadquarters")
	public String enviarAgendamentoParaMatriz(@FormParam("agendamentoId") Long agendamentoId) {
		try {
			matrizService.envioInformacao(agendamentoId);
			return "ok";
		} catch (Exception e) {
			return e.getMessage();
		}
	}
}
