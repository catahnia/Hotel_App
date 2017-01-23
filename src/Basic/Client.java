package Basic;

/**
 * Created by Mitsos on 25/05/16.
 */
public class Client {
    private int Client_Code;
    private String Client_Fname;
    private String Client_Lname;
    private String Client_Phone;


    public Client(String client_Lname, String client_Fname, String phone) {

        Client_Fname = client_Lname;
        Client_Lname = client_Fname;
        Client_Phone = phone;

    }

    public Client() {
    }

    public int getClient_Code() {
        return Client_Code;
    }

    public void setClient_Code(int client_Code) {
        Client_Code = client_Code;
    }

    public String getClient_Fname() {
        return Client_Fname;
    }

    public void setClient_Fname(String client_Fname) {
        Client_Fname = client_Fname;
    }

    public String getClient_Lname() {
        return Client_Lname;
    }

    public void setClient_Lname(String client_Lname) {
        Client_Lname = client_Lname;
    }

    public String getClient_Phone() {
        return Client_Phone;
    }

    public void setClient_Phone(String client_Phone) {
        Client_Phone = client_Phone;
    }
}
