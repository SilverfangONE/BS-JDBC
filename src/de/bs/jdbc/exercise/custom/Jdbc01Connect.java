package de.bs.jdbc.exercise.custom;

import de.bs.jdbc.util.MYSQLManager;

import java.sql.Connection;

/**
 * exercise 1.
 */
public class Jdbc01Connect {

    public static void main(String[] args) throws Exception {
        try {
            Connection con = MYSQLManager.getSession();
        } catch (Exception e) { e.printStackTrace(); }
        finally { MYSQLManager.close(); }
    }
}
