package de.nm.jdbc.ueb.exercises;

import de.nm.jdbc.ueb.util.MYSQLManager;

import java.sql.Connection;

/**
 * exercise 3. print
 */

public class jdbc03Connect {

    public static void main(String[] args) {
        try {
            Connection con = MYSQLManager.getSessionDB();

            System.out.println(MYSQLManager.Statements.query("desc LIEFERANT"));
            MYSQLManager.View.print(MYSQLManager.Statements.query("SELECT * FROM LIEFERANT ORDER BY id DESC"));
        } catch (Exception e) { e.printStackTrace(); }
    }
}
