package com.company.repuestos1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.repuestos1.model.Proveedores;
import com.company.repuestos1.response.ProveedoresResponseRest;
import com.company.repuestos1.services.IProveedorService;


@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/v3")
public class ProveedorRestController {
	@Autowired
	private IProveedorService service;
	
	@GetMapping("/proveedores")
	public ResponseEntity<ProveedoresResponseRest> searchProveedores(){
		ResponseEntity<ProveedoresResponseRest> response = service.search();
		return response;
	}
	
	@PostMapping("/proveedores")
	public ResponseEntity<ProveedoresResponseRest> save(@RequestBody Proveedores proveedores){
		ResponseEntity<ProveedoresResponseRest> response = service.save(proveedores);
		return response;
	}
	
	@GetMapping("/proveedores/{id}")
	public ResponseEntity<ProveedoresResponseRest> searchById(@PathVariable Long id){
		ResponseEntity<ProveedoresResponseRest> response = service.searchById(id);
		return response;
	}
	
	@PutMapping("/proveedores/{id}")
	public ResponseEntity<ProveedoresResponseRest> update(@RequestBody Proveedores proveedor, @PathVariable Long id){
		ResponseEntity<ProveedoresResponseRest> response = service.update(proveedor, id);
		return response;
	}
	
	@GetMapping("/proveedores/filter/{nombre}")
	public ResponseEntity<ProveedoresResponseRest> searchByName(@PathVariable String nombre){
		ResponseEntity<ProveedoresResponseRest> response = service.searchByNombre(nombre);
		return response;
	}
}

