package com.jpabook.start.ch5;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class page181 {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        //logic(em); // 먼저 logic(em)을 실행한 후, 주석처리하고 logic2(em)을 실행합시다..
        logic2(em);
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

        System.out.println("find team...");
        Team5 findTeam = em.find(Team5.class, "team1");
        List<Member5> members = findTeam.getMembers();

        System.out.println("for loops...");
        for (Member5 memberItem : members) {
            System.out.println("member.username = " + memberItem.getUsername());
        }

        /*
        * Find member...
graph...
팀 이름 = 팀1
find team...
for loops...
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

    public static void logic2(EntityManager em) {
        System.out.println("find team...");
        Team5 findTeam = em.find(Team5.class, "team1");
        List<Member5> members = findTeam.getMembers();

        System.out.println("for loops...");
        for (Member5 memberItem : members) {
            System.out.println("member.username = " + memberItem.getUsername()); // 실제로 field에 접근해야 select query가 실행 된다.
        }

        /*
        * find team...
Hibernate:
    select
        team5x0_.TEAM_ID as team_id1_4_0_,
        team5x0_.name as name2_4_0_
    from
        Team5 team5x0_
    where
        team5x0_.TEAM_ID=?
for loops...
Hibernate:
    select
        members0_.TEAM_ID as team_id3_3_0_,
        members0_.MEMBER_ID as member_i1_3_0_,
        members0_.MEMBER_ID as member_i1_3_1_,
        members0_.TEAM_ID as team_id3_3_1_,
        members0_.username as username2_3_1_
    from
        Member5 members0_
    where
        members0_.TEAM_ID=?
member.username = 회원1
member.username = 회원2*/
    }

}
