package de.bs.jdbc.exercise;

import de.bs.jdbc.util.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Jdbc03ExecuteQuery
 *
 * @author kaoeclya | Robin
 */
public class Jdbc03ExecuteQuery {

    public static void main(final String[] args) {
        System.out.println("JDBC Execute Query\n==================");

        Connection connection = null;
        Statement statement;

        try {
            connection = Util.getConnection();
            statement = connection.createStatement();

            String sql1 = "DESC LIEFERANT";
            ResultSet rs = statement.executeQuery(sql1);
            Util.showResultSet(rs);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            Util.closeConnection(connection);
        }
    }
}