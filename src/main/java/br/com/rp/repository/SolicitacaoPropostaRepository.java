package br.com.rp.repository;

import br.com.rp.domain.Regiao;
import javax.ejb.Local;

import br.com.rp.domain.SolicitacaoProposta;
import java.util.List;

@Local
public interface SolicitacaoPropostaRepository extends Repository<SolicitacaoProposta> {
    
    abstract List buscarSolicitacoesPorRegiao(Regiao regiao);
    
}
