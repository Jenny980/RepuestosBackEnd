package com.company.repuestos1.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="Venta")
public class Venta implements Serializable{
	
	LocalDate localDate = LocalDate.now();
	/**
	 * 
	 */
	private static final long serialVersionUID = 63393709412423859L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private LocalDate fecha;
	@ManyToMany
	private Set<Producto> productos =new HashSet<>();
}
