package com.getechnologiesmx.test.services.impl;

import java.util.List;

import org.hibernate.annotations.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.getechnologiesmx.test.entities.Persona;
import com.getechnologiesmx.test.repositories.PersonaRepository;
import com.getechnologiesmx.test.services.DirectorioService;
import com.getechnologiesmx.test.vo.PersonaVo;

@Service
public class DirectorioServiceImpl implements DirectorioService{

	@Autowired
	private PersonaRepository repository;
	
	@Override
	public Persona findPersonaByIdentificacion(String identificacion) throws NotFoundException {
		Persona p = repository.findByIdentificacion(identificacion).orElseThrow(() -> new NotFoundException());
		return p;
	}

	@Override
	public List<Persona> findPersonas(List<String> ids) throws NotFoundException {
		List<Persona> personas = repository.findAllPersonas(ids);
		if(personas == null  || personas.isEmpty()) {
			throw new NotFoundException();
		}
		return personas;
	}

	@Override
	public void deletePersonaByIdentificacion(String identificacion) throws NotFoundException {
		Persona p = repository.findByIdentificacion(identificacion).orElseThrow(() -> new NotFoundException());
		repository.delete(p);
	}

	@Override
	public void storePersona(PersonaVo pvo) {
		Persona p = new Persona();
		p.setApellidoMaterno(pvo.getApellidoMaterno());
		p.setApellidoPaterno(pvo.getApellidoPaterno());
		p.setNombre(pvo.getNombre());
		p.setIdentificacion(pvo.getIdentificacion());
		
		repository.save(p);
	}

}
