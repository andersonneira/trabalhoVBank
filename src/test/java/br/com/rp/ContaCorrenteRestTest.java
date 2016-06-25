package br.com.rp;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.jboss.arquillian.persistence.CleanupUsingScript;
import org.jboss.arquillian.persistence.TestExecutionPhase;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.junit.Assert;
import org.junit.Test;

@CleanupUsingScript(value="db/cleanup.sql", phase=TestExecutionPhase.AFTER)
public class ContaCorrenteRestTest extends AbstractTest {

	private static final String URL = "http://localhost:8080/vbank/api/manageaccount";
	
	@Test
	@UsingDataSet({"db/regiao.xml", "db/cliente.xml", "db/conta.xml", "db/transacao.xml", "db/agendamento.xml"})
    public void realizarTransferenciaEntreContas() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(URL+"/transferbetweenaccounts");
        Form form = new Form();
        form.param("contaId", "1000");
        form.param("valor", "1000");
        form.param("contaDestinoNumero", "1220122022");
        form.param("contaDestinoDigitoVerificador", "1");
        Response response = target.request().post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED), Response.class);
        Assert.assertEquals("ok", response.readEntity(String.class));
    }
	
	@Test
	@UsingDataSet({"db/regiao.xml", "db/cliente.xml", "db/conta.xml", "db/transacao.xml", "db/agendamento.xml"})
	public void realizarTransferenciaOutrosBancos() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(URL+"/transferothersbanks");
		Form form = new Form();
		form.param("contaId", "1000");
		form.param("valor", "1000");
		form.param("agenciaDestinoNumero", "001");
		form.param("agenciaDestinoDigitoVerificador", "9");
		form.param("contaDestinoNumero", "234123125");
		form.param("contaDestinoDigitoVerificador", "8");
		Response response = target.request().post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED), Response.class);
		Assert.assertEquals("ok", response.readEntity(String.class));
	}
	
	@Test
	@UsingDataSet({"db/regiao.xml", "db/cliente.xml", "db/conta.xml", "db/transacao.xml", "db/agendamento.xml"})
	public void consultaContaCorrenteById() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(URL+"/findAccountByid/1000");
		Response response = target.request().get();
		Assert.assertEquals(Status.OK, response.getStatusInfo());
	}
	
	@Test
	@UsingDataSet({"db/regiao.xml", "db/cliente.xml", "db/conta.xml", "db/transacao.xml", "db/agendamento.xml"})
	public void consultaResumoContasPorCiente() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(URL+"/findSummaryAccountsByClient/1000");
		Response response = target.request().get();
		Assert.assertEquals(Status.OK, response.getStatusInfo());
	}
	
	@Test
	@UsingDataSet({"db/regiao.xml", "db/cliente.xml", "db/conta.xml", "db/transacao.xml", "db/agendamento.xml"})
	public void consultaResumoContasPorConta() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(URL+"/findSummaryAccountsById/1000");
		Response response = target.request().get();
		Assert.assertEquals(Status.OK, response.getStatusInfo());
	}
	
	@Test
	@UsingDataSet({"db/regiao.xml", "db/cliente.xml", "db/conta.xml", "db/transacao.xml", "db/agendamento.xml"})
	public void consultaMovimentacaoBancariaPorContaCorrenteIdPorIntervaloData() {
		Client client = ClientBuilder.newClient();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		WebTarget target = client.target(URL+"/findAccountsHandlingByIdByInitialDateByFinalDate/1000/"+sdf.format(getHoraInicial())+"/"+sdf.format(getHoraFinal()));
		Response response = target.request().get();
		Assert.assertEquals(Status.OK, response.getStatusInfo());
	}
	
	private Date getHoraInicial() {
		Calendar calInicial = Calendar.getInstance();
		calInicial.set(Calendar.DAY_OF_MONTH, 1);
		calInicial.set(Calendar.MONTH, 0);
		calInicial.set(Calendar.YEAR, 2016);
		calInicial.set(Calendar.HOUR, 23);
		calInicial.set(Calendar.MINUTE, 59);
		calInicial.set(Calendar.SECOND, 59);
		return calInicial.getTime();
	}
	
	private Date getHoraFinal() {
		Calendar calFinal = Calendar.getInstance();
		calFinal.set(Calendar.DAY_OF_MONTH, 24);
		calFinal.set(Calendar.MONTH, 5);
		calFinal.set(Calendar.YEAR, 2016);
		calFinal.set(Calendar.HOUR, 25);
		calFinal.set(Calendar.MINUTE, 59);
		calFinal.set(Calendar.SECOND, 59);
		return calFinal.getTime();
	}
	
}
