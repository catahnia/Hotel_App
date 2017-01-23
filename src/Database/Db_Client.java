package Database;

import Basic.Client;
import Controller.BasicController;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by Mitsos on 28/05/16.
 */
public class Db_Client extends Database {
    private ResultSet rs;


    public Db_Client() {
        super();
    }

    //Επιστρεψει εναν πελατη, μαζι με ολα τα στοιχεια του, απ τη βαση (αναζητηση βαση επιθέτου)
    public Client getClient (String s){
        Client client=new Client();


        try {
            rs=getSt().executeQuery("SELECT * From Clients WHERE lname='"+s+"';");

        }
        catch (SQLException e){
            System.out.println("SQL Exception"+e);

        }
        try {
            while (rs.next())
            {
                client.setClient_Code(rs.getInt(1));
                client.setClient_Lname(rs.getString(3));
                client.setClient_Fname(rs.getString(2));
                client.setClient_Phone(rs.getString(4));
            }

        }
        catch (SQLException e){

            System.out.println("SQL Exception"+e);

        }


        return client;


    }

    //Δημιουργει εναν νεο πελατη στη βαση
    public  void insertClient (Client client)
    {
        System.out.println(client.getClient_Fname());
        try {
            getSt().executeUpdate("INSERT INTO Clients (fname, lname, phone) VALUES ('"  + client.getClient_Fname() +"','"+client.getClient_Lname()+"','"+client.getClient_Phone()+ "')");
        }
        catch (SQLException e){
            System.out.println("SQL Exception"+e);

        }
    }

    //Επιστρεψει εναν πελατη, μαζι με ολα τα στοιχεια του, απ τη βαση (αναζητηση βαση κωδικου πελατη)

    public Client getClient (int s){
        Client client=new Client();


        try {
            rs=getSt().executeQuery("SELECT * From Clients WHERE idClient='"+s+"';");

        }
        catch (SQLException e){
            System.out.println("SQL Exception"+e);

        }
        try {
            while (rs.next())
            {
                client.setClient_Code(rs.getInt(1));
                client.setClient_Lname(rs.getString(3));
                client.setClient_Fname(rs.getString(2));
                client.setClient_Phone(rs.getString(4));
            }

        }
        catch (SQLException e){

            System.out.println("SQL Exception"+e);

        }

        return client;
    }

    //Αλλαγη στοιχειων πελατη
    public void changeInfo(Client c) {

        try {
            getSt().executeUpdate("UPDATE Clients SET fname='"+c.getClient_Fname()+"',lname='"+c.getClient_Lname()+"',phone='"+c.getClient_Phone()+"' WHERE idClient='"+c.getClient_Code()+"';");
        }
        catch (SQLException e){
            System.out.println("SQL Exception"+e);

        }

    }
}
