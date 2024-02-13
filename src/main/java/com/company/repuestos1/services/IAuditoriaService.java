package com.company.repuestos1.services;

import org.springframework.http.ResponseEntity;

import com.company.repuestos1.model.Auditoria;
import com.company.repuestos1.response.AuditoriaResponseRest;

public interface IAuditoriaService {
	public ResponseEntity<AuditoriaResponseRest> search();
	public ResponseEntity<AuditoriaResponseRest> save(Auditoria auditoria);
	public ResponseEntity<AuditoriaResponseRest> searchById(Long id);
	public ResponseEntity<AuditoriaResponseRest> update(Auditoria auditoria, Long id);
	public ResponseEntity<AuditoriaResponseRest> searchByIdUsuario(Long id);
	public ResponseEntity<AuditoriaResponseRest> searchByUsuario(String usuario);
}
