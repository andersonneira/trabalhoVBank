package br.com.rp.services;

import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
@Startup
public class AgendamentoTaskService {

	@EJB
	private AgendamentoService agendamentoService;
	
	@Asynchronous
	@Schedule(second="*", minute="20", hour="*", dayOfMonth="*", month="*", year="*")
	public void realizarAgendamentoPendentes() {
		agendamentoService.realizarAgendamentoPendentes();
	}
}
