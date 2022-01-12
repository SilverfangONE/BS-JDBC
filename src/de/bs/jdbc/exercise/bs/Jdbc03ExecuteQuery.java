package de.bs.jdbc.exercise.bs;

import de.bs.jdbc.util.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * Beispiel: Jdbc03ExecuteQuery
 *
 * @author <a href="mailto:mgn.schule@gmx.de">Michael Niedermair</a>
 * @version $Revision: 1117 $
 */
public class Jdbc03ExecuteQuery {

    public static void main(final String[] args) {

        System.out.println("JDBC Execute Query");
        System.out.println("==================");

        Connection connection = null;
        Statement statement;

        try {
            Util.connect("javatest");
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
