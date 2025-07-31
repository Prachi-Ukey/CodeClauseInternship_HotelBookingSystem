package hotel.booking.system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AddRooms extends JFrame implements ActionListener{
    
    JButton add, cancel;
    JTextField tfroom, tfprice;
    JComboBox typecombo,availablecombo,cleancombo;
    
    AddRooms(){
        
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        JLabel heading = new JLabel("Add Rooms");
        heading.setBounds(150,20,200,20);
        heading.setFont(new Font("Tahoma", Font.BOLD, 16));
        add(heading);
        
        JLabel lbroomno = new JLabel("Room Number");
        lbroomno.setBounds(60,80,120,30);
        lbroomno.setFont(new Font("Tahoma", Font.PLAIN, 17));
        add(lbroomno);
        
        tfroom = new JTextField();
        tfroom.setBounds(200,80,150,30);
        add(tfroom);
        
        JLabel lbavailable = new JLabel("Available");
        lbavailable.setBounds(60,130,120,30);
        lbavailable.setFont(new Font("Tahoma", Font.PLAIN, 17));
        add(lbavailable);
        
        String availableOptions[] = {"Available","Occupied"};
        availablecombo = new JComboBox(availableOptions);
        availablecombo.setBounds(200,130,150,30);
        availablecombo.setBackground(Color.WHITE);
        add(availablecombo);
        
        JLabel lbclean = new JLabel("Available");
        lbclean.setBounds(60,180,120,30);
        lbclean.setFont(new Font("Tahoma", Font.PLAIN, 17));
        add(lbclean);
        
        String cleanOptions[] = {"Cleaned","Dirty"};
        cleancombo = new JComboBox(cleanOptions);
        cleancombo.setBounds(200,180,150,30);
        cleancombo.setBackground(Color.WHITE);
        add(cleancombo);
        
        JLabel lbprice = new JLabel("Price");
        lbprice.setBounds(60,230,120,30);
        lbprice.setFont(new Font("Tahoma", Font.PLAIN, 17));
        add(lbprice);
        
        tfprice = new JTextField();
        tfprice.setBounds(200,230,150,30);
        add(tfprice);
        
        JLabel lbtype = new JLabel("Bed Type");
        lbtype.setBounds(60,280,120,30);
        lbtype.setFont(new Font("Tahoma", Font.PLAIN, 17));
        add(lbtype);
        
        String typeOptions[] = {"Single Bed","Double Bed"};
        typecombo = new JComboBox(typeOptions);
        typecombo.setBounds(200,280,150,30);
        typecombo.setBackground(Color.WHITE);
        add(typecombo);
        
        add = new JButton("Add Room");
        add.setForeground(Color.WHITE);
        add.setBackground(Color.BLACK);
        add.setBounds(60,350,130,30);
        add.addActionListener(this);
        add(add);
        
        cancel = new JButton("cancel");
        cancel.setForeground(Color.WHITE);
        cancel.setBackground(Color.BLACK);
        cancel.setBounds(220,350,130,30);
        cancel.addActionListener(this);
        add(cancel);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/rooms.jpg"));
        JLabel image = new JLabel(i1);
        image.setBounds(400,30,500,300);
        add(image);
                
        setBounds(330, 200,940,470);
        setVisible(true);   
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == add){
            String roomnumber = tfroom.getText();
            String availability = (String) availablecombo.getSelectedItem();
            String status = (String) cleancombo.getSelectedItem();
            String price = tfprice.getText();
            String type = (String) typecombo.getSelectedItem();
            
            try{
            Conn conn = new Conn();
            
            String query = "insert into room values('"+roomnumber+"', '"+availability+"','"+status+"','"+price+"','"+type+"')";
            
            conn.s.executeUpdate(query);
            
            JOptionPane.showMessageDialog(null, "New Roon Added successfully");
            
            setVisible(false);
            
            }catch(Exception e){
                e.printStackTrace();
            }                  
        }else{
            setVisible(false);
        }
    }
    
    public static void main(String[] args){
        new AddRooms();
    }   
}
