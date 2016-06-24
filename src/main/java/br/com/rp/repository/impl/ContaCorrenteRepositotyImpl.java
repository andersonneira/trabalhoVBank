package br.com.rp.repository.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.rp.domain.ContaCorrente;
import br.com.rp.repository.ContaCorrenteRepository;

@Stateless
public class ContaCorrenteRepositotyImpl extends AbstractRepositoryImpl<ContaCorrente>
		implements ContaCorrenteRepository {

	public ContaCorrenteRepositotyImpl() {
		super(ContaCorrente.class);
	}

	public ContaCorrente save(ContaCorrente object) {
		return super.save(object);
	}

	@Override
	public ContaCorrente findByContaByDigitoVerificador(String conta, String digitoVerificador) {
		try {
			Query qry = em.createQuery("from ContaCorrente cc where cc.numero = :conta and cc.digitoVerificador = :digitoVerificador", ContaCorrente.class);
			qry.setParameter("conta", conta);
			qry.setParameter("digitoVerificador", digitoVerificador);
			return (ContaCorrente) qry.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ContaCorrente> findByClienteDocumento(String clienteDocumento) {
		Query qry = em.createQuery("from ContaCorrente cc where cc.cliente.documento = :documento", ContaCorrente.class);
		qry.setParameter("documento", clienteDocumento);
		return qry.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ContaCorrente> findByClienteId(Long clienteId) {
		Query qry = em.createQuery("from ContaCorrente cc where cc.cliente.id = :clienteId", ContaCorrente.class);
		qry.setParameter("clienteId", clienteId);
		return qry.getResultList();
	}
}
