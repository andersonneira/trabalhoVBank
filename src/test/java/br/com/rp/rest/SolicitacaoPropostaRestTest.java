package br.com.rp.rest;

import java.net.URL;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.jboss.arquillian.test.api.ArquillianResource;
import org.junit.Assert;
import org.junit.Test;

public class SolicitacaoPropostaRestTest extends AbstractRestTest {

    private static final String URL = "http://localhost:8080/vbank/api/requestproposal";

    @Test
    public void deveRetornarOkPeloRest(@ArquillianResource URL baseURI) {
        Client client = ClientBuilder.newClient();

        WebTarget target = client.target(URL);
        target.matrixParam("nome", "anderson b de neira");
        target.matrixParam("email", "andersonneira@gmail.com");
        target.matrixParam("cep", "99999-999");

        Response response = target.request().get();
        String resposta = (String) response.getEntity();

        Assert.assertEquals("ok", resposta);
    }

}
