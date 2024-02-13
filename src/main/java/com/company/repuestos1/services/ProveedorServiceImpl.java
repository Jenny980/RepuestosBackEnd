package com.company.repuestos1.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.company.repuestos1.dao.IProveedoresDao;
import com.company.repuestos1.model.Proveedores;
import com.company.repuestos1.response.ProveedoresResponseRest;

@Service
public class ProveedorServiceImpl implements IProveedorService{

	@Autowired
	private IProveedoresDao proveedorDao;

	@Override
	public ResponseEntity<ProveedoresResponseRest> search() {
		ProveedoresResponseRest response = new ProveedoresResponseRest();
		try {
			List<Proveedores> proveedor = (List<Proveedores>) proveedorDao.findAll();
			response.getProveedoresResponse().setProveedores(proveedor);
			response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
		} catch (Exception e) {
			response.setMetadata("Respuesta nook", "-1", "Error al consultar");
			e.getStackTrace();
			return new ResponseEntity<ProveedoresResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return new ResponseEntity<ProveedoresResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<ProveedoresResponseRest> save(Proveedores proveedor) {
		ProveedoresResponseRest response = new ProveedoresResponseRest();
		List<Proveedores> list = new ArrayList<>();
		try {
			Proveedores proveedorSave = proveedorDao.save(proveedor);
			if(proveedorSave != null) {
				list.add(proveedorSave);
				response.getProveedoresResponse().setProveedores(list);
				response.setMetadata("Respuesta ok", "00", "Proveedor guardado");
			} else {
				response.setMetadata("Respuesta nook", "-1", "Proveedor no guardado");
				return new ResponseEntity<ProveedoresResponseRest>(response, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			response.setMetadata("Respuesta nook", "-1", "Error al guardar Proveedor");
			e.getStackTrace();
			return new ResponseEntity<ProveedoresResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return new ResponseEntity<ProveedoresResponseRest>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ProveedoresResponseRest> searchById(Long id) {
		ProveedoresResponseRest response = new ProveedoresResponseRest();
		List<Proveedores> list = new ArrayList<>();
		try {
			Optional<Proveedores> usuario = proveedorDao.findById(id);
			
			if(usuario.isPresent()) {
				list.add(usuario.get());
				response.getProveedoresResponse().setProveedores(list);
				response.setMetadata("Respuesta ok", "00", "Proveedor encontrado");
			} else {
				response.setMetadata("Respuesta nook", "-1", "Proveedor no encontrado");
				return new ResponseEntity<ProveedoresResponseRest>(response, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			response.setMetadata("Respuesta nook", "-1", "Error al consultar por id");
			e.getStackTrace();
			return new ResponseEntity<ProveedoresResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return new ResponseEntity<ProveedoresResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<ProveedoresResponseRest> update(Proveedores proveedor, Long id) {
		ProveedoresResponseRest response = new ProveedoresResponseRest();
		List<Proveedores> list = new ArrayList<>();
		try {
			Optional<Proveedores> proveedorSearch = proveedorDao.findById(id);
			if(proveedorSearch.isPresent()) {
				proveedorSearch.get().setNombre(proveedor.getNombre());
				proveedorSearch.get().setDireccion(proveedor.getDireccion());
				proveedorSearch.get().setCiudad(proveedor.getCiudad());
				proveedorSearch.get().setTelefono(proveedor.getTelefono());
				
				Proveedores usuarioToUpdate = proveedorDao.save(proveedorSearch.get());
				
				if(usuarioToUpdate != null) {
					list.add(usuarioToUpdate);
					response.getProveedoresResponse().setProveedores(list);
					response.setMetadata("Respuesta ok", "00", "Usuario actualizado");
				} else {
					response.setMetadata("Respuesta nook", "-1", "Usuario no actualizado");
					return new ResponseEntity<ProveedoresResponseRest>(response, HttpStatus.BAD_REQUEST);
				}
			}else {
				response.setMetadata("Respuesta nook", "-1", "Usuario no encontrado");
				return new ResponseEntity<ProveedoresResponseRest>(response, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			response.setMetadata("Respuesta nook", "-1", "Error al actualizar usuario");
			e.getStackTrace();
			return new ResponseEntity<ProveedoresResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return new ResponseEntity<ProveedoresResponseRest>(response, HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<ProveedoresResponseRest> searchByNombre(String nombre) {
		ProveedoresResponseRest response = new ProveedoresResponseRest();
		List<Proveedores> list = new ArrayList<>();
		List<Proveedores> listAux = new ArrayList<>();
		try {
			
			listAux = proveedorDao.findBynombreContainingIgnoreCase(nombre);
			
			if(listAux.size() > 0) {
				
				listAux.stream().forEach((p) -> {
					list.add(p);
				});
				
				response.getProveedoresResponse().setProveedores(listAux);
				response.setMetadata("Respuesta ok", "00", "Cliente encontrado");
			} else {
				response.setMetadata("Respuesta nook", "-1", "Cliente no encontrado");
				return new ResponseEntity<ProveedoresResponseRest>(response, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			response.setMetadata("Respuesta nook", "-1", "Error al buscar por nombre");
			e.getStackTrace();
			return new ResponseEntity<ProveedoresResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return new ResponseEntity<ProveedoresResponseRest>(response, HttpStatus.OK);
	}

}

