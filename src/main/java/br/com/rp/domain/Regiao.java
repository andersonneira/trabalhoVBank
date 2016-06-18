/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.rp.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author anderson
 */
@Entity
@Table(name = "regiao")
public class Regiao extends BaseEntity {

    @Column(name = "nome", length = 100, nullable = false)
    @Size(min = 3, max = 100)
    private String nome;
    //@Cep
    private String cepInicial;
    //@Cep
    private String cepFinal;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCepInicial() {
        return cepInicial;
    }

    public void setCepInicial(String cepInicial) {
        this.cepInicial = cepInicial;
    }

    public String getCepFinal() {
        return cepFinal;
    }

    public void setCepFinal(String cepFinal) {
        this.cepFinal = cepFinal;
    }

}
