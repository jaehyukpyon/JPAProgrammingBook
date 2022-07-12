package com.jpabook.start.ch6;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class pg225 {

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
        System.out.println("logic2 starts...");

        System.out.println("em.find...");
        Product6 product = em.find(Product6.class, "productA");

        System.out.println("getMembers...");
        List<Member6> members = product.getMembers();

        System.out.println("for loop");
        for (Member6 member : members) {
            System.out.println("member = " + member.getUsername());
        }

        /*
        * logic2 starts...
            em.find...
            Hibernate:
                select
                    product6x0_.PRODUCT_ID as PRODUCT_1_6_0_,
                    product6x0_.name as name2_6_0_
                from
                    Product6 product6x0_
                where
                    product6x0_.PRODUCT_ID=?
            getMembers...
            for loop
            Hibernate:
                select
                    members0_.PRODUCT_ID as PRODUCT_2_5_0_,
                    members0_.MEMBER_ID as MEMBER_I1_5_0_,
                    member6x1_.MEMBER_ID as MEMBER_I1_4_1_,
                    member6x1_.username as username2_4_1_
                from
                    MEMBER_PRODUCT members0_
                inner join
                    Member6 member6x1_
                        on members0_.MEMBER_ID=member6x1_.MEMBER_ID
                where
                    members0_.PRODUCT_ID=?
            member = 회원1*/
    }

    private static void logic(EntityManager em) {

        Product6 productA = new Product6();
        productA.setId("productA");
        productA.setName("상품A");
        em.persist(productA);

        Member6 member1 = new Member6();
        member1.setId("member1");
        member1.setUsername("회원1");

        member1.addProduct(productA);
        em.persist(member1);
    }
}
