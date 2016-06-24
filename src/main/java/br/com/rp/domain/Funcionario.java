package br.com.rp.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import br.com.rp.anotations.Documento;

@Entity
@Table(name="funcionario")
public class Funcionario extends BaseEntity{
   
	@Column(name = "nome", length = 100, nullable = false)
    @Size(min = 3, max = 100)
    private String nome;
	
	@Column(name = "senha", length = 10, nullable = false)
    @Size(min = 6, max = 10)
    private String senha;
	
	@Documento
	private String documento;
	//Construtor sem parametro
	public Funcionario(){
	}
	//Construtor com parametro
	public Funcionario(String nome, String senha, String documentoo){
		this.nome=nome;
		this.senha=senha;
		this.documento=documentoo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getDocumento() {
		return documento;
	}
	public void setDocumento(String documento) {
		this.documento = documento;
	}
	
	
}
