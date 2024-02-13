package com.company.repuestos1.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
@Table(name="Usuario")
public class Usuario implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 815428216242371486L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String nombre;
	private String apellido;
	private Long rol;/**
	 * administrador = 1 , vendedor = 2
	 */
	@Column(unique = true)
	private long cc;
	@Column(unique = true)
	private String email;
	@Size(min = 8, max = 15)
	private String contrasena;

}