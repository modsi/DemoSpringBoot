package com.sasina.order.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.sasina.order.domains.CustomerOrder;
import com.sasina.order.domains.Product;

public interface OrderRepository extends CrudRepository<CustomerOrder, Long> {
	CustomerOrder findByOrderNo(String orderNo);

	CustomerOrder findById(long id);

	CustomerOrder findByProduct(Product product);

	@Query(value = "SELECT max(id)+1 FROM customer_order", nativeQuery = true)
	Long getMaxId();

//	@Query("select e from CustomerOrder e where e.orderNo like %:keyword% or e.customerName like %:keyword% ")
	List<CustomerOrder> findAllByOrderNoContainingOrCustomerNameContaining(String s1, String s2);
}
