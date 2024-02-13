package com.company.repuestos1.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.company.repuestos1.model.Usuario;


public interface IUsuarioDao  extends CrudRepository<Usuario, Long>{
	List<Usuario> findByemailContainingIgnoreCase(String email);
}
