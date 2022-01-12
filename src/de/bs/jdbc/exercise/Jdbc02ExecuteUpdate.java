package de.bs.jdbc.exercise;

import de.bs.jdbc.util.Util;

import java.sql.*;

/**
 * Jdbc02ExecuteUpdate
 *
 * @author kaoeclya | Robin
 */
public class Jdbc02ExecuteUpdate {

    public static void main(final String[] args) throws SQLException {
        System.out.println("JDBC Execute Update\n===================");

        Connection connection = null;
        Statement statement = null;

        try {
            connection = Util.getConnection();
            statement = connection.createStatement();

            String sql1 = "DROP TABLE IF EXISTS LIEFERANT;";
            int rowsUpdated = statement.executeUpdate(sql1);
            System.out.println("Statement 1: " + rowsUpdated + " rows updated");

            String sql2 = "CREATE TABLE LIEFERANT (id INTEGER UNSIGNED PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255) NOT NULL,created TIMESTAMP, updated TIMESTAMP DEFAULT {ts '2013-01-01 01:00:00.0'});";
            rowsUpdated = statement.executeUpdate(sql2);
            System.out.println("Statement 2: " + rowsUpdated + " rows updated");

            statement.addBatch("INSERT INTO LIEFERANT SET name='Niedermair'");
            statement.addBatch("INSERT INTO LIEFERANT SET name='Müller'");
            statement.addBatch("INSERT INTO LIEFERANT SET name='Maier'");

            int[] rowsu = statement.executeBatch();
            System.out.println("Batch Statements:");
            for (int i = 0; i < rowsu.length; i++) {
                String sb3 = " - Statement " +
                        (i + 1) +
                        ": " +
                        rowsu[i] +
                        " rows updated";
                System.out.println(sb3);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                statement.close();
                System.out.println("Statement successfully closed.");
                Util.closeConnection(connection);
            } catch (SQLException e) {
                System.out.println("SQLException: Couldn't close statement!");
                e.printStackTrace();
            }
        }
    }
}
