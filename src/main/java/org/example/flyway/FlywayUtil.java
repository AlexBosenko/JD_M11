package org.example.flyway;

import org.example.properties.PropertyReader;
import org.flywaydb.core.Flyway;

public class FlywayUtil {
    public static void flywayMigrate() {
        String url = PropertyReader.getInstance().getConnectionUrl();
        String user = PropertyReader.getInstance().getUser();
        String password = PropertyReader.getInstance().getPassword();

        Flyway flyway = Flyway
                .configure()
                .dataSource(url, user, password)
                .load();
        flyway.migrate();
    }
}
