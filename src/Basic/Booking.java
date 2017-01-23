package Basic;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;


/**
 * Created by Mitsos on 25/05/16.
 */
public class Booking {
    private int BookingId;
    private String BookingDate;
    private String ArrivalDate;
    private String DepartureDate;
    private Client BookingClient;
    private List<Room> BookingRooms;
    private int checkIn;

    public Booking(int bookingId, String bookingDate, String arrivalDate, String departureDate, Client bookingClient, List<Room> bookingRooms) {
        BookingId = bookingId;
        BookingDate = bookingDate;
        ArrivalDate = arrivalDate;
        DepartureDate = departureDate;
        BookingClient = bookingClient;
        BookingRooms = bookingRooms;
    }

    public Booking() {
        BookingRooms = new ArrayList<>();
    }

    public int getBookingId() {
        return BookingId;
    }

    public void setBookingId(int bookingId) {
        BookingId = bookingId;
    }
    public void setClientID(int a)
    {
        BookingClient.setClient_Code(a);
    }

    public String getBookingDate() {
        return BookingDate;
    }

    public void setBookingDate(String bookingDate) {
        BookingDate = bookingDate;
    }

    public String getArrivalDate() {
        return ArrivalDate;
    }

    public void setArrivalDate(String arrivalDate) {
        ArrivalDate = arrivalDate;
    }

    public String getDepartureDate() {
        return DepartureDate;
    }

    public void setDepartureDate(String departureDate) {
        DepartureDate = departureDate;
    }

    public int getBookingClient() {
        return BookingClient.getClient_Code();
    }

    public void setBookingClient(Client bookingClient) {
        BookingClient = bookingClient;
    }

    public List<Room> getBookingRooms() {
        return BookingRooms;
    }

    public void setBookingRooms(List<Room> bookingRooms) {
        BookingRooms = bookingRooms;
    }


    public void setCheckIn(int checkIn) {
        this.checkIn = checkIn;
    }

    public Client getClient(){
        return BookingClient;
    }
}
