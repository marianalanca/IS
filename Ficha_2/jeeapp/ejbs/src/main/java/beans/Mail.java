package beans;

import data.ClientUser;
import data.CompanyManager;
import data.Trip;
import data.TripDTO;

import javax.annotation.Resource;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.mail.Session;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.time.LocalDateTime;
import java.util.List;


@Singleton
public class Mail implements IMail{
    @PersistenceContext(unitName = "ClientsBus")
    EntityManager em;

    ICompanyManagers cm;

    @Resource(mappedName="java:jboss/mail/Default")
    private Session mailSession;

    @Schedule(dayOfWeek = "*",hour = "22",minute = "15")
    public void sendEmail(){

        try{

            double total = 0;

            LocalDateTime today = LocalDateTime.now();
            List<TripDTO> trips = cm.findTripsByDate(today.toString());

            for(TripDTO t: trips){
                total += t.getPrice();
            }

            TypedQuery<CompanyManager> q = em.createQuery("from CompanyManager ", CompanyManager.class);
            List<CompanyManager> cmList = q.getResultList();

            for(CompanyManager c: cmList){
                MimeMessage m = new MimeMessage(mailSession);
                Address[] to = new InternetAddress[] {new InternetAddress(c.getEmail()) };

                m.setRecipients(Message.RecipientType.TO, to);
                m.setSubject("Daily revenue");
                m.setSentDate(new java.util.Date());
                m.setContent("Dear Company Manager,\nThe total revenue is " + total +
                        ".\nContinuation of good work","text/plain");
                Transport.send(m);
                System.out.println("Mail sent!");
            }
        }
        catch (javax.mail.MessagingException e)
        {
            e.printStackTrace();
            System.out.println("Error in Sending Mail: "+e);
        }
    }

}
