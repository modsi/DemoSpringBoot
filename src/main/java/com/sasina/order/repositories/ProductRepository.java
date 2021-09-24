package com.sasina.order.repositories;


import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Sort;

import com.sasina.order.domains.ColorMaster;
import com.sasina.order.domains.CustomerOrder;
import com.sasina.order.domains.Product;
import com.sasina.order.domains.SleeveType;


@Repository
public interface  ProductRepository extends CrudRepository<Product, Long> {
	
	List<Product> findByCustomerOrder(CustomerOrder customerOrder);
	List<Product> findBySleeveType(SleeveType sleeveType);
	List<Product> findByColorMaster(ColorMaster colorMaster);

}



