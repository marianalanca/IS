package uc.mei.is;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

import java.io.File;

public class App 
{

    public static void main( String[] args )
    {
        JAXBContext jaxbContext = null;
        try {

            // Normal JAXB RI
            jaxbContext = JAXBContext.newInstance(Class.class);

            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            Class o = new Class();
            o.addToList(new Student("201134441110", "Alberto", "21"));
            o.addToList(new Student("201134441116", "Patricia", "22"));
            o.addToList(new Student("201134441210", "Luis", "21"));

            // output to a xml file
            jaxbMarshaller.marshal(o, new File("fruit.xml"));

            // output to console
            // jaxbMarshaller.marshal(o, System.out);

        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }
}
