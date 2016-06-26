package br.com.rp.repository.impl;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import br.com.rp.domain.Cliente;
import br.com.rp.repository.ClienteRepository;

import javax.persistence.Query;

@Stateless
public class ClienteRepositoryImpl extends AbstractRepositoryImpl<Cliente>
		implements ClienteRepository {

	public ClienteRepositoryImpl() {
		super(Cliente.class);
	}

	/**
	 * Este metodo recebe uma string de documento e verifica se este ja está
	 * cadastrado, se estiver o metodo retorna true, e não estiver o metodo
	 * retorna false.
	 * 
	 * @param documento
	 * @return true/false.
	 */
	@Override
	public boolean verificaSeJaExisteEsteDocumentoInserido(String documento) {
		Query q = em
				.createQuery(" from Cliente obj where obj.documento = :documento ");
		q.setParameter("documento", documento);
		return !q.getResultList().isEmpty();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Cliente findById(Long id) {
		return super.findById(id);
	}
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Cliente save(Cliente cliente) {
		return super.save(cliente);
	}
}
