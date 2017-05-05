package com.imta.microservices.eshop.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@RequestMapping(value = "/", method = { RequestMethod.GET })
	public ResponseEntity<Map<String, Object>> getBills() {
		Map<String, Object> json = new HashMap<String, Object>();

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=UTF-8");
		return (new ResponseEntity<Map<String, Object>>(json, headers, HttpStatus.OK));
	}
	
	  @RequestMapping("/health")
	    public ResponseEntity<Map<String, Object>> healthCheck(){
	        Map<String, Object> message = new HashMap<String, Object>();

	        message.put("location", "/health");
	        message.put("summary", "Check ok !");
	        message.put("code", 200);

	        Map<String, Object> json = new HashMap<String, Object>();
	        json.put("success", true);
	        json.put("message", message);

	        HttpHeaders headers = new HttpHeaders();
	        headers.add("Content-Type", "application/json; charset=UTF-8");
	        return (new ResponseEntity<Map<String, Object>>(json, headers, HttpStatus.OK));
	    }
}