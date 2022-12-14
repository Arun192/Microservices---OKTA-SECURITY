package org.arun.orderservice.model;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponse {

	private long orderId;
	private Instant orderDate;
	private String orderSatus;
	private long amount;
	private ProductDetails productdetails;
	private PaymentDetails paymentDetails;

	@Data
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	public static class ProductDetails {

		private String productName;
		private long productId;
		private long quantity;
		private long price;
	}

	@Data
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	public static class PaymentDetails {

		private long paymentId;
		private PaymentMode paymentMode;
		private String paymentStatus;
		private Instant paymentDate;

	}
}