package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private static Database database_instance = null;

    public Connection con;

    private Database(){
            try {
                con = DriverManager.getConnection(
                        "jdbc:oracle:thin:@localhost:1521:xe", "dba", "sql");
            }
            catch (SQLException e){
                System.out.println("Nu s-a putut conecta la baza de date");
                e.printStackTrace();
            }
        }

        public void closeConnection() throws SQLException {
        if(con != null)
            con.close();
        }

        public static Database getInstance(){
            if(database_instance == null)
                database_instance = new Database();
            return database_instance;
        }


}
