package com.jpabook.start.ch6;

import com.jpabook.start.Member;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/*@Entity*/
public class Product6 {

    @Id
    @Column(name = "PRODUCT_ID")
    private String id;

    private String name;

    @ManyToMany(mappedBy = "products")
    private List<Member6> members = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Member6> getMembers() {
        return members;
    }

    public void setMembers(List<Member6> members) {
        this.members = members;
    }
}
