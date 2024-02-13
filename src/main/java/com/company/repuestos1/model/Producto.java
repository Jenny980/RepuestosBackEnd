package com.company.repuestos1.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="Producto")
public class Producto implements Serializable {

	private static final long serialVersionUID = -5345302164938679307L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	private Long cantidadBodega;
	private String descripcion;
	private String modelo;
	private Long precio;

}
