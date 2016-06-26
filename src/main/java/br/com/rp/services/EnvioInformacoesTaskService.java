package br.com.rp.services;

import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
@Startup
public class EnvioInformacoesTaskService {

	@EJB
	private EnvioBacenService bacenService;
	
	@EJB
	private EnvioMatrizService matrizService;
	
	@Asynchronous
	@Schedule(second="0", minute="5", hour="*", dayOfMonth="*", month="*", year="*")
	public void realizarEnvioInformacoesBacen() {
		bacenService.envioInformacoes();
	}
	
	@Asynchronous
	@Schedule(second="0", minute="0", hour="22", dayOfMonth="*", month="*", year="*")
	public void realizarEnvioInformacoesMatriz() {
		matrizService.envioInformacoes();
	}
}
