package com.sasina.order.repositories;


import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sasina.order.domains.SleeveType;


@Repository
public interface  SleeveTypeRepository extends CrudRepository<SleeveType, Long> {
	SleeveType findById(long id);

}



