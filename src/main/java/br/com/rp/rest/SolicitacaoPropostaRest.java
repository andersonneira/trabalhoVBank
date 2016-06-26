package br.com.rp.rest;

import java.util.List;

import br.com.rp.domain.SolicitacaoProposta;
import br.com.rp.repository.ClienteRepository;
import br.com.rp.repository.RegiaoRepository;
import br.com.rp.repository.SolicitacaoPropostaRepository;
import br.com.rp.services.SolicitacaoPropostaService;

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
    @EJB
    private RegiaoRepository regiaoRepository;

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
    @Path("/listAllProposalByRegion/{regionId}")
    @GET
    public List<SolicitacaoProposta> consultaSolicitacaoPropostaPeloIdDaRegiao(@PathParam("regionId") String regionId) {
    	return repository.buscarSolicitacoesPorRegiao(regiaoRepository.findById(Long.valueOf(regionId)));
    }
    
    @Path("/proposalAccept/{id}/{msg}")
    @POST
    public void aprovarProposta(@PathParam("id") String id, @PathParam("id") String msg){
    	SolicitacaoPropostaService sps = new SolicitacaoPropostaService();
    	sps.aprovarProposta(msg, Long.valueOf(id));
    }
    @Path("/proposalDeny/{id}/{msg}")
    @POST
    public void rejeitarProposta(@PathParam("id") String id, @PathParam("id") String msg){
    	SolicitacaoPropostaService sps = new SolicitacaoPropostaService();
    	sps.rejeitarProposta(Long.valueOf(id), msg);
    }
}
