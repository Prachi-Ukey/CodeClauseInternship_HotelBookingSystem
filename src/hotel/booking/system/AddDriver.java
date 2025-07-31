package hotel.booking.system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AddDriver extends JFrame implements ActionListener{
    
    JButton add, cancel;
    JTextField tfname, tfage, tfcompany, tfmodel, tflocation;
    JComboBox availablecombo,gendercombo;
    
    AddDriver(){
        
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        JLabel heading = new JLabel("Add Drivers");
        heading.setBounds(150,10,200,20);
        heading.setFont(new Font("Tahoma", Font.BOLD, 16));
        add(heading);
        
        JLabel lbname = new JLabel("Name");
        lbname.setBounds(60,70,120,30);
        lbname.setFont(new Font("Tahoma", Font.PLAIN, 17));
        add(lbname);
        
        tfname = new JTextField();
        tfname.setBounds(200,70,150,30);
        add(tfname);
        
        JLabel lbage = new JLabel("Age");
        lbage.setBounds(60,110,120,30);
        lbage.setFont(new Font("Tahoma", Font.PLAIN, 17));
        add(lbage);
        
        tfage = new JTextField();
        tfage.setBounds(200,110,150,30);
        add(tfage);
        
        JLabel lbgender = new JLabel("Gender");
        lbgender.setBounds(60,150,120,30);
        lbgender.setFont(new Font("Tahoma", Font.PLAIN, 17));
        add(lbgender);
        
        String genderOptions[] = {"Male","Female"};
        gendercombo = new JComboBox(genderOptions);
        gendercombo.setBounds(200,150,150,30);
        gendercombo.setBackground(Color.WHITE);
        add(gendercombo);
        
        JLabel lbcompany = new JLabel("Car Company");
        lbcompany.setBounds(60,190,120,30);
        lbcompany.setFont(new Font("Tahoma", Font.PLAIN, 17));
        add(lbcompany);
        
        tfcompany = new JTextField();
        tfcompany.setBounds(200,190,150,30);
        add(tfcompany);
        
        JLabel lbtype = new JLabel("Car Model");
        lbtype.setBounds(60,230,120,30);
        lbtype.setFont(new Font("Tahoma", Font.PLAIN, 17));
        add(lbtype);
        
        tfmodel = new JTextField();
        tfmodel.setBounds(200,230,150,30);
        add(tfmodel);
        
        JLabel lbavailable = new JLabel("Available");
        lbavailable.setBounds(60,270,120,30);
        lbavailable.setFont(new Font("Tahoma", Font.PLAIN, 17));
        add(lbavailable);
        
        String driverOptions[] = {"Available","Busy"};
        availablecombo = new JComboBox(driverOptions);
        availablecombo.setBounds(200,270,150,30);
        availablecombo.setBackground(Color.WHITE);
        add(availablecombo);
        
        JLabel lblocation = new JLabel("Location");
        lblocation.setBounds(60,310,120,30);
        lblocation.setFont(new Font("Tahoma", Font.PLAIN, 17));
        add(lblocation);
        
        tflocation = new JTextField();
        tflocation.setBounds(200,310,150,30);
        add(tflocation);
        
        add = new JButton("Add Driver");
        add.setForeground(Color.WHITE);
        add.setBackground(Color.BLACK);
        add.setBounds(60,370,130,30);
        add.addActionListener(this);
        add(add);
        
        cancel = new JButton("cancel");
        cancel.setForeground(Color.WHITE);
        cancel.setBackground(Color.BLACK);
        cancel.setBounds(220,370,130,30);
        cancel.addActionListener(this);
        add(cancel);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/driver.jpg"));
        Image i2 = i1.getImage().getScaledInstance(500,300,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(400,30,500,300);
        add(image);
                
        setBounds(450, 200,1000,500);
        setVisible(true);   
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == add){
            String name = tfname.getText();
            String age = tfage.getText();
            String gender = (String) gendercombo.getSelectedItem();
            String company = tfcompany.getText();
            String model = tfmodel.getText();
            String available = (String) availablecombo.getSelectedItem();
            String location = tflocation.getText();
            
            try{
            Conn conn = new Conn();
            
            String query = "insert into driver values('"+name+"', '"+age+"','"+gender+"','"+company+"','"+model+"','"+available+"','"+location+"')";
            
            conn.s.executeUpdate(query);
            
            JOptionPane.showMessageDialog(null, "New Driver Added successfully");
            
            setVisible(false);
            
            }catch(Exception e){
                e.printStackTrace();
            }         
        }else{
            setVisible(false);
        }
    }
    
    public static void main(String[] args){
        new AddDriver();
    }  
}
