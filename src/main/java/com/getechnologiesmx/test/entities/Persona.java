package com.getechnologiesmx.test.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="persona")
@Data
public class Persona {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "nombre", nullable = false)
    private String nombre;
	
	@Column(name = "apellido_paterno", nullable = false)
    private String apellidoPaterno;
	
	@Column(name = "apellido_materno", nullable = true)
    private String apellidoMaterno;
	
	@Column(name = "identificacion", nullable = false, unique = true)
    private String identificacion;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "persona")
	@JsonManagedReference
    private List<Factura> facturas;
}
