package pt;

import java.awt.*;
import java.sql.*;
import javax.swing.*;
import java.awt.event.*;

public class Main implements ActionListener {

    JFrame f1;
    Button b3, b4;

    Main() {
        f1 = new JFrame();
        b3 = new Button("Login");
        b4 = new Button("New User");
        b3.setBounds(80, 40, 140, 30);
        b4.setBounds(80, 100, 140, 30);
        b3.addActionListener(this);
        b4.addActionListener(this);
        f1.setVisible(true);
        f1.setLayout(null);
        f1.setTitle("Typing Patterns/HOME");
        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f1.add(b3);
        f1.add(b4);
        f1.setSize(400, 400);
    }

    public void actionPerformed(ActionEvent e) {
        {
            if (e.getSource() == b3) {
                f1.setVisible(false);
                new login();
            } else {
                if (e.getSource() == b4) {
                    f1.setVisible(false);
                    new newuser();

                }
            }

        }
    }

    public static void main(String[] args) {
        new Main();
    }

}
