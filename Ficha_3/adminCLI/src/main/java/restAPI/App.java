package restAPI;

import data.ClientDTO;
import data.CurrencyDTO;
import data.ManagerDTO;

import java.awt.*;
import java.text.ParseException;
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
        WebTarget target;
        Response response;
        int option = -1;
        String aux;
        int aux2;

        Client client = ClientBuilder.newClient();

        System.out.println("WELCOME\n");

        while(option != 0){

            System.out.println("\nMENU\n 0. Exit\n 1. Add Manager\n 2. Add Client\n 3. Add Currency\n" +
                    " 4. List Managers\n 5. List Clients\n 6. List Currencies\n" +
                    " 7. Get credit per client\n 8. Get payments\n" +
                    " 9. Get the current balance of a client\n10. Get total\n" +
                    "11. Get total payments\n12. Get total balance\n" +
                    "13. Compute the bill for each client for the last month\n" +
                    "14. Get list of clients without payments for the last two months\n" +
                    "15. Get person with the highest outstanding debt\n" +
                    "16. Get manager who has made the highest revenue in payments from his clients\n\n");

            do{
                option = myObj.nextInt();
                aux = myObj.nextLine();

                try{
                    //option = Integer.parseInt(aux);

                    switch (option){

                        case(0):
                            myObj.close();
                            return;

                        case(1):

                            target = client.target("http://wildfly:8080/rest/services/myservice/addManager"); //tenho duvida no caminho
                            response = target.request().get();
                            aux = response.readEntity(String.class);
                            System.out.println("RESPONSE1: " + aux);
                            response.close();

                            break;

                        case(2):

                            System.out.print("Manager's id: ");
                            aux2 = myObj.nextInt();
                            aux = myObj.nextLine();
                            ManagerDTO m = new ManagerDTO(aux2);

                            target = client.target("http://wildfly:8080/rest/services/myservice/addClient");
                            //target = target.queryParam("id_manager", aux2);
                            //response = target.request().get();
                            Entity<ManagerDTO> send_manager = Entity.entity(m, MediaType.APPLICATION_JSON);
                            response = target.request().post(send_manager);
                            aux = response.readEntity(String.class);
                            System.out.println("RESPONSE2: " + aux);
                            response.close();

                            break;

                        case(3):

                            System.out.print("Currency name: ");
                            aux = myObj.nextLine();
                            System.out.print("Currency value: ");
                            double value_aux = myObj.nextDouble();
                            CurrencyDTO currency = new CurrencyDTO(aux, value_aux);

                            aux = myObj.nextLine(); // limpar \n perdidos

                            target = client.target("http://wildfly:8080/rest/services/myservice/addCurrency");
                            Entity<CurrencyDTO> send_currency = Entity.entity(currency, MediaType.APPLICATION_JSON);
                            response = target.request().post(send_currency);
                            aux = response.readEntity(String.class);
                            System.out.println("RESPONSE3: " + aux);
                            response.close();

                            break;
                        case(4):
                            target = client.target("http://wildfly:8080/rest/services/myservice/clients");
                            response = target.request().get();
                            List<ManagerDTO> managers = response.readEntity(new GenericType<List<ManagerDTO>>() {
                            });
                            for (ManagerDTO c: managers) {
                                System.out.println(c);
                            }
                            break;
                        case(5):
                            target = client.target("http://wildfly:8080/rest/services/myservice/clients");
                            response = target.request().get();
                            List<ClientDTO> clients = response.readEntity(new GenericType<List<ClientDTO>>() {
                            });
                            for (ClientDTO c: clients) {
                                System.out.println(c);
                            }
                            break;
                        case(6):
                            //cenas
                            break;
                        case(7):
                            //cenas
                            break;
                        case(8):
                            //cenas
                            break;
                        case(9):
                            //cenas
                            break;
                        case(10):
                            //cenas
                            break;
                        case(11):
                            //cenas
                            break;
                        case(12):
                            //cenas
                            break;
                        case(13):
                            //cenas
                            break;
                        case(14):
                            //cenas
                            break;
                        case(15):
                            //cenas
                            break;
                        case(16):
                            //cenas
                            break;
                        default:
                            System.out.println("Invalid option. Try again");
                    }

                }/*
                catch(ParseException e){
                    System.out.print("Error in parse.\n Try again: ");
                }*/
                catch (Exception e){
                    System.out.print("ERRO: ");
                    e.printStackTrace();
                }

            }while(option == -1);

        }


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

        //myObj.close();
    }

}

