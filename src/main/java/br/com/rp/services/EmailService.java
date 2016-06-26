/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.rp.services;

import java.nio.charset.StandardCharsets;

import javax.ejb.Stateless;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

/**
 *
 * @author anderson
 */
@Stateless
public class EmailService {

    private static final String ENDERECO_EMAIL = "posjava.unicesumar@gmail.com";
    private static final String SENHA = "posjava258@";

    public static void enviarEmailConfirmacaoConta() {
        new Thread() {
            public void run() {
                try {
                    HtmlEmail email = configurarEmailPadrao();
                    email.addTo(""); // colocar o email da pessoa
                } catch (EmailException ex) {

                }
            }
        }.start();
    }
    
    public static void enviarEmailRejeicao(String mensagem, String emaill) {
        new Thread() {
            public void run() {
                try {
                    HtmlEmail email = configurarEmailPadrao();
                    email.setSubject("Solicitação de proposta Vbank Rejeitada");
                    email.addTo(emaill);
                    email.setMsg(mensagem);
                    email.send();
                } catch (EmailException ex) {

                }
            }
        }.start();
    }
    public static void enviarEmailAprovacao(String mensagem, String emaill) {
        new Thread() {
            public void run() {
                try {
                    HtmlEmail email = configurarEmailPadrao();
                    email.setSubject("Solicitação de proposta Vbank aprovada");
                    email.addTo(emaill);
                    email.setMsg("Altere sua senha no primeiro acesso!!! A senha padrão de aesso de sua conta é a junção do seu cpf mais seu CEP com os caracteres especiais /n" + mensagem);
                    email.send();
                } catch (EmailException ex) {

                }
            }
        }.start();
    }

    private static HtmlEmail configurarEmailPadrao() throws EmailException {
        HtmlEmail email = new HtmlEmail();
        email.setHostName("smtp.gmail.com");
        email.setSmtpPort(465);
        email.setAuthentication(ENDERECO_EMAIL, SENHA);
        email.setSSLOnConnect(true);
        email.setCharset(StandardCharsets.UTF_8.toString());
        email.setFrom(ENDERECO_EMAIL, "Sistema VBANK");
        return email;
    }
}
