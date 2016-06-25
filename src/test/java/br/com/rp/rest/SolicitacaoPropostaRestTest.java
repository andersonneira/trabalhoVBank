package br.com.rp.rest;

import br.com.rp.AbstractTest;
import br.com.rp.domain.Funcionario;
import br.com.rp.domain.SolicitacaoProposta;

import java.net.URL;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.arquillian.persistence.UsingDataSet;
import org.junit.Assert;
import org.junit.Test;

public class SolicitacaoPropostaRestTest extends AbstractTest {

    private static final String URL = "http://localhost:8080/vbank/api/requestproposal";

    @Test
    public void deveEnviarUmaRequisicaoComSucesso() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(URL);
        Form form = new Form();
        form.param("nome", "anderosn b de neira");
        form.param("email", "anderosnneira@gmail.com");
        form.param("cep", "99999-999");
        form.param("documento", "866.576.159-49");
        Response response = target.request().post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED), Response.class);
        Assert.assertEquals("ok", response.readEntity(String.class));
    }

    /**
     * Se já houver uma proposta com o cpf solicitado, não será possivel inserir
     * uma novo, pois todos os dias vamos remover as solicitações mais velhas
     * que 30 dias.
     */
    @Test
    @UsingDataSet("db/solicitaproposta.xml")
    public void deveNaoInserirPoisJaTemPropostaComEsteCpf() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(URL);
        Form form = new Form();
        form.param("nome", "anderosn b de neira");
        form.param("email", "anderosnneira@gmail.com");
        form.param("cep", "99999-999");
        form.param("documento", "828.588.613-15");
        Response response = target.request().post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED), Response.class);
        Assert.assertNotEquals("ok", response.readEntity(String.class));
    }

//    @Test
//    public void deveRetornarMotivoRejeicaoPeloRest() {
//        Client client = ClientBuilder.newClient();
//
//        WebTarget target = client.target(URL + "/rejectProposal/1/rejeitada");
//        Response response = target.request().put(null);
//        String resposta = (String) response.getEntity();
//
//        Assert.assertEquals("rejeitada", resposta);
//    }
//
//    @Test
//    @UsingDataSet("db/solicitaproposta.xml")
//    public void deveRetornarUmaSolicitacaoPropostaPeloIdComRest() {
//        Client client = ClientBuilder.newClient();
//        WebTarget target = client.target(URL + "/findProposalByid/1");
//        Response response = target.request().get();
//        SolicitacaoProposta sp = response.readEntity(SolicitacaoProposta.class);
//        String anderson = new String("anderson");
//        Assert.assertEquals(anderson, sp.getNome());
//    }

}
