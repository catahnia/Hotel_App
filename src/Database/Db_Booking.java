package Database;

import Basic.Booking;
import Basic.Room;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Mitsos on 05/06/16.
 */
public class Db_Booking extends Database{


    private ResultSet rs;

    public Db_Booking(){
        super();
    }

    //Δημιουργια μιας κρατησης στη βαση και εγγραφη των δωματιων της κρατησης στον Πινακα Reservations
    public void insertBooking(Booking b)
    {
        try {
            getSt().executeUpdate("INSERT INTO Bookings (bookingDate, arrivalDate, departureDate, clientID) VALUES (STR_TO_DATE('" +
                    b.getBookingDate() +"','%d/%m/%Y'),STR_TO_DATE('"+b.getArrivalDate()+"','%d/%m/%Y'),STR_TO_DATE('"+b.getDepartureDate()+"','%d/%m/%Y'),"+
                    b.getBookingClient()+
                    ")");
            int a=getBookID();
            for (Room r :b.getBookingRooms()) {

                getSt().executeUpdate("INSERT INTO reservations (bookId,roomId) Values (" + a + "," + r.getRoomId() + ");");
            }



        }
        catch (SQLException e){
            System.out.println("SQL Exception insertBooking"+e);
        }

    }

    //Ανακτηση μιας κρατησης απ τη βαση, βαση κωδικου επιστρεφει εναν θετικο, δειγμα του οτι βρεθηκε αυτη η εγγραση

    public int getBooking(int a) {
        int c=0;

        try{
            rs=getSt().executeQuery("SELECT * FROM Bookings where bookingID="+a+";");
        }
        catch (SQLException e){
            System.out.println("SQL Exception getBooking1"+e);

        }
        try {
            while (rs.next()) {

                c++;

            }
        }
        catch (SQLException e)
        {
            System.out.println("SQL Exception getBooking 2"+e);
        }

        return c;
    }
    //διαγράφει μια κρατηση καθως και τα δωματια της κρατησης απ τον πιανκα Reservations

    public void deleteBooking(int a) {
        try
        {
            getSt().executeUpdate("DELETE FROM Reservations WHERE bookid="+a+";");

        }
        catch (SQLException e){
            System.out.println("SQL Exception delete a"+e);
        }

        try
        {
            getSt().executeUpdate("DELETE FROM Bookings WHERE bookingID="+a+";");

        }
        catch (SQLException e){
            System.out.println("SQL Exception delete b"+e);
        }
    }

    //Μεταφερει μια κρατηση στον πινακα Stored_Bookings καθως και τα δωματια στον Stored_Reservations

    public void storeBooking(int a) {


        try
        {
            getSt().executeUpdate("insert into Strored_Bookigs (bookingID,clientID,bookingDate,arrivalDate,departureDate)" +
                    "(select bookingID,clientID, bookingDate, arrivalDate, departureDate From Bookings where bookingID='"+a+"');");

        }
        catch (SQLException e){
            System.out.println("SQL Exception store b"+e);
        }
        try
        {
            getSt().executeUpdate("INSERT INTO Stored_Reservations (storedBookingId,bookid,roomId) " +
                    "select idStrored_Bookigs, bookingID, roomID from Strored_Bookigs inner join Reservations on bookingID=bookid where bookingID='"+a+"';");

        }
        catch (SQLException e){
            System.out.println("SQL Exception store a"+e);
        }
    }

    //Δημιουργει ενα check-in για τον πελατη

    public void checkIn(int a){

        try{
            getSt().executeUpdate("UPDATE Bookings SET checkIn=1 WHERE bookingID="+a+";");

        }
        catch (SQLException e){
            System.out.println("SQL Exception"+e);
        }
    }

    //Επιστρεφει μια κρατηση απ τη Βαση. Ταυτοχρονα παιρνει και τα στοιχεια για τον πελατη της κρατησης καθως και τα δωματια μαζι με τα στοιχεια τους

    public Booking getBook(int a)
    {

        Booking booking=new Booking();
        Db_Client dbc=new Db_Client();
        Db_Room dbr=new Db_Room();

        try{
            rs=getSt().executeQuery("SELECT * FROM Bookings where bookingID="+a+";");
        }
        catch (SQLException e){
            System.out.println("SQL Exception"+e);

        }

        try {
            while (rs.next()) {

                SimpleDateFormat model = new SimpleDateFormat("dd/MM/YYYY");

                booking.setBookingId(rs.getInt(1));
                booking.setBookingClient(dbc.getClient(rs.getInt(2)));
                booking.setBookingRooms(dbr.getRoom(rs.getInt(1)));


                String book=model.format(rs.getDate(3));
                booking.setBookingDate(book);

                String arrival=model.format(rs.getDate(4));
                booking.setArrivalDate(arrival);

                String departure=model.format(rs.getDate(5));
                booking.setDepartureDate(departure);
                booking.setCheckIn(rs.getInt(6));

                System.out.println(booking.getBookingRooms());

            }
        }
        catch (SQLException e)
        {
            System.out.println("SQL Exception"+e);
        }

        return booking;
    }

    //Αλλαζει τις ημερομηνιες μιας κρατησης
    public void changeDates(String arrival, String departure, int id){
        try
        {
            getSt().executeUpdate("UPDATE Bookings SET arrivalDate=STR_TO_DATE('"+arrival+"','%d/%m/%Y'),departureDate=STR_TO_DATE('"+departure+"','%d/%m/%Y') WHERE bookingID="+id+";");

        }
        catch (SQLException e){
            System.out.println("SQL Exception"+e);
        }
    }

    //Γραφει στη βαση τα νεα δωματια, αν εχει ζητηθει αλλαγη τυπου

    public void changeRoomType(List<Room> palia ,List<Room> nea, int id){
        int i=0;

        try {
            getSt().executeUpdate("DELETE FROM Reservations WHERE bookid="+id+";");

        } catch (SQLException e) {
            System.out.println("SQL Exception" + e);
        }


        for (Room r : palia) {
            try {
                getSt().executeUpdate("INSERT INTO Reservations (bookid, roomid) VALUES ("+id+","+nea.get(i).getRoomId()+");");

            } catch (SQLException e) {
                System.out.println("SQL Exception" + e);
            }
            i++;


        }
    }

    //επιτρεφει το τελευταιο bookID που υπαρχει στη βαση (το χρειαζομαστε λογω του auto-increment

    public int getBookID()
    {
        int c=0;
        try{
            rs=getSt().executeQuery("SELECT bookingID FROM Bookings;");
        }
        catch (SQLException e){
            System.out.println("SQL Exception"+e);

        }
        try {
            while (rs.next()) {

                c=rs.getInt(1);

            }
        }
        catch (SQLException e)
        {
            System.out.println("SQL Exception"+e);
        }
        return c;
    }
}
