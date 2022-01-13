package de.bs.jdbc.exercise.custom;

import de.bs.jdbc.util.MYSQLManager;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *  1) create table resultTest(id, name, gehalt)
 *
 *  Folgende Aufgaben über das ResultSet ändern/einfügen:
 *
 *  2) insert into (1 Maier 100), (2 Huber 500)
 *
 *  3) Geahlt von Maier um 5 % erhöhen
 *
 *  4) Mirarbeiter Huber löschen
 *
 *
 */

public class jdbc01cosConnect {

    private static String[] setup = {
            "DROP TABLE IF EXISTS RESULTTEST",
            "CREATE TABLE RESULTTEST (" +
                "id INT AUTO_INCREMENT, " +
                "name VARCHAR(50), " +
                "gehalt DECIMAL(10, 2), " +
                "PRIMARY KEY (id)" +
            ")"
    };

    public static void main(String[] args) throws SQLException {

        // 1.)

        MYSQLManager.Statements.batch(setup);
        ResultSet resultSet = MYSQLManager.Statements.query("SELECT * FROM RESULTTEST", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

        // 2.)

        resultSet.moveToInsertRow();
        resultSet.updateString("name", "Maier");
        resultSet.updateDouble("gehalt", 100.00);
        resultSet.insertRow();

        resultSet.moveToInsertRow();
        resultSet.updateString("name", "Huber");
        resultSet.updateDouble("gehalt", 500.00);
        resultSet.insertRow();

        // 3.)

        resultSet.updateDouble("gehalt", resultSet.getObject("gehalt", Double.class) * 1.05);

        // 4.)

        resultSet.last();

        resultSet.deleteRow();

    }
}
