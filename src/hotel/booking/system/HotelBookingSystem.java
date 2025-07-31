package hotel.booking.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HotelBookingSystem extends JFrame implements ActionListener{
    HotelBookingSystem(){  //It runs when an  object of the class is created
        //setSize(1366, 830);
        //setLocation(90,60);
        setBounds(90,60,1366,830);
        setLayout(null);
        
        ImageIcon i1  = new ImageIcon(ClassLoader.getSystemResource("icons/hotel.png"));
        JLabel image = new JLabel(i1);
        image.setBounds(0, 0, 1366, 830); //(x, y, width, height)
        add(image);
        
        JLabel text = new JLabel("Hotel Booking System");
        text.setBounds(450, 5 , 1000, 90);
        text.setForeground(Color.WHITE);
        text.setFont(new Font("serif", Font.PLAIN, 50));
        image.add(text);
        
        JButton next = new JButton("Next");
        next.setBounds(600, 80, 150, 40);
        next.setBackground(Color.WHITE);
        next.setForeground(Color.BLACK);
        next.addActionListener(this);
        next.setFont(new Font("serif", Font.PLAIN, 24));
        image.add(next);
        
        setVisible(true);   //make window visible        
    }
    
    public void actionPerformed(ActionEvent ae){
        setVisible(false);
        new Login();     
    }
    
    public static void main(String[] args) {
        new HotelBookingSystem();  //Class Object
    }   
}
