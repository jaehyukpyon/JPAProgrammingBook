package com.jpabook.start.ch6_pg221;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class page223 {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        try {
            System.out.println("transaction start!");
            tx.begin();

            //logic(em);
            find(em);

            System.out.println("transaction commit!");
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
            emf.close();
        }
    }

    private static void logic(EntityManager em) {
        Product productA = new Product();
        productA.setId("productA");
        productA.setName("상품A");
        em.persist(productA);

        Member member1 = new Member();
        member1.setId("member1");
        member1.setUsername("회원1");
        member1.getProducts().add(productA);
        em.persist(member1);
    }

    private static void find(EntityManager em) {
        Member member = em.find(Member.class, "member1");

        System.out.println("----- before -----");
        List<Product> products = member.getProducts();
        System.out.println("----- after -----");

        for (Product product : products) {
            System.out.println("product.name: " + product.getName());
        }
    }

}
