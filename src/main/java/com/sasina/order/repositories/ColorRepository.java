package com.sasina.order.repositories;


import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sasina.order.domains.ColorMaster;


@Repository
public interface  ColorRepository extends CrudRepository<ColorMaster, Long> {
	ColorMaster findById(long id);
}



