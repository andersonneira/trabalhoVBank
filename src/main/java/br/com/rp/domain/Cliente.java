package br.com.rp.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import br.com.rp.anotations.Cep;
import br.com.rp.anotations.Documento;
import br.com.rp.anotations.Email;

@Entity
@Table(name="Cliente")
public class Cliente extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	@Column(name="email", nullable=false)
	@Email
	private String email;
	
	@Column(name="nome", nullable=false, length=45)
	@Size(min=3, max=45)
	private String nome;
	
	@Column(name="cep", nullable=false)
	@Cep
	private String cep;
	
	@Column(name="documento", nullable=false, unique=true)
	@Documento
	private String documento;

	public Cliente() {
		super();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	@Override
	public String toString() {
		return "Cliente [email=" + email + ", nome=" + nome + ", cep=" + cep + "]";
	}

	
}
