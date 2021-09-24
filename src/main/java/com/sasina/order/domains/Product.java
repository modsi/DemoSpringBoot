package com.sasina.order.domains;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="product")
public class Product {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="order_id")
    private CustomerOrder customerOrder;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "color_id")
    private ColorMaster colorMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="sleece_type_id")
    private SleeveType sleeveType;
	
	@Column(name="text_screen")
    private String textScreen;
	
	@Column(name="text_color_code")
    private String textColorCode;
    
    public ColorMaster getColorMaster() {
		return colorMaster;
	}

	public void setColorMaster(ColorMaster colorMaster) {
		this.colorMaster = colorMaster;
	}

	public SleeveType getSleeveType() {
		return sleeveType;
	}

	public void setSleeveType(SleeveType sleeveType) {
		this.sleeveType = sleeveType;
	}

	public String getTextScreen() {
		return textScreen;
	}

	public void setTextScreen(String textScreen) {
		this.textScreen = textScreen;
	}

	public String getTextColorCode() {
		return textColorCode;
	}

	public void setTextColorCode(String textColorCode) {
		this.textColorCode = textColorCode;
	}

	public Product() {
    }
    
    public Product(CustomerOrder customerOrder, ColorMaster colorMaster, SleeveType sleeveType, String textScreen, String textColorCode) {
    	this.customerOrder = customerOrder;
    	this.colorMaster = colorMaster;
    	this.sleeveType = sleeveType;
    	this.textScreen = textScreen;
    	this.textColorCode = textColorCode;
    }


   
    public Product(CustomerOrder customerOrder) {
		this.customerOrder = customerOrder;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CustomerOrder getCustomerOrder() {
        return customerOrder;
    }

    public void setCustomerOrder(CustomerOrder customerOrder) {
        this.customerOrder = customerOrder;
    }
    
    @Override
    public String toString() {
        return "Product " + "id=" + id //+ ", customerOrder=" + customerOrder.getOrderNo()
        //+ ", colorMaster=" + colorMaster.getColorName() + ", sleeveType=" + sleeveType.getType()
        + ", textScreen=" + textScreen + ", textColorCode=" + textColorCode;
    }
}
