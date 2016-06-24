package br.com.rp.repository;

import br.com.rp.domain.ContaCorrente;

import java.util.List;

import javax.ejb.Local;


@Local
public interface ContaCorrenteRepository extends Repository<ContaCorrente> {

	public ContaCorrente findByContaByDigitoVerificador(String conta, String digitoVerificador);
	public List<ContaCorrente> findByClienteDocumento(String clienteDocumento);
	public List<ContaCorrente> findByClienteId(Long clienteId);
	
}
