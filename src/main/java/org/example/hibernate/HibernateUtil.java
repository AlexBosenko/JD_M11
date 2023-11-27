package org.example.hibernate;

import org.example.entity.Client;
import org.example.entity.Planet;
import org.example.entity.Ticket;
import org.example.properties.PropertyReader;
import org.flywaydb.core.Flyway;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final HibernateUtil INSTANCE;

    static {
        INSTANCE = new HibernateUtil();
    }

    private static SessionFactory sessionFactory;

    private HibernateUtil() {
        String url = PropertyReader.getInstance().getConnectionUrl();
        String user = PropertyReader.getInstance().getUser();
        String password = PropertyReader.getInstance().getPassword();
        Flyway flyway = Flyway
                .configure()
                .dataSource(url, user, password)
                .load();
        flyway.migrate();

        sessionFactory = new Configuration()
                .addAnnotatedClass(Client.class)
                .addAnnotatedClass(Planet.class)
                .addAnnotatedClass(Ticket.class)
                .buildSessionFactory();
    }

    public static HibernateUtil getInstance() {
        return INSTANCE;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void close() {
        sessionFactory.close();
    }
}
