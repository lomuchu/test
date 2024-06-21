package com.getechnologiesmx.test.controllers;

import java.util.List;

import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.getechnologiesmx.test.entities.Factura;
import com.getechnologiesmx.test.services.FacturaService;
import com.getechnologiesmx.test.vo.FacturaVo;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("factura")
@Slf4j
public class FacturaController {
	
	@Autowired
	private FacturaService service;

	@PostMapping(value = "/")
	public ResponseEntity<?> create(@RequestBody FacturaVo pvo){
		try {
			service.storeFactura(pvo);
			return new ResponseEntity<>(HttpStatus.OK);
		}catch(Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> findByIdentifiacion( @NotNull @PathVariable("id") String identificacion){
		
			try {
				List<Factura> p = service.finFacturasByPersona(identificacion);
				return new ResponseEntity<>(p,HttpStatus.OK);
			} catch (NotFoundException e) {
				log.error(e.getMessage());
				return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
			}catch(Exception e) {
				log.error(e.getMessage());
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
	}
}
