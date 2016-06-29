package pt;

import java.awt.*;
import java.sql.*;
import javax.swing.*;
import java.awt.event.*;

public class login implements KeyListener, ActionListener {

    JFrame f2;
    long keypressed[] = new long[10];
    static long keyreleased[] = new long[10];
    static long presstime[] = new long[10];
    static long intertime[] = new long[10];
    static int i = 1;
    static long speed = 0;
    static String s = " ";
    Button b5, b6;
    TextField tf1, tf2;
    // JPasswordField=p;

    login() {
//p=new JPasswordField();
        f2 = new JFrame();
        b5 = new Button("submit");
        b6 = new Button("home");
        tf1 = new TextField();
        tf2 = new TextField();

        b5.setBounds(80, 100, 140, 30);
        b6.setBounds(80, 170, 140, 30);
        //b2.setBounds(80,40,40,40);
        tf1.setBounds(80, 0, 160, 30);
        tf2.setBounds(80, 50, 160, 30);
        //  ta.setRows(10);

        b5.addActionListener(this);
        b6.addActionListener(this);
        f2.setVisible(true);
        f2.setLayout(null);
        f2.setTitle("Typing Patterns/LOGIN");
        f2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f2.add(b5);
        f2.add(b6);
        f2.add(tf1);
        f2.add(tf2);
        f2.setSize(400, 400);
        tf2.addKeyListener(this);
    }

    public void keyPressed(java.awt.event.KeyEvent evt) {
        KeyPressed(evt);
    }

    public void KeyPressed(java.awt.event.KeyEvent evt) {
        keypressed[i] = new java.util.Date().getTime();
        System.out.println("key pressed= " + keypressed[i]);
    }

    public void keyReleased(java.awt.event.KeyEvent evt) {
        KeyReleased(evt);
    }

    public void KeyReleased(java.awt.event.KeyEvent evt) {
        keyreleased[i] = new java.util.Date().getTime();
        System.out.println("key released= " + keyreleased[i]);
        presstime[i] = (keyreleased[i] - keypressed[i]);
        intertime[i] = (keypressed[i] - keyreleased[i - 1]);
        System.out.println("time= " + presstime[i]);
        System.out.println("interkey time= " + intertime[i]);
        i++;
    }

    public void keyTyped(java.awt.event.KeyEvent evt) {
        KeyTyped(evt);
    }

    public void KeyTyped(java.awt.event.KeyEvent evt) {

    }

    public void actionPerformed(ActionEvent e) {
        {
            if (e.getSource() == b5) {
                s = tf1.getText();
                System.out.println(" " + s);
                System.out.println(i);
                int j = (i - 1);
                int p1 = 0, p2 = 0;

                //database code goes here
                try {
                    Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
                    Connection c = DriverManager.getConnection("jdbc:odbc:mydsn");
                    Statement s1 = c.createStatement();
                    //int x=s1.executeUpdate("create table s8(name text,presstime int,iktime int,speed int)");
                    // System.out.println(x);
                    long p = 0;
                    long ik = 0;
                    for (int l = 1; l < 9; l++) {
                        p += presstime[l];
                        System.out.println("p=" + p);
                        ik += intertime[l + 1];
                        System.out.println("ik=" + ik);
                    }
                    int l1 = 0;
                    for (l1 = 1; l1 < i; l1++) {
                        speed += (presstime[l1] + intertime[l1 + 1]);
                    }
                    System.out.print("speed=" + speed);
                    //long speed=(p1+p2)/j;
                    speed = p + ik;
                    ResultSet r = s1.executeQuery(""
                            + "select * from s8");
                    int k = 1;
                    int[] spress = new int[10];
                    int[] sik = new int[10];
                    int[] ss = new int[10];
                    long d[] = new long[10];
                    long d1 = 0, d2 = 0;
                    while (r.next()) {
                        String s2 = r.getString(1);
                        if (s.equals(s2)) {
                            spress[k] = r.getInt(2);
                            sik[k] = r.getInt(3);
                            ss[k] = r.getInt(4);
                            d2 = ss[k] - speed;
                            d[k] = spress[k] - presstime[k];
                            d1 += d[k];
                            k++;
                            System.out.println(d1 + "  " + p + " d2=" + d2);
                        }
                    }
                    System.out.println("d1=" + d1 + "p=" + p);
                    if (mod(d1) < (p / 8) && mod(d2 / 10) < (speed / 6)) {
                        f2.setVisible(false);
                        new welcome();
                    } else {
                        tf1.setText("wrong user");
                    }
                    i = 0;
                    j = 0;
                    tf2.setText("");
                } catch (Exception e1) {
                    System.out.print(e1);
                }
            }
            if (e.getSource() == b6) {
                f2.setVisible(false);
                i = 1;
                e = null;
                speed = 0;
                new Main();
            }
        }
    }

    public static void main(String[] args) {
        new login();
    }

    static long mod(long d1) {
        if (d1 > 0) {
            return d1;
        } else {
            return -d1;
        }
    }

}
