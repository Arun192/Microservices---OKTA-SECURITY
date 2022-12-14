 package org.arun.orderservice.service;

import java.time.Instant;

import org.arun.orderservice.entity.Order;
import org.arun.orderservice.exception.CustomException;
import org.arun.orderservice.external.client.PaymentService;
import org.arun.orderservice.external.client.ProductService;
import org.arun.orderservice.external.request.PaymentRequest;
import org.arun.orderservice.external.response.PaymentResponse;
import org.arun.orderservice.external.response.ProductResponse;
import org.arun.orderservice.model.OrderRequest;
import org.arun.orderservice.model.OrderResponse;
import org.arun.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.extern.log4j.Log4j2;


@Service
@Log4j2
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private ProductService productService;

	@Autowired
	private PaymentService paymentService;

	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public long placeOder(OrderRequest orderRequest) {

		// Order Entity -> Save the Data with Status Order Created
		// Product Service to block Product (Reduce the Quantity)
		// Payment Service -> Payments ->Success ->COMPLETE ,else Cancel

		log.info("Placing Order Request: {} ", orderRequest);

		productService.reduceQuantity(orderRequest.getProductId(), orderRequest.getQuantity());

		log.info("Creating Order with Status CREATED !");

		Order order = Order.builder().amount(orderRequest.getTotalAmount()).orderStatus("CREATED..!")
				.productId(orderRequest.getProductId()).orderDate(Instant.now()).quantity(orderRequest.getQuantity())
				.build();
		order = orderRepository.save(order);

		log.info("Calling Payment Service To Complete the Payment");

		PaymentRequest paymentRequest = PaymentRequest.builder().orderId(order.getId())
				.paymentMode(orderRequest.getPaymentMode()).amount(orderRequest.getTotalAmount()).build();

		String orderStatus = null;

		try {

			paymentService.doPayment(paymentRequest);
			log.info("Payment done Successfully . Changing the order status to placed");
			orderStatus = "PLACED...!";
		} catch (Exception e) {

			log.error("Error Occured in Payment Changing order status to PAYMENT_FAILED");

			orderStatus = "PAYMENT_FAILED";
		}

		order.setOrderStatus(orderStatus);
		orderRepository.save(order);

		log.info(" Order Places Successfully with Order Id: {} ", order.getId());

		return order.getId();
	}

	@Override
	public OrderResponse getOrderDetails(long orderId) {
		log.info("Get Order details for Order Id : {}", orderId);

		Order order = orderRepository.findById(orderId).orElseThrow(
				() -> new CustomException("Order not found for the order Id : " + orderId, "NOT_FOUND", 404));
		
		log.info("Invoking Product Service to fetch the product for id: {} ",order.getProductId());
		
		ProductResponse productResponse = restTemplate.getForObject("http://PRODUCT-SERVICE/product/"+order.getProductId(), ProductResponse.class);
		
		
		log.info("Getting Payment information from Payment Service ..!");
		
		PaymentResponse paymentResponse =restTemplate.getForObject("http://PAYMENT-SERVICE/payment/order/"+order.getId(), PaymentResponse.class);
		
		OrderResponse.ProductDetails productDetails=OrderResponse.ProductDetails.builder()
				
				.productName(productResponse.getProductName())
				.productId(productResponse.getProductId())
				.price(productResponse.getPrice())
				.quantity(productResponse.getQuantity())
				.build();
		
		
		OrderResponse.PaymentDetails paymentDetails=OrderResponse.PaymentDetails.builder()
				.paymentId(paymentResponse.getPaymentId())
				.paymentStatus(paymentResponse.getStatus())
				.paymentDate(paymentResponse.getPaymentDate())
				.paymentMode(paymentResponse.getPaymentMode())
				 .build();
		
		
		
		OrderResponse orderResponse =OrderResponse.builder()
						.orderId(order.getId())
						.orderSatus(order.getOrderStatus())
						.amount(order.getAmount())
						.orderDate(order.getOrderDate())
						.productdetails(productDetails)
						.paymentDetails(paymentDetails)
						.build();

		return orderResponse;
	}

}
