package br.com.rp.services;

import br.com.rp.domain.Regiao;
import br.com.rp.domain.SolicitacaoProposta;
import br.com.rp.repository.SolicitacaoPropostaRepository;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class SolicitacaoPropostaService {

	@EJB
	private SolicitacaoPropostaRepository repository;

	public List<SolicitacaoProposta> getAllSolicitacoesPropostas() {
		return repository.getAll();
	}

	public List<Regiao> buscarSolicitacoesPorRegiao(Regiao regiao) {
		return repository.buscarSolicitacoesPorRegiao(regiao);
	}

	public SolicitacaoProposta findById(Long id) {
		return repository.findById(id);
	}
	public SolicitacaoProposta save(SolicitacaoProposta sp) {
		return repository.save(sp);
	}
	public SolicitacaoProposta rejeitarProposta(Long id, String motivoRejeicao) {
			SolicitacaoProposta spRejeitada = new SolicitacaoProposta();
			spRejeitada = findById(id);
			spRejeitada.setMotivoRejeicao(motivoRejeicao);
			return save(spRejeitada);
		}
	public SolicitacaoProposta aprovarProposta (){
			
		return null;
	}
}
