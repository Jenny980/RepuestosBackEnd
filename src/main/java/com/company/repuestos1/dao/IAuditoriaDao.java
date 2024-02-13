package com.company.repuestos1.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.company.repuestos1.model.Auditoria;

public interface IAuditoriaDao extends CrudRepository<Auditoria, Long>{
	List<Auditoria> findAllByidUsuario(Long idUsuario);
	List<Auditoria> findByusuarioContainingIgnoreCase(String usuario);
	
}

