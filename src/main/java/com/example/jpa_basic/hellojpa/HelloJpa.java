package com.example.jpa_basic.hellojpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HelloJpa {

    private final EntityManagerFactory emf;

    public HelloJpa(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public void helloPersist() {
        System.out.println("HelloJpa.helloPersist");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            HelloMember helloMember = new HelloMember();
            helloMember.setId(1L);
            helloMember.setName("Hello JPA");

            // Persisting the member entity
            em.persist(helloMember);

            HelloMember helloMember2 = new HelloMember();
            helloMember2.setId(2L);
            helloMember2.setName("Hello Second");

            // Persisting the member entity
            em.persist(helloMember2);

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public void helloFind() {
        System.out.println("HelloJpa.helloFind");
        EntityManager em = emf.createEntityManager();
        try {
            // find member entity by ID
            HelloMember findHelloMember = em.find(HelloMember.class, 1L);
            System.out.println("findMember = " + findHelloMember.getId() + " / " + findHelloMember.getName());
        } catch (Exception e) {
            //
        } finally {
            em.close();
        }
    }

    public void helloRemove() {
        System.out.println("HelloJpa.helloRemove");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            // find member entity by ID
            HelloMember findHelloMember = em.find(HelloMember.class, 1L);
            // Remove the member entity
            em.remove(findHelloMember);
            System.out.println("findMember = " + findHelloMember.getId() + " / " + findHelloMember.getName());
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public void helloUpdate() {
        System.out.println("HelloJpa.helloUpdate");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            // find member entity by ID
            HelloMember findHelloMember = em.find(HelloMember.class, 2L);
            findHelloMember.setName("Hello IntelliJ IDEA");
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public void helloFindJPQL() {
        System.out.println("HelloJpa.helloFindJPQL");
        EntityManager em = emf.createEntityManager();
        try {
            List<HelloMember> findHelloMembers = em.createQuery("select m from StudentIdentity m", HelloMember.class)
                .getResultList();
            System.out.println("findMembers = " + findHelloMembers);

            List<HelloMember> findMembers2 = em.createQuery("select m from StudentIdentity m where m.id >= 1", HelloMember.class)
                .getResultList();
            System.out.println("findMembers2 = " + findMembers2);
        } catch (Exception e) {
            //
        } finally {
            em.close();
        }
    }

    public void firstLevelCache() {
        System.out.println("HelloJpa.firstLevelCache");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            HelloMember helloMember = new HelloMember();
            helloMember.setId(1000L);
            helloMember.setName("Hello JPA");

            // persistence context
            System.out.println("before persist");
            em.persist(helloMember);
            System.out.println("after persist");

            HelloMember find1 = em.find(HelloMember.class, 1000L);
            HelloMember find2 = em.find(HelloMember.class, 1000L);
            HelloMember find3 = em.find(HelloMember.class, 1000L);
            System.out.println("find1 = " + find1.getId() + " / " + find1.getName());
            System.out.println("find2 = " + find2.getId() + " / " + find2.getName());
            System.out.println("find3 = " + find3.getId() + " / " + find3.getName());

            System.out.println("System.identityHashCode(find1) = " + System.identityHashCode(find1));
            System.out.println("System.identityHashCode(find2) = " + System.identityHashCode(find2));
            System.out.println("System.identityHashCode(find3) = " + System.identityHashCode(find3));

            System.out.println("before commit");
            em.getTransaction().commit();
            System.out.println("after commit");
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public void lazyLoading() {
        System.out.println("HelloJpa.lazyLoading");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            HelloMember helloMember1 = new HelloMember();
            helloMember1.setId(1001L);
            helloMember1.setName("1001 Member");

            HelloMember helloMember2 = new HelloMember();
            helloMember2.setId(1002L);
            helloMember2.setName("1002 Member");

            System.out.println("member1 persist");
            em.persist(helloMember1);
            System.out.println("member2 persist");
            em.persist(helloMember2); // the insert query is not executed yet

            // When you commit, the insert query is executed.
            System.out.println("before commit");
            em.getTransaction().commit();
            System.out.println("after commit");
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    // Bad usage examples
    public void badUsage() {
        System.out.println("HelloJpa.badUsage");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            HelloMember findHelloMember = em.find(HelloMember.class, 1001L);
            // findMember = Member{id=1001, name='1001 Member'}
            System.out.println("findMember = " + findHelloMember);

            // bad usage: JPA detects changes internally and automatically generates update queries.
            // dirty checking
            if (findHelloMember.getName().equals("1001 Member")) {
                findHelloMember.setName("Member Name Updated");
                em.getTransaction().commit();
            }
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

}
