package de.bs.jdbc.exercise.custom;

import de.bs.jdbc.util.MYSQLManager;

/**
 * exercise 2.
 */
public class Jdbc02Connect {

    public static void main(String[] args) throws Exception {
        try {

            // drop table if exists lieferant
            System.out.println(MYSQLManager.Statements.update("DROP TABLE IF EXISTS LIEFERANT"));

            // create table lieferant
            System.out.println(MYSQLManager.Statements.update(
        "CREATE TABLE LIEFERANT (" +
                    "id INTEGER UNSIGNED AUTO_INCREMENT," +
                    "name VARCHAR(225) NOT NULL," +
                    "PRIMARY KEY (id)" +
                ");"
            ));

            // insert into lieferant

            System.out.println(MYSQLManager.Statements.batch(new String[]{
                    "INSERT INTO LIEFERANT SET name =\"Niedermair\"",
                    "INSERT INTO LIEFERANT SET name =\"Müller\"",
                    "INSERT INTO LIEFERANT SET name = \"Maier\""
            }));
        }
        catch (Exception e) { System.out.println(e); }
        finally { MYSQLManager.close(); }
    }
}
