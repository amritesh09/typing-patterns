package pt;

import java.awt.*;
import java.sql.*;
import javax.swing.*;
import java.awt.event.*;

public class newuser implements KeyListener, ActionListener {

    JFrame f1;
    static long keypressed[] = new long[100];
    static long keyreleased[] = new long[100];
    static long presstime[] = new long[100];
    static long intertime[] = new long[100];
    static int i = 1;
    static long speed = 0;
    static String s = " ";
    int level = 0;
    long d = 0, d1 = 0;
    Button b1, b2, b3;
    TextArea ta;
    int l1 = 0;
    JLabel jl, jl2;
    TextField tf;

    newuser() {

        f1 = new JFrame();
        b3 = new Button("home");
        //b2=new Button("hello");
        ta = new TextArea(10, 10);
        JLabel jl[] = new JLabel[10];
        for (int m = 0; m < 10; m++) {
            jl[m] = new JLabel();
        }
        tf = new TextField();
        b1 = new Button("submit");
        b1.setBounds(80, 200, 140, 30);
        b3.setBounds(80, 260, 140, 30);
//    jl2.setBounds(80,300,200,30);
        //b2.setBounds(80,40,40,40);
        tf.setBounds(80, 0, 160, 30);
        //  ta.setRows(10);

        ta.setBounds(80, 40, 160, 180);
        for (int m = 0; m < 10; m++) {
            jl[m].setBounds(60, 44 + (m * 15), 20, 15);
            String s3 = " ";
            s3 += (m + 1);
            //jl.setText("1\n2\n3\n4\n5\n6\n7\n8\n9\n10\n");
            jl[m].setText(s3);
        }
        b1.addActionListener(this);
        b3.addActionListener(this);
        ta.addKeyListener(this);

        f1.setVisible(true);
        f1.setTitle("Typing Patterns/NEW USER REGISTRATION");
        f1.setLayout(null);
        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f1.add(b1);
        f1.add(b3);
        for (int m = 0; m < 10; m++) {
            f1.add(jl[m]);
        }
        f1.add(tf);
        f1.add(ta);
        f1.setSize(400, 400);
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
            if (e.getSource() == b1) {
                s = tf.getText();
                System.out.println(" " + s);
                System.out.println(i);
                int j = (i - 1) / 10;
                int p1 = 0, p2 = 0;//speed+=presstime[0];
                for (l1 = 1; l1 < (j - 1); l1++) {
                    speed += (presstime[l1] + intertime[l1 + 1]);
                }
                speed += (2 * presstime[l1 + 1]);
                System.out.println("speed=" + speed);
                //database code goes here
                try {
                    Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
                    Connection c = DriverManager.getConnection("jdbc:odbc:mydsn");
                    Statement s1 = c.createStatement();
                    //int x=s1.executeUpdate("create table s8(name text,presstime int,iktime int,speed int)");
                    //System.out.println(x);
                    for (int l = 1; l <= j; l++) {
                        int press = 0, ik = 0;
                        p1 = 0;
                        p2 = 0;
                        for (int k = 0; k < 10 * j; k = k + j) //90
                        {
                            p1 += presstime[k + l];
                            if (k != 0 && (k + 1 - j) > 0) {
                                d = presstime[k + 1] - presstime[k + 1 - j];
                            } else {
                                d = 0;
                            }
                            d1 = d1 + d;
                            System.out.println("d1= " + d1);
                        }
                        press = p1 / 10;
                        d1 = d1 / 10;
                        if (d1 < login.mod(press / 10)) {
                            level = 5;
                        } else if (d1 < login.mod(press / 9)) {
                            level = 4;
                        } else if (d1 < login.mod(press / 8)) {
                            level = 3;
                        } else if (d1 < login.mod(press / 7)) {
                            level = 2;
                        } else if (d1 < login.mod(press / 6)) {
                            level = 1;
                        } else {
                            level = 0;
                        }
                        for (int k = 1; k < 10; k++) {
                            p2 += intertime[j * k + l];
                        }
                        ik = p2 / 10;
                        // long speed=(p1+p2)/j;
//if(level>0)
                        {
                            int x1 = s1.executeUpdate("insert into s8 values('" + s + "','" + press + "','" + ik + "','" + speed + "')");
                            System.out.println(x1);
                            //jl2.setText("You have level "+level+"security"); System.out.println("You have level "+level+"security");
                        }
//else
                        //  jl2.setText("Sorry please try again");System.out.println("Sorry please try again");
                    }
                } catch (Exception e1) {
                    System.out.print(e1);
                }
            } else if (e.getSource() == b3) {
                f1.setVisible(false);
                System.out.print("1");
                new Main();
            }
        }
    }

    /* if(e.getSource()==b2)
	{
	tf.setText("hello");
	}
	}*/
    public static void main(String s[]) {
        new newuser();

    }
}
