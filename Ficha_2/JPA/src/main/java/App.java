import data.CompanyManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.text.ParseException;

public class App {
    public static void main(String[] args) throws ParseException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ClientsBus");
        EntityManager em = emf.createEntityManager();
        EntityTransaction trx = em.getTransaction();

        trx.begin();

        // vai-se buscar à consola
        // public CompanyManager(String email, String password)
        CompanyManager manager = new CompanyManager(args[0],args[1]);
        em.persist(manager);
        trx.commit();
    }
}
