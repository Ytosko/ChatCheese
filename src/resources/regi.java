package resources;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Window.Type;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Point;
import java.awt.Frame;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.DatabaseMetaData;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Enumeration;
import java.awt.event.ActionEvent;

public class regi {

	public JFrame frmChatcheeseRegistration;
	private JTextField name1;
	private JTextField name2;
	private JTextField mail;
	String macad;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					regi window = new regi();
					window.frmChatcheeseRegistration.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public regi() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmChatcheeseRegistration = new JFrame();
		frmChatcheeseRegistration.setBounds(new Rectangle(100, 100, 0, 0));
		frmChatcheeseRegistration.setState(Frame.ICONIFIED);
		frmChatcheeseRegistration.setLocation(new Point(7, 6));
		frmChatcheeseRegistration.setAutoRequestFocus(false);
		frmChatcheeseRegistration.setResizable(false);
		frmChatcheeseRegistration.setIconImage(Toolkit.getDefaultToolkit().getImage("Icons\\chat_96px.png"));
		frmChatcheeseRegistration.setFont(new Font("Arial Narrow", Font.PLAIN, 12));
		frmChatcheeseRegistration.setType(Type.UTILITY);
		frmChatcheeseRegistration.getContentPane().setBackground(Color.WHITE);
		frmChatcheeseRegistration.getContentPane().setLayout(null);
		
		JLabel lblChatcheeseSignUp = new JLabel("ChatCheese Sign up");
		lblChatcheeseSignUp.setHorizontalAlignment(SwingConstants.CENTER);
		lblChatcheeseSignUp.setFocusable(false);
		lblChatcheeseSignUp.setFocusTraversalKeysEnabled(false);
		lblChatcheeseSignUp.setForeground(Color.BLUE);
		lblChatcheeseSignUp.setFont(new Font("Malgun Gothic", Font.BOLD, 24));
		lblChatcheeseSignUp.setIcon(new ImageIcon("Icons\\chat_96px.png"));
		lblChatcheeseSignUp.setBounds(10, 11, 423, 80);
		frmChatcheeseRegistration.getContentPane().add(lblChatcheeseSignUp);
		
		name1 = new JTextField();
		name1.setForeground(new Color(128, 0, 0));
		name1.setFont(new Font("Consolas", Font.PLAIN, 15));
		name1.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.BLUE));
		name1.setBounds(28, 180, 179, 56);
		frmChatcheeseRegistration.getContentPane().add(name1);
		name1.setColumns(10);
		
		name2 = new JTextField();
		name2.setForeground(new Color(128, 0, 0));
		name2.setFont(new Font("Consolas", Font.PLAIN, 15));
		name2.setColumns(10);
		name2.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.BLUE));
		name2.setBounds(243, 180, 172, 56);
		frmChatcheeseRegistration.getContentPane().add(name2);
		
		mail = new JTextField();
		mail.setForeground(new Color(128, 0, 0));
		mail.setFont(new Font("Consolas", Font.PLAIN, 15));
		mail.setColumns(10);
		mail.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.BLUE));
		mail.setBounds(28, 281, 387, 56);
		frmChatcheeseRegistration.getContentPane().add(mail);
		
		JPasswordField pw1 = new JPasswordField();
		pw1.setForeground(new Color(128, 0, 0));
		pw1.setFont(new Font("Consolas", Font.PLAIN, 15));
		pw1.setColumns(10);
		pw1.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.BLUE));
		pw1.setBounds(28, 385, 179, 56);
		frmChatcheeseRegistration.getContentPane().add(pw1);
		
		JPasswordField pw2 = new JPasswordField();
		pw2.setForeground(new Color(128, 0, 0));
		pw2.setFont(new Font("Consolas", Font.PLAIN, 15));
		pw2.setColumns(10);
		pw2.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.BLUE));
		pw2.setBounds(243, 385, 172, 56);
		frmChatcheeseRegistration.getContentPane().add(pw2);
		
		JLabel lblFirstName = new JLabel("First name");
		lblFirstName.setForeground(new Color(0, 0, 139));
		lblFirstName.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		lblFirstName.setBounds(28, 146, 387, 38);
		frmChatcheeseRegistration.getContentPane().add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setForeground(new Color(0, 0, 139));
		lblLastName.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		lblLastName.setBounds(243, 146, 172, 38);
		frmChatcheeseRegistration.getContentPane().add(lblLastName);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setForeground(new Color(0, 0, 139));
		lblEmail.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		lblEmail.setBounds(28, 247, 387, 38);
		frmChatcheeseRegistration.getContentPane().add(lblEmail);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(new Color(0, 0, 139));
		lblPassword.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		lblPassword.setBounds(28, 348, 387, 38);
		frmChatcheeseRegistration.getContentPane().add(lblPassword);
		
		JLabel lblRetypePassword = new JLabel("Retype Password");
		lblRetypePassword.setForeground(new Color(0, 0, 139));
		lblRetypePassword.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		lblRetypePassword.setBounds(243, 348, 172, 38);
		frmChatcheeseRegistration.getContentPane().add(lblRetypePassword);
		
		JRadioButton okayy = new JRadioButton("");
		okayy.setBackground(Color.WHITE);
		okayy.setBounds(28, 475, 21, 38);
		frmChatcheeseRegistration.getContentPane().add(okayy);
		
		JTextPane txtpnWeAreGoing = new JTextPane();
		txtpnWeAreGoing.setForeground(Color.DARK_GRAY);
		txtpnWeAreGoing.setFont(new Font("Tw Cen MT", Font.BOLD, 14));
		txtpnWeAreGoing.setBackground(Color.WHITE);
		txtpnWeAreGoing.setEditable(false);
		txtpnWeAreGoing.setText("We are going to track your MAC address for your simple log in from future that means you need to log in once from this device. Please don't use ChatCheese if you have any complain with this system.");
		txtpnWeAreGoing.setBounds(55, 483, 360, 85);
		frmChatcheeseRegistration.getContentPane().add(txtpnWeAreGoing);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(okayy.isSelected() == false || name1.getText().toString() == null || name2.getText().toString() == null || mail.getText().toString() == null || pw1.getPassword().toString() == null || pw2.getPassword().toString() == null || !new String(pw1.getPassword()).equals(new String(pw2.getPassword()))) {
					JOptionPane.showMessageDialog(frmChatcheeseRegistration,"there must be some error. Check again!","Error",JOptionPane.WARNING_MESSAGE); 
				}
				
				else {
					  
					 String name = name1.getText().toString().trim() + " " + name2.getText().toString().trim();
						String email = mail.getText().toString().trim();
						String pwds = new String(pw1.getPassword());
						new Thread(new Runnable(){
						    public void run() {
						    	JOptionPane.showOptionDialog(frmChatcheeseRegistration, "Please wait","Registering", JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE, null, new Object[]{}, null);
						    }
						}).start();
						
						try {
						    
						    Enumeration<NetworkInterface> networks = NetworkInterface.getNetworkInterfaces();
						    while(networks.hasMoreElements()) {
						      NetworkInterface network = networks.nextElement();
						      byte[] mac = network.getHardwareAddress();
						      StringBuilder sb = new StringBuilder();
						      if(mac != null) {

						        
						        for (int i = 0; i < mac.length; i++) {
						          sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
						        }
						        macad = sb.toString();
						      }
						    }
						    
						  } catch (SocketException e11){
						    e11.printStackTrace();
						  }
						Connection x;
						Statement y;
						
						try {
							Class.forName("com.mysql.jdbc.Driver").newInstance();
							x = (Connection) DriverManager.getConnection( "jdbc:mysql://db4free.net:3306/ytosko_1", "ytosko_1", "Psycho1990");
							y = (Statement) x.createStatement();
	                       String sql = ("INSERT INTO LogINX(mac,name,email,password)" + "VALUES (?,?,?,?)");
	                       
	                       PreparedStatement preparedStatement = (PreparedStatement) x.prepareStatement(sql);
	                       preparedStatement.setString(1, macad);
	                       preparedStatement.setString(2, name);
	                       preparedStatement.setString(3, email);
	                       preparedStatement.setString(4, pwds);
	                       preparedStatement.executeUpdate(); 
	                       
	                       DatabaseMetaData dbm = (DatabaseMetaData) x.getMetaData();
							ResultSet tables = dbm.getTables(null, null, macad, null);
							
							if(tables.next()) {
								
							}else {
								
							
								Connection x1 = (Connection) DriverManager.getConnection(
										"jdbc:mysql://db4free.net:3306/ytosko_1",
										"ytosko_1", "Psycho1990");
								Statement y1 = (Statement) x1.createStatement();
								
								String sql1 = "CREATE TABLE IF NOT EXISTS ytosko_1.`"+ macad +"` ("
								        + "  `INDEX` int(10) NOT NULL AUTO_INCREMENT,"
								        + "  `CHATS` varchar(100) NOT NULL,"
								        + "   PRiMARY KEY(`INDEX`)"
								        + ") ENGINE = InnoDB DEFAULT CHARSET = latin1 AUTO_INCREMENT =1";

								y1.executeUpdate(sql1);
							}
		                       
							
	                        x.close();
	                        y.close();
	                        frmChatcheeseRegistration.dispose();
	                        
						} catch (Exception e1) {
							System.out.println("Error here!");
						}
						
				}
				
				
			}
		});
		btnRegister.setRequestFocusEnabled(false);
		btnRegister.setRolloverEnabled(false);
		btnRegister.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnRegister.setForeground(Color.BLUE);
		btnRegister.setFont(new Font("Eras Medium ITC", Font.BOLD, 22));
		btnRegister.setBackground(Color.WHITE);
		btnRegister.setBounds(162, 568, 106, 56);
		frmChatcheeseRegistration.getContentPane().add(btnRegister);
		frmChatcheeseRegistration.setBackground(Color.WHITE);
		frmChatcheeseRegistration.setBounds(100, 100, 449, 687);
		frmChatcheeseRegistration.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
        Rectangle rect = defaultScreen.getDefaultConfiguration().getBounds();
        int x = (int) rect.getMaxX() - frmChatcheeseRegistration.getWidth();
        int y = (int) rect.getMaxY() - frmChatcheeseRegistration.getHeight() - 35;
        frmChatcheeseRegistration.setLocation(x,y);
	}
}
