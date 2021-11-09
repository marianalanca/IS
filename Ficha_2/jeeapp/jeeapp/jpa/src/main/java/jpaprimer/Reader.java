package jpaprimer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Reader {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("clientsbus");
        EntityManager em = emf.createEntityManager();
        //Query q = em.createQuery("from Professor p");

        /*List<Professor> lp = q.getResultList();
        for (Professor p : lp) {
            System.out.println(p);
            for (Student s : p.getStudents())
                System.out.println(s);
        }*/
    }
    
}
