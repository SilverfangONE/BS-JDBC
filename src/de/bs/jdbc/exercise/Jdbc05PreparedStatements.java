package de.bs.jdbc.exercise;

import de.bs.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Jdbc05PreparedStatements {

   public static void main(final String[] args) {

      System.out.println("JDBC Prepared Statements");
      System.out.println("========================");

      Connection connection = null;
      PreparedStatement pst = null;

      try {
         Util.connect("fb2");
         connection = Util.getConnection();
         // 2. Prepared Statement erzeugen
         pst = connection
                  .prepareStatement("SELECT * from SPIELER WHERE Geschlecht=?");

         // 3. Werte zuweisen
         // TODO
         pst.execute();

         // 4. Metadaten ermitteln
         // TODO

         // 5. Resultset ausgeben
         // TODO

      } catch (SQLException e) {
         System.out.println(e.getMessage());
         e.printStackTrace();
      } finally {
         Util.closeConnection(connection);
      }
   }
}
