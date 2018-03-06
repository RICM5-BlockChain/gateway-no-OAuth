package io.github.jhipster.application.web.rest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.annotation.security.RolesAllowed;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.github.jhipster.application.security.AuthoritiesConstants;

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
		
		try {
		//String url = "http://89.86.39.88:1880/UGA/get?transaction="+id;";
		
		String url = "http://192.168.43.206:1880/UGA/get?transaction="+Transaction;
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		
		// optional default is GET
		con.setRequestMethod("GET");

		//add request header
		con.setRequestProperty("User-Agent", "Mozilla/5.0");

				
		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		new InputStreamReader(con.getInputStream()));
		
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		//print result
		System.out.println(response.toString());
		String digest = response.toString();
		d.setDigest(digest);
		HttpHeaders httpHeaders = new HttpHeaders();
		return new ResponseEntity<>(d, httpHeaders, HttpStatus.OK);
		
		
		}
		catch(Exception e) {
			e.printStackTrace();
			HttpHeaders httpHeaders = new HttpHeaders();
			return new ResponseEntity<>(d, httpHeaders, HttpStatus.NOT_FOUND);
		}
		
		
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
