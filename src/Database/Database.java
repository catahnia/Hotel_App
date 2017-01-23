package Database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Mitsos on 25/05/16.
 */
public class Database {

    private Connection conn;
    private Statement st;

    public Database(){
        try {
            connect();
        }
        catch (Exception e){
            System.out.println("Cound not find Driver"+e);

        }
    }

    public void connect() throws Exception {

        if (conn != null) return;

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Cound not find Driver"+e);
        }

        String connectionURL = "jdbc:mysql://localhost:3306/hotel?autoReconnect=true&useSSL=false";

        conn = DriverManager.getConnection(connectionURL, "hotel_user", "hotel");

        st= conn.createStatement();
    }

    public void close() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Connection getConn() {
        return conn;
    }

    public Statement getSt() {
        return st;
    }

    public void setSt(Statement st) {
        this.st = st;
    }


}
