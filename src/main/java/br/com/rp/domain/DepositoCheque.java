package br.com.rp.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name="deposito_cheque")
public class DepositoCheque extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	@Column(name="cmc7", nullable=false)
	private String cmc7;

	@Column(name="imagem")
	@Lob
	private String imagem;
	
	public DepositoCheque() {
		super();
	}

	public DepositoCheque(String cmc7, String imagem) {
		super();
		this.cmc7 = cmc7;
		this.imagem = imagem;
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
		return "DepositoCheque [cmc7=" + cmc7 + ", imagem=" + imagem + "]";
	}
}
