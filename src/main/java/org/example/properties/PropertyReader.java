package org.example.properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {
    private static final PropertyReader INSTANCE = new PropertyReader();
    private static Properties properties;

    private PropertyReader() {
        try (InputStream inputStream = PropertyReader.class.getClassLoader().getResourceAsStream("app.properties")) {
            if (inputStream == null) {
                System.out.println("Unable to find app.properties");
                properties = null;
            } else {
                properties = new Properties();
                properties.load(inputStream);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static PropertyReader getInstance() {
        return INSTANCE;
    }

    public String getConnectionUrl() {
        if (properties == null) {
            return null;
        }

        return new StringBuilder("jdbc:postgresql://")
                .append(properties.getProperty("postgres.db.host"))
                .append(":")
                .append(properties.getProperty("postgres.db.port"))
                .append("/")
                .append(properties.getProperty("postgres.db.database"))
                .append("?currentschema=public")
                .toString();
    }

    public String getUser() {
        if (properties == null) {
            return null;
        }

        return properties.getProperty("postgres.db.username");
    }

    public String getPassword() {
        if (properties == null) {
            return null;
        }

        return properties.getProperty("postgres.db.password");
    }
}
