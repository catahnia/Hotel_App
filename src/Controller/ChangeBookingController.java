package Controller;

import Basic.Booking;
import Basic.Client;
import Basic.Room;
import Database.Db_Booking;
import Database.Db_Client;
import Database.Db_Room;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by Mitsos on 09/06/16.
 */
public class ChangeBookingController {

    private static List<Room> rooms ;
    private static Db_Client dbclient = new Db_Client();
    private static Db_Room dbroom = new Db_Room();
    private static Booking booking ;
    private static Db_Booking dbbooking = new Db_Booking();



    //καλει την αναζητη κρατησης βαση string και αφου βρεθει δημιουργει το αντ/νο booking
    public static void getBooking (String s)
    {
        int a=Integer.parseInt(s);

        booking=dbbooking.getBook(a);

    }

    //επιστρεφει στο interface την ημ/νια αφιξης
    public static String getArrivalDate()
    {

        return booking.getArrivalDate();
    }

    //επιστρεφει στο interface την ημ/νια αφιξης

    public static String getDepartureDate()
    {
        return booking.getDepartureDate();
    }

    //επιστρεψει τον τυπο των δωματιων μιας κρατησης
    public static int getRoomType(){
        rooms = booking.getBookingRooms();
        //String s=Integer.toString(rooms.get(0).getRoomType());
        return rooms.get(0).getRoomType();
    }

    //αναζητα διαθεσιμα δωματια για νεες ημ/νιες με σκοπο να γινει αλλαγη στην κρατηση

    public static boolean searchAvailable(String arrival, String departure)
    {
        rooms=booking.getBookingRooms();

        List<Room> rooms2=new ArrayList<>();

        rooms2=dbroom.searchRooms(arrival,departure,rooms.get(0).getRoomType(),rooms.size());

        int a=0;

        for(Room r: rooms2){                    //Αν στα δωματια που επισταφηκαν βρεθουν τα δωματια που εκλεισε, τοτε μπορει να αλλαξει η ημ/νια
            for(Room c : rooms){
                if(r.getRoomId()==c.getRoomId())
                    a++;
            }
        }

        if(rooms.size()==a){
            dbbooking.changeDates(arrival,departure,booking.getBookingId());
            return true;

        }else{
            return false;
        }


    }

    //αναζητα διαθεσιμα δωματια με σκοπο να ειναι εφικτη η αλλαγη τυπου δωματιων

    public static boolean searchAvailableRoomType(String type)
    {
        rooms=booking.getBookingRooms();

        List<Room> rooms1=new ArrayList<>();

        rooms1=dbroom.searchRooms(booking.getArrivalDate(),booking.getDepartureDate(),Integer.parseInt(type),1);

        System.out.println(rooms);
        System.out.println(rooms1);

        if(rooms1.size()<rooms.size()){
            return false;
        }
        else {
            dbbooking.changeRoomType(rooms,rooms1,booking.getBookingId());
            return true;
        }


    }

    //επιστρεφει το ονομα του πελατη της κρατησης
    public static String getClientName(){
        Client c = booking.getClient();

        return c.getClient_Fname();

    }

    //επιστρεφει το επιθετο του πελατη μιας κρατησης
    public static String getClientLast(){
        Client c = booking.getClient();

        return c.getClient_Lname();
    }

    //επιστρεφει το τηλεφωνο του πελατη μιας κρατησης

    public static String getClientPhone(){

        Client c = booking.getClient();

        return c.getClient_Phone();
    }

    //καλη τη συναρητηση αλλαγης στοιχειων πελατη, με τα νεα στοιεια που ελαβε απ το interface
    public static void changeInfo(String name,String last,String phone){
        Client c=booking.getClient();

        c.setClient_Phone(phone);
        c.setClient_Fname(name);
        c.setClient_Lname(last);
        dbclient.changeInfo(c);
    }

    //καθαριζει τα αντικειμενα της κλασης

    public static void clear(){
        booking=new Booking();
        rooms=new ArrayList<>();
    }

    //αναζητα δωματια με σκοπο την προσθηκη δωματιου
    public static boolean addRoom(){
        rooms=booking.getBookingRooms();


        List<Room> rooms3=new ArrayList<>();

        try {
            rooms3 = dbroom.searchRooms(booking.getArrivalDate(), booking.getDepartureDate(), rooms.get(0).getRoomType(), rooms.size());
        }
        catch (NullPointerException e)
        {
            System.out.println(e);

        }
        if(rooms3.size()<1){

            return false;
        }
        else {

            dbroom.addRoom(booking.getBookingId(), rooms3.get(0).getRoomId());
            return true;
        }

    }


}
