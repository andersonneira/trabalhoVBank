package br.com.rp.repository;

import java.util.List;

import javax.ejb.Local;

import br.com.rp.domain.Regiao;

@Local
public interface RegiaoRepository extends Repository<Regiao> {
	abstract List buscarClientesPorRegiao(Regiao regiao);
	
}
