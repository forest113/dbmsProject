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

/**
 *
 * @author 91818
 */
public class Foodmenu extends JFrame {
    Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	private JPanel contentPane;
	private JTable table;
 
        Choice c1;
    public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Foodmenu frame = new Foodmenu();
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
    

public Foodmenu() throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(530, 200, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPickUpService = new JLabel("In-Room Dining Menu");
		lblPickUpService.setFont(new Font("Tahoma", Font.ITALIC, 24));
		lblPickUpService.setBounds(40, 8, 378, 25);
		contentPane.add(lblPickUpService);
		
		JLabel lblTypeOfCar = new JLabel("Select Item:");
		lblTypeOfCar.setBounds(39, 59, 139, 14);
		contentPane.add(lblTypeOfCar);
                
                JComboBox food=new JComboBox();
                food.setBounds(130, 54, 150, 25);
                contentPane.add(food);
                 conn c  = new conn();
                String displayCustomersql = "select * from food_item";
                                        ResultSet r = c.s.executeQuery(displayCustomersql);

                while (r.next()) {  

                    food.addItem(r.getString("Item_Name"));  
                }
                 c1 = new Choice();
                 c1.setBounds(200, 54, 150, 25);
		//contentPane.add(c1);
                c1.add("All");
                c1.add("SUV");
                c1.add("Sedan");
                c1.add("Hatchback");
                
               
                JLabel id = new JLabel("Enter Quantity:");
                //nodata.setVisible(false);
		id.setBounds(39, 92, 139, 14);
                //nodata.setForeground(Color.RED);
		contentPane.add(id);
                
                JLabel nadr = new JLabel("Order Successful.");
                nadr.setFont(new Font("Tahoma", Font.ITALIC, 12));
                nadr.setVisible(false);
		nadr.setBounds(472, 74, 289, 14);
               nadr.setForeground(Color.BLUE);
		contentPane.add(nadr);
                
               /* JRadioButton pick=new JRadioButton("Pick-up", true);
                pick.setBounds(518, 54, 90, 14);
                contentPane.add(pick);
                 JRadioButton drop=new JRadioButton("Drop", false);
                drop.setBounds(616, 54, 90, 14);
                contentPane.add(drop);*/
                JTextField Tf=new JTextField();
                 Tf.setBounds(142, 91, 52, 18);
                 contentPane.add(Tf);
                 
                 JLabel rmid = new JLabel("Enter Room No:");
                //nodata.setVisible(false);
		rmid.setBounds(39, 122, 139, 14);
                //nodata.setForeground(Color.RED);
		contentPane.add(rmid);
                
                JTextField troo=new JTextField();
                 troo.setBounds(142, 122, 52, 18);
                 contentPane.add(troo);
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
                 JLabel nodata = new JLabel("No menu items available.");
                nodata.setVisible(false);
                 nodata.setFont(new Font("Tahoma", Font.ITALIC, 12));
		nodata.setBounds(472, 74, 289, 14);
                nodata.setForeground(Color.RED);
		contentPane.add(nodata);
               
               JLabel noloc=new JLabel("Room is vacant. Please check again.");
                noloc.setVisible(false);
		noloc.setBounds(472, 74, 289, 14);
                noloc.setForeground(Color.RED);
		contentPane.add(noloc);
                
                JLabel price=new JLabel();
                 price.setVisible(false);
		price.setBounds(472, 94, 289, 14);
                price.setForeground(Color.BLUE);
		contentPane.add(price);
                 
                  JButton goo = new JButton("Confirm Order");
		goo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
                                    conn c  = new conn();
                                    nadr.setVisible(false);
                                    noloc.setVisible(false);
                                     nodata.setVisible(false);
                                      price.setVisible(false);
                                    String quant = Tf.getText();
                                    String rm = troo.getText();
                                    
                                    int q=Integer.parseInt(quant);
                                      String getp="select Item_Price from food_item where Item_Name='"+food.getSelectedItem()+"'";
                                       ResultSet r3 = c.s.executeQuery(getp);
                                      String pri=null;
                                       while (r3.next()) {  

                                            pri=r3.getString("Item_Price");  
                                       }
                                    
                                      int p=Integer.parseInt(pri); 
                                     int cost=p*q;
                                     price.setText("Collect Rs. "+String.valueOf(cost));
                                     String check="select Cust_Aadhar_ID from occupies where Check_Out is NULL and Room_No='"+rm+"'";
                                      ResultSet r2 = c.s.executeQuery(check);
                                      String cid=null;
                                      
                                      while (r2.next()) {  

                                            cid=r2.getString("Cust_Aadhar_ID");  
                                       }
                                      if(cid==null)
                                      {
                                            nadr.setVisible(false);
                                          noloc.setVisible(true);
                                           price.setVisible(false);
                                      }
                                      else
                                      {
                                          String sql = "INSERT INTO eats VALUES(ADDTIME(NOW(),'05:30:0'),'"+quant+"','"+food.getSelectedItem()+"','"+cid+"')"; 
                                        c.s.executeUpdate(sql);
                                        nadr.setVisible(true);
                                         price.setVisible(true);
                                        
                                        
                                    //}
                                      }
                                      //System.out.println(cid);
                                    /*if(loc=="")
                                    {
                                        noloc.setVisible(true);
                                    }
                                    else
                                    {*/
                                         
                                       
				
				//String displayCustomersql = /*"select employee.Emp_Name,employee.Emp_Age,employee.emp_Gender,driver.Car_Type FROM */" select driver.Avail_Status FROM driver where  TIMESTAMPDIFF(SECOND,driver.Avail_Status, NOW())>0 ";
                                
				
			}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
				
			
		});
               goo.setBounds(72, 162, 120, 20);
                goo.setBackground(Color.BLACK);
                goo.setForeground(Color.WHITE);
		contentPane.add(goo);
                
              JLabel lblInfo = new JLabel("ITEM NAME");
		lblInfo.setBounds(178, 208, 96, 14);
                    lblInfo.setVisible(false);
		contentPane.add(lblInfo);
                
                JLabel lblTypeOfDriver = new JLabel("ITEM PRICE");
                lblTypeOfDriver.setVisible(false);
		lblTypeOfDriver.setBounds(506, 208, 120, 14);
		contentPane.add(lblTypeOfDriver);
		
		
                
                JButton btnLoadData = new JButton("Show Menu");
		btnLoadData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
                                    conn c  = new conn();
                                    nodata.setVisible(false);
                                    nadr.setVisible(false);
                                    noloc.setVisible(false);
                                     price.setVisible(false);
                                    lblTypeOfDriver.setVisible(true);
                                    lblInfo.setVisible(true);
                                    
                                        String displayCustomersql = "select food_item.Item_Name,food_item.Item_Price from food_item";
                                        ResultSet rs = c.s.executeQuery(displayCustomersql);
                                        if (!rs.isBeforeFirst() ) {   
                                            nodata.setVisible(true);
                                               }                                       
                                        table.setModel(DbUtils.resultSetToTableModel(rs));
                                    
                                        
                                    
				
				//String displayCustomersql = /*"select employee.Emp_Name,employee.Emp_Age,employee.emp_Gender,driver.Car_Type FROM */" select driver.Avail_Status FROM driver where  TIMESTAMPDIFF(SECOND,driver.Avail_Status, NOW())>0 ";
                                
				
			}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
				
			
		});
		btnLoadData.setBounds(290, 8, 128, 25);
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
                             price.setVisible(false);
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
		
		
		
		
		
		
	
		/*JLabel lblAirport = new JLabel("NEXT AVAILABLE AT");
		lblAirport.setBounds(650, 208, 156, 14);
		contentPane.add(lblAirport);*/
		
		/*JLabel lblAvailable = new JLabel("Location");
		lblAvailable.setBounds(700, 208, 73, 14);
		contentPane.add(lblAvailable);*/
                
                
                
                getContentPane().setBackground(Color.WHITE);
	}
}
