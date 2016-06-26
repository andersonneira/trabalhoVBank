package br.com.rp.repository.impl;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import br.com.rp.domain.Configuracao;
import br.com.rp.repository.ConfiguracaoRepository;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.persistence.Query;

@Stateless
public class ConfiguracaoRepositoryImpl extends
        AbstractRepositoryImpl<Configuracao> implements ConfiguracaoRepository {

    public ConfiguracaoRepositoryImpl() {
        super(Configuracao.class);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public Configuracao save(Configuracao object) {
        return super.save(object);
    }

    @Override
    public Configuracao getConfiguracao() {
        Query q = em.createQuery("from Configuracao obj");
        List resultList = q.getResultList();
        if (!resultList.isEmpty()) {
            return (Configuracao) resultList.get(0);
        }
        return criarConfiguracaoPadrao();
    }

    private Configuracao criarConfiguracaoPadrao() {
        Configuracao c = new Configuracao();
        c.setHoraAberturaOperacao(getAbertura());
        c.setHoraFechamentoOperacao(getFechamento());
        c.setLimitePadrao(new BigDecimal("1000"));
        return save(c);
    }

    private static Date getAbertura() {
        Calendar date = new GregorianCalendar();
        date.set(Calendar.HOUR_OF_DAY, 6);
        date.set(Calendar.MINUTE, 0);
        date.set(Calendar.SECOND, 0);
        date.set(Calendar.MILLISECOND, 0);
        return date.getTime();
    }

    private static Date getFechamento() {
        Calendar date = new GregorianCalendar();
        date.set(Calendar.HOUR_OF_DAY, 21);
        date.set(Calendar.MINUTE, 30);
        date.set(Calendar.SECOND, 00);
        date.set(Calendar.MILLISECOND, 00);
        return date.getTime();
    }
}
