package com.jpabook.start.ch6_pg221;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class page225 {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        try {
            System.out.println("transaction start!");
            tx.begin();

            logic(em);

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
        Product product = em.find(Product.class, "productA");

        System.out.println("----- before -----");
        List<Member> members = product.getMembers();
        System.out.println("----- after -----");

        for (Member member : members) {
            System.out.println("member: " + member.getUsername());
        }
    }

}
