package br.com.rp.repository;

import javax.ejb.Local;

import br.com.rp.domain.Cliente;

@Local
public interface ClienteRepository extends Repository<Cliente> {

    abstract boolean verificaSeJaExisteEsteDocumentoInserido(String documento);
    
}
