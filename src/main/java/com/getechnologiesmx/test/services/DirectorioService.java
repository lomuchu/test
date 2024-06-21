package com.getechnologiesmx.test.services;

import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import com.getechnologiesmx.test.entities.Persona;
import com.getechnologiesmx.test.vo.PersonaVo;

public interface DirectorioService {
	
	Persona       findPersonaByIdentificacion(String identificacion) throws NotFoundException;
	List<Persona> findPersonas(List<String> ids) throws NotFoundException;
	void deletePersonaByIdentificacion(String identificacion) throws NotFoundException;
	void storePersona(PersonaVo persona);
}
