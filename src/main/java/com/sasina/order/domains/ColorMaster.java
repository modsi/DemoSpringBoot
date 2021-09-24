package com.sasina.order.domains;

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
@Table(name="color_master")
public class ColorMaster {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@Column(name="color_name", nullable = false, unique = true)
    private String colorName;
	
	 @OneToMany(mappedBy="colorMaster")
	 private List<Product> product;
    
    public ColorMaster() {
    }

    public ColorMaster(String colorName) {
    	this.colorName = colorName;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }
    
    public List<Product> getProduct() {
        return product;
    }

    public void setProduct(List<Product> product) {
        this.product = product;
    }
    
    @Override
    public String toString() {
        return "Color " +
                "id=" + id +
                ", colorName='" + colorName + '\'' +
                '}';
    }
}
