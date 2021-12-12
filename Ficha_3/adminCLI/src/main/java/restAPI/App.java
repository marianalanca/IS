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
                    " 9. Get current balance of a client\n10. Get total credits\n" +
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
                            ManagerDTO manager = new ManagerDTO(aux2);

                            target = client.target("http://wildfly:8080/rest/services/myservice/addClient");
                            Entity<ManagerDTO> send_manager = Entity.entity(manager, MediaType.APPLICATION_JSON);
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

                            target = client.target("http://wildfly:8080/rest/services/myservice/listManagers");
                            response = target.request().get();
                            List<ManagerDTO> managers = response.readEntity(new GenericType<List<ManagerDTO>>(){});
                            System.out.println("RESPONSE4:");
                            for (ManagerDTO m: managers) {
                                System.out.println(m.toString());
                            }
                            response.close();
                            break;

                        case(5):

                            target = client.target("http://wildfly:8080/rest/services/myservice/listClients");
                            response = target.request().get();
                            List<ClientDTO> clients = response.readEntity(new GenericType<>() {
                            });
                            System.out.println("RESPONSE5:");
                            for (ClientDTO c: clients) {
                                System.out.println(c.toString());
                            }
                            break;

                        case(6):
                            target = client.target("http://wildfly:8080/rest/services/myservice/listCurrencies");
                            response = target.request().get();
                            List<CurrencyDTO> currencies = response.readEntity(new GenericType<>() {
                            });
                            System.out.println("RESPONSE6:");
                            for (CurrencyDTO c: currencies) {
                                System.out.println(c.toString());
                            }
                            break;

                        case(7):

                            target = client.target("http://wildfly:8080/rest/services/myservice/creditPerClient");
                            response = target.request().get();
                            List<String> creditsPerClient = response.readEntity(new GenericType<>() {
                            });
                            System.out.println("RESPONSE7:");
                            for (String c: creditsPerClient) {
                                System.out.println(c);
                            }

                            break;

                        case(8):

                            target = client.target("http://wildfly:8080/rest/services/myservice/paymentsPerClient");
                            response = target.request().get();
                            List<String> paymentsPerClient = response.readEntity(new GenericType<>() {
                            });
                            System.out.println("RESPONSE8:");
                            for (String p: paymentsPerClient) {
                                System.out.println(p);
                            }

                            break;

                        case(9):

                            System.out.print("Client's id: ");
                            aux2 = myObj.nextInt();
                            aux = myObj.nextLine();
                            ClientDTO balanceC = new ClientDTO(aux2);

                            target = client.target("http://wildfly:8080/rest/services/myservice/balanceClient");
                            Entity<ClientDTO> send_client = Entity.entity(balanceC, MediaType.APPLICATION_JSON);
                            response = target.request().post(send_client);
                            aux = response.readEntity(String.class);
                            System.out.println("RESPONSE9: " + aux);
                            response.close();

                            break;
                        case(10):

                            target = client.target("http://wildfly:8080/rest/services/myservice/totalCredits");
                            response = target.request().get();
                            aux = response.readEntity(String.class);
                            System.out.println("RESPONSE10: " + aux);

                            break;

                        case(11):

                            target = client.target("http://wildfly:8080/rest/services/myservice/totalPayments");
                            response = target.request().get();
                            aux = response.readEntity(String.class);
                            System.out.println("RESPONSE11: " + aux);

                            break;

                        case(12):

                            target = client.target("http://wildfly:8080/rest/services/myservice/totalBalance");
                            response = target.request().get();
                            aux = response.readEntity(String.class);
                            System.out.println("RESPONSE12: " + aux);

                            break;

                        case(13):

                            target = client.target("http://wildfly:8080/rest/services/myservice/billLastMonth");
                            response = target.request().get();
                            List<String> billLastMonth = response.readEntity(new GenericType<>() {
                            });
                            System.out.println("RESPONSE13:");
                            for (String p: billLastMonth) {
                                System.out.println(p);
                            }

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

