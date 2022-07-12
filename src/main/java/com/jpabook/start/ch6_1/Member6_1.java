package com.jpabook.start.ch6_1;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Member6_1 {

    @Id
    @Column(name = "MEMBER_ID")
    private String id;

    private String uaername;

    @OneToMany(mappedBy = "member")
    private List<MemberProduct6_1> memberProducts = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<MemberProduct6_1> getMemberProducts() {
        return memberProducts;
    }

    public void setMemberProducts(List<MemberProduct6_1> memberProducts) {
        this.memberProducts = memberProducts;
    }

    public String getUaername() {
        return uaername;
    }

    public void setUaername(String uaername) {
        this.uaername = uaername;
    }
}
