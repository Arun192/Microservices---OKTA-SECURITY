package org.arun.orderservice.controller;

import org.arun.orderservice.model.OrderRequest;
import org.arun.orderservice.model.OrderResponse;
import org.arun.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/order")
@Log4j2
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@PostMapping("/placeOrder")
	public ResponseEntity<Long> placeOrder(@RequestBody OrderRequest orderRequest)
	{
		
		long orderId =orderService.placeOder(orderRequest);
		log.info("Order Id: {} ",orderId);
		
		return new ResponseEntity<Long>(orderId,HttpStatus.OK);
		
	}
	
	@GetMapping("/{orderId}")
	public ResponseEntity<OrderResponse> getOrderDetails(@PathVariable("orderId") long orderId){
		
		OrderResponse orderResponse =orderService.getOrderDetails(orderId);
		
		return new ResponseEntity<OrderResponse>(orderResponse,HttpStatus.OK);
	}

	
}
