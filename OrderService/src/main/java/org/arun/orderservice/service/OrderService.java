package org.arun.orderservice.service;

import org.arun.orderservice.model.OrderRequest;
import org.arun.orderservice.model.OrderResponse;

public interface OrderService {

	long placeOder(OrderRequest orderRequest);

	OrderResponse getOrderDetails(long orderId);
	
	

}
