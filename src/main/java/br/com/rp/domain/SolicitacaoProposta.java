package br.com.rp.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import br.com.rp.anotations.Cep;
import br.com.rp.anotations.Documento;
import br.com.rp.anotations.Email;

@Entity
@Table(name = "solicitacao_proposta")
public class SolicitacaoProposta extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Column(name = "nome", length = 100, nullable = false)
    @Size(min = 3, max = 100)
    private String nome;
    @Column(name = "email", nullable = false)
    @Email
    private String email;

    @Column(name = "motivo_rejeicao")
    private String motivoRejeicao;
    @Cep
    private String cep;

    @Column(name = "documento", nullable = false, unique = true)
    @Documento
    private String documento;

    public SolicitacaoProposta() {
    }

    public SolicitacaoProposta(String nome, String email, String cep) {
        this.nome = nome;
        this.email = email;
        this.cep = cep;
    }

    public SolicitacaoProposta(String nome, String email, String cep, String documento) {
        this.nome = nome;
        this.email = email;
        this.cep = cep;
        this.documento = documento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotivoRejeicao() {
        return motivoRejeicao;
    }

    public void setMotivoRejeicao(String motivoRejeicao) {
        this.motivoRejeicao = motivoRejeicao;
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
}
