package com.imta.microservices.eshop.hello.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	private AtomicInteger cpt = new AtomicInteger();


	@RequestMapping(value = "/hello", method = { RequestMethod.GET })
	public ResponseEntity<Map<String, Object>> getBills() {
		Map<String, Object> json = new HashMap<>();

		System.out.println("Nb : " + cpt.incrementAndGet());

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=UTF-8");
		return (new ResponseEntity<>(json, headers, HttpStatus.OK));
	}

	@RequestMapping("/health")
	public ResponseEntity<Map<String, Object>> healthCheck(){
		Map<String, Object> message = new HashMap<>();

		message.put("location", "/health");
		message.put("summary", "Check ok !");
		message.put("code", 200);

		Map<String, Object> json = new HashMap<>();
		json.put("success", true);
		json.put("message", message);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=UTF-8");
		return (new ResponseEntity<>(json, headers, HttpStatus.OK));
	}
}