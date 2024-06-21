package com.getechnologiesmx.test.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.getechnologiesmx.test.entities.Persona;

public interface PersonaRepository extends CrudRepository<Persona, Long>{

	Optional<Persona> findByIdentificacion(String identificacion);
	
	@Query( value = "select * from persona where identificacion in (:ids)", nativeQuery = true)
	List<Persona> findAllPersonas(@Param("ids") List<String> identificaciones);
}
