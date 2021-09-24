package com.sasina.order.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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

@Controller
public class OrderController {
	@Value("${spring.application.name}")
	String appName;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private ProductRepository productRepository;

	@GetMapping("/order")
	public String listOrder(Model model) {
		model.addAttribute("orders", orderRepository.findAll());
		return "order-list";
	}

	@PostMapping(params = "seachorder", path = { "/order" })
	public String seachOrder(@ModelAttribute String keyword, Model model) {
		System.out.println("keyword >> " + keyword);
		model.addAttribute("orders",
				orderRepository.findAllByOrderNoContainingOrCustomerNameContaining(keyword, keyword));
		return "order-list";
	}

	@RequestMapping(value = "/seachorder", method = RequestMethod.POST, params = { "seachorder" })
	public String seachOrder(Model model,@RequestParam String keyword) {
		System.out.println("keyword >> " + keyword);
		model.addAttribute("orders",
				orderRepository.findAllByOrderNoContainingOrCustomerNameContaining(keyword, keyword));
		return "order-list";
	}

	@GetMapping("/")
	public String showIndex() {
		return "index";
	}

	@RequestMapping("/delete/{id}")
	public String deleteOrder(@PathVariable(name = "id") long id) {
		CustomerOrder customerOrder = orderRepository.findById(id);
		if (customerOrder.getProduct() != null) {
			for (Product p : customerOrder.getProduct()) {
				productRepository.deleteById(p.getId());
			}
		}
		orderRepository.deleteById(id);
		return "redirect:/order";
	}

//
//    @GetMapping
//    public Iterable findAll() {
//        return orderRepository.findAll();
//    }

//    @GetMapping("/title/{bookTitle}")
//    public List findByTitle(@PathVariable String bookTitle) {
//        return bookRepository.findByTitle(bookTitle);
//    }
//
//    @GetMapping("/{id}")
//    public Book findOne(@PathVariable Long id) {
//        return bookRepository.findById(id)
//          .orElseThrow(BookNotFoundException::new);
//    }
//
//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public Book create(@RequestBody Book book) {
//        return bookRepository.save(book);
//    }
//
//    @DeleteMapping("/{id}")
//    public void delete(@PathVariable Long id) {
//        bookRepository.findById(id)
//          .orElseThrow(BookNotFoundException::new);
//        bookRepository.deleteById(id);
//    }
//
//    @PutMapping("/{id}")
//    public Book updateBook(@RequestBody Book book, @PathVariable Long id) {
//        if (book.getId() != id) {
//          throw new BookIdMismatchException();
//        }
//        bookRepository.findById(id)
//          .orElseThrow(BookNotFoundException::new);
//        return bookRepository.save(book);
//    }

}
