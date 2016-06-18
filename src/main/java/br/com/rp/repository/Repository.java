package br.com.rp.repository;

import java.util.List;

import javax.validation.Valid;

import br.com.rp.domain.BaseEntity;

public interface Repository<T extends BaseEntity> {

	List<T> getAll();

	T findById(Long id);

	T save(@Valid T object);

	void remove(Long id);

}
