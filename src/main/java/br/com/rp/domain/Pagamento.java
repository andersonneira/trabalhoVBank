package br.com.rp.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import br.com.rp.enums.TipoPagamento;

@Entity
@Table(name = "pagamento")
public class Pagamento extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Column(name = "linha_digitavel", nullable=false)
	@Size(min=5)
	private String linhaDigitavel;
	
	@Column(name = "tipo_pagamento", nullable=false)
	@Enumerated(EnumType.STRING)
	private TipoPagamento tipoPagamento;
	
	@Column(name = "favorecido", nullable=false)
	private String favorecido;

	public Pagamento(String linhaDigitavel, TipoPagamento tipoPagamento, String favorecido) {
		super();
		this.linhaDigitavel = linhaDigitavel;
		this.tipoPagamento = tipoPagamento;
		this.favorecido = favorecido;
	}

	public Pagamento() {
		super();
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
		return "Pagamento [linhaDigitavel=" + linhaDigitavel + ", tipoPagamento=" + tipoPagamento + ", favorecido="
				+ favorecido + "]";
	}
}
