package br.com.rp.rest;

import br.com.rp.domain.SolicitacaoProposta;
import br.com.rp.repository.SolicitacaoPropostaRepository;

import javax.ejb.EJB;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/requestproposal")
@Produces("application/json")
public class SolicitacaoPropostaRest {

    @EJB
    private SolicitacaoPropostaRepository repository;

    @POST
    public String addSolicitacaoProposta(String nome, String email, String cep) {
        try {
            repository.save(new SolicitacaoProposta(nome, email, cep));
            return "ok";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

}
