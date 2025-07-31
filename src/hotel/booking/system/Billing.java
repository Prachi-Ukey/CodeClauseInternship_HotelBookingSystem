package hotel.booking.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.SimpleDateFormat;

public class Billing extends JFrame implements ActionListener {

    Choice ccustomer;
    JLabel lblname, lblroom, lblcheckin, lblprice, lbltotal;
    JButton generate, back;

    Billing() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel heading = new JLabel("Billing");
        heading.setFont(new Font("Tahoma", Font.BOLD, 20));
        heading.setBounds(150, 20, 200, 30);
        add(heading);

        JLabel lblid = new JLabel("Customer ID");
        lblid.setBounds(30, 70, 100, 20);
        add(lblid);

        ccustomer = new Choice();
        ccustomer.setBounds(150, 70, 150, 25);
        add(ccustomer);

        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("SELECT number FROM customer");
            while (rs.next()) {
                ccustomer.add(rs.getString("number"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        addLabel("Name:", 110);
        lblname = addValueLabel(110);

        addLabel("Room No:", 140);
        lblroom = addValueLabel(140);

        addLabel("Check-In:", 170);
        lblcheckin = addValueLabel(170);

        addLabel("Price/Day:", 200);
        lblprice = addValueLabel(200);

        addLabel("Total Bill:", 230);
        lbltotal = new JLabel();
        lbltotal.setFont(new Font("Tahoma", Font.BOLD, 16));
        lbltotal.setBounds(150, 230, 200, 20);
        add(lbltotal);

        generate = new JButton("Generate Bill");
        generate.setBounds(30, 280, 130, 30);
        generate.setBackground(Color.BLACK);
        generate.setForeground(Color.WHITE);
        generate.addActionListener(this);
        add(generate);

        back = new JButton("Back");
        back.setBounds(180, 280, 100, 30);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        add(back);

        setBounds(400, 200, 400, 380);
        setVisible(true);
    }

    private void addLabel(String text, int y) {
        JLabel label = new JLabel(text);
        label.setBounds(30, y, 100, 20);
        add(label);
    }

    private JLabel addValueLabel(int y) {
        JLabel label = new JLabel();
        label.setBounds(150, y, 200, 20);
        add(label);
        return label;
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == generate) {
            String id = ccustomer.getSelectedItem();

            try {
                Conn c = new Conn();

                String room = null, name = null, checkin = null;
                ResultSet rs1 = c.s.executeQuery("SELECT * FROM customer WHERE number = '" + id + "'");
                if (rs1.next()) {
                    room = rs1.getString("room");
                    name = rs1.getString("name");
                    checkin = rs1.getString("checkintime");
                } else {
                    JOptionPane.showMessageDialog(null, "Customer not found.");
                    return;
                }

                lblroom.setText(room);
                lblname.setText(name);
                lblcheckin.setText(checkin);

                int pricePerDay = 0;
                ResultSet rs2 = c.s.executeQuery("SELECT price FROM room WHERE roomnumber = '" + room + "'");
                if (rs2.next()) {
                    pricePerDay = rs2.getInt("price");
                } else {
                    JOptionPane.showMessageDialog(null, "Room not found.");
                    return;
                }
                lblprice.setText("₹ " + pricePerDay);

                java.util.Date checkInDate;
                try {
                    checkInDate = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy").parse(checkin);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Invalid date format in DB.");
                    return;
                }

                java.util.Date currentDate = new java.util.Date();
                long diff = currentDate.getTime() - checkInDate.getTime();
                int days = (int) (diff / (1000 * 60 * 60 * 24));
                if (days == 0) days = 1;

                int total = pricePerDay * days;
                lbltotal.setText("₹ " + total);

            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error generating bill.");
            }
        } else if (ae.getSource() == back) {
            setVisible(false);
            new Reception();
        }
    }

    public static void main(String[] args) {
        new Billing();
    }
}
