package com.company.repuestos1.services;

import org.springframework.http.ResponseEntity;

import com.company.repuestos1.model.Proveedores;
import com.company.repuestos1.response.ProveedoresResponseRest;

public interface IProveedorService {
	public ResponseEntity<ProveedoresResponseRest> search();
	public ResponseEntity<ProveedoresResponseRest> save(Proveedores Proveedor);
	//public ResponseEntity<ProveedoresResponseRest> searchByEmail(String email);
	public ResponseEntity<ProveedoresResponseRest> searchById(Long id);
	public ResponseEntity<ProveedoresResponseRest> update(Proveedores Proveedor, Long id);

	public ResponseEntity<ProveedoresResponseRest> searchByNombre(String nombre);
}
