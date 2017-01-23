package Interface;

import Controller.BasicController;
import Controller.ChangeBookingController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Created by Mitsos on 06/06/16.
 */
public class UserInterface {
    private JPanel panel1;
    private JTabbedPane tabbedPane1;
    private JTextField clientTextField;
    private JButton searchClientButton;
    private JButton newClientButton;
    private JTextField arrivalDateTextField;
    private JTextField departureDateTextField;
    private JTextField roomTypeTextField;
    private JTextField numberOfRoomsTextField;
    private JTextField roomsTextField;
    private JButton searchRoomsButton;
    private JButton makeBookingButton;
    private JButton clearButton;
    private JLabel departureDateLabel;
    private JPanel cancelBooking;
    private JTextField bookIdTextField;
    private JButton deleteButton;
    private JPanel checkOut;
    private JPanel changeBooking;
    private JTextField checkoutIDtextField;
    private JButton checkOutButton;
    private JPanel checkIn;
    private JTextField checkInTextField;
    private JButton checkInButton;
    private JLabel checkInLabel;
    private JTabbedPane tabbedPane2;
    private JPanel changeDates;
    private JTextField changeBookingIdTextField;
    private JButton changeBookingSearchButton;
    private JTextField changeArrivalTextField;
    private JButton changeDatesButton;
    private JTextField changeDepartureTextField;
    private JLabel arrivalDateLabel;
    private JLabel departureDateLabel1;
    private JButton changeRoomTypeButton;
    private JTextField changeRoomTypeTextField;
    private JTextField changeTypeBookIDTextField;
    private JButton addRoomButton;
    private JTextField addRoomBookIDTextField;
    private JButton addRoomSearchButtom;
    private JTextField changeClientBookIdTextField;
    private JButton changeClientSearchButton;
    private JTextField clientFirstNameTextField;
    private JTextField clientLastNameTextField;
    private JTextField clientPhoneTextField;
    private JButton changeClientButton;
    private JPanel changeRoomType;
    private JPanel addRoom;
    private JPanel changeClientInfo;
    private JButton changeRoomSearchButton;


    public UserInterface() {
        searchClientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BasicController.getClient(clientTextField.getText());
                if(BasicController.getC().getClient_Lname()!=null) {
                    clientTextField.setText(BasicController.getC().getClient_Lname());
                }else {
                    JOptionPane.showMessageDialog(null,"The client was not found, try again.");
                    clientTextField.setText("");
                }

            }
        });
        newClientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame2 = new JFrame("New Client");
                frame2.setContentPane(new NewClient().getPanel2());
                frame2.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                frame2.pack();
                frame2.setVisible(true);
                clientTextField.setText(BasicController.getC().getClient_Lname());

            }
        });
        searchRoomsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BasicController.searchRooms(arrivalDateTextField.getText(),departureDateTextField.getText(),roomTypeTextField.getText(),numberOfRoomsTextField.getText());
                JFrame frame3 = new JFrame("Available Rooms");
                frame3.setContentPane(new AvailableRooms2().getPanel3());
                frame3.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                frame3.pack();
                frame3.setVisible(true);
                panel1.revalidate();
                panel1.repaint();

            }
        });
        makeBookingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int cost=BasicController.getCost(arrivalDateTextField.getText(), departureDateTextField.getText());
                int n = JOptionPane.showConfirmDialog(
                        null, "The cost is : "+cost,
                        "Confirm Reservation",
                        JOptionPane.YES_NO_OPTION);
                if (n == JOptionPane.YES_OPTION) {
                    BasicController.makeBooking(arrivalDateTextField.getText(), departureDateTextField.getText());
                    clearButton.doClick();
                }
                else
                {
                    System.exit(0);
                }
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int a = BasicController.getBook(bookIdTextField.getText());
                if (a == 0) {
                    int n = JOptionPane.showConfirmDialog(
                            null, "The booking was not found! Enter BookId again! ",
                            "Search Again?",
                            JOptionPane.YES_NO_OPTION);
                    if (n == JOptionPane.YES_OPTION) {


                    } else {
                        System.exit(0);
                    }

                } else {
                    int n = JOptionPane.showConfirmDialog(
                            null, "Delete Booking? ",
                            "Conformation",
                            JOptionPane.YES_NO_OPTION);

                    if (n == JOptionPane.YES_OPTION) {
                        BasicController.deleteBook(bookIdTextField.getText());
                        System.exit(0);
                    } else {
                        System.exit(0);
                    }


                }
            }
        });
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clientTextField.setText("");
                arrivalDateTextField.setText("");
                departureDateTextField.setText("");
                numberOfRoomsTextField.setText("");
                roomTypeTextField.setText("");
                roomsTextField.setText("");
                BasicController.clear();
            }
        });
        checkOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int a = BasicController.getBook(checkoutIDtextField.getText());
                if (a == 0) {
                    int n = JOptionPane.showConfirmDialog(
                            null, "The booking was not found! Enter BookId again! ",
                            "Search Again?",
                            JOptionPane.YES_NO_OPTION);
                    if (n == JOptionPane.YES_OPTION) {


                    }
                    else {
                        System.exit(0);
                    }

                } else {
                    int n = JOptionPane.showConfirmDialog(
                            null, "Check out? ",
                            "Conformation",
                            JOptionPane.YES_NO_OPTION);

                    if (n == JOptionPane.YES_OPTION) {
                        BasicController.storeBooking(checkoutIDtextField.getText());
                        BasicController.deleteBook(checkoutIDtextField.getText());
                        checkoutIDtextField.setText("");
                        BasicController.clear();
                    } else {
                        System.exit(0);
                    }


                }

            }
        });
        checkInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int a = BasicController.getBook(checkInTextField.getText());
                if (a == 0) {
                    int n = JOptionPane.showConfirmDialog(
                            null, "The booking was not found! Enter BookId again! ",
                            "Search Again?",
                            JOptionPane.YES_NO_OPTION);
                    if (n == JOptionPane.YES_OPTION) {


                    } else {
                        System.exit(0);
                    }

                } else {
                    int n = JOptionPane.showConfirmDialog(
                            null, "Check In? ",
                            "Conformation",
                            JOptionPane.YES_NO_OPTION);

                    if (n == JOptionPane.YES_OPTION) {

                        BasicController.checkIn(checkInTextField.getText());
                        checkInTextField.setText("");
                        BasicController.clear();
                    }
                    else {
                        System.exit(0);
                    }
                }
            }
        });
        changeBookingSearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int a = BasicController.getBook(changeBookingIdTextField.getText());
                if(a==0){
                    int n = JOptionPane.showConfirmDialog(
                            null, "The booking was not found! Enter BookId again! ",
                            "Search Again?",
                            JOptionPane.YES_NO_OPTION);
                    if (n == JOptionPane.YES_OPTION) {


                    } else {
                        System.exit(0);
                    }

                } else {
                    ChangeBookingController.getBooking(changeBookingIdTextField.getText());
                    panel1.revalidate();
                    panel1.repaint();
                    changeArrivalTextField.setText(ChangeBookingController.getArrivalDate());
                    changeDepartureTextField.setText(ChangeBookingController.getDepartureDate());
                    changeDatesButton.setEnabled(true);
                }
            }

        });
        changeDatesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean a;
                a=ChangeBookingController.searchAvailable(changeArrivalTextField.getText(),changeDepartureTextField.getText());
                if (a){

                    JOptionPane.showMessageDialog(null,"Successful date change");
                }
                else{
                    JOptionPane.showMessageDialog(null,"Unsuccessful date change");

                }
                changeBookingIdTextField.setText("");
                changeArrivalTextField.setText("");
                changeDepartureTextField.setText("");
                ChangeBookingController.clear();

            }
        });
        changeRoomSearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int a = BasicController.getBook(changeTypeBookIDTextField.getText());
                if (a == 0) {
                    int n = JOptionPane.showConfirmDialog(
                            null, "The booking was not found! Enter BookId again! ",
                            "Search Again?",
                            JOptionPane.YES_NO_OPTION);
                    if (n == JOptionPane.YES_OPTION) {


                    } else {
                        System.exit(0);
                    }

                } else {
                    ChangeBookingController.getBooking(changeTypeBookIDTextField.getText());
                    String s=Integer.toString(ChangeBookingController.getRoomType());
                    panel1.revalidate();
                    panel1.repaint();
                    changeRoomTypeTextField.setText(s);
                    changeRoomTypeButton.setEnabled(true);

                }
            }
        });
        changeRoomTypeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean a;
                a=ChangeBookingController.searchAvailableRoomType(changeRoomTypeTextField.getText());
                if (a){

                    JOptionPane.showMessageDialog(null,"Successful room type change");
                }
                else {
                    JOptionPane.showMessageDialog(null,"Unsuccessful room type change");

                }
                changeRoomTypeTextField.setText("");
                changeTypeBookIDTextField.setText("");
                ChangeBookingController.clear();




            }
        });
        changeClientSearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int a = BasicController.getBook(changeClientBookIdTextField.getText());
                if (a == 0) {
                    int n = JOptionPane.showConfirmDialog(
                            null, "The booking was not found! Enter BookId again! ",
                            "Search Again?",
                            JOptionPane.YES_NO_OPTION);
                    if (n == JOptionPane.YES_OPTION) {


                    } else {
                        System.exit(0);
                    }

                } else {
                    ChangeBookingController.getBooking(changeClientBookIdTextField.getText());

                    clientFirstNameTextField.setText(ChangeBookingController.getClientName());
                    clientLastNameTextField.setText(ChangeBookingController.getClientLast());
                    clientPhoneTextField.setText(ChangeBookingController.getClientPhone());
                    panel1.revalidate();
                    panel1.repaint();
                    changeClientButton.setEnabled(true);
                }


            }
        });
        changeClientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChangeBookingController.changeInfo(clientFirstNameTextField.getText(),clientLastNameTextField.getText(),clientPhoneTextField.getText());
                changeClientBookIdTextField.setText("");
                clientFirstNameTextField.setText("");
                clientLastNameTextField.setText("");
                clientPhoneTextField.setText("");
                ChangeBookingController.clear();

            }
        });
        addRoomSearchButtom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int a=BasicController.getBook(addRoomBookIDTextField.getText());
                if (a == 0) {
                    int n = JOptionPane.showConfirmDialog(
                            null, "The booking was not found! Enter BookId again! ",
                            "Search Again?",
                            JOptionPane.YES_NO_OPTION);
                    if (n == JOptionPane.YES_OPTION) {


                    } else {
                        System.exit(0);
                    }

                } else {
                    ChangeBookingController.getBooking(addRoomBookIDTextField.getText());
                    addRoomButton.setEnabled(true);

                }

            }
        });
        addRoomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean a;
                a=ChangeBookingController.addRoom();
                if (a){

                    JOptionPane.showMessageDialog(null,"Successful room addition");
                }
                else {
                    JOptionPane.showMessageDialog(null,"Unsuccessful room addition");

                }
                addRoomBookIDTextField.setText("");
                ChangeBookingController.clear();


            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Hotel Managment");
        frame.setContentPane(new UserInterface().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}
