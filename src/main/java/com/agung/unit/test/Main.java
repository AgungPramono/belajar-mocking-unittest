package com.agung.unit.test;

import com.agung.unit.test.helper.ConnectionHelper;
import org.flywaydb.core.Flyway;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hallo");

        Connection con = ConnectionHelper.connect();
        if (con != null){
            System.out.println("Connection Success");
        }
        Flyway flyway = Flyway.configure().dataSource("jdbc:h2:~/unittest","sa","").load();
        flyway.migrate();
    }

    public static void identifierVariable(){
        Integer a = 2;
        Integer b= 0;

        Integer hasil = a + b;
    }
}
