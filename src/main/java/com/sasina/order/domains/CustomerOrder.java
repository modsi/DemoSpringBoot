package com.sasina.order.domains;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name="customer_order")
public class CustomerOrder {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@Column(name="order_no", nullable = false, unique = true)
    private String orderNo;
	
	@Column(name="customer_name")
    private String customerName;
	
	@Column(name="customer_address")
    private String customerAddress;
	
	@Column(name="customer_mobile")
    private String customerMobile;
	
	@Column(name="customer_email")
    private String customerEmail;
    
    @OneToMany(mappedBy="customerOrder")
    private List<Product> product;

    public CustomerOrder() {
    }
    
    public CustomerOrder(String orderNo,String customerName, String customerAddress, String customerMobile, String customerEmail) {
    	this.orderNo = orderNo;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerMobile = customerMobile;
        this.customerEmail = customerEmail;
    }

    @Override
    public String toString() {
        return "Order >> " +"id=" + id +", orderNo=" + orderNo + ", customerName="  +customerName 
        		+ ", customerAddress =" + customerAddress + ", customerMobile =" + customerMobile
        		+ ", customerEmail =" + customerEmail;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
    
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    
    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }
    
    public String getCustomerMobile() {
        return customerMobile;
    }

    public void setCustomerMobile(String customerMobile) {
        this.customerMobile = customerMobile;
    }
    
    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }
    
    public List<Product> getProduct() {
        return product;
    }

    public void setProduct(List<Product> products) {
        this.product = products;
    }    
    
	
}
