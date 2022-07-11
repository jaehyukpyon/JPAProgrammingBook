package com.jpabook.start.ch5;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class pg174 {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        logic(em);
        tx.commit();
    }

    private static void logic(EntityManager em) {
        Team5 team1 = new Team5("team1", "팀1");
        em.persist(team1);

        Member5 member1 = new Member5("member1", "회원1");
        member1.setTeam(team1);
        em.persist(member1);

        Member5 member2 = new Member5("member2", "회원2");
        member2.setTeam(team1);
        em.persist(member2);

        System.out.println("Find member...");
        Member5 member = em.find(Member5.class, "member1");

        System.out.println("graph...");
        Team5 team = member.getTeam();
        System.out.println("팀 이름 = " + team.getName());

        /*
        * Find member...
graph...
팀 이름 = 팀1
Hibernate:
    insert
    into
        Team5
        (name, TEAM_ID)
    values
        (?, ?)
Hibernate:
    insert
    into
        Member5
        (TEAM_ID, username, MEMBER_ID)
    values
        (?, ?, ?)
Hibernate:
    insert
    into
        Member5
        (TEAM_ID, username, MEMBER_ID)
    values
        (?, ?, ?)*/
    }

}
