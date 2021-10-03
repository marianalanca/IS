package uc.mei.is;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

import java.io.File;

public class App 
{
    // tirar namespace
    public static void main( String[] args )
    {
        JAXBContext jaxbContext = null;
        try {

            // Normal JAXB RI
            jaxbContext = JAXBContext.newInstance(Owner.class);

            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            // String birth_date, String telephone, String address
            Owner o = new Owner("1234", "Joaquim", "5-5-2020", "919191919",
                    "rua da cabrita, vila moleza");

            // String id, String name, String species, String  gender, String  weight, String birth_date,String  free_form
            o.addToList(new Pet("001", "Alberto", "caniche", "m", "1.2", "5-5-2020",
                    "feio"));
            o.addToList(new Pet("002", "Bolacha", "gato", "f", "10", "5-5-2020",
                    "feioso"));
            o.addToList(new Pet("003", "Joaquina", "Avestruz", "f", "100", "5-5-2020",
                    "bonita mais ou menos"));

            // output to a xml file
            jaxbMarshaller.marshal(o, new File("cabrita.xml"));

            // output to console
            // jaxbMarshaller.marshal(o, System.out);

        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }
}
