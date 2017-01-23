package Controller;

import Basic.Booking;
import Basic.Client;
import Basic.Room;
import Database.Db_Booking;
import Database.Db_Client;
import Database.Db_Room;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

/**
 * Created by Mitsos on 05/06/16.
 */
public class BasicController {
    private static Client c = new Client();
    private static List<Room> rooms = new ArrayList<>();
    private static Db_Client dbclient = new Db_Client();
    private static Db_Room dbroom = new Db_Room();
    private static Booking booking = new Booking();
    private static Db_Booking dbbooking = new Db_Booking();

    private static List<Integer> selectedRooms=new ArrayList<>();


    //παιρνει τα στοιχεια ενος πελατη και καλη τη συναρτηση που τον αποθηκευει στη βαση
    public static void saveClient(String name, String lastname, String phone){
        c.setClient_Fname(name);
        c.setClient_Lname(lastname);
        c.setClient_Phone(phone);
        dbclient.insertClient(c);

    }

    //καλει την συναρτηση αναζητησης δωματιων απ τη βαση, κι αν δεν βρει οσα ζητησε ο πελατης ψαχνει για εναλλακτικα
    public static void searchRooms (String arrival, String departure, String roomType, String noRooms){

        List<Room> rooms2=new ArrayList<>();
        int rType = Integer.parseInt(roomType);
        int nRooms = Integer.parseInt(noRooms);

        rooms = dbroom.searchRooms(arrival, departure, rType, nRooms);


        if(rooms.size()<nRooms||rooms.isEmpty())
        {
            rooms2=dbroom.searchAlternatives(arrival,departure,rType,nRooms);
            rooms=new ArrayList<>();
            for(Room r: rooms2)
            {
                rooms.add(r);
            }


        }

    }
    //καλει τη συναρτηση που ψαχνει για πελατες στη βαση
    public static void getClient(String s){
        c = dbclient.getClient(s);
    }

    //επιστρεφει τον πελατη που δημιουργηθηκε εδω
    public static Client getC() {
        return c;
    }

    //επιστρεφει τη λιστα δωματιων που βρεθηκαν σε Strings για να παρουσιαστουν στο interface
    public static List<String> getrooms(){

        List<String> s=new ArrayList<>();

        for(Room r : rooms )
        {
            s.add("Room Number : "+Integer.toString(r.getRoomId())+"\t Room Type : "+Integer.toString(r.getRoomType())+"\t Room Cost : "+Integer.toString((r.getRoomCost())));
        }

        return s;
    }

    //παιρνει απ το interface τα δωματια που επελεξε ο χρηστης και τα βαζει τους κωδικους τους σε μια λιστα
    public static void selectRooms(List<String> roomlist)
    {
        String roomID;

        for(String s : roomlist)
        {
            roomID= s.substring(14,17);
            int a = Integer.parseInt(roomID.trim());

            selectedRooms.add(a);

        }
        rooms=new ArrayList<>();
        for(Integer a:selectedRooms)
        {
            Room room=new Room();
            room.setRoomId(a);
            rooms.add(room);
        }

    }

    //θετει τις τιμες του αντ/νου booking και καλει τη συναρτηση που την αποθηκευει
    public static void makeBooking(String arrival, String departure){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY");
        Date date = new Date();

        booking.setBookingDate(dateFormat.format(date));
        booking.setArrivalDate(arrival);
        booking.setDepartureDate(departure);
        booking.setBookingClient(c);

        booking.setBookingRooms(rooms);

        dbbooking.insertBooking(booking);

    }

    //καλη τη συναρτηση υπολογισμου κοστους και επιστρεφει στο interface το κοστος

    public static int getCost(String arrival, String departure) {
        int cost=0;

        cost=dbroom.costCalculation(arrival, departure, selectedRooms);


        return cost;
    }

    //επιστρεφει μια τιμη ως ενδειξη οτι βρεθηκε μια κρατηση
    public static int getBook(String s)
    {
        int a=Integer.parseInt(s);
        int b=0;
        b=dbbooking.getBooking(a);

        return b;

    }

    //καλει τη συναρτηση διαγραφης κρατησης
    public static void deleteBook(String s)
    {
        int a=Integer.parseInt(s);

        dbbooking.deleteBooking(a);

    }

    //καλει την συναρτηση αποθηκευσης κρατησης
    public static void storeBooking(String s)
    {
        int a=Integer.parseInt(s);

        dbbooking.storeBooking(a);
    }

    //καλει την συναρτηση που δημιουργει check-in
    public static void checkIn(String s)
    {
        int a=Integer.parseInt(s);

        dbbooking.checkIn(a);
    }

    //καθαριζει ολα τα δημιουργηθεντα αντικειμενα
    public static void clear(){
        c = new Client();
        rooms = new ArrayList<>();
        dbclient = new Db_Client();
        dbroom = new Db_Room();
        booking = new Booking();
        dbbooking = new Db_Booking();
        selectedRooms = new ArrayList<>();

    }


}
