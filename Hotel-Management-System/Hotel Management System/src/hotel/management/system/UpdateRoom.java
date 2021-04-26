/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.management.system;

import java.awt.BorderLayout;
import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;
public class UpdateRoom extends JFrame {
Connection conn = null;
PreparedStatement pst = null;
	private JPanel contentPane;
	private JTextField txt_ID;
	private JTextField txt_rno;
	private JTextField txt_Status;
	private JTextField txt_beds;
        
        Choice c1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateRoom frame = new UpdateRoom();
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
	public UpdateRoom() throws SQLException {
		//conn = Javaconnect.getDBConnection();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(530, 200, 1000, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
                
                ImageIcon i1  = new ImageIcon(ClassLoader.getSystemResource("hotel/management/system/icons/seventh.jpg"));
                Image i3 = i1.getImage().getScaledInstance(550, 250,Image.SCALE_DEFAULT);
                ImageIcon i2 = new ImageIcon(i3);
                JLabel l1 = new JLabel(i2);
                l1.setBounds(400,80,600,250);
                add(l1);
		
		JLabel lblUpdateRoomStatus = new JLabel("Update Room details");
		lblUpdateRoomStatus.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblUpdateRoomStatus.setBounds(85, 11, 206, 34);
		contentPane.add(lblUpdateRoomStatus);
		
		JLabel lblNewLabel = new JLabel("Room No:");
		lblNewLabel.setBounds(27, 85, 90, 20);
		contentPane.add(lblNewLabel);
                
              
		
		JLabel lblAvailability = new JLabel("Availability:");
		lblAvailability.setBounds(27, 115, 90, 20);
		contentPane.add(lblAvailability);
		
		JLabel lblbeds = new JLabel("No of beds:");
		lblbeds.setBounds(27, 145, 90, 20);
		contentPane.add(lblbeds);
		
		
		txt_rno = new JTextField();
		txt_rno.setBounds(160, 85, 140, 20);
		contentPane.add(txt_rno);
		txt_rno.setColumns(10);
		
		txt_Status = new JTextField();
		txt_Status.setBounds(160, 115, 140, 20);
		contentPane.add(txt_Status);
		txt_Status.setColumns(10);
                
                txt_beds = new JTextField();
		txt_beds.setBounds(160, 145, 140, 20);
		contentPane.add(txt_beds);
		txt_beds.setColumns(10); 
                
                txt_rno.addFocusListener(new FocusListener() {
                    @Override
                    public void focusGained(FocusEvent e) {
                        //To change body of generated methods, choose Tools | Templates.
                    }

                    @Override
                    public void focusLost(FocusEvent e) {
                       conn c = new conn();
                    String s1 =  txt_rno.getText();
                    try{
                        String qcheck = "select * from room where Room_No = "+s1;
                        ResultSet rs1 = c.s.executeQuery(qcheck);
                        if (rs1.absolute(1)){
                            
                            txt_Status.setText(rs1.getString("Avail_Status"));
                            txt_beds.setText(rs1.getString("No_of_Beds"));
                            
                        }
                    }catch(SQLException e1){
	    			System.out.println(e1.getMessage());
	    		}
                    }
                    
                });
                
                JLabel h2 = new JLabel("Add a New Room");
		h2.setFont(new Font("Tahoma", Font.PLAIN, 17));
		h2.setBounds(95, 220, 206, 34);
		contentPane.add(h2);
                
                JLabel lblNewLabel1 = new JLabel("Room No:");
		lblNewLabel1.setBounds(27, 280, 90, 20);
		contentPane.add(lblNewLabel1);
                
              
		
		JLabel lblAvailability1 = new JLabel("Availability:");
		lblAvailability1.setBounds(27, 310, 90, 20);
		contentPane.add(lblAvailability1);
		
		JLabel lblbeds1 = new JLabel("No of beds:");
		lblbeds1.setBounds(27, 340, 90, 20);
		contentPane.add(lblbeds1);
		
		
		JTextField txt_rno1 = new JTextField();
		txt_rno1.setBounds(160, 280, 140, 20);
		contentPane.add(txt_rno1);
		txt_rno1.setColumns(10);
		
		JTextField txt_Status1 = new JTextField();
		txt_Status1.setBounds(160, 310, 140, 20);
		contentPane.add(txt_Status1);
		txt_Status1.setColumns(10);
                
                JTextField txt_beds1 = new JTextField();
		txt_beds1.setBounds(160, 340, 140, 20);
		contentPane.add(txt_beds1);
		txt_beds1.setColumns(10);
                
                
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) throws NumberFormatException {
				
				try{
                                    String s1 = txt_rno.getText();
                                    String s2 = txt_Status.getText();
                                    String s3 = txt_beds.getText();
                                    int price = Integer.parseInt(s3)*2000;
                                    String s4 = String.valueOf(price);
                                    conn c = new conn();
                                    String str = "update room set Avail_Status = '"+s2+"', No_of_beds = '"+s3+"', Price ="+s4+"  where Room_No = "+s1;
                                    c.s.executeUpdate(str);
                                    JOptionPane.showMessageDialog(null, "Update Sucessful");
                                    txt_rno.setText(null);
                                    txt_Status.setText(null);
                                    txt_beds.setText(null);
                                    //new Reception().setVisible(true);
                                    //setVisible(false);
				}catch (Exception ee){
					ee.printStackTrace();
				}
				
			
			}
		});
		btnUpdate.setBounds(27, 175, 90, 20);
                btnUpdate.setBackground(Color.BLACK);
                btnUpdate.setForeground(Color.WHITE);
		contentPane.add(btnUpdate);
                
                JButton b1 = new JButton("Add");
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                            try{
                                String s1 = txt_rno1.getText();
                                String s2 = txt_Status1.getText();
                                String s3 = txt_beds1.getText();
                                int price = Integer.parseInt(s3)*2000;
                                String s4 = String.valueOf(price);
				conn c = new conn();
                                c.s.execute("insert into room values("+s1+",'"+s2+"','C',"+s4+","+s3+")");
                                JOptionPane.showMessageDialog(null, "Insertion Sucessful");
                                txt_rno1.setText(null);
                                txt_Status1.setText(null);
                                txt_beds1.setText(null);
                            }catch(Exception ee){}
                       
                        }
		});
		b1.setBounds(27, 370, 90, 20);
                b1.setBackground(Color.BLACK);
                b1.setForeground(Color.WHITE);
		contentPane.add(b1);
		
		JButton btnExit = new JButton("Back");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Reception().setVisible(true);
                                setVisible(false);
			}
		});
		btnExit.setBounds(27, 400, 90, 20);
                btnExit.setBackground(Color.BLACK);
                btnExit.setForeground(Color.WHITE);
		contentPane.add(btnExit);
		
		/*JLabel lblRoomId = new JLabel("Room Number:");
		lblRoomId.setBounds(27, 133, 100, 14);
		contentPane.add(lblRoomId); */
		
		getContentPane().setBackground(Color.WHITE);
	}

}