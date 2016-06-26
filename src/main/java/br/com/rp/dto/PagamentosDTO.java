package br.com.rp.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import br.com.rp.enums.TipoPagamento;

public class PagamentosDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String contaNumero;

	private String contaDigitoVerificador;

	private Date dataRealizacao;
	
	private BigDecimal valor;
	
	private String linhaDigitavel;
	
	private TipoPagamento tipoPagamento;
	
	private String favorecido;

	public PagamentosDTO() {
		super();
	}

	public PagamentosDTO(String contaNumero, String contaDigitoVerificador, Date dataRealizacao, BigDecimal valor,
			String linhaDigitavel, TipoPagamento tipoPagamento, String favorecido) {
		super();
		this.contaNumero = contaNumero;
		this.contaDigitoVerificador = contaDigitoVerificador;
		this.dataRealizacao = dataRealizacao;
		this.valor = valor;
		this.linhaDigitavel = linhaDigitavel;
		this.tipoPagamento = tipoPagamento;
		this.favorecido = favorecido;
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

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String getLinhaDigitavel() {
		return linhaDigitavel;
	}

	public void setLinhaDigitavel(String linhaDigitavel) {
		this.linhaDigitavel = linhaDigitavel;
	}

	public TipoPagamento getTipoPagamento() {
		return tipoPagamento;
	}

	public void setTipoPagamento(TipoPagamento tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}

	public String getFavorecido() {
		return favorecido;
	}

	public void setFavorecido(String favorecido) {
		this.favorecido = favorecido;
	}

	@Override
	public String toString() {
		return "PagamentosDTO [contaNumero=" + contaNumero + ", contaDigitoVerificador=" + contaDigitoVerificador
				+ ", dataRealizacao=" + dataRealizacao + ", valor=" + valor + ", linhaDigitavel=" + linhaDigitavel
				+ ", tipoPagamento=" + tipoPagamento + ", favorecido=" + favorecido + "]";
	}
}
