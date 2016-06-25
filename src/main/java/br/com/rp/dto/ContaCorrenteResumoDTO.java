package br.com.rp.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import br.com.rp.domain.Agendamento;
import br.com.rp.domain.ContaCorrente;
import br.com.rp.enums.TipoConta;

public class ContaCorrenteResumoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long clienteId;

	private Long contaCorrenteId;

	private String contaCorrenteNumero;
	
	private String contaCorrenteDigitoVerificador;
	
	private TipoConta contaCorrenteTipo;
	
	private BigDecimal contaCorrenteLimite;
	
	private BigDecimal saldo;

	public ContaCorrenteResumoDTO(ContaCorrente contaCorrente, List<Agendamento> lstAgendamnetos) {
		setClienteId(contaCorrente.getCliente().getId());
		setContaCorrenteId(contaCorrente.getId());
		setContaCorrenteDigitoVerificador(contaCorrente.getDigitoVerificador());
		setContaCorrenteNumero(contaCorrente.getNumero());
		setContaCorrenteLimite(contaCorrente.getLimite());
		setContaCorrenteTipo(contaCorrente.getTipoConta());
		setSaldo(contaCorrente.getSaldo(lstAgendamnetos));
	}

	public Long getClienteId() {
		return clienteId;
	}

	public void setClienteId(Long clienteId) {
		this.clienteId = clienteId;
	}

	public Long getContaCorrenteId() {
		return contaCorrenteId;
	}

	public void setContaCorrenteId(Long contaCorrenteId) {
		this.contaCorrenteId = contaCorrenteId;
	}

	public String getContaCorrenteNumero() {
		return contaCorrenteNumero;
	}

	public void setContaCorrenteNumero(String contaCorrenteNumero) {
		this.contaCorrenteNumero = contaCorrenteNumero;
	}

	public String getContaCorrenteDigitoVerificador() {
		return contaCorrenteDigitoVerificador;
	}

	public void setContaCorrenteDigitoVerificador(String contaCorrenteDigitoVerificador) {
		this.contaCorrenteDigitoVerificador = contaCorrenteDigitoVerificador;
	}

	public TipoConta getContaCorrenteTipo() {
		return contaCorrenteTipo;
	}

	public void setContaCorrenteTipo(TipoConta contaCorrenteTipo) {
		this.contaCorrenteTipo = contaCorrenteTipo;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}
	
	public BigDecimal getContaCorrenteLimite() {
		return contaCorrenteLimite;
	}

	public void setContaCorrenteLimite(BigDecimal contaCorrenteLimite) {
		this.contaCorrenteLimite = contaCorrenteLimite;
	}

	@Override
	public String toString() {
		return "ContaCorrenteResumoDTO [clienteId=" + clienteId + ", contaCorrenteId=" + contaCorrenteId
				+ ", contaCorrenteNumero=" + contaCorrenteNumero + ", contaCorrenteDigitoVerificador="
				+ contaCorrenteDigitoVerificador + ", contaCorrenteTipo=" + contaCorrenteTipo + ", contaCorrenteLimite="
				+ contaCorrenteLimite + ", saldo=" + saldo + "]";
	}
}