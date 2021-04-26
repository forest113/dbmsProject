/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.management.system;

import java.awt.BorderLayout;
import java.awt.*;
import java.awt.EventQueue;


import java.sql.*;	
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.Border;

import java.awt.Font;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.time.temporal.ChronoUnit;
import java.time.LocalDate;
import java.time.*; 

public class CheckOut extends JFrame{
	Connection conn = null;
	PreparedStatement pst = null;
	private JPanel contentPane;
	private JTextField t0,t1;
        Choice c1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CheckOut frame = new CheckOut();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public void close(){
		this.dispose();
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public CheckOut() throws SQLException {
		//conn = Javaconnect.getDBConnection();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(530, 200, 800, 394);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
                
              /*  ImageIcon i1  = new ImageIcon(ClassLoader.getSystemResource("hotel/management/system/icons/sixth.jpg"));
                Image i3 = i1.getImage().getScaledInstance(400, 225,Image.SCALE_DEFAULT);
                ImageIcon i2 = new ImageIcon(i3);
                JLabel l1 = new JLabel(i2);
                l1.setBounds(300,0,500,225);
                add(l1); */
		
		JLabel lblCheckOut = new JLabel("Check Out ");
		lblCheckOut.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCheckOut.setBounds(70, 11, 140, 35);
		contentPane.add(lblCheckOut);
		
		JLabel lblName = new JLabel("Room No :");
		lblName.setBounds(20, 80, 80, 20);
		contentPane.add(lblName);
                
                t0 = new JTextField();
                t0.setBounds(130, 80, 150, 20);
		contentPane.add(t0);
                JButton btnGetDetails = new JButton("Get price");
                btnGetDetails.setBackground(Color.BLACK);
                btnGetDetails.setForeground(Color.WHITE);
                btnGetDetails.setBounds(290,78,100,25);
                contentPane.add(btnGetDetails);
            /*    ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("hotel/management/system/icons/tick.png"));
                Image i5 = i4.getImage().getScaledInstance(20, 20,Image.SCALE_DEFAULT);
                ImageIcon i6 = new ImageIcon(i5);
                JButton l2 = new JButton(i6);
                l2.setBounds(290,82,20,20);
                add(l2); */
                JLabel lblaadhartext = new JLabel("Cust Adhar:");
		lblaadhartext.setBounds(20, 110, 86, 20);
		contentPane.add(lblaadhartext);
                JTextField tfaadhar = new JTextField();
		tfaadhar.setBounds(130, 110, 150, 20);
		contentPane.add(tfaadhar);
                
                JLabel lblamountTxt = new JLabel("Amount due:");
		lblamountTxt.setBounds(20, 135, 86, 20);
		contentPane.add(lblamountTxt);
                JLabel lblamount = new JLabel(" ");
		lblamount.setBounds(130, 135, 150, 20);
		contentPane.add(lblamount);
                
                JLabel lblfeedback = new JLabel("FeedBack:");
		lblfeedback.setBounds(20, 160, 86, 20);
		contentPane.add(lblfeedback);
                JTextArea feedback = new JTextArea();
		feedback.setBounds(130, 160, 150, 50);
                Border border = BorderFactory.createLineBorder(Color.DARK_GRAY, 1);
                feedback.setBorder(border);
		contentPane.add(feedback);
                
                t0.addFocusListener(new FocusListener() {
                    @Override
                    public void focusGained(FocusEvent e) {
                       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }

                    @Override
                    public void focusLost(FocusEvent e) {
                        try{
                            
                            conn c = new conn();
                            String number = t0.getText();
                            ResultSet rs = c.s.executeQuery("select * from room where Room_No ="+number);
                            rs.next();
                            String AStatus = rs.getString("Avail_Status");
                            if(AStatus.compareTo("A")==0){
                                JOptionPane.showMessageDialog(null, "That room is not currently occupied");
                                t0.setText(null);
                            }
                        }catch(Exception e1){ }
                    }
                    
                });
                
                btnGetDetails.addActionListener(new ActionListener(){
                    
                    public void actionPerformed(ActionEvent ae){
                        //System.out.println("Hi");
                        try{
                            
                            conn c = new conn();
                            String number = t0.getText();
                            String aadhar = tfaadhar.getText();
                            ResultSet rs = c.s.executeQuery("select * from occupies where Room_No ="+number+" and Cust_Aadhar_ID = '"+aadhar+"' order by Check_In DESC");
                            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                            LocalDate cdate = timestamp.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                            if(rs.next()){
                                System.out.println("clicked");
                                //lblaadhar.setText(rs.getString("Cust_Aadhar_ID")); 
                                String chin = rs.getString("Check_in");
                                System.out.println(chin);
                                t1.setText(chin);
                                timestamp = Timestamp.valueOf(chin);
                                LocalDate pdate = timestamp.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                                long noOfDays = ChronoUnit.DAYS.between(pdate,cdate);
                                
                                System.out.println(noOfDays+","+cdate);
                                ResultSet rs1 = c.s.executeQuery("select * from room where Room_No = "+number);
                                rs1.next();
                                int price = rs1.getInt("Price");
                                long Amt = price*noOfDays;
                                String amt = String.valueOf(Amt);
                                lblamount.setText("Rs."+amt+"/-");
                                
                                
                            }
                            else{
                                 JOptionPane.showMessageDialog(null, "Room no or Aadhar number is incorrect");
                                t0.setText(null);
                                tfaadhar.setText(null);
                            }
                        }catch(Exception e){ }
                    }
                });

		
		
		
		t1 = new JTextField();
                t1.setBounds(130, 132, 150, 20);
		//contentPane.add(t1);
                
                
                
                
                
		
                
		JButton btnCheckOut = new JButton("Check Out");
		btnCheckOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                                String id = tfaadhar.getText();
                                String s1 = t0.getText();
                                String s2 = feedback.getText();
                                String s3 = t1.getText();
				String q1 = "update occupies set Check_Out = now(), Feedback = '"+s2+"' where Check_In = '"+s3+"'and Cust_Aadhar_ID = '"+id+"' and Room_No = "+s1;
                                String q2 = "update room set Avail_Status = 'A' where Room_No = "+s1;
                                
                                
				conn c = new conn();

	    		try{
	    			
	    			
	    			c.s.executeUpdate(q1);
	    			c.s.executeUpdate(q2);
	    			JOptionPane.showMessageDialog(null, "Check Out Successful");
	    			new Reception().setVisible(true);
                                setVisible(false);
	    		}catch(SQLException e1){
	    			System.out.println(e1.getMessage());
	    		}
			}
		});
		btnCheckOut.setBounds(50, 300, 100, 25);
                btnCheckOut.setBackground(Color.BLACK);
                btnCheckOut.setForeground(Color.WHITE);
		contentPane.add(btnCheckOut);
		
		JButton btnExit = new JButton("Back");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new mainMenu().setVisible(true);
                                setVisible(false);
			}
		});
		btnExit.setBounds(160, 300, 100, 25);
                btnExit.setBackground(Color.BLACK);
                btnExit.setForeground(Color.WHITE);
		contentPane.add(btnExit);
                
                
                getContentPane().setBackground(Color.WHITE);
	}

}