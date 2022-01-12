package de.bs.jdbc.util;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

import org.nocrala.tools.texttablefmt.Table;

public class Util {
    private static Connection connection = null;

    public static Connection getConnection() {
        return connection;
    }

    public static void connect(String db) {
        try {
            String urlBuilder = getProperty("db.url") + db + getProperty("db.url.optionals");
            connection = DriverManager.getConnection(
                    urlBuilder, getProperty("db.user"), getProperty("db.password"));
            System.out.println("Connection opened!");
        } catch (SQLException | IOException e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void closeConnection(AutoCloseable connection) {
        try {
            connection.close();
            System.out.println("Connection closed!");
        } catch (Exception e) {
            System.out.println("Exception: Couldn't close connection!");
        }
    }

    public static String getProperty(String name) throws IOException {
        Properties prop = new Properties();
        prop.load(new FileReader("props/mariadb.properties"));
        return prop.getProperty(name);
    }

    public static void showResultSet(ResultSet resultSet) {
        try {
            ResultSetMetaData meta = resultSet.getMetaData();
            final int colmax = meta.getColumnCount();
            final Table t = new Table(colmax);
            for (int col = 1; col <= colmax; col++) {
                t.addCell(resultSet.getMetaData().getColumnLabel(col));
            }
            while (resultSet.next()) {
                for (int col = 1; col <= colmax; col++) {
                    final Object o = resultSet.getObject(col);
                    t.addCell(o.toString());
                }
            }
            System.out.println(t.render());

        } catch (Exception e) {
            System.out.println("Exception: Something went wrong!");
            e.printStackTrace();
        }
    }

    public static void showConnectionMetaData(Connection connection) {
        try {
            DatabaseMetaData dbmd = connection.getMetaData();
            System.out.println("Metadaten der Datenbank:");
            System.out.println("DB: " + dbmd.getDatabaseProductName());
            System.out.println("Version: " + dbmd.getDatabaseProductVersion());
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
    }
}
