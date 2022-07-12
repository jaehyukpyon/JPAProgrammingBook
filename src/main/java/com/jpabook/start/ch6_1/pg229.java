package com.jpabook.start.ch6_1;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class pg229 {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        //logic(em);
        logic2(em);
        tx.commit();
    }

    private static void logic2(EntityManager em) {
        MemberProductId6_1 memberProductId = new MemberProductId6_1();
        memberProductId.setMember("member1");
        memberProductId.setProduct("productA");

        System.out.println("em.find");
        MemberProduct6_1 memberProduct = em.find(MemberProduct6_1.class, memberProductId);

        System.out.println("memberProduct.getMember()");
        Member6_1 member = memberProduct.getMember();

        System.out.println("memberProduct.getProduct()");
        Product6_1 product = memberProduct.getProduct();

        System.out.println("member = " + member.getUaername());
        System.out.println("product = " + product.getName());
        System.out.println("orderAmount = " + memberProduct.getOrderAmount());

        /*
        * em.find
            Hibernate:
                select
                    memberprod0_.MEMBER_ID as MEMBER_I1_7_0_,
                    memberprod0_.PRODUCT_ID as PRODUCT_2_7_0_,
                    memberprod0_.orderAmount as orderAmo3_7_0_,
                    member6_1x1_.MEMBER_ID as MEMBER_I1_5_1_,
                    member6_1x1_.uaername as uaername2_5_1_,
                    product6_1x2_.PRODUCT_ID as PRODUCT_1_9_2_,
                    product6_1x2_.name as name2_9_2_
                from
                    MemberProduct6_1 memberprod0_
                inner join
                    Member6_1 member6_1x1_
                        on memberprod0_.MEMBER_ID=member6_1x1_.MEMBER_ID
                inner join
                    Product6_1 product6_1x2_
                        on memberprod0_.PRODUCT_ID=product6_1x2_.PRODUCT_ID
                where
                    memberprod0_.MEMBER_ID=?
                    and memberprod0_.PRODUCT_ID=?
            memberProduct.getMember()
            memberProduct.getProduct()
            member = 회원1
            product = 상품1
            orderAmount = 2*/
    }

    private static void logic(EntityManager em) {
        Member6_1 member1 = new Member6_1();
        member1.setId("member1");
        member1.setUaername("회원1");
        em.persist(member1);

        Product6_1 productA = new Product6_1();
        productA.setId("productA");
        productA.setName("상품1");
        em.persist(productA);

        MemberProduct6_1 memberProduct = new MemberProduct6_1();
        memberProduct.setProduct(productA);
        memberProduct.setMember(member1);
        memberProduct.setOrderAmount(2);
        em.persist(memberProduct);

        /*
        * Hibernate:
        insert
        into
            Member6_1
            (uaername, MEMBER_ID)
        values
            (?, ?)
    Hibernate:
        insert
        into
            Product6_1
            (name, PRODUCT_ID)
        values
            (?, ?)
    Hibernate:
        insert
        into
            MemberProduct6_1
            (orderAmount, MEMBER_ID, PRODUCT_ID)
        values
            (?, ?, ?)*/
    }

}
