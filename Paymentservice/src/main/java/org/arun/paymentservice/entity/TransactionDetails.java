package org.arun.paymentservice.entity;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TRANSACTION_DETAILS")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "ORDER_ID")
	private long orderId;
	
	@Column(name = "PAYMENT_MODE")
	private String paymentMode;
	
	@Column(name = "REFERENCE_NUMBER")
	private String referenceNumber;
	
	@Column(name = "PAYMENT_DATE")
	private Instant paymentDate;
	
	@Column(name = "PAYMENT_STATUS")
	private String paymentStatus;
	
	@Column(name = "AMOUNT")
	private long amount;
}
