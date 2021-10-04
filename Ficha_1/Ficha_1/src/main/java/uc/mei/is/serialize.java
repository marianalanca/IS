package uc.mei.is;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class serialize
{
    private static int pet_count;
    public static void main( String[] args )
    {
        for (int i=1;i<=100;i++) {
            try {
                JSONParser jsonParser = new JSONParser();

                FileReader reader = new FileReader("test_files/owners"+i+".json");

                //Read JSON file
                Object obj = jsonParser.parse(reader);

                JSONArray ownersList = (JSONArray) obj;

                Owners owners_ = new Owners();


                //Iterate over owners array
                for (Object owner: ownersList) {
                    Owner owner_ = parseOwnerObject((JSONObject) owner);

                    owners_.list.add(owner_);
                }

                long startTime = System.nanoTime();

                // Normal JAXB RI
                JAXBContext jaxbContext = JAXBContext.newInstance(Owners.class);

                Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

                // output pretty printed
                jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

                // output to a xml file
                jaxbMarshaller.marshal(owners_, new File("xml_files/ml_filesowners"+i+".xml"));

                long endTime = System.nanoTime();

                long totalTime = endTime - startTime;
                System.out.println(totalTime);

                // write to file

            } catch (JAXBException | IOException | ParseException e) {
                e.printStackTrace();
            }
        }


    }

    private static Owner parseOwnerObject(JSONObject owner)
    {
        //Get employee first name
        String name = (String) owner.get("name");
        String address = (String) owner.get("address");
        String birth_date = (String) owner.get("birthdate");
        String telephone = (String) owner.get("telephone");
        String id = String.valueOf(owner.get("id"));

        // tratar dos pets

        ArrayList<Pet> petList = new ArrayList<>();

        for (Object pet: (JSONArray) owner.get("pets")) {
            petList.add(parsePetObject((JSONObject) pet));
        }

        pet_count += petList.size();

        return new Owner(id, name, birth_date, telephone, address, petList);
    }

    private static Pet parsePetObject(JSONObject pet)
    {
        //Get pet info
        String name = (String) pet.get("name");
        String species = (String) pet.get("species");
        String gender = (String) pet.get("gender");
        String weight = String.valueOf(pet.get("weight"));
        String birth_date = (String) pet.get("birth_date");
        String free_form = (String) pet.get("free_form");
        String id = String.valueOf(pet.get("id"));
        return new Pet(id, name, species, gender,  weight, birth_date, free_form );
    }
}
