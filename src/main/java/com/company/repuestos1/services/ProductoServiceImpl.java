package com.company.repuestos1.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.company.repuestos1.dao.IProductoDao;
import com.company.repuestos1.model.Producto;
import com.company.repuestos1.response.ProductoResponseRest;


@Service
public class ProductoServiceImpl implements IProductoService{

	@Autowired
	private IProductoDao productoDao;

	@Override
	public ResponseEntity<ProductoResponseRest> search() {
		ProductoResponseRest response = new ProductoResponseRest();
		try {
			List<Producto> producto = (List<Producto>) productoDao.findAll();
			response.getProductoResponse().setProducto(producto);
			response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
		} catch (Exception e) {
			response.setMetadata("Respuesta nook", "-1", "Error al consultar");
			e.getStackTrace();
			return new ResponseEntity<ProductoResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return new ResponseEntity<ProductoResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<ProductoResponseRest> save(Producto producto) {
		ProductoResponseRest response = new ProductoResponseRest();
		List<Producto> list = new ArrayList<>();
		try {
			Producto productoSave = productoDao.save(producto);
			if(productoSave != null) {
				list.add(productoSave);
				response.getProductoResponse().setProducto(list);
				response.setMetadata("Respuesta ok", "00", "Producto guardado");
			} else {
				response.setMetadata("Respuesta nook", "-1", "Producto no guardado");
				return new ResponseEntity<ProductoResponseRest>(response, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			response.setMetadata("Respuesta nook", "-1", "Error al guardar producto");
			e.getStackTrace();
			return new ResponseEntity<ProductoResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return new ResponseEntity<ProductoResponseRest>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ProductoResponseRest> searchById(Long id) {
		ProductoResponseRest response = new ProductoResponseRest();
		List<Producto> list = new ArrayList<>();
		try {
			Optional<Producto> usuario = productoDao.findById(id);
			
			if(usuario.isPresent()) {
				list.add(usuario.get());
				response.getProductoResponse().setProducto(list);
				response.setMetadata("Respuesta ok", "00", "Producto encontrado");
			} else {
				response.setMetadata("Respuesta nook", "-1", "Producto no encontrado");
				return new ResponseEntity<ProductoResponseRest>(response, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			response.setMetadata("Respuesta nook", "-1", "Error al consultar por id");
			e.getStackTrace();
			return new ResponseEntity<ProductoResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return new ResponseEntity<ProductoResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<ProductoResponseRest> update(Producto producto, Long id) {
		ProductoResponseRest response = new ProductoResponseRest();
		List<Producto> list = new ArrayList<>();
		try {
			Optional<Producto> productoSearch = productoDao.findById(id);
			if(productoSearch.isPresent()) {
				productoSearch.get().setNombre(producto.getNombre());
				productoSearch.get().setCantidadBodega(producto.getCantidadBodega());
				productoSearch.get().setDescripcion(producto.getDescripcion());
				productoSearch.get().setModelo(producto.getModelo());
				productoSearch.get().setPrecio(producto.getPrecio());
				
				Producto usuarioToUpdate = productoDao.save(productoSearch.get());
				
				if(usuarioToUpdate != null) {
					list.add(usuarioToUpdate);
					response.getProductoResponse().setProducto(list);
					response.setMetadata("Respuesta ok", "00", "Producto actualizado");
				} else {
					response.setMetadata("Respuesta nook", "-1", "Producto no actualizado");
					return new ResponseEntity<ProductoResponseRest>(response, HttpStatus.BAD_REQUEST);
				}
			}else {
				response.setMetadata("Respuesta nook", "-1", "Producto no encontrado");
				return new ResponseEntity<ProductoResponseRest>(response, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			response.setMetadata("Respuesta nook", "-1", "Error al actualizar Producto");
			e.getStackTrace();
			return new ResponseEntity<ProductoResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return new ResponseEntity<ProductoResponseRest>(response, HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<ProductoResponseRest> searchByNombre(String nombre) {
		ProductoResponseRest response = new ProductoResponseRest();
		List<Producto> list = new ArrayList<>();
		List<Producto> listAux = new ArrayList<>();
		try {
			
			listAux = productoDao.findBynombreContainingIgnoreCase(nombre);
			
			if(listAux.size() > 0) {
				
				listAux.stream().forEach((p) -> {
					list.add(p);
				});
				
				response.getProductoResponse().setProducto(listAux);
				response.setMetadata("Respuesta ok", "00", "Producto encontrado");
			} else {
				response.setMetadata("Respuesta nook", "-1", "Producto no encontrado");
				return new ResponseEntity<ProductoResponseRest>(response, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			response.setMetadata("Respuesta nook", "-1", "Error al buscar por nombre");
			e.getStackTrace();
			return new ResponseEntity<ProductoResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return new ResponseEntity<ProductoResponseRest>(response, HttpStatus.OK);
	}

}


