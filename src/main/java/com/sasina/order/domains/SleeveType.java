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
@Table(name="sleeve_type")
public class SleeveType {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@Column(name="type", nullable = false, unique = true)
    private String type;
	
	@OneToMany(mappedBy="sleeveType")
	private List<Product> product;
    
    public SleeveType() {
    }
    
    public SleeveType(String type) {
    	this.type = type;
    }

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    public List<Product> getProduct() {
        return product;
    }

    public void setProduct(List<Product> product) {
        this.product = product;
    }
    
    @Override
    public String toString() {
        return "SleeveType " +
                "id=" + id +
                ", type='" + type ;
    }
}
