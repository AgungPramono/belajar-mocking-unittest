package com.agung.test;

import org.flywaydb.core.Flyway;
import org.junit.Before;

public class BaseTest {

    public static void createSkemaData(){
        Flyway flyway =
                Flyway.configure()
                        .dataSource("jdbc:h2:~/unittest","sa","")
                        .load();
        flyway.migrate();
    }

}
