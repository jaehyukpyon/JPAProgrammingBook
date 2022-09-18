package com.jpabook.start.ch8_pg308;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class page308 {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        try {
            System.out.println("transaction start!");

            tx.begin();

            //logic(em);
            //deleteLogic(em);

            deleteCascade(em);

            System.out.println("transaction commit!");
            System.out.println("성공했습니다.!");

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
            emf.close();
        }
    }

    private static void deleteCascade(EntityManager em) {
        Parent findParent = em.find(Parent.class, 1L);
        em.remove(findParent);
        System.out.println("deleteCascade 종료!!!");
    }

    private static void logic(EntityManager em) {
        Parent parent = new Parent();
        em.persist(parent);

        Long parentId = parent.getId();
        Parent unPersistedParent = new Parent();
        unPersistedParent.setId(parentId);

        Child child1 = new Child();
        child1.setParent(unPersistedParent);
        em.persist(child1);
    }

    private static void deleteLogic(EntityManager em) {
        Parent parent = new Parent();
        em.persist(parent);

        em.remove(parent);
        System.out.println("deleteLogic ends...");
    }

}
