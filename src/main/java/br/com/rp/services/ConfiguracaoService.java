package br.com.rp.services;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.rp.domain.Configuracao;
import br.com.rp.repository.ConfiguracaoRepository;

@Stateless
public class ConfiguracaoService {

	@EJB
	private ConfiguracaoRepository repository;

	public List<Configuracao> getAllConfiguracoes() {
		return repository.getAll();
	}
	
	public void salvarConfiguracao(String horaAberturaOperacao, String horaFechamentoOperacao, BigDecimal limitePadrao) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		List<Configuracao> lst = repository.getAll();
		Configuracao configuracao = new Configuracao();
		for (Configuracao conf : lst) {
			if (configuracao.getId() == null) {
				configuracao = conf;
			}
			if (!conf.getId().equals(configuracao.getId())) {
				repository.remove(conf.getId());
			}
		}
		System.out.println("configuracao id "+configuracao.getId());
		configuracao.setHoraAberturaOperacao(sdf.parse(horaAberturaOperacao));
		configuracao.setHoraFechamentoOperacao(sdf.parse(horaFechamentoOperacao));
		configuracao.setLimitePadrao(limitePadrao);
		repository.save(configuracao);
	}
}
