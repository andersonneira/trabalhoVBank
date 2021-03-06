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
import br.com.rp.services.PagamentoService;

@Path("/manageaccount")
@Produces("application/json")
public class ContaCorrenteRest {
	
	@EJB
	private ContaCorrenteService service;
	
	@EJB
	private DepositoChequeService depositoChequeService;

	@EJB
	private PagamentoService pagamentoService;
	
	@POST
	@Path("/cancelschedule")
	public String cancelarAgendamento(@FormParam("agendamentoId") Long agendamentoId) {
		try {
			pagamentoService.cancelarAgendamento(agendamentoId);
			return "ok";
		} catch (Exception e) {
			return e.getMessage();
		}
	}
	
	@POST
	@Path("/taxbillschedule")
	public String agendarPagamentoImposto(@FormParam("contaId") Long contaId, @FormParam("valor") BigDecimal valor,
			@FormParam("linhaDigitavel") String linhaDigitavel, @FormParam("favorecido") String favorecido,
			@FormParam("datapagamento") String datapagamento, @FormParam("datavencimento") String datavencimento) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			pagamentoService.agendarPagamentoImposto(contaId, linhaDigitavel, valor, favorecido, sdf.parse(datapagamento), sdf.parse(datavencimento));
			return "ok";
		} catch (Exception e) {
			return e.getMessage();
		}
	}
	
	@POST
	@Path("/electricitybillschedule")
	public String agendarPagamentoLuz(@FormParam("contaId") Long contaId, @FormParam("valor") BigDecimal valor,
			@FormParam("linhaDigitavel") String linhaDigitavel, @FormParam("favorecido") String favorecido,
			@FormParam("datapagamento") String datapagamento, @FormParam("datavencimento") String datavencimento) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			pagamentoService.agendarPagamentoLuz(contaId, linhaDigitavel, valor, favorecido, sdf.parse(datapagamento), sdf.parse(datavencimento));
			return "ok";
		} catch (Exception e) {
			return e.getMessage();
		}
	}
	
	@POST
	@Path("/waterbillschedule")
	public String agendarPagamentoAgua(@FormParam("contaId") Long contaId, @FormParam("valor") BigDecimal valor,
			@FormParam("linhaDigitavel") String linhaDigitavel, @FormParam("favorecido") String favorecido,
			@FormParam("datapagamento") String datapagamento, @FormParam("datavencimento") String datavencimento) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			pagamentoService.agendarPagamentoAgua(contaId, linhaDigitavel, valor, favorecido, sdf.parse(datapagamento), sdf.parse(datavencimento));
			return "ok";
		} catch (Exception e) {
			return e.getMessage();
		}
	}
	
	@POST
	@Path("/billschedule")
	public String agendarPagamentoTitulo(@FormParam("contaId") Long contaId, @FormParam("valor") BigDecimal valor,
			@FormParam("linhaDigitavel") String linhaDigitavel, @FormParam("favorecido") String favorecido,
			@FormParam("datapagamento") String datapagamento, @FormParam("datavencimento") String datavencimento) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			pagamentoService.agendarPagamentoTitulo(contaId, linhaDigitavel, valor, favorecido, sdf.parse(datapagamento), sdf.parse(datavencimento));
			return "ok";
		} catch (Exception e) {
			return e.getMessage();
		}
	}
	
	@POST
	@Path("/taxbillpayment")
	public String realizarPagamentoImposto(@FormParam("contaId") Long contaId, @FormParam("valor") BigDecimal valor,
			@FormParam("linhaDigitavel") String linhaDigitavel, @FormParam("favorecido") String favorecido) {
		try {
			pagamentoService.realizarPagamentoImposto(contaId, linhaDigitavel, valor, favorecido);
			return "ok";
		} catch (Exception e) {
			return e.getMessage();
		}
	}
	
	@POST
	@Path("/electricitybillpayment")
	public String realizarPagamentoLuz(@FormParam("contaId") Long contaId, @FormParam("valor") BigDecimal valor,
			@FormParam("linhaDigitavel") String linhaDigitavel, @FormParam("favorecido") String favorecido) {
		try {
			pagamentoService.realizarPagamentoLuz(contaId, linhaDigitavel, valor, favorecido);
			return "ok";
		} catch (Exception e) {
			return e.getMessage();
		}
	}
	
	@POST
	@Path("/waterbillpayment")
	public String realizarPagamentoAgua(@FormParam("contaId") Long contaId, @FormParam("valor") BigDecimal valor,
			@FormParam("linhaDigitavel") String linhaDigitavel, @FormParam("favorecido") String favorecido) {
		try {
			pagamentoService.realizarPagamentoAgua(contaId, linhaDigitavel, valor, favorecido);
			return "ok";
		} catch (Exception e) {
			return e.getMessage();
		}
	}
	
	@POST
	@Path("/billpayment")
	public String realizarPagamentoTitulo(@FormParam("contaId") Long contaId, @FormParam("valor") BigDecimal valor,
			@FormParam("linhaDigitavel") String linhaDigitavel, @FormParam("favorecido") String favorecido) {
		try {
			pagamentoService.realizarPagamentoTitulo(contaId, linhaDigitavel, valor, favorecido);
			return "ok";
		} catch (Exception e) {
			return e.getMessage();
		}
	}
	
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
