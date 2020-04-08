package com.noblens.rentalui;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
 


@FeignClient(name = "bien", url = "localhost:6001")
public interface RentalProxy {
	/*
	 * 
	 * OBJECT BIEN
	 * 
	 */
	@GetMapping(value = "/bienlist")
	List<Bien> bienlist();
	
	@PostMapping("/creerbien")
	Bien creerbien(@RequestBody Bien bien);
	
	@GetMapping(value = "/biendetaille/{id}")
	Bien biendetaille(@PathVariable("id") int id);

}
