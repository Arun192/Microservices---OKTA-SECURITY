package org.arun.orderservice.service;

import static org.mockito.ArgumentMatchers.refEq;

import java.util.ArrayList;
import java.util.List;

import org.springframework.cloud.client.DefaultServiceInstance;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;

import reactor.core.publisher.Flux;

public class TestServiceInstanceListSupplier implements ServiceInstanceListSupplier{

	@Override
	public Flux<List<ServiceInstance>> get() {
		
		List<ServiceInstance> result = new ArrayList<>();
		
		result.add(new DefaultServiceInstance(
				"PAYMENT-SERVICE",
				"PAYMENT-SERVICE",
				"localhost",
				9191,
				false
				
		));
		

		result.add(new DefaultServiceInstance(
				"PRODUCT-SERVICE",
				"PRODUCT-SERVICE",
				"localhost",
				9191,
				false
				
		));
		
		
		return Flux.just(result);
	}

	@Override
	public String getServiceId() {
		// TODO Auto-generated method stub
		return null;
	}

}
