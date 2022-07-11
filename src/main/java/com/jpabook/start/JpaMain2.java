package com.jpabook.start;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain2 {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        try {
            logic(em);

            System.out.println("transaction start!");
            tx.begin();

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
        /*System.out.println("삭제...");
        em.remove(member);*/

        /*
        * 등록...
        수정...
        한 건 조회...
        findMember=재혁, age=20
        목록 조회...
        Hibernate:
            select
                member0_.ID as id1_0_,
                member0_.age as age2_0_,
                member0_.NAME as name3_0_
            from
                MEMBER member0_
        members.size=0
        transaction start!
        transaction commit!
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
        * */
    }

}
