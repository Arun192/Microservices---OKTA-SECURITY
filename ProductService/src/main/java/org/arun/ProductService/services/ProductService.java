package org.arun.productservice.services;

import org.arun.productservice.model.ProductRequest;
import org.arun.productservice.model.ProductResponse;

public interface ProductService {

	long addProduct(ProductRequest productRequest);

	ProductResponse getProductById(long productId);

	void reduceQuantity(long productId, long quantity);

}
