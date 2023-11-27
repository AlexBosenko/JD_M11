package org.example;

import org.example.dao.ClientCrudService;
import org.example.dao.PlanetCrudService;
import org.example.dao.TicketCrudService;
import org.example.entity.Client;
import org.example.entity.Planet;
import org.example.entity.Ticket;
import org.example.flyway.FlywayUtil;
import org.example.hibernate.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        FlywayUtil.flywayMigrate();
        HibernateUtil util = HibernateUtil.getInstance();

        Session session = util.getSessionFactory().openSession();

        //Client CRUD usage
        ClientCrudService clientCrudService = new ClientCrudService(session);

        System.out.println("=============1.1 Create new Client=============");
        Client newClient = clientCrudService.createNewClient("Tom Smth");
        System.out.println("newClient = " + newClient);

        System.out.println("=============1.2 Read Client by id=============");
        Long clientId = 2L;
        Client clientById = clientCrudService.getClientById(clientId);
        System.out.println("Client by ID = " + clientId + ": " + clientById);

        System.out.println("=============1.3 Read all Clients=============");
        List<Client> clients = clientCrudService.getAllClients();
        clients.forEach(client -> System.out.println("client = " + client));

        System.out.println("=============1.4 Update Client name=============");
        Client updatedClient = clientCrudService.setClientNameById(newClient.getId(), "Tom Cruise");
        System.out.println("updatedClient = " + updatedClient);

        System.out.println("=============1.5 Delete Client by id=============");
        clientCrudService.deleteClientById(newClient.getId());
        clients = clientCrudService.getAllClients();
        clients.forEach(client -> System.out.println("client = " + client));

        //Planet CRUD usage
        PlanetCrudService planetCrudService = new PlanetCrudService(session);

        System.out.println("=============2.1 Create new Planet=============");
        Planet newPlanet = planetCrudService.createNewPlanet("UR78", "Ur...");
        System.out.println("newPlanet = " + newPlanet);

        System.out.println("=============2.2 Read Planet by id=============");
        String planetId = "SAT77";
        Planet planetById = planetCrudService.getPlanetById(planetId);
        System.out.println("planetById = " + planetId + ": " + planetById);

        System.out.println("=============2.3 Read all Planets=============");
        List<Planet> planets = planetCrudService.getAllPlanets();
        planets.forEach(planet -> System.out.println("planet = " + planet));

        System.out.println("=============2.4 Update Planet name=============");
        Planet updatedPlanet = planetCrudService.setPlanetNameById(newPlanet.getId(), "Uranus");
        System.out.println("updatedPlanet = " + updatedPlanet);

        System.out.println("=============2.5 Delete Planet by id=============");
        planetCrudService.deletePlanetById(newPlanet.getId());
        planets = planetCrudService.getAllPlanets();
        planets.forEach(planet -> System.out.println("planet = " + planet));

        //Ticket CRUD usage
        TicketCrudService ticketCrudService = new TicketCrudService(session);

        System.out.println("=============3.1 Create new Ticket=============");
        Long ticketClientId = 2L;
        Client ticketClientById = clientCrudService.getClientById(ticketClientId);
        System.out.printf("Client by ID = %s: %s", ticketClientId, ticketClientById);
        String fromPlanetId = "VEN1";
        Planet fromPlanetById = planetCrudService.getPlanetById(fromPlanetId);
        String toPlanetId = "NEPT";
        Planet toPlanetById = planetCrudService.getPlanetById(toPlanetId);

        Ticket newTicket = ticketCrudService.createNewTicket(clientById, fromPlanetById, toPlanetById);
        System.out.println("newTicket = " + newTicket);

        System.out.println("=============3.2 Read Ticket by Client=============");
        ticketClientId = 3L;
        ticketClientById = clientCrudService.getClientById(ticketClientId);
        List<Ticket> clientTickets = ticketCrudService.getClientTickets(ticketClientById);
        clientTickets.forEach(clientTicket -> System.out.println("client ticket = " + clientTicket));

        System.out.println("=============3.3 Read all Tickets=============");
        List<Ticket> tickets = ticketCrudService.getAllTickets();
        tickets.forEach(ticket -> System.out.println("ticket = " + ticket));

        System.out.println("=============3.4 Update client Ticket=============");
        Long inClientId = 2L;
        Client inClient = clientCrudService.getClientById(inClientId);
        Long outClientId = 10L;
        Client outClient = clientCrudService.getClientById(outClientId);
        boolean updateResult = ticketCrudService.setClientById(inClient, outClient);
        if (updateResult) {
            System.out.println("All client " + outClient.getName() + " tickets");
            clientTickets = ticketCrudService.getClientTickets(outClient);
            clientTickets.forEach(clientTicket -> System.out.println("client ticket = " + clientTicket));
        }

        System.out.println("=============3.5 Delete Ticket by id=============");
        ticketCrudService.deleteTicketById(newTicket.getId());
        tickets = ticketCrudService.getAllTickets();
        tickets.forEach(ticket -> System.out.println("ticket = " + ticket));

        session.close();
        util.close();
    }
}