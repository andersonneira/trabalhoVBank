package br.com.rp.services;

import br.com.rp.domain.Cliente;
import br.com.rp.domain.ContaCorrente;
import br.com.rp.domain.Regiao;
import br.com.rp.domain.SolicitacaoProposta;
import br.com.rp.enums.StatusConta;
import br.com.rp.enums.TipoConta;
import br.com.rp.repository.ClienteRepository;
import br.com.rp.repository.ContaCorrenteRepository;
import br.com.rp.repository.SolicitacaoPropostaRepository;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class SolicitacaoPropostaService {

	@EJB
	private SolicitacaoPropostaRepository repository;

	@EJB
	private ClienteRepository clienteRepository;

	@EJB
	private ContaCorrenteRepository contaCorrenteRepository;

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

	public void remove(Long id) {
		repository.remove(id);
	}

	public SolicitacaoProposta rejeitarProposta(Long id, String motivoRejeicao) {
		SolicitacaoProposta spRejeitada = new SolicitacaoProposta();
		spRejeitada = findById(id);
		spRejeitada.setMotivoRejeicao(motivoRejeicao);
		return save(spRejeitada);
	}

	public void aprovarProposta(String mensagem, Long id) {
		try {
			SolicitacaoProposta sp = new SolicitacaoProposta();
			sp = repository.findById(id);
			String email = sp.getEmail();
			String nome = sp.getNome();
			String cep = sp.getCep();
			String doc = sp.getDocumento();

			Cliente cl = new Cliente();
			cl.setNome(nome);
			cl.setDocumento(doc);
			cl.setCep(cep);
			cl.setEmail(email);
			RegiaoService rs = new RegiaoService();
			if (rs.setarRegiaoPorCep(cl)!=null) {
				cl.setRegiao(rs.setarRegiaoPorCep(cl));
			}
			clienteRepository.save(cl);
			ContaCorrente cc = new ContaCorrente();
			cc.setCliente(cl);
			cc.setTipoConta(TipoConta.CORRENTE);
			cc.setStatusConta(StatusConta.APROVADA);
			BigDecimal limite = new BigDecimal(1000);
			cc.setLimite(limite);
			cc.setSenha(cl.getDocumento() + cl.getCep());
			contaCorrenteRepository.save(cc);

			repository.remove(id);
			
			EmailService es = new EmailService();
			es.enviarEmailAprovacao(mensagem, cl.getEmail());
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
}
