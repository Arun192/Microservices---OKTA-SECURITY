package org.arun.ProductService.services;

import org.arun.ProductService.model.ProductRequest;
import org.arun.ProductService.model.ProductResponse;

public interface ProductService {

	long addProduct(ProductRequest productRequest);

	ProductResponse getProductById(long productId);

	void reduceQuantity(long productId, long quantity);

}