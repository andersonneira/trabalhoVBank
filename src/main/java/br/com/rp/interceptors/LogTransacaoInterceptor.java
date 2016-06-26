/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.rp.interceptors;

import br.com.rp.repository.ConfiguracaoRepository;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.ejb.EJB;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

/**
 *
 * @author anderson
 */
public class LogTransacaoInterceptor {

    @EJB
    private ConfiguracaoRepository repository;

    @AroundInvoke
    public Object logarTodasTransacoes(InvocationContext invocationContext) throws Exception {
        Object proceed = null;
        boolean deuErro = false;
        try {
            if (podeFazerTransacoes()) {
                proceed = invocationContext.proceed();
            } else {
                throw new Exception("Out of the transaction period");
            }
        } catch (Exception ex) {
            deuErro = true;
            throw ex;
        } finally {
            String metodo = invocationContext.getMethod().getName();
            if (metodo.equals("realizarTransferenciaEntreContas")) {
                criarLogTransfereciaMesmoBanco(invocationContext.getParameters(), deuErro);
            }
            if (metodo.equals("realizarTransferenciaOutrosBancos")) {
                criarLogTransfereciaBancosDiferentes(invocationContext.getParameters(), deuErro);
            }
        }
        return proceed;
    }

    private boolean podeFazerTransacoes() {
        Date agora = GregorianCalendar.getInstance().getTime();
        Date abertura = configurarData(repository.getConfiguracao().getHoraAberturaOperacao());
        Date fechamento = configurarData(repository.getConfiguracao().getHoraFechamentoOperacao());
        return (agora.after(abertura) && agora.before(fechamento));
    }

    private void criarLogTransfereciaMesmoBanco(Object[] param, boolean deuErro) {
        try {
            File file = new File(System.getProperty("user.dir") + "/logmesmobanco.txt");
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("contaId  = " + param[0] + " | valor  = " + param[1] + " | contadestino = " + param[2] + "-" + param[3]
                    + (deuErro ? " | A operação falhou\n" : " | A operação deu certo \n"));
            bw.flush();
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void criarLogTransfereciaBancosDiferentes(Object[] param, boolean deuErro) {
        try {
            File file = new File(System.getProperty("user.dir") + "/logbancosdifetentes.txt");
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("contaId  = " + param[0] + " | valor  = " + param[1] + " | agenciadestino" + param[2] + "-" + param[3]
                    + "| contadestino = " + param[4] + "-" + param[5]
                    + (deuErro ? "| A operação falhou\n" : " |A operação deu certo \n"));
            bw.flush();
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Date configurarData(Date date) {
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.HOUR_OF_DAY, date.getHours());
        cal.set(Calendar.MINUTE, date.getMinutes());
        cal.set(Calendar.SECOND, date.getSeconds());
        cal.set(Calendar.MILLISECOND, 00);
        return cal.getTime();
    }
}
