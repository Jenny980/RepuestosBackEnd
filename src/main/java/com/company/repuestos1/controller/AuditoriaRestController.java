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

import com.company.repuestos1.model.Auditoria;
import com.company.repuestos1.response.AuditoriaResponseRest;
import com.company.repuestos1.services.IAuditoriaService;



@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/v3")
public class AuditoriaRestController {

	@Autowired
	private IAuditoriaService service;
	
	@GetMapping("/auditorias")
	public ResponseEntity<AuditoriaResponseRest> searchProveedores(){
		ResponseEntity<AuditoriaResponseRest> response = service.search();
		return response;
	}
	
	@PostMapping("/auditorias")
	public ResponseEntity<AuditoriaResponseRest> save(@RequestBody Auditoria auditoria){
		ResponseEntity<AuditoriaResponseRest> response = service.save(auditoria);
		return response;
	}
	
	@GetMapping("/auditorias/{id}")
	public ResponseEntity<AuditoriaResponseRest> searchById(@PathVariable Long id){
		ResponseEntity<AuditoriaResponseRest> response = service.searchById(id);
		return response;
	}
	
	@PutMapping("/auditorias/{id}")
	public ResponseEntity<AuditoriaResponseRest> update(@RequestBody Auditoria auditoria, @PathVariable Long id){
		ResponseEntity<AuditoriaResponseRest> response = service.update(auditoria, id);
		return response;
	}
	
	@GetMapping("/auditorias/filter/{idUsuario}")
	public ResponseEntity<AuditoriaResponseRest> searchByIdUsuario(@PathVariable Long idUsuario){
		ResponseEntity<AuditoriaResponseRest> response = service.searchByIdUsuario(idUsuario);
		return response;
	}
	
	@GetMapping("/auditorias/filter1/{usuario}")
	public ResponseEntity<AuditoriaResponseRest> searchByName(@PathVariable String usuario){
		ResponseEntity<AuditoriaResponseRest> response = service.searchByUsuario(usuario);
		return response;
	}
}


