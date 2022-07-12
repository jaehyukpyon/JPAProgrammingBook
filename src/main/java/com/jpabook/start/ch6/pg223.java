package com.jpabook.start.ch6;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class pg223 {

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
        System.out.println("logic 2 starts...");
        System.out.println("em.find...");
        Member6 member = em.find(Member6.class, "member1");

        System.out.println("member.getProducts()");
        List<Product6> products = member.getProducts();

        System.out.println("foor loop");
        for (Product6 product : products) {
            System.out.println("product.name = " + product.getName());
        }
        /*
        * logic 2 starts...
            em.find...
            Hibernate:
                select
                    member6x0_.MEMBER_ID as MEMBER_I1_4_0_,
                    member6x0_.username as username2_4_0_
                from
                    Member6 member6x0_
                where
                    member6x0_.MEMBER_ID=?
            member.getProducts()
            foor loop
            Hibernate:
                select
                    products0_.MEMBER_ID as MEMBER_I1_5_0_,
                    products0_.PRODUCT_ID as PRODUCT_2_5_0_,
                    product6x1_.PRODUCT_ID as PRODUCT_1_6_1_,
                    product6x1_.name as name2_6_1_
                from
                    MEMBER_PRODUCT products0_
                inner join
                    Product6 product6x1_
                        on products0_.PRODUCT_ID=product6x1_.PRODUCT_ID
                where
                    products0_.MEMBER_ID=?
            product.name = 상품A*/
    }

    private static void logic(EntityManager em) {

        Product6 productA = new Product6();
        productA.setId("productA");
        productA.setName("상품A");
        em.persist(productA);

        Member6 member1 = new Member6();
        member1.setId("member1");
        member1.setUsername("회원1");
        member1.getProducts().add(productA);
        em.persist(member1);
    }

}
