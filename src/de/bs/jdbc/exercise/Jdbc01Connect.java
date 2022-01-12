package de.bs.jdbc.exercise;

import de.bs.jdbc.util.Util;

import java.sql.Connection;

public class Jdbc01Connect {
    public static void main(String[] args) {
        System.out.println("JDBC Connection Test");
        System.out.println("====================");
        System.out.println("MySQL JDBC-Treiber geladen!");

        Util.connect("javatest");
        Connection connection = Util.getConnection();
        Util.showConnectionMetaData(connection);

        Util.closeConnection(connection);
    }
}
