package com.getechnologiesmx.test.controllers;

import java.util.List;

import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.getechnologiesmx.test.entities.Persona;
import com.getechnologiesmx.test.services.DirectorioService;
import com.getechnologiesmx.test.vo.FindVo;
import com.getechnologiesmx.test.vo.PersonaVo;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("directorio")
@Slf4j
public class DirectorioController {

	@Autowired
	private DirectorioService directorioService;
	
	@PostMapping(value = "/")
	public ResponseEntity<?> create(@RequestBody PersonaVo pvo){
		try {
			directorioService.storePersona(pvo);
			return new ResponseEntity<>(HttpStatus.OK);
		}catch(Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> findByIdentifiacion( @NotNull @PathVariable("id") String identificacion){
		
			try {
				Persona p = directorioService.findPersonaByIdentificacion(identificacion);
				return new ResponseEntity<>(p,HttpStatus.OK);
			} catch (NotFoundException e) {
				log.error(e.getMessage());
				return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
			}catch(Exception e) {
				log.error(e.getMessage());
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@NotNull @PathVariable("id") String identificacion){
		
			try {
				directorioService.deletePersonaByIdentificacion(identificacion);
				return new ResponseEntity<>(HttpStatus.OK);
			} catch (NotFoundException e) {
				log.error(e.getMessage());
				return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
			}catch(Exception e) {
				log.error(e.getMessage());
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
	}
	
	@PutMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> findByIdentifiaciones( @RequestBody FindVo pvo){
		
			try {
				List<Persona> personas = directorioService.findPersonas(pvo.getIdentificaciones());
				return new ResponseEntity<>(personas,HttpStatus.OK);
			} catch (NotFoundException e) {
				log.error(e.getMessage());
				return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
			}catch(Exception e) {
				log.error(e.getMessage());
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
	}
}
