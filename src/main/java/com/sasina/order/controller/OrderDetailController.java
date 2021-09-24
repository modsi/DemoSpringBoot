package com.sasina.order.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sasina.order.domains.ColorMaster;
import com.sasina.order.domains.CustomerOrder;
import com.sasina.order.domains.SleeveType;
import com.sasina.order.domains.Product;
import com.sasina.order.repositories.ColorRepository;
import com.sasina.order.repositories.OrderRepository;
import com.sasina.order.repositories.SleeveTypeRepository;

@Controller
public class OrderDetailController {

	private static final String AJAX_HEADER_NAME = "X-Requested-With";
	private static final String AJAX_HEADER_VALUE = "XMLHttpRequest";	
	
	@Autowired
	private OrderRepository orderRepository;

	@GetMapping("/orderdetail{orderNo}")
	public String getOrderDetail(String orderNo, Model model) {
		System.out.println("orderNo >> " + orderNo);
		CustomerOrder customerOrder = orderRepository.findByOrderNo(orderNo);
		System.out.println(customerOrder.toString());
		model.addAttribute("action", "Edit");
		model.addAttribute("orderNo", orderNo);
		model.addAttribute("customerOrder", customerOrder);
		return "order-detail";
	}

	@GetMapping("/neworder")
	public String newOrder(Model model) {
		String orderNo = "";
		model.addAttribute("action", "New");
		model.addAttribute("orderNo", orderNo);
		model.addAttribute("customerOrder", new CustomerOrder());
		return "order-detail";
	}	
	
	@PostMapping(params = "saveOrder", path = { "/neworder", "/orderdetail{orderNo}" })
	public String saveOrder(@ModelAttribute CustomerOrder customerOrder) {
		System.out.println(customerOrder.toString());
		CustomerOrder temCustomerOrder = null;
		String genOrederNo = customerOrder.getOrderNo();
		if (genOrederNo == null) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMyy");  
			LocalDateTime now = LocalDateTime.now(); 
			genOrederNo = "OR" + dtf.format(now) + (String.format("%04d", orderRepository.getMaxId()));			
		}
		
		temCustomerOrder = orderRepository.findByOrderNo(genOrederNo);
		if(temCustomerOrder == null) {			
//			System.out.println("genOrederNo >> " + genOrederNo );
			temCustomerOrder = new CustomerOrder(genOrederNo, customerOrder.getCustomerName(), customerOrder.getCustomerAddress()
					, customerOrder.getCustomerMobile(), customerOrder.getCustomerEmail());
			orderRepository.save(temCustomerOrder);
		}else {
//			System.out.println(temCustomerOrder.toString());
			customerOrder.setId(temCustomerOrder.getId());
			orderRepository.save(customerOrder);
		}
		return "redirect:/order";
	}	
	

}
