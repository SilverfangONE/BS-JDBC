package de.bs.jdbc.util;

import org.nocrala.tools.texttablefmt.Table;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.HashMap;
import java.util.Properties;

/**
 * Manager for connecting to MYSQL-DB
 */

public class MYSQLManager {

    /**
     * MYSQL-DB session singleton object for executing queries
     */

    private static Connection session = connectionFactory();

    private MYSQLManager() throws SQLException, IOException {}

    /**
     * Creates Connection with DriverManager with args from property-file
     *
     * @return established connection object
     * @throws IOException
     * @throws SQLException
     */

    public static Connection connectionFactory() {
        try{
            Properties prop = loadProps();
            System.out.println(".. try to connect");
            Connection con = DriverManager.getConnection(
                    prop.getProperty("DBURL"),
                    prop.getProperty("DBUSER"),
                    prop.getProperty("DBPW")
            );
            System.out.println(".. connected with " + con.getMetaData().getDatabaseProductName());
            System.out.println(getMetaData(con.getMetaData()));
            return con;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * reconnect to database if the session was closed
     *
     * @return boolean returns false if a session is already opened
     */

    public static boolean openSession() {
        if(session == null) {
            connectionFactory();
            return true;
        }
        return false;
    }

    /**
     * close the current session
     *
     * @return true if the session has been closed, otherwise false
     * @throws Exception
     */

    public static boolean close() throws Exception {
        return close(session);
    }

    /**
     * close given autocloseable object
     *
     * @param closeable
     * @return true if the session has been closed, otherwise false
     * @throws Exception
     */

    private static boolean close(AutoCloseable closeable) throws Exception {
        if (closeable == null) return false;
        else closeable.close();
        return true;
    }

    /**
     * Convert MetaData from current session consnection to a string
     *
     * @return MetaData from current MySQL-DB Session
     * @throws SQLException
     */

    public static String getMetaData() throws SQLException {
        return getMetaData(session.getMetaData());
    }

    /**
     * Convert MetaData from given DatabaseMetaData object to a string
     *
     * @param metaData DatabaseMetaData object which should be converted to a string
     * @return Metadata as string
     * @throws SQLException
     */

    private static String getMetaData(DatabaseMetaData metaData) throws SQLException {
    return new StringBuilder().append("Meta-Daten der " + metaData.getDatabaseProductName() + " DB:")
                .append("\n")
                .append("DB: " + metaData.getDatabaseProductName())
                .append("\n")
                .append("Version: " + metaData.getDatabaseProductVersion())
                .append("\n")
                .append("Treiber: " + metaData.getDriverName())
                .toString();
    }

    /**
     * loads props from properties file
     *
     * @return properties object
     * @throws IOException
     */

    private static Properties loadProps() throws IOException {
        Properties prop = new Properties();
        prop.load(new FileReader("props/Connection.properties"));
        return prop;
    }

    // getter && setter

    public static Connection getSessionDB() {
        return session;
    }

    private static void setSession(Connection ses) {
        session = ses;
    }

    // print table in cmd out

    /**
     * Prints Result-Sets in cmd out
     */

    public static class View {

        /**
         * prints out result set as a table format on the cmd
         *
         * @param res result set witch should get printed out
         * @throws SQLException
         */

        public static void print(ResultSet res) throws SQLException {

            ResultSetMetaData meta = res.getMetaData();

            final int colsMax = meta.getColumnCount();

            Table table = new Table(colsMax);

            for (int i = 1; i < colsMax; i++)
                try { table.addCell(meta.getColumnLabel(i)); }
                catch (Exception e) { table.addCell("null"); };

            while (res.next())
                for (int i = 1; i <= colsMax; i++)
                    try { table.addCell(res.getObject(i).toString());}
                    catch (Exception e) { table.addCell("null"); }

            System.out.println(table.render());;
        }
    }

    // statements

    /**
     * Execute-SQL-Statements for current session
     */

    public static class Statements {

        // query

        /**
         * executes SQL-Statement as Query
         *
         * @param statement sql-statement
         * @return Set with result data
         * @throws SQLException
         */

        public static ResultSet query(String statement) throws SQLException {
            return session.createStatement().executeQuery(statement);
        }

        /**
         * executes SQL-Statement as Query with opts
         *
         * @param statement sql-statement
         * @param pointerOpt pointer behaviour
         * @param editMode read only or updatable
         * @return Set with result data
         * @throws SQLException
         */

        public static ResultSet query(String statement, int pointerOpt, int editMode) throws SQLException {
            return session.createStatement(pointerOpt, editMode).executeQuery(statement);
        }

        // update

        /**
         * executes SQL-Statement as Update
         *
         * @param statement sql-statement
         * @return number of changed rows/datasets
         * @throws SQLException
         */

        public static int update(String statement) throws SQLException {
            return session.createStatement().executeUpdate(statement);
        }

        // execute for all

        /**
         * executes SQL-Statements
         *
         * @param statement sql-statements
         * @throws SQLException
         */

        public static void execute(String statement) throws SQLException {
            session.createStatement().execute(statement);
        }

        // batch for multiple statements

        /**
         * executes multiple sql statements
         *
         * @param statements as string array
         * @throws SQLException
         */

        public static int[] batch(String[] statements) throws SQLException {
            Statement st = session.createStatement();
            for(String statement: statements) st.addBatch(statement);
            return st.executeBatch();
        }
    }

}
