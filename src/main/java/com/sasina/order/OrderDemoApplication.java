package com.sasina.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.sasina.order.domains.ColorMaster;
import com.sasina.order.domains.CustomerOrder;
import com.sasina.order.repositories.ColorRepository;
import com.sasina.order.repositories.OrderRepository;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;


@SpringBootApplication
@ComponentScan({"com.sasina.order"})
@EntityScan("com.sasina.order.domains")
@EnableJpaRepositories("com.sasina.order.repositories")
public class OrderDemoApplication {
	
//	private static final Logger log = LoggerFactory.getLogger(OrderDemoApplication.class);
   
    
	public static void main(String[] args) {
		SpringApplication.run(OrderDemoApplication.class, args);
	}	
	

//	 @Bean
//	  public CommandLineRunner demo(OrderRepository repository) {
//	    return (args) -> {
//	      // save a few customers
////	      repository.save(new ColorMaster("orange"));
////	      repository.save(new ColorMaster("pink"));
//
//	      // fetch all customers
//	      System.out.println("Order found with findAll():");
//	      System.out.println("-------------------------------");
//	      for (CustomerOrder customerOrder : repository.findAll()) {
//	    	  System.out.println(customerOrder.toString());
//	      }
//	      System.out.println("");
//
//	      CustomerOrder customerOrder = repository.findById(1);
//	      System.out.println("Order found with findById(1):");
//	      System.out.println("--------------------------------");
//	      System.out.println(customerOrder.toString());
//	      System.out.println("");
//	     
//	    };
//	  }

}
