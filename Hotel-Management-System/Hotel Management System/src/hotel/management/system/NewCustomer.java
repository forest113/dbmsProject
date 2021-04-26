/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.management.system;

import java.awt.BorderLayout;
import java.awt.*;
import java.awt.EventQueue;

import javax.swing.border.EmptyBorder;

import java.awt.Font;
import java.awt.Image;
import java.sql.*;	
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
public class NewCustomer extends JFrame {
	Connection conn = null;
	PreparedStatement pst = null;
	private JPanel contentPane;
	private JTextField t0,t1,t2,t3,t4,t5,t6;
        JComboBox comboBox;
        JRadioButton r1,r2,r3;
        Choice c1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewCustomer frame = new NewCustomer();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public NewCustomer() throws SQLException {
		
                setBounds(530, 200, 850, 550);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
                
                
          /*      ImageIcon i1  = new ImageIcon(ClassLoader.getSystemResource("hotel/management/system/icons/fifth.png"));
                Image i3 = i1.getImage().getScaledInstance(300, 400,Image.SCALE_DEFAULT);
                ImageIcon i2 = new ImageIcon(i3); 
                JLabel l1 = new JLabel(i2);
                l1.setBounds(480,10,300,500);
                add(l1); */
		
		JLabel lblName = new JLabel("NEW CUSTOMER FORM");
		lblName.setFont(new Font("Yu Mincho", Font.PLAIN, 20));
		lblName.setBounds(118, 11, 260, 53);
		contentPane.add(lblName);
                
                JLabel lblId = new JLabel("Aadhar ID :");
		lblId.setBounds(35, 76, 200, 14);
		contentPane.add(lblId);
                
                /*comboBox = new JComboBox(new String[] {"Passport", "Aadhar Card", "Voter Id", "Driving license"});
		comboBox.setBounds(271, 73, 150, 20);
		contentPane.add(comboBox); */
                t0 = new JTextField();
		t0.setBounds(271, 73, 150, 20);
		contentPane.add(t0);
		t0.setColumns(10);
                
                JLabel l2 = new JLabel("Name :");
		l2.setBounds(35, 111, 200, 14);
		contentPane.add(l2);
                
                t1 = new JTextField();
		t1.setBounds(271, 111, 150, 20);
		contentPane.add(t1);
		t1.setColumns(10);
		
		JLabel lblName_1 = new JLabel("DOB(yyyy-mm-dd) :");
		lblName_1.setBounds(35, 151, 200, 14);
		contentPane.add(lblName_1);
		
		t2 = new JTextField();
		t2.setBounds(271, 151, 150, 20);
		contentPane.add(t2);
		t2.setColumns(10);

                
		JLabel lblGender = new JLabel("Gender :");
		lblGender.setBounds(35, 191, 200, 14);
		contentPane.add(lblGender);
                
                r1 = new JRadioButton("Male");
                r1.setFont(new Font("Raleway", Font.BOLD, 14));
                r1.setBackground(Color.WHITE);
                r1.setBounds(271, 191, 80, 12);
                add(r1);
                
                r2 = new JRadioButton("Female");
                r2.setFont(new Font("Raleway", Font.BOLD, 14));
                r2.setBackground(Color.WHITE);
                r2.setBounds(350, 191, 100, 12);
		add(r2);
                
                r3 = new JRadioButton("Other");
                r3.setFont(new Font("Raleway", Font.BOLD, 14));
                r3.setBackground(Color.WHITE);
                r3.setBounds(440, 191, 120, 12);
		add(r3);
                
		JLabel lblCountry = new JLabel("Contat No. :");
		lblCountry.setBounds(35, 231, 200, 14);
		contentPane.add(lblCountry);
		
		JLabel lblReserveRoomNumber = new JLabel("Allocated Room Number :");
		lblReserveRoomNumber.setBounds(35, 274, 200, 14);
		contentPane.add(lblReserveRoomNumber);
                
                c1 = new Choice();
                c1.add(" ");
                
                try{
                    conn c = new conn();
                    ResultSet rs = c.s.executeQuery("select * from room where Avail_Status = 'A'");
                    while(rs.next()){
                        String rno = rs.getString("Room_No");
                        
                        String beds;
                        int b = rs.getInt("No_of_Beds");
                        beds = String.valueOf(b);
                        rno = rno.concat(",");
                        rno = rno.concat(beds);
                        rno = rno.concat(" beds");
                        c1.add(rno);    
                    }
                }catch(Exception e){ }
                c1.setBounds(271, 274, 150, 20);
                
		contentPane.add(c1);
		
		JLabel lblCheckInStatus = new JLabel("Email-ID :");
		lblCheckInStatus.setBounds(35, 316, 200, 14);
		contentPane.add(lblCheckInStatus);
		
	/*	JLabel lblDeposite = new JLabel("Deposit :");
		lblDeposite.setBounds(35, 359, 200, 14);
		contentPane.add(lblDeposite); */
		
		
		
		
		
		t3 = new JTextField();
		t3.setBounds(271, 231, 150, 20);
		contentPane.add(t3);
		t3.setColumns(10);
		
		
		t5 = new JTextField();
		t5.setBounds(271, 316, 150, 20);
		contentPane.add(t5);
		t5.setColumns(10);
		
	/*	t6 = new JTextField();
		t6.setBounds(271, 359, 150, 20);
		contentPane.add(t6);
		t6.setColumns(10); */
                
                 t0.addFocusListener(new FocusListener() {
                 @Override
                 public void focusGained(FocusEvent e) {
                 }

                 @Override
                 public void focusLost(FocusEvent e) {
                    conn c = new conn();
                    String s1 =  t0.getText();
                    try{
                        String qcheck = "select * from customer where Cust_Aadhar_ID = '"+s1+"'";
                        ResultSet rs1 = c.s.executeQuery(qcheck);
                        if (rs1.absolute(1)){
                            JOptionPane.showMessageDialog(null, "This is an existing customer, directly choose room no");
                            t1.setText(rs1.getString("Cust_Name"));
                            t2.setText(rs1.getString("Cust_DOB"));
                            String gender = rs1.getString("Cust_Gender");
                            t3.setText(rs1.getString("Cust_Contact_no"));
                            t5.setText(rs1.getString("Email_ID"));
                            if(gender.compareToIgnoreCase("Female")==0) { 
                                //System.out.println("female");
                                r2.setSelected(true);
                            }
                            if(gender.compareToIgnoreCase("Male")==0) {
                                //System.out.println("male");
                                r1.setSelected(true);
                            }
                            if(gender.compareToIgnoreCase("Other")==0) {
                                r3.setSelected(true);
                            }
                        }
                    }catch(SQLException e1){
	    			System.out.println(e1.getMessage());
	    		}
		    		catch(NumberFormatException s){
		    			JOptionPane.showMessageDialog(null, "Please enter a valid Number");
			}
                 }
    });
                JLabel price = new JLabel(); 
                price.setBounds(430, 274, 100, 20);
                price.setBackground(Color.LIGHT_GRAY);
                
                contentPane.add(price);
                             
                 c1.addItemListener(new ItemListener() {
                     @Override
                     public void itemStateChanged(ItemEvent ie) {
                         String selection = c1.getSelectedItem();
                         String[] rno = selection.split(",");
                         conn c = new conn();
                         try {
                             String querry = "select Price from room where Room_No = "+rno[0];
                             ResultSet rs = c.s.executeQuery(querry);
                             rs.next();
                             
                             price.setText("Price:"+rs.getString("Price"));
                             price.setOpaque(true);
                             
                        }catch(SQLException e1){
	    			System.out.println(e1.getMessage());
	    		}
                     }
                 });
                 
		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                            conn c = new conn();
                            String radio = null;
                            
                            if(r1.isSelected()){ 
                                radio = "Male";
                            }
                            else if(r2.isSelected()){ 
                                radio = "Female";
                            }
                            else if(r3.isSelected()){
                                radio = "Other";                                
                            }
                            
                            String s6 = c1.getSelectedItem();
                          
                            try{
	    			
                                String s1 =  t0.getText(); 
	    			String s2 =  t1.getText();
	    			String s3 =  t2.getText();
                                String s4 =  radio;
	    			String s5 =  t3.getText();
	    			String s7 =  t5.getText();
                             //   String s8 =  t6.getText();
                                String[] room = s6.split(",");
                                String qcheck = "select * from customer where Cust_Aadhar_ID = '"+s1+"'";
                                ResultSet rs1 = c.s.executeQuery(qcheck);
                                if (rs1.absolute(1)){
                                   // JOptionPane.showMessageDialog(null, "This is an existing customer, directly choose room no");
                                    String q1 = "update customer set Cust_Name ='"+s2+"',Cust_DOB ='"+s3+"',Cust_Gender ='"+s4+"',Cust_Contact_No='"+s5+"',Email_ID='"+s7+"' where Cust_Aadhar_ID ='"+s1+"'";
                                    String q2 = "update room set Avail_Status = 'NA' where Room_No = "+room[0]+";";
                                    String q3 = "insert into occupies values(now(),"+room[0]+",'"+s1+"',NULL,NULL)";
                                    c.s.execute(q1);
                                    c.s.executeUpdate(q2);
                                    c.s.execute(q3);
                                }
                                
                                else {
                                    String q1 = "insert into customer values('"+s1+"','"+s2+"','"+s3+"','"+s4+"','"+s5+"','"+s7+"')";
                                    String q2 = "update room set Avail_Status = 'NA' where Room_No = "+room[0]+";";
                                    String q3 = "insert into occupies values(now(),"+room[0]+",'"+s1+"',NULL,NULL)";
                                    c.s.executeUpdate(q1);
                                    c.s.execute(q3);
                                    c.s.execute(q2);
                                }
	    			
	    			JOptionPane.showMessageDialog(null, "Data Inserted Successfully");
                                new Reception().setVisible(true);
                                setVisible(false);
	    		}catch(SQLException e1){
	    			System.out.println(e1.getMessage());
	    		}
		    		catch(NumberFormatException s){
		    			JOptionPane.showMessageDialog(null, "Please enter a valid Number");
			}
			}
		});
		btnNewButton.setBounds(100, 430, 120, 30);
                btnNewButton.setBackground(Color.BLACK);
                btnNewButton.setForeground(Color.WHITE);
		contentPane.add(btnNewButton);
		
		JButton btnExit = new JButton("Back");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                            new Reception().setVisible(true);
                            setVisible(false);
			}
		}); 
		btnExit.setBounds(260, 430, 120, 30);
                btnExit.setBackground(Color.BLACK);
                btnExit.setForeground(Color.WHITE);
		contentPane.add(btnExit);
                
                getContentPane().setBackground(Color.WHITE);
	}
}