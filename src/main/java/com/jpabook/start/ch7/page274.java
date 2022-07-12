package com.jpabook.start.ch7;

import javafx.scene.Parent;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class page274 {

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
        Parent7 findParent = em.find(Parent7.class, 2L);
        /*
        * select
        parent7x0_.PARENT_ID as PARENT_I1_9_0_,
        parent7x0_.name as name2_9_0_,
        parent7x0_1_.CHILD_ID as CHILD_ID1_10_0_,
        child7x1_.CHILD_ID as CHILD_ID1_1_1_,
        child7x1_.name as name2_1_1_
    from
        Parent7 parent7x0_
    left outer join
        PARENT_CHILD parent7x0_1_
            on parent7x0_.PARENT_ID=parent7x0_1_.PARENT_ID
    left outer join
        Child7 child7x1_
            on parent7x0_1_.CHILD_ID=child7x1_.CHILD_ID
    where
        parent7x0_.PARENT_ID=2
        * */
    }

    private static void logic(EntityManager em) {
        Child7 child = new Child7();
        //child.setId(1004L);
        child.setName("child1004");
        em.persist(child);

        Parent7 parent = new Parent7();
        //parent.setId(7777L);
        parent.setName("parent7777");
        parent.setChild(child);
        em.persist(parent);

        /*
        * Hibernate:
        call next value for hibernate_sequence
    Hibernate:
        call next value for hibernate_sequence
    Hibernate:
        insert
        into
            Child7
            (name, CHILD_ID)
        values
            (?, ?)
    Hibernate:
        insert
        into
            Parent7
            (name, PARENT_ID)
        values
            (?, ?)
    Hibernate:
        insert
        into
            PARENT_CHILD
            (CHILD_ID, PARENT_ID)
        values
            (?, ?)*/
    }
}
