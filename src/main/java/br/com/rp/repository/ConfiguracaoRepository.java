package br.com.rp.repository;

import javax.ejb.Local;

import br.com.rp.domain.Configuracao;

@Local
public interface ConfiguracaoRepository extends Repository<Configuracao> {
    
    abstract Configuracao getConfiguracao();

}
