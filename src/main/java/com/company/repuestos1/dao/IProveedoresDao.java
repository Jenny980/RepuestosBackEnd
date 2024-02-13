package com.company.repuestos1.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.company.repuestos1.model.Proveedores;

public interface IProveedoresDao  extends CrudRepository<Proveedores, Long>{
	List<Proveedores> findBynombreContainingIgnoreCase(String name);
}
