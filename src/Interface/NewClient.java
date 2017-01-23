package Interface;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Controller.BasicController;
/**
 * Created by Mitsos on 06/06/16.
 */
public class NewClient {
    private JPanel panel2;
    private JButton registerButton;
    private JTextField firstNameTextField;
    private JTextField lastNameTextField;
    private JTextField phoneTextField;


    public NewClient() {

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               BasicController.saveClient(firstNameTextField.getText(),lastNameTextField.getText(),phoneTextField.getText());

            }
        });
    }
    public JPanel getPanel2() {
        return panel2;
    }

    public JTextField getLastNameTextField() {
        return lastNameTextField;
    }
}
