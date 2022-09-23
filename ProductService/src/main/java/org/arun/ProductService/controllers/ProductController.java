package org.arun.ProductService.controllers;

import org.arun.ProductService.model.ProductRequest;
import org.arun.ProductService.model.ProductResponse;
import org.arun.ProductService.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;

	@PostMapping
	public ResponseEntity<Long> addProduct(@RequestBody ProductRequest productRequest) {
		long productId = productService.addProduct(productRequest);
		return new ResponseEntity<>(productId, HttpStatus.CREATED);
	}

	@GetMapping("/{Id}")
	public ResponseEntity<ProductResponse> getProductById(@PathVariable("Id") long productId) {
		ProductResponse productResponse = productService.getProductById(productId);
		return new ResponseEntity<ProductResponse>(productResponse, HttpStatus.OK);

	}

	@PutMapping("/reduceQuantity/{Id}")
	public ResponseEntity<Void> reduceQuantity(@PathVariable("Id") long productId, @RequestParam long quantity) {
		
		productService.reduceQuantity(productId , quantity);
		return new ResponseEntity<Void>(HttpStatus.OK);

	}
}
