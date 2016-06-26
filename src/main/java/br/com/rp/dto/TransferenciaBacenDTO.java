package br.com.rp.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class TransferenciaBacenDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String contaNumero;

	private String contaDigitoVerificador;

	private Date dataRealizacao;
	
	private String agenciaDestinoNumero;
	
	private String agenciaDestinoDigitoVerificador;

	private String contaDestinoNumero;
	
	private String contaDestinoDigitoVerificador;

	private BigDecimal valor;
	
	public TransferenciaBacenDTO() {
		super();
	}

	public TransferenciaBacenDTO(String contaNumero, String contaDigitoVerificador, Date dataRealizacao,
			String agenciaDestinoNumero, String agenciaDestinoDigitoVerificador, String contaDestinoNumero,
			String contaDestinoDigitoVerificador, BigDecimal valor) {
		super();
		this.contaNumero = contaNumero;
		this.contaDigitoVerificador = contaDigitoVerificador;
		this.dataRealizacao = dataRealizacao;
		this.agenciaDestinoNumero = agenciaDestinoNumero;
		this.agenciaDestinoDigitoVerificador = agenciaDestinoDigitoVerificador;
		this.contaDestinoNumero = contaDestinoNumero;
		this.contaDestinoDigitoVerificador = contaDestinoDigitoVerificador;
		this.valor = valor;
	}

	public String getContaNumero() {
		return contaNumero;
	}

	public void setContaNumero(String contaNumero) {
		this.contaNumero = contaNumero;
	}

	public String getContaDigitoVerificador() {
		return contaDigitoVerificador;
	}

	public void setContaDigitoVerificador(String contaDigitoVerificador) {
		this.contaDigitoVerificador = contaDigitoVerificador;
	}

	public Date getDataRealizacao() {
		return dataRealizacao;
	}

	public void setDataRealizacao(Date dataRealizacao) {
		this.dataRealizacao = dataRealizacao;
	}

	public String getAgenciaDestinoNumero() {
		return agenciaDestinoNumero;
	}

	public void setAgenciaDestinoNumero(String agenciaDestinoNumero) {
		this.agenciaDestinoNumero = agenciaDestinoNumero;
	}

	public String getAgenciaDestinoDigitoVerificador() {
		return agenciaDestinoDigitoVerificador;
	}

	public void setAgenciaDestinoDigitoVerificador(String agenciaDestinoDigitoVerificador) {
		this.agenciaDestinoDigitoVerificador = agenciaDestinoDigitoVerificador;
	}

	public String getContaDestinoNumero() {
		return contaDestinoNumero;
	}

	public void setContaDestinoNumero(String contaDestinoNumero) {
		this.contaDestinoNumero = contaDestinoNumero;
	}

	public String getContaDestinoDigitoVerificador() {
		return contaDestinoDigitoVerificador;
	}

	public void setContaDestinoDigitoVerificador(String contaDestinoDigitoVerificador) {
		this.contaDestinoDigitoVerificador = contaDestinoDigitoVerificador;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "TransacaoBacenDTO [contaNumero=" + contaNumero + ", contaDigitoVerificador=" + contaDigitoVerificador
				+ ", dataRealizacao=" + dataRealizacao + ", agenciaDestinoNumero=" + agenciaDestinoNumero
				+ ", agenciaDestinoDigitoVerificador=" + agenciaDestinoDigitoVerificador + ", contaDestinoNumero="
				+ contaDestinoNumero + ", contaDestinoDigitoVerificador=" + contaDestinoDigitoVerificador + ", valor="
				+ valor + "]";
	}
}
