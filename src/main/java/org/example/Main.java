package org.example;

import org.example.dao.ClientCrudService;
import org.example.dao.PlanetCrudService;
import org.example.entity.Client;
import org.example.entity.Planet;
import org.example.hibernate.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        HibernateUtil util = HibernateUtil.getInstance();

        Session session = util.getSessionFactory().openSession();

        //Client CRUD usage
        ClientCrudService clientCrudService = new ClientCrudService(session);

        System.out.println("1.1 Create new Client");
        Client newClient = clientCrudService.createNewClient("Tom Smth");
        System.out.println("newClient = " + newClient);

        System.out.println("1.2 Read Client by id");
        Long clientId = 2L;
        Client clientById = clientCrudService.getClientById(clientId);
        System.out.println("Client by ID = " + clientId + ": " + clientById);

        System.out.println("1.3 Read all Clients");
        List<Client> clients = clientCrudService.getAllClients();
        clients.forEach(client -> System.out.println("client = " + client));

        System.out.println("1.4 Update Client name");
        Client updatedClient = clientCrudService.setClientNameById(newClient.getId(), "Tom Cruise");
        System.out.println("updatedClient = " + updatedClient);

        System.out.println("1.5 Delete Client by id");
        clientCrudService.deleteClientById(newClient.getId());
        clients = clientCrudService.getAllClients();
        clients.forEach(client -> System.out.println("client = " + client));

        //Planet CRUD usage
        PlanetCrudService planetCrudService = new PlanetCrudService(session);

        System.out.println("2.1 Create new Planet");
        Planet newPlanet = planetCrudService.createNewPlanet("UR78", "Ur...");
        System.out.println("newPlanet = " + newPlanet);

        System.out.println("2.2 Read Planet by id");
        String planetId = "SAT77";
        Planet planetById = planetCrudService.getPlanetById(planetId);
        System.out.println("planetById = " + planetId + ": " + planetById);

        System.out.println("2.3 Read all Planets");
        List<Planet> planets = planetCrudService.getAllPlanets();
        planets.forEach(planet -> System.out.println("planet = " + planet));

        System.out.println("2.4 Update Planet name");
        Planet updatedPlanet = planetCrudService.setPlanetNameById(newPlanet.getId(), "Uranus");
        System.out.println("updatedPlanet = " + updatedPlanet);

        System.out.println("2.5 Delete Planet by id");
        planetCrudService.deletePlanetById(newPlanet.getId());
        planets = planetCrudService.getAllPlanets();
        planets.forEach(planet -> System.out.println("planet = " + planet));

        session.close();
        util.close();
    }
}