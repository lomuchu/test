package com.getechnologiesmx.test.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.getechnologiesmx.test.entities.Factura;

public interface FacturaRepository extends CrudRepository<Factura, Long>{

	@Query(value = "select * from factura where id_persona = :id", nativeQuery = true)
	List<Factura> finByPersonaIdentifacion(@Param("id") Long identificacion);
}
