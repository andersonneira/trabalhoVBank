package br.com.rp.rest;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import br.com.rp.domain.ContaCorrente;
import br.com.rp.dto.ContaCorrenteMovimentacaoDTO;
import br.com.rp.dto.ContaCorrenteResumoDTO;
import br.com.rp.services.ContaCorrenteService;
import br.com.rp.services.DepositoChequeService;

@Path("/manageaccount")
@Produces("application/json")
public class ContaCorrenteRest {
	
	@EJB
	private ContaCorrenteService service;
	
	@EJB
	private DepositoChequeService depositoChequeService;
	
	@POST
	@Path("/depositcheckinaccount")
	public String realizarDepositoChequeEmConta(@FormParam("contaId") Long contaId, @FormParam("valor") BigDecimal valor,
			@FormParam("cmc7") String cmc7, @FormParam("imagem") String imagem) {
		try {
			depositoChequeService.realizarDepositoChequeEmConta(contaId, cmc7, imagem, valor);
			return "ok";
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	@POST
	@Path("/transferbetweenaccounts")
    public String realizarTransferenciaEntreContas(@FormParam("contaId") Long contaId, @FormParam("valor") BigDecimal valor,
            @FormParam("contaDestinoNumero") String contaDestinoNumero, @FormParam("contaDestinoDigitoVerificador") String contaDestinoDigitoVerificador) {
        try {
        	service.realizarTransferenciaEntreContas(contaId, valor, contaDestinoNumero, contaDestinoDigitoVerificador);
            return "ok";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
	
	@POST
	@Path("/transferothersbanks")
    public String realizarTransferenciaOutrosBancos(@FormParam("contaId") Long contaId, @FormParam("valor") BigDecimal valor,
            @FormParam("agenciaDestinoNumero") String agenciaDestinoNumero, @FormParam("agenciaDestinoDigitoVerificador") String agenciaDestinoDigitoVerificador,
            @FormParam("contaDestinoNumero") String contaDestinoNumero, @FormParam("contaDestinoDigitoVerificador") String contaDestinoDigitoVerificador) {
        try {
        	service.realizarTransferenciaOutrosBancos(contaId, valor, agenciaDestinoNumero, agenciaDestinoDigitoVerificador, contaDestinoNumero, contaDestinoDigitoVerificador);
            return "ok";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
	
	@Path("/findAccountByid/{id}")
    @GET
    public ContaCorrente consultaContaCorrenteById(@PathParam("id") Long id) {
        return service.getContaCorrenteById(id);
    }
	
	@Path("/findSummaryAccountsByClient/{clienteId}")
    @GET
    public List<ContaCorrenteResumoDTO> consultaResumoContasPorCiente(@PathParam("clienteId") Long clienteId) {
        return service.getResumoContasPorCiente(clienteId);
    }
	
	@Path("/findSummaryAccountsById/{id}")
	@GET
	public ContaCorrenteResumoDTO consultaResumoContasPorConta(@PathParam("id") Long id) {
		return service.getResumoConta(id);
	}
	
	@Path("/findAccountsHandlingByIdByInitialDateByFinalDate/{contaCorrenteId}/{dataInicial}/{dataFinal}")
	@GET
	public List<ContaCorrenteMovimentacaoDTO> consultaMovimentacaoBancariaPorContaCorrenteIdPorIntervaloData(@PathParam("contaCorrenteId") Long contaCorrenteId, @PathParam("dataInicial") String dataInicial, @PathParam("dataFinal") String dataFinal) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return service.getMovimentacaoBancariaPorContaCorrenteIdPorIntervaloData(contaCorrenteId, sdf.parse(dataInicial), sdf.parse(dataFinal));
	}
	
	
}
