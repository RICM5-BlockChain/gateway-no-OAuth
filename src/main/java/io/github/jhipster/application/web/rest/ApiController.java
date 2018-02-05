package io.github.jhipster.application.web.rest;

import javax.annotation.security.RolesAllowed;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.github.jhipster.application.security.AuthoritiesConstants;
import io.github.jhipster.application.security.jwt.JWTConfigurer;

@RestController
@RequestMapping("/api")
@RolesAllowed(AuthoritiesConstants.ANONYMOUS)
public class ApiController {
	
	@RolesAllowed(AuthoritiesConstants.ANONYMOUS)
	@GetMapping("getDigest")
	public ResponseEntity<Digest> getDigest(int Transaction){
		Digest d = new Digest("");
		
		/*
		 * Acces à la blockChain 
		 */
		String digest = "digestcoucou "+Transaction;
		d.setDigest(digest);
		HttpHeaders httpHeaders = new HttpHeaders();
		return new ResponseEntity<>(d, httpHeaders, HttpStatus.OK);
	}
	
	
	
	
	static class Digest{
		private String digest; 
		
		Digest(String digest){
			this.digest=digest;
		}
		
		 @JsonProperty("digest")
		 String getDigest() {
			 return digest;
		 }
		 
		 void setDigest(String digest) {
			 this.digest=digest;
		 }
	}

}
