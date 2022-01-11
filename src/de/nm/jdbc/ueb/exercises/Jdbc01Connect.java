package de.nm.jdbc.ueb.exercises;

import de.nm.jdbc.ueb.util.MYSQLManager;

import java.sql.Connection;

/**
 * exercise 1.
 */
public class Jdbc01Connect {

    public static void main(String[] args) {
        try { Connection con = MYSQLManager.getSessionDB(); }
        catch (Exception e) { e.printStackTrace(); }
    }
}
