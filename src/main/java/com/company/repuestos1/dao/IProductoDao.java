package com.company.repuestos1.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.company.repuestos1.model.Producto;

public interface IProductoDao extends CrudRepository<Producto, Long>{
	List<Producto> findBynombreContainingIgnoreCase(String name);
}
