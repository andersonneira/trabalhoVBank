package br.com.rp.services;

import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
@Startup
public class EnvioBacenTaskService {

	@EJB
	private EnvioBacenService service;
	
	@Asynchronous
	@Schedule(second="*", minute="5", hour="*", dayOfMonth="*", month="*", year="*")
	public void realizarAgendamentoPendentes() {
		service.envioInformacoes();
	}
}
