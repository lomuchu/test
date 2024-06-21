package com.getechnologiesmx.test.entities;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "factura")
@Data
public class Factura {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "fecha", nullable = false)
	private Date fecha;

	@Column(name = "monto", nullable = false)
	private Double monto;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_PERSONA", nullable = false, updatable = false)
	@JsonBackReference
	private Persona persona;
}
