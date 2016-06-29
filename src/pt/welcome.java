package pt;

import java.awt.*;
import java.sql.*;
import javax.swing.*;
import java.awt.event.*;

public class welcome {

    welcome() {
        JFrame f4;
        f4 = new JFrame();
        JLabel l;
        l = new JLabel();
        l.setBounds(80, 100, 160, 50);
        l.setText("welcome");
        f4.setVisible(true);
        f4.add(l);
        f4.setLayout(null);
        f4.setSize(400, 400);
        f4.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public static void main(String[] args) {
        new welcome();
    }

}
