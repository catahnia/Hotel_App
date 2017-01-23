package Database;

import Basic.Room;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mitsos on 29/05/16.
 */
public class Db_Room extends Database{

    private ResultSet rs;

    public Db_Room() {
        super();
    }

    //Κανει αναζητη για διαθεσιμα δωματια βαση κρητηριων και επιστρεφει μια λιστα με τα διαθεσιμα
    public List<Room> searchRooms (String arrival, String departure, int roomType, int noRooms){
        List<Room> rooms = new ArrayList<>();

        try {
            rs=getSt().executeQuery("SELECT * FROM Rooms WHERE room_type ="+roomType+" AND idRooms NOT IN " +
                    "(SELECT Reservations.roomid FROM Reservations inner JOIN Bookings" +
                    "  ON  Reservations.bookid=Bookings.bookingID WHERE " +
                    " (Bookings.arrivalDate " +
                    "BETWEEN STR_TO_DATE('"+arrival +"','%d/%m/%Y') AND STR_TO_DATE('"+departure +"','%d/%m/%Y'))" +
                    "OR (Bookings.departureDate BETWEEN STR_TO_DATE('"+arrival +"','%d/%m/%Y') AND STR_TO_DATE('"+departure +"','%d/%m/%Y')));");

        }
        catch (SQLException e){
            System.out.println("SQL Exception search 1"+e);

        }
        try {
            while (rs.next())
            {
                Room r=new Room();
                r.setRoomId(rs.getInt(1));
                r.setRoomType(rs.getInt(2));
                r.setRoomCost(rs.getInt(3));
                rooms.add(r);
            }

        }
        catch (SQLException e){

            System.out.println("SQL Exception search 2"+e);

        }

        System.out.println(rooms);
        return rooms;
    }

    //χρησιμοποιειται για τον υπολογισμο του διαστηματος διαμονης και του κοστους διαμονης
    public int costCalculation(String a, String b, List<Integer> rooms)
    {
        int c=0;
        int cost=0;
        try {
            rs=getSt().executeQuery("SELECT datediff(STR_TO_DATE('"+b +"','%d/%m/%Y'),STR_TO_DATE('"+a +"','%d/%m/%Y'))");
        }
        catch (SQLException e){
            System.out.println("SQL Exception Cost calculation 1"+e);

        }
        try {
            while (rs.next())
            {
                c=rs.getInt(1);
            }

        }
        catch (SQLException e){

            System.out.println("SQL Exception Cost Calculation 2"+e);

        }
        for(Integer r : rooms)
        {
            try {
                rs=getSt().executeQuery("SELECT cost from Rooms WHERE idRooms="+r+";");
            }
            catch (SQLException e){
                System.out.println("SQL Exception Cost calculation 1"+e);

            }
            try {
                while (rs.next())
                {
                    cost+=rs.getInt(1);
                }

            }
            catch (SQLException e){

                System.out.println("SQL Exception Cost Calculation 2"+e);

            }


        }

        return  c*cost;
    }

    //κανει και αυτη αναζητηση αλλα στις δοθεντες ημ/νιες προσθετει 3 μερες και αναζητα ανεξαρτητως τυπου δωματιου

    public List<Room> searchAlternatives (String arrival,String departure, int roomType, int noRooms)
    {
        List<Room> rooms = new ArrayList<>();

        try {
            rs=getSt().executeQuery("SELECT * FROM Rooms WHERE idRooms NOT IN " +
                    "(SELECT Bookings.roomID FROM Bookings INNER JOIN Reservations" +
                    " ON  Bookings.bookingID = Reservations.bookid WHERE " +
                    " (Bookings.arrivalDate " +
                    "BETWEEN DATE_ADD(STR_TO_DATE('"+arrival +"','%d/%m/%Y'),INTERVAL 3 DAY )" +
                    "AND DATE_ADD(STR_TO_DATE('"+departure +"','%d/%m/%Y'),INTERVAL 3 DAY)" +
                    "OR (Bookings.departureDate BETWEEN DATE_ADD(STR_TO_DATE('"+arrival +"','%d/%m/%Y'),INTERVAL 3 DAY )" +
                    "AND DATE_ADD(STR_TO_DATE('"+arrival +"','%d/%m/%Y'),INTERVAL 3 DAY))));");

        }
        catch (SQLException e){
            System.out.println("SQL Exception alt search 1"+e);

        }
        try {
            while (rs.next())
            {
                Room r=new Room();
                r.setRoomId(rs.getInt(1));
                r.setRoomType(rs.getInt(2));
                r.setRoomCost(rs.getInt(3));
                rooms.add(r);
            }

        }
        catch (SQLException e){

            System.out.println("SQL Exception alt search 2"+e);

        }


        return rooms;

    }

    //επιστρεφει τα δωματια μιας κρατησης

    public List<Room> getRoom(int a){
        List<Room> rooms=new ArrayList<>();
        Room room=new Room();

        try {
            rs=getSt().executeQuery("Select * from Rooms inner join Reservations on idRooms=roomid where bookid="+a+";");

        }
        catch (SQLException e){
            System.out.println("SQL Exception"+e);

        }
        try {
            while (rs.next())
            {
                room.setRoomId(rs.getInt(1));
                room.setRoomType(rs.getInt(2));
                room.setRoomCost(rs.getInt(3));
                rooms.add(room);
            }

        }
        catch (SQLException e){

            System.out.println("SQL Exception"+e);

        }

        return rooms;


    }

    //προσθετει ενα δωματιο στο πινακα Reservations (λιστα δωματιων κρατησης)

    public void addRoom(int bookid, int roomid)
    {
        try{
            getSt().executeUpdate("INSERT INTO Reservations (bookid, roomid) VALUES ("+bookid+","+roomid+");");

        }
        catch (SQLException e) {
            System.out.println("SQl Exception "+e);
        }
    }


}
