package com.company.repuestos1.services;

import org.springframework.http.ResponseEntity;

import com.company.repuestos1.model.Producto;
import com.company.repuestos1.response.ProductoResponseRest;


public interface IProductoService {
	public ResponseEntity<ProductoResponseRest> search();
	public ResponseEntity<ProductoResponseRest> save(Producto producto);
	//public ResponseEntity<ProveedoresResponseRest> searchByEmail(String email);
	public ResponseEntity<ProductoResponseRest> searchById(Long id);
	public ResponseEntity<ProductoResponseRest> update(Producto producto, Long id);
	public ResponseEntity<ProductoResponseRest> searchByNombre(String nombre);
}
