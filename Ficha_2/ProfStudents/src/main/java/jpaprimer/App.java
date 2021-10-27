package jpaprimer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ClientsBus");
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();

        Trip[] myprofs = {
            new Trip(),
            new Trip()
        };
    }
}
