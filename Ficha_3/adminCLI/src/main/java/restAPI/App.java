package restAPI;

import java.awt.*;
import java.util.List;
import java.util.Scanner;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
//import beans.Person;

public class App {

    public static void main(String[] args) {

        Scanner myObj = new Scanner(System.in);
        int option = -1;
        String aux;

        Client client = ClientBuilder.newClient();

        while(option != 0){

            System.out.println("WELCOME\n");
            System.out.println("MENU\n 0.Exit\n 1. Add Manager\n 2. Add Client\n 3. Add Currency\n" +
                    " 4. List Managers\n 5. List Clients\n 6. List Currencies\n" +
                    " 7. Get credit per client\n 8. Get payments\n" +
                    " 9. Get the current balance of a client\n10. Get total\n" +
                    "11. Get total payments\n 12. Get total balance\n" +
                    "13. Compute the bill for each client for the last month\n" +
                    "14. Get list of clients without payments for the last two months\n" +
                    "15. Get person with the highest outstanding debt\n" +
                    "16. Get manager who has made the highest revenue in payments from his clients\n\n");

            do{
                aux = myObj.nextLine();
                try{
                    option = Integer.parseInt(aux);

                    switch (option){
                        case(0):
                            return;
                        case(1):
                            WebTarget target = client.target("http://localhost:8080/restAPI/rest/myservice/addManager"); //tenho duvida no caminho
                            Response response = target.request().get();
                            String value = response.readEntity(String.class);
                            System.out.println("RESPONSE1: " + value);
                            response.close();
                            //cenas
                            break;
                        case(2):
                            //cenas
                            break;
                        case(3):
                            //cenas
                            break;
                        default:
                            System.out.println("Invalid option. Try again");
                    }

                }
                catch(Exception e){
                    System.out.print("Error in parse.\n Try again: ");
                }

            }while(option == -1);

        }


        /*

        target = client.target("http://localhost:8080/restAPI/rest/myservice/addClient");
        target = target.queryParam("name", "xpto");
        response = target.request().get();
        value = response.readEntity(String.class);
        System.out.println("RESPONSE2: " + value);
        response.close();

        /*
        target = client.target("http://localhost:8080/restws/rest/myservice/person3/xpto");
        response = target.request().get();
        value = response.readEntity(String.class);
        System.out.println("RESPONSE3: " + value);
        response.close();

        target = client.target("http://localhost:8080/restws/rest/myservice/person4");
        Person p = new Person("Peter", 40);
        Entity<Person> input = Entity.entity(p, MediaType.APPLICATION_JSON);
        response = target.request().post(input);
        value = response.readEntity(String.class);
        System.out.println("RESPONSE4: " + value);
        response.close();

        target = client.target("http://localhost:8080/restws/rest/myservice/person5");
        response = target.request().post(input);
        p = response.readEntity(Person.class);
        System.out.println("RESPONSE5: " + p.getName() + " " + p.getAge());
        response.close();

        target = client.target("http://localhost:8080/restws/rest/myservice/person6");
        response = target.request().get();
        List<Person> personList = response.readEntity(new GenericType<List<Person>>(){});
        System.out.println("RESPONSE6: " + personList);
        response.close();
        */

        myObj.close();
    }

}

