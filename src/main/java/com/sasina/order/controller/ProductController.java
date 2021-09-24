package com.sasina.order.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sasina.order.domains.ColorMaster;
import com.sasina.order.domains.CustomerOrder;
import com.sasina.order.domains.Product;
import com.sasina.order.domains.SleeveType;
import com.sasina.order.repositories.ColorRepository;
import com.sasina.order.repositories.OrderRepository;
import com.sasina.order.repositories.ProductRepository;
import com.sasina.order.repositories.SleeveTypeRepository;

@Controller
public class ProductController {

	@Autowired
	private ColorRepository colorRepository;

	@Autowired
	private SleeveTypeRepository sleeveTypeRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private OrderRepository orderRepository;

	@GetMapping("/product{orderNo}")
	public String getProductDetail(String orderNo, Model model) {
		CustomerOrder customerOrder = orderRepository.findByOrderNo(orderNo);
		List<Product> products = productRepository.findByCustomerOrder(customerOrder);
		model.addAttribute("orderNo", orderNo);
		model.addAttribute("product", new Product(customerOrder));
		model.addAttribute("sleeveType", new SleeveType());
		model.addAttribute("colorMaster", new ColorMaster());
		model.addAttribute("customerOrder", customerOrder);
		model.addAttribute("products", products);
		return "product";
	}
	
	@GetMapping("/product/show{productId}")
	public String showProductDetail(String productId, Model model) {
		System.out.println("productId >> " + productId);
		Product product = productRepository.findById(Long.parseLong(productId)).get();
		CustomerOrder customerOrder = product.getCustomerOrder();
		System.out.println("customerOrder >> " + customerOrder);
		List<Product> products = productRepository.findByCustomerOrder(customerOrder);
		model.addAttribute("orderNo", customerOrder.getOrderNo());
		model.addAttribute("product", product);
		model.addAttribute("sleeveType", new SleeveType());
		model.addAttribute("colorMaster", new ColorMaster());
		model.addAttribute("customerOrder", customerOrder);
		model.addAttribute("products", products);
		return "product";
	}
	
	@GetMapping("/product/delete{productId}")
	public String deleteProduct(String productId, Model model) {
		System.out.println("productId >> " + productId);
		Product product = productRepository.findById(Long.parseLong(productId)).get();
		String orderNo = product.getCustomerOrder().getOrderNo();
		productRepository.delete(product);
		return "redirect:/product?orderNo="+ orderNo;
	}

	@PostMapping(params = "saveProduct", path = { "/product{orderNo}", "/product/show{productId}" })
	public String saveProduct(@ModelAttribute Product product, @ModelAttribute CustomerOrder customerOrder,
			@ModelAttribute SleeveType sleeveType, @ModelAttribute ColorMaster colorMaster, String productId) {
		System.out.println(product.toString());
		System.out.println(customerOrder.toString());
		System.out.println(sleeveType.toString());
		System.out.println(colorMaster.toString());
		System.out.println("productId >> " + productId);
		Product tmpProduct;
		if(productId == null) {
			System.out.println("newProduct");
			CustomerOrder o = orderRepository.findByOrderNo(customerOrder.getOrderNo());
			ColorMaster c = colorRepository.findById(colorMaster.getId()).get();
			SleeveType s = sleeveTypeRepository.findById(sleeveType.getId()).get();
			tmpProduct = new Product(o, c, s, product.getTextScreen(), product.getTextColorCode());
			System.out.println(tmpProduct.toString());
			productRepository.save(tmpProduct);
		}else {
			tmpProduct = productRepository.findById(Long.parseLong(productId)).get();
			System.out.println("updateProduct " );
			ColorMaster c = colorRepository.findById(colorMaster.getId()).get();
			SleeveType s = sleeveTypeRepository.findById(sleeveType.getId()).get();			
			tmpProduct.setColorMaster(c);
			tmpProduct.setSleeveType(s);
			tmpProduct.setTextScreen(product.getTextScreen());
			tmpProduct.setTextColorCode(product.getTextColorCode());
			System.out.println(tmpProduct.toString());
			productRepository.save(tmpProduct);
		}
		
		return "redirect:/product?orderNo="+ tmpProduct.getCustomerOrder().getOrderNo();
	}
	

	@ModelAttribute("colorList")
	public Iterable<ColorMaster> getColorList() {
		Iterable<ColorMaster> colorList = colorRepository.findAll();
		return colorList;
	}

	@ModelAttribute("sleeveList")
	public Iterable<SleeveType> getSleeveList() {
		Iterable<SleeveType> sleeveList = sleeveTypeRepository.findAll();
		return sleeveList;
	}

}
