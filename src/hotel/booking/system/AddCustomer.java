package hotel.booking.system;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.Date;
import java.awt.event.*;

public class AddCustomer extends JFrame implements ActionListener{
    
    JTextField tfnumber,tfname, tfcountry;
    JComboBox idcombo;
    JRadioButton rmale, rfemale;
    Choice croom;
    JLabel checkintime;
    JButton add, back;
    
    AddCustomer(){
        
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        JLabel text = new JLabel("NEW CUSTOMER FORM");
        text.setBounds(100,20,300,30);
        text.setFont(new Font("Raleway", Font.PLAIN, 20));
        add(text);
        
        JLabel id = new JLabel("ID");
        id.setBounds(35,80,100,20);
        id.setFont(new Font("Raleway", Font.PLAIN, 20));
        add(id);
        
        String options[] = {"Aadhar Card","Passport","Driving License","Voter-id-Card","Ration Card"};
        idcombo = new JComboBox(options);
        idcombo.setBounds(200,80,150,25);
        idcombo.setBackground(Color.WHITE);
        add(idcombo);
        
        JLabel number = new JLabel("Number");
        number.setBounds(35,120,100,20);
        number.setFont(new Font("Raleway", Font.PLAIN, 20));
        add(number);
        
        tfnumber = new JTextField();
        tfnumber.setBounds(200,120,150,25);
        add(tfnumber);
        
        JLabel name = new JLabel("Name");
        name.setBounds(35,160,100,20);
        name.setFont(new Font("Raleway", Font.PLAIN, 20));
        add(name);
        
        tfname = new JTextField();
        tfname.setBounds(200,160,150,25);
        add(tfname);
        
        JLabel gender = new JLabel("Gender");
        gender.setBounds(35,200,100,20);
        gender.setFont(new Font("Raleway", Font.PLAIN, 20));
        add(gender);
        
        rmale = new JRadioButton("Male");
        rmale.setBackground(Color.WHITE);
        rmale.setBounds(200,200,60,25);
        add(rmale);
        
        rfemale = new JRadioButton("Female");
        rfemale.setBackground(Color.WHITE);
        rfemale.setBounds(270,200,100,25);
        add(rfemale);
        
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(rmale);
        genderGroup.add(rfemale);

        
        JLabel country = new JLabel("Country");
        country.setBounds(35,240,100,20);
        country.setFont(new Font("Raleway", Font.PLAIN, 20));
        add(country);
        
        tfcountry = new JTextField();
        tfcountry.setBounds(200,240,150,25);
        add(tfcountry);
        
        JLabel room = new JLabel("Room Number");
        room.setBounds(35,280,150,20);
        room.setFont(new Font("Raleway", Font.PLAIN, 20));
        add(room);
        
        croom = new Choice();
        try{
            Conn conn = new Conn();
            String query = "select * from room where availability = 'Available'";
            ResultSet rs = conn.s.executeQuery(query);
            while(rs.next()){
                croom.add(rs.getString("roomnumber"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        croom.setBounds(200,280,150,25);
        add(croom); 
        
        JLabel time = new JLabel("Checkin Time");
        time.setBounds(35,320,150,20);
        time.setFont(new Font("Raleway", Font.PLAIN, 20));
        add(time);
        
        Date date = new Date();
        
        checkintime = new JLabel(""+ date);
        checkintime.setBounds(200,320,150,25);
        checkintime.setFont(new Font("Raleway", Font.PLAIN, 16));
        add(checkintime);
        
        add = new JButton("Add");
        add.setBackground(Color.BLACK);
        add.setForeground(Color.WHITE);
        add.setBounds(50,410,120,30);
        add.addActionListener(this);
        add(add);
        
        back = new JButton("Back");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setBounds(200,410,120,30);
        back.addActionListener(this);
        add(back);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/man.png"));
        Image i2 = i1.getImage().getScaledInstance(300,400,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(400,50,300,400);
        add(image);
        
        setBounds(350, 200,800,550);
        setVisible(true);     
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == add){
            String id = (String) idcombo.getSelectedItem();
            String number = tfnumber.getText();
            String name = tfname.getText();
            String gender = null;
            if(rmale.isSelected()){
                gender = "Male";
            }else{
                gender = "Female";
            }
            String country = tfcountry.getText();
            String room = croom.getSelectedItem();
            String time = checkintime.getText();
            
            try{
                String query1 = "insert into customer values('"+id+"', '"+number+"','"+name+"','"+gender+"','"+country+"','"+room+"','"+time+"')";
                String query2 = "update room set availability = 'Occupied' where roomnumber = '"+room+"'";
                
                Conn conn = new Conn();
                conn.s.executeUpdate(query1);
                conn.s.executeUpdate(query2);
                
                JOptionPane.showMessageDialog(null, "New Customer Added Successfully");
                
                setVisible(false);
                new Reception();
                
            }catch(Exception e){
                e.printStackTrace();
            }
        }else if(ae.getSource() == back){
            setVisible(false);
            new Reception();           
        }
    }
    
    public static void main(String[] args){
        new AddCustomer();       
    }  
}
