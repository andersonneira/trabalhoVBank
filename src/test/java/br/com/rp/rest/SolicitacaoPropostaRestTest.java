package br.com.rp.rest;

import br.com.rp.AbstractTest;
import br.com.rp.domain.Funcionario;
import br.com.rp.domain.SolicitacaoProposta;

import java.net.URL;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.jboss.arquillian.persistence.UsingDataSet;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.junit.Assert;
import org.junit.Test;

public class SolicitacaoPropostaRestTest extends AbstractTest {

    private static final String URL = "http://localhost:8080/vbank/api/requestproposal";

    @Test
    public void deveRetornarOkPeloRest() {
        Client client = ClientBuilder.newClient();

        WebTarget target = client.target(URL);
        target.matrixParam("nome", "anderson b de neira");
        target.matrixParam("email", "andersonneira@gmail.com");
        target.matrixParam("cep", "99999-999");

        Response response = target.request().get();
        String resposta = (String) response.getEntity();

        Assert.assertEquals("ok", resposta);
    }
    @Test
    public void deveRetornarMotivoRejeicaoPeloRest() {
        Client client = ClientBuilder.newClient();

        WebTarget target = client.target(URL+"/rejectProposal/1/rejeitada");
        Response response = target.request().put(null);
        String resposta = (String) response.getEntity();

        Assert.assertEquals("rejeitada", resposta);
    }
    
    @Test
    @UsingDataSet("db/solicitaproposta.xml")
    public void deveRetornarUmaSolicitacaoPropostaPeloIdComRest() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(URL+"/findProposalByid/1");
		Response response = target.request().get();
		SolicitacaoProposta sp = response.readEntity(SolicitacaoProposta.class);
		String anderson = new String("anderson");
		Assert.assertEquals(anderson, sp.getNome());
    }
    

}
