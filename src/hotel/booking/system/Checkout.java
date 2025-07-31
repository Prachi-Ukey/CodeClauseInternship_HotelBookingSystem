package hotel.booking.system;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;
import java.util.Date;

public class Checkout extends JFrame implements ActionListener {
    
    Choice ccustomer;
    JButton checkoutBtn, back;
    JLabel roomnumber,checkintime,checkouttime;

    Checkout() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel text = new JLabel("Checkout");
        text.setFont(new Font("Tahoma", Font.PLAIN, 20));
        text.setBounds(100, 20, 200, 30);
        text.setForeground(Color.BLUE);
        add(text);

        JLabel id = new JLabel("Customer Id");
        id.setBounds(30, 80, 100, 30);
        add(id);

        ccustomer = new Choice();
        ccustomer.setBounds(150, 80, 150, 25);
        add(ccustomer);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/tick.png"));
        Image i2 = i1.getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(310,80, 20, 20);
        add(image);

        JLabel room = new JLabel("Room Number");
        room.setBounds(30, 130, 100, 30);
        add(room);

        roomnumber = new JLabel("Customer Id");
        roomnumber.setBounds(150, 130, 100, 30);
        add(roomnumber);
        
        JLabel checkin = new JLabel("Checkin Time");
        checkin.setBounds(30, 180, 100, 30);
        add(checkin);

        checkintime = new JLabel();
        checkintime.setBounds(150, 180, 100, 30);
        add(checkintime);
        
        JLabel checkout = new JLabel("Checkout Time");
        checkout.setBounds(30, 230, 150, 30);
        add(checkout);

        Date date = new Date();
        checkouttime = new JLabel("" + date);
        checkouttime.setBounds(150, 230, 180, 30);
        add(checkouttime);
        
        checkoutBtn = new JButton("Checkout");
        checkoutBtn.setBackground(Color.BLACK);
        checkoutBtn.setForeground(Color.WHITE);
        checkoutBtn.setBounds(30, 280, 120, 30);
        checkoutBtn.addActionListener(this);
        add(checkoutBtn);

        back = new JButton("Back");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setBounds(170, 280, 120, 30);
        back.addActionListener(this);
        add(back);
        
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from customer");
            while (rs.next()) {
                ccustomer.add(rs.getString("number"));
                roomnumber.setText(rs.getString("room"));
                checkintime.setText(rs.getString("checkintime"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("icons/checkout.jpg"));
        Image i5 = i4.getImage().getScaledInstance(400,250,Image.SCALE_DEFAULT);
        ImageIcon i6 = new ImageIcon(i5);
        JLabel image1 = new JLabel(i6);
        image1.setBounds(340,80, 400, 250);
        add(image1);

        setBounds(300, 200, 800, 400);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == checkoutBtn) {
            String query1 = "delete from customer where number = '"+ccustomer.getSelectedItem()+"'";
            String query2 = "update room set availability = 'Available' where roomnumber = '"+roomnumber.getText()+"'";

            try {
                Conn c = new Conn();
                c.s.executeUpdate(query1);
                c.s.executeUpdate(query2);
                JOptionPane.showMessageDialog(null, "Checkout Done");
                setVisible(false);
                new Reception();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            setVisible(false);
            new Reception();
        }
    }

    public static void main(String[] args) {
        new Checkout();
    }
}
