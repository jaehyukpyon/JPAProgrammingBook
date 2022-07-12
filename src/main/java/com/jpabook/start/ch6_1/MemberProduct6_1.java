package com.jpabook.start.ch6_1;

import javax.persistence.*;

@Entity
@IdClass(MemberProductId6_1.class)
public class MemberProduct6_1 {

    @Id
    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member6_1 member;

    @Id
    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product6_1 product;

    private int orderAmount;

    public Member6_1 getMember() {
        return member;
    }

    public void setMember(Member6_1 member) {
        this.member = member;
    }

    public Product6_1 getProduct() {
        return product;
    }

    public void setProduct(Product6_1 product) {
        this.product = product;
    }

    public int getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(int orderAmount) {
        this.orderAmount = orderAmount;
    }
}
