package org.arun.paymentservice.repository;

import org.arun.paymentservice.entity.TransactionDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransatcionDetailsRepository extends JpaRepository<TransactionDetails, Long>{
	
	TransactionDetails findByOrderId(long orderId);

}
