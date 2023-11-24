package org.example.dao;

import org.example.entity.Client;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ClientCrudService {
    private final Session session;

    public ClientCrudService(Session session) {
        this.session = session;
    }

    public Client createNewClient(String name) {
        Transaction transaction = session.beginTransaction();

        Client newClient = new Client();
        newClient.setName(name);
        session.persist(newClient);

        transaction.commit();

        return newClient;
    }

    public Client getClientById(long id) {
        return session.get(Client.class, id);
    }

    public List<Client> getAllClients() {
        return session.createQuery("from Client", Client.class).list();
    }

    public Client setClientNameById(Long id, String newName) {
        Transaction transaction = session.beginTransaction();

        Client client = session.get(Client.class, id);
        client.setName(newName);
        session.persist(client);

        transaction.commit();

        return client;
    }

    public void deleteClientById(Long id) {
        Transaction transaction = session.beginTransaction();

        Client client = session.get(Client.class, id);
        session.remove(client);

        transaction.commit();
    }
}
