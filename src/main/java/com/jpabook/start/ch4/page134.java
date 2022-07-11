package com.jpabook.start.ch4;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class page134 {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        tx.begin();

        logic(em);

        tx.commit();
    }

    private static void logic(EntityManager em) {

        Board4 board4 = new Board4();
        em.persist(board4);
        System.out.println("board4.id = " + board4.getId());

        /*
        * Hibernate:
            insert
            into
                Board4
                (id, data)
            values
                (default, ?)
        board4.id = 1

        * 프로그램 종료-----
        * */

    }

}
