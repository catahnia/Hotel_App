package Interface;

import Controller.BasicController;

import javax.swing.*;
import javax.swing.JScrollPane;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import static com.sun.tools.internal.xjc.reader.Ring.add;

/**
 * Created by Mitsos on 07/06/16.
 */
public class AvailableRooms2  {
    private JPanel panel3;
    private JLabel jLabel;
    private JButton addButton;
    private JList<String> roomList;
    private JScrollPane scrollPane;



    AvailableRooms2(){
        panel3 = new JPanel();
        jLabel= new JLabel("Room List (If other type of rooms appears means there is no available room for selected dates" +
                " and showing results for after 3 dates)");
        addButton = new JButton("Add Rooms");

        panel3.setBorder(BorderFactory.createEmptyBorder(200, 150, 200,150));
        panel3.setLayout(new BorderLayout());
        panel3.add(jLabel, BorderLayout.NORTH);
        panel3.add(addButton,BorderLayout.SOUTH);


        DefaultListModel<String> dlm=new DefaultListModel<>();


        List<String> s;
        s= BasicController.getrooms();

        if (s.isEmpty())
        {
            JOptionPane.showMessageDialog(null, "No Rooms found");
            System.exit(0);

        }
        for(String room : s)
        {
            dlm.addElement(room);
        }



        roomList=new JList<>(dlm);
        roomList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        scrollPane = new JScrollPane(roomList);

        panel3.add(roomList,BorderLayout.CENTER);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<String> selectedValue = roomList.getSelectedValuesList();

                BasicController.selectRooms(selectedValue);
                panel3.setVisible(false);

            }
        });
    }

    public JPanel getPanel3() {
        return panel3;
    }



}
