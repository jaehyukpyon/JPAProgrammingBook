package com.jpabook.start.ch6_pg221;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Getter
@Setter
@Entity
public class Product {

    @Id
    @Column(name = "PRODUCT_ID")
    private String id;

    private String name;

    @ManyToMany(mappedBy = "products")
    private List<Member> members;

}
