package br.com.rp.rest;

import br.com.rp.domain.SolicitacaoProposta;
import br.com.rp.repository.ClienteRepository;
import br.com.rp.repository.SolicitacaoPropostaRepository;

import javax.ejb.EJB;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/requestproposal")
@Produces("application/json")
public class SolicitacaoPropostaRest {

    @EJB
    private SolicitacaoPropostaRepository repository;
    @EJB
    private ClienteRepository clienteRepository;

    @POST
    public String addSolicitacaoProposta(@FormParam("nome") String nome, @FormParam("email") String email,
            @FormParam("cep") String cep, @FormParam("documento") String documento) {
        try {
            if (clienteRepository.verificaSeJaExisteEsteDocumentoInserido(documento)) {
                return "This document has already been registered.";
            }
            repository.save(new SolicitacaoProposta(nome, email, cep, documento));
            return "ok";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Path("/findProposalByid/{id}")
    @GET
    public SolicitacaoProposta consultaSolicitacaoPropostaPeloId(@PathParam("id") String id) {
        return repository.findById(Long.valueOf(id));
    }
}
