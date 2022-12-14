package org.arun.paymentservice.service;

import java.time.Instant;

import org.arun.paymentservice.entity.TransactionDetails;
import org.arun.paymentservice.model.PaymentMode;
import org.arun.paymentservice.model.PaymentRequest;
import org.arun.paymentservice.model.PaymentResponse;
import org.arun.paymentservice.repository.TransatcionDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class PaymentServiceImpl implements PaymentService{

	@Autowired
	private TransatcionDetailsRepository transatcionDetailsRepository;
	
	@Override
	public Long doPayment(PaymentRequest paymentRequest) {
		
		log.info("Recording Payment Details : {} ",paymentRequest);
		
		TransactionDetails transactionDetails=TransactionDetails.builder()
				.paymentDate(Instant.now())
				.paymentMode(paymentRequest.getPaymentMode().name())
				.paymentStatus("SUCCESS")
				.orderId(paymentRequest.getOrderId())
				.referenceNumber(paymentRequest.getReferenceNumber())
				.amount(paymentRequest.getAmount())
				.build();
		transatcionDetailsRepository.save(transactionDetails);
		
		log.info("Transaction Completed with Id: {} ", transactionDetails.getId());
		return transactionDetails.getId();
	}

	@Override
	public PaymentResponse getPaymentDetailsByOrderId(String orderId) {
		
		log.info("Getting Payment Details for Order Id {} : " , orderId);
		
		TransactionDetails transactionDetails = transatcionDetailsRepository.findByOrderId(Long.valueOf(orderId));
		
		PaymentResponse paymentResponse = PaymentResponse.builder()
				
				.paymentId(transactionDetails.getId())
				.paymentMode(PaymentMode.valueOf(transactionDetails.getPaymentMode()))
				.paymentDate(transactionDetails.getPaymentDate())
				.orderId(transactionDetails.getOrderId())
				.status(transactionDetails.getPaymentStatus())
				.amount(transactionDetails.getAmount())
				.build();
		
		return paymentResponse;
	}

}
