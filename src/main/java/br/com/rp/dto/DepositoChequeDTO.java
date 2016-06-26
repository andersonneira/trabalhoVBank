package br.com.rp.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class DepositoChequeDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String contaNumero;

	private String contaDigitoVerificador;

	private Date dataRealizacao;
	
	private BigDecimal valor;
	
	private String cmc7;
	
	private String imagem;

	public DepositoChequeDTO() {
		super();
	}

	public DepositoChequeDTO(String contaNumero, String contaDigitoVerificador, Date dataRealizacao, BigDecimal valor,
			String cmc7, String imagem) {
		super();
		this.contaNumero = contaNumero;
		this.contaDigitoVerificador = contaDigitoVerificador;
		this.dataRealizacao = dataRealizacao;
		this.valor = valor;
		this.cmc7 = cmc7;
		this.imagem = imagem;
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

	public String getCmc7() {
		return cmc7;
	}

	public void setCmc7(String cmc7) {
		this.cmc7 = cmc7;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	@Override
	public String toString() {
		return "DepositoChequeDTO [contaNumero=" + contaNumero + ", contaDigitoVerificador=" + contaDigitoVerificador
				+ ", dataRealizacao=" + dataRealizacao + ", valor=" + valor + ", cmc7=" + cmc7 + ", imagem=" + imagem
				+ "]";
	}
}
