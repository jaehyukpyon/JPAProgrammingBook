package com.jpabook.start;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

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
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }

    private static void logic(EntityManager em) {

        String id = "id1";
        Member member = new Member();
        member.setId(id);
        member.setUsername("지한");
        member.setAge(2);

        System.out.println("등록...");
        em.persist(member); // 등록

        System.out.println("수정...");
        member.setAge(20);
        member.setUsername("재혁");

        // 한 건 조회
        System.out.println("한 건 조회...");
        Member findMember = em.find(Member.class, id);
        System.out.println("findMember=" + findMember.getUsername() + ", age=" + findMember.getAge());

        // 목록 조회
        System.out.println("목록 조회...");
        List<Member> members = em.createQuery("select m from Member m", Member.class).getResultList();
        System.out.println("members.size=" + members.size());

        // 삭제
        System.out.println("삭제...");
        em.remove(member);

        /*
        * transaction start!
        등록...
        수정...
        한 건 조회...
        findMember=재혁, age=20
        목록 조회...
        Hibernate:
            insert
            into
                MEMBER
                (age, NAME, ID)
            values
                (?, ?, ?)
        Hibernate:
            update
                MEMBER
            set
                age=?,
                NAME=?
            where
                ID=?
        Hibernate:
            select
                member0_.ID as id1_0_,
                member0_.age as age2_0_,
                member0_.NAME as name3_0_
            from
                MEMBER member0_
        members.size=1
        삭제...
        transaction commit!
        Hibernate:
            delete
            from
                MEMBER
            where
                ID=?
        * */
    }

}
