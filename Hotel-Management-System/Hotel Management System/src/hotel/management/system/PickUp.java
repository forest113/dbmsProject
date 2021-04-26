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
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PickUp extends JFrame {
	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	private JPanel contentPane;
	private JTable table;
        Choice c1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PickUp frame = new PickUp();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public void close()
	{
		this.dispose();
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public PickUp() throws SQLException {
		//conn = Javaconnect.getDBConnection();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(530, 200, 800, 600);
		contentPane = new JPanel();
                contentPane.setBackground(Color.black);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
                
               
		
		JLabel lblPickUpService = new JLabel("Airport Pick-up and Drop Service(Available Drivers)");
		lblPickUpService.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPickUpService.setBounds(40, 11, 378, 25);
		contentPane.add(lblPickUpService);
		
		JLabel lblTypeOfCar = new JLabel("Filter By: Type of Car");
		lblTypeOfCar.setBounds(29, 54, 139, 14);
		contentPane.add(lblTypeOfCar);
                
                 c1 = new Choice();
                 c1.setBounds(200, 54, 150, 25);
		contentPane.add(c1);
                c1.add("All");
                c1.add("SUV");
                c1.add("Sedan");
                c1.add("Hatchback");
                
                JLabel trip = new JLabel("Schedule Airport trip:");
                trip.setFont(new Font("Tahoma", Font.BOLD, 12));
                //nodata.setVisible(false);
		trip.setBounds(408, 54, 139, 14);
                //nodata.setForeground(Color.RED);
		contentPane.add(trip);
                JLabel id = new JLabel("Enter Driver's ID:");
                //nodata.setVisible(false);
		id.setBounds(408, 74, 139, 14);
                //nodata.setForeground(Color.RED);
		contentPane.add(id);
                
                JLabel nadr = new JLabel("Trip scheduled. Driver busy for next 3 hours.");
                nadr.setFont(new Font("Tahoma", Font.ITALIC, 12));
                nadr.setVisible(false);
		nadr.setBounds(272, 334, 289, 14);
               nadr.setForeground(Color.BLUE);
		contentPane.add(nadr);
                
               /* JRadioButton pick=new JRadioButton("Pick-up", true);
                pick.setBounds(518, 54, 90, 14);
                contentPane.add(pick);
                 JRadioButton drop=new JRadioButton("Drop", false);
                drop.setBounds(616, 54, 90, 14);
                contentPane.add(drop);*/
                JTextField Tf=new JTextField();
                 Tf.setBounds(518,74,60,16);
                 contentPane.add(Tf);
                 
                
               /* JLabel adrs=new JLabel("Pick-up Location");
              adrs.setBounds(408, 74, 139, 14);
                contentPane.add(adrs);
                if(pick.isSelected()){    
                    drop.setSelected(false);
                     adrs.setText("Pick-up Location");
                } 
                if(drop.isSelected()){    
                    pick.setSelected(false);
                     adrs.setText("Drop Location");
                }*/
                 JLabel nodata = new JLabel("No such driver available.");
                nodata.setVisible(false);
                 nodata.setFont(new Font("Tahoma", Font.ITALIC, 12));
		nodata.setBounds(272, 334, 179, 14);
                nodata.setForeground(Color.RED);
		contentPane.add(nodata);
               
               JLabel noloc=new JLabel("Please enter valid Driver ID.");
                noloc.setVisible(false);
		noloc.setBounds(502, 254, 139, 14);
                noloc.setForeground(Color.RED);
		contentPane.add(noloc);
                  JButton goo = new JButton("Schedule");
		goo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
                                    conn c  = new conn();
                                    nadr.setVisible(false);
                                    noloc.setVisible(false);
                                     nodata.setVisible(false);
                                    String loc = Tf.getText();
                                    ResultSet rs1 = c.s.executeQuery("select Emp_ID from driver where TIMESTAMPDIFF(SECOND, driver.Avail_Status, ADDTIME(NOW(),'05:30:0'))>0");
                                    int flag=0;
                                     while(rs1.next()){
                                        if (loc==(rs1.getString("Emp_ID")))
                                        {
                                            flag=1;
                                        }
                                         }
                                     if(flag==0)
                                    {
                                        noloc.setVisible(true);
                                    }
                                    else
                                    {
                                         
                                          String sql = "update driver SET driver.Avail_Status=ADDTIME(NOW(),'08:30:0') where driver.Emp_Id='"+loc+"'"; 
                                        c.s.executeUpdate(sql);
                                        nadr.setVisible(true);
                                    }
				
				//String displayCustomersql = /*"select employee.Emp_Name,employee.Emp_Age,employee.emp_Gender,driver.Car_Type FROM */" select driver.Avail_Status FROM driver where  TIMESTAMPDIFF(SECOND,driver.Avail_Status, NOW())>0 ";
                                
				
			}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
				
			
		});
               goo.setBounds(581, 98, 100, 20);
                goo.setBackground(Color.BLACK);
                goo.setForeground(Color.WHITE);
		contentPane.add(goo);
                
              
                
                JButton btnLoadData = new JButton("Show Available Drivers");
		btnLoadData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
                                    conn c  = new conn();
                                    nodata.setVisible(false);
                                    nadr.setVisible(false);
                                    String data = c1.getItem(c1.getSelectedIndex());
                                    
                                    if(data=="All")
                                    {
                                        String displayCustomersql = "select employee.Emp_Id,employee.Emp_Name,employee.Emp_Age,employee.emp_Gender,driver.Car_Type FROM employee INNER JOIN driver where employee.Emp_Id=driver.Emp_Id AND TIMESTAMPDIFF(SECOND, driver.Avail_Status, ADDTIME(NOW(),'05:30:0'))>0";
                                        ResultSet rs = c.s.executeQuery(displayCustomersql);
                                        if (!rs.isBeforeFirst() ) {   
                                            nodata.setVisible(true);
                                               }                                       
                                        table.setModel(DbUtils.resultSetToTableModel(rs));
                                    }
                                    else
                                    {
                                        String displayCustomersql = "select employee.Emp_Id,employee.Emp_Name,employee.Emp_Age,employee.emp_Gender,driver.Car_Type FROM employee INNER JOIN driver where employee.Emp_Id=driver.Emp_Id AND TIMESTAMPDIFF(SECOND, driver.Avail_Status, ADDTIME(NOW(),'05:30:0'))>0 AND driver.Car_Type ='"+c1.getSelectedItem()+"'";
                                        ResultSet rs = c.s.executeQuery(displayCustomersql);
                                        if (!rs.isBeforeFirst() ) {   
                                            nodata.setVisible(true);
                                               } 
                                        table.setModel(DbUtils.resultSetToTableModel(rs));
                                    }
				
				//String displayCustomersql = /*"select employee.Emp_Name,employee.Emp_Age,employee.emp_Gender,driver.Car_Type FROM */" select driver.Avail_Status FROM driver where  TIMESTAMPDIFF(SECOND,driver.Avail_Status, NOW())>0 ";
                                
				
			}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
				
			
		});
		btnLoadData.setBounds(202, 88, 180, 30);
                btnLoadData.setBackground(Color.BLACK);
                btnLoadData.setForeground(Color.WHITE);
		contentPane.add(btnLoadData);
		
               // c1 = new Choice();
                /*try{
                    conn c = new conn();
                    ResultSet rs = c.s.executeQuery("select * from driver");
                    while(rs.next()){
                        c1.add(rs.getString("brand"));    
                    }
                }catch(Exception e){ }*/
               

                JLabel lblid = new JLabel("DRIVER ID");
               // lblid.setFont(new Font("",Font.ITALIC,));
		lblid.setBounds(44, 208, 96, 14);
		contentPane.add(lblid);
		
		JLabel lblInfo = new JLabel("DRIVER NAME");
		lblInfo.setBounds(198, 208, 96, 14);
		contentPane.add(lblInfo);
		
		/*JButton btnRegister = new JButton("Filter by Availibilty");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String SQL = "select * from driver where brand = '"+c1.getSelectedItem()+"'";
				try{
				
					conn c = new conn();
					rs = c.s.executeQuery(SQL);
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
					
					
				}catch (SQLException ss)
				{
					ss.printStackTrace();
				}
				
				
			}
		});
		btnRegister.setBounds(200, 500, 120, 30);
                btnRegister.setBackground(Color.BLACK);
                btnRegister.setForeground(Color.WHITE);
		contentPane.add(btnRegister);*/
		
		JButton btnExit = new JButton("Back");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
                            nadr.setVisible(false);
				new mainMenu().setVisible(true);
                                setVisible(false);
			}
		});
		btnExit.setBounds(320, 500, 120, 30);
                btnExit.setBackground(Color.BLACK);
                btnExit.setForeground(Color.WHITE);
		contentPane.add(btnExit);
		
		table = new JTable();
		table.setBounds(0, 233, 800, 250);
		contentPane.add(table);
		
		JLabel lblNewLabel = new JLabel("DRIVER AGE");
		lblNewLabel.setBounds(349, 208, 96, 14);
		contentPane.add(lblNewLabel);
		
		
		
		JLabel lblTypeOfDriver = new JLabel("DRIVER GENDER");
		lblTypeOfDriver.setBounds(506, 208, 120, 14);
		contentPane.add(lblTypeOfDriver);
		
		JLabel lblDateOfThe = new JLabel("CAR TYPE");
		lblDateOfThe.setBounds(669, 208, 75, 14);
		contentPane.add(lblDateOfThe);
	
		/*JLabel lblAirport = new JLabel("NEXT AVAILABLE AT");
		lblAirport.setBounds(650, 208, 156, 14);
		contentPane.add(lblAirport);*/
		
		/*JLabel lblAvailable = new JLabel("Location");
		lblAvailable.setBounds(700, 208, 73, 14);
		contentPane.add(lblAvailable);*/
                
                
                
                getContentPane().setBackground(Color.WHITE);
	}
}