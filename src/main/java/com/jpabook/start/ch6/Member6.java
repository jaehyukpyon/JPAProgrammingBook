package com.jpabook.start.ch6;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/*@Entity*/
public class Member6 {

    @Id
    @Column(name = "MEMBER_ID")
    private String id;

    private String username;

    @ManyToMany
    @JoinTable(name = "MEMBER_PRODUCT",
                joinColumns = @JoinColumn(name = "MEMBER_ID"),
                inverseJoinColumns = @JoinColumn(name = "PRODUCT_ID"))
    private List<Product6> products = new ArrayList<>();


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Product6> getProducts() {
        return products;
    }

    public void setProducts(List<Product6> products) {
        this.products = products;
    }

    public void addProduct(Product6 product6) {
        products.add(product6);
        product6.getMembers().add(this);
    }
}
