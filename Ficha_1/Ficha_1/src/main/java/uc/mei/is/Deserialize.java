package uc.mei.is;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;
import java.util.ArrayList;

public class Deserialize
{
    // tirar namespace
    public static void main( String[] args )
    {
        for (int i=1;i<=100;i++) {
            JAXBContext jaxbContext = null;
            try {
                // Normal JAXB RI
                jaxbContext = JAXBContext.newInstance(Owners.class);



                File file = new File("xml_files/ml_filesowners"+i +".xml");

                Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

                // repettir 5 vezes cada
                long startTime = System.nanoTime();
                Owners hdb = (Owners) jaxbUnmarshaller.unmarshal(file);

                long endTime = System.nanoTime();

                long totalTime = endTime - startTime;
                System.out.println(totalTime);

            } catch (JAXBException e) {
                e.printStackTrace();
            }
        }


    }
}
