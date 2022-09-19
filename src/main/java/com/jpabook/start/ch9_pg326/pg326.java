package com.jpabook.start.ch9_pg326;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class pg326 {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        try {
            System.out.println("transaction start!");

            tx.begin();

            //logic(em);

            System.out.println("before transaction commit...");
            tx.commit();
            System.out.println("after transaction commit...");
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
            emf.close();
        }
    }

    /*private static void logic(EntityManager em) {
        Member member = new Member();
        member.setZipcode(new Zipcode("1111", "2222"));

        em.persist(member);
        member.setZipcode(new Zipcode("1111", "2222"));
    }*/

}
