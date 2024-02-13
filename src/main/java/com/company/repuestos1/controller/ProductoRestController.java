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

import com.company.repuestos1.model.Producto;
import com.company.repuestos1.response.ProductoResponseRest;
import com.company.repuestos1.services.IProductoService;


@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/v3")
public class ProductoRestController {

	@Autowired
	private IProductoService service;
	
	@GetMapping("/productos")
	public ResponseEntity<ProductoResponseRest> searchProveedores(){
		ResponseEntity<ProductoResponseRest> response = service.search();
		return response;
	}
	
	@PostMapping("/productos")
	public ResponseEntity<ProductoResponseRest> save(@RequestBody Producto producto){
		ResponseEntity<ProductoResponseRest> response = service.save(producto);
		return response;
	}
	
	@GetMapping("/productos/{id}")
	public ResponseEntity<ProductoResponseRest> searchById(@PathVariable Long id){
		ResponseEntity<ProductoResponseRest> response = service.searchById(id);
		return response;
	}
	
	@PutMapping("/productos/{id}")
	public ResponseEntity<ProductoResponseRest> update(@RequestBody Producto producto, @PathVariable Long id){
		ResponseEntity<ProductoResponseRest> response = service.update(producto, id);
		return response;
	}
	
	@GetMapping("/productos/filter/{nombre}")
	public ResponseEntity<ProductoResponseRest> searchByName(@PathVariable String nombre){
		ResponseEntity<ProductoResponseRest> response = service.searchByNombre(nombre);
		return response;
	}
}


