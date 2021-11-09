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

        // COMPANY MANAGER JPA

        CompanyManager[] managers_list = {
            new CompanyManager("cm1@cm.com", "pass"),
            new CompanyManager("cm2@cm.com", "pass"),
            new CompanyManager("cm3@cm.com", "pass"),
            new CompanyManager("cm4@cm.com", "pass")
        };

        et.begin();

        for (CompanyManager s : managers_list)
            em.persist(s);
        et.commit();
        emf.close();
    }
}
