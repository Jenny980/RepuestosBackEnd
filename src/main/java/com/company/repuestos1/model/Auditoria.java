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
@Table(name="Auditoria")
public class Auditoria implements Serializable{
private static final long serialVersionUID = 815428216242371486L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String accion;
	private String usuario;
	private Long idUsuario;
}
