package resources;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.EventQueue;
import java.awt.GraphicsConfiguration;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.Dialog.ModalExclusionType;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ListSelectionModel;

public class saved {

	public JFrame frmChatting;
	private JList list;
	private JTextArea msg;
	DefaultListModel l;
	String macad;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					saved window = new saved();
					window.frmChatting.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws SocketException 
	 */
	public saved() throws SocketException {
		
		Enumeration<NetworkInterface> networks = NetworkInterface.getNetworkInterfaces();
		while (networks.hasMoreElements()) {
			NetworkInterface network = networks.nextElement();
			byte[] mac = network.getHardwareAddress();
			StringBuilder sb = new StringBuilder();
			if (mac != null) {

				for (int i = 0; i < mac.length; i++) {
					sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
				}
				macad = sb.toString();
			}
		}
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmChatting =  new JFrame();
		frmChatting.setAutoRequestFocus(false);
		frmChatting.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		frmChatting.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmChatting.setLocation(new Point(300, 300));
		frmChatting.getContentPane().setBackground(Color.WHITE);
		frmChatting.getContentPane().setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(168, 11, 15, 488);
		separator.setOrientation(SwingConstants.VERTICAL);
		frmChatting.getContentPane().add(separator);
		
		JLabel lblChatRooms = new JLabel("Chat rooms");
		lblChatRooms.setBounds(10, 11, 148, 30);
		lblChatRooms.setForeground(Color.BLUE);
		lblChatRooms.setFont(new Font("Segoe UI Historic", Font.BOLD, 16));
		frmChatting.getContentPane().add(lblChatRooms);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 56, 148, 443);
		scrollPane.setOpaque(false);
		scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		scrollPane.setBackground(Color.WHITE);
		frmChatting.getContentPane().add(scrollPane);
		

		
		l = new DefaultListModel();
		Thread red = new Thread(new Runnable() {
			public void run() {
		ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();

		ses.scheduleAtFixedRate(new Runnable() {
		    @Override
		    public void run() {
		    	
		    	Connection x;
				Statement y;
				ResultSet z;
				try {
					Class.forName("com.mysql.jdbc.Driver").newInstance();
					x = (Connection) DriverManager.getConnection("jdbc:mysql://db4free.net:3306/ytosko_1",
							"ytosko_1", "Psycho1990");
					y = (Statement) x.createStatement();
					if(macad!=null) {
						String sql = "SELECT * FROM `" + macad + "_Saved`";
						y.executeQuery(sql);
						z = y.getResultSet();
						l = new DefaultListModel();
						int flag = 0;
						while(z.next()) {
							String line = z.getString("CHATS");	
							if(msg.getText().toString().equals("Please wait while messages are loaded from server")) {
							msg.setText("");
							}
								l.addElement(line);
								System.out.println("Added : " + line);
								flag++;
							
						}
						System.out.println("Added : " + flag);
						if(flag==0) {
							msg.setText("No saved chat found in server for this MAC address");
						}
						list.setModel(l);
						z.close();
						y.close();
					}
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

		    }
		}, 0, 30, TimeUnit.SECONDS);
			}
		});
		red.start();
		
		
		list = new JList(l);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println(list.getSelectedValue().toString());
				String port = list.getSelectedValue().toString();
				Thread t = new Thread(new Runnable() {
					public void run() {
						JOptionPane.showOptionDialog(frmChatting, "Please wait", "Loading messages",
								JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
								new Object[] {}, null);
					}
				});
				t.start();
				msg.setText("");
				Connection x;
				Statement y;
				ResultSet z;
				try{
					Class.forName("com.mysql.jdbc.Driver").newInstance();
					x = (Connection) DriverManager.getConnection(
							"jdbc:mysql://db4free.net:3306/ytosko_1",
							"ytosko_1", "Psycho1990");
					String sql = "SELECT * FROM `" + macad + "_Saved` WHERE CHATS ='"
							+ port + "'";
					y = (Statement) x.createStatement();

					z = y.executeQuery(sql);
					if (z.next()) {
						String s = z.getString("MESSAGES");
						msg.setText(s);
						
						x.close();
						y.close();
						t.interrupt();

					} else {
						t.interrupt();
						x.close();
						y.close();
					}
					x.close();
					y.close();
				}catch(Exception e1){
					
				}
			}
		});
		list.setForeground(new Color(0, 51, 51));
		list.setFont(new Font("Perpetua", Font.BOLD, 22));
		list.setBounds(new Rectangle(0, 0, 0, 457));
		scrollPane.setViewportView(list);
		
		
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBackground(Color.WHITE);
		scrollPane_1.setBorder(new EmptyBorder(0, 0, 0, 0));
		scrollPane_1.setBounds(193, 11, 697, 488);
		frmChatting.getContentPane().add(scrollPane_1);
		
		msg = new JTextArea();
		msg.setForeground(Color.DARK_GRAY);
		msg.setFont(new Font("Monospaced", Font.BOLD, 16));
		msg.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
		msg.setBackground(Color.WHITE);
		msg.setWrapStyleWord(true);
		msg.setLineWrap(true);
		msg.setText("Please wait while messages are loaded from server");
		scrollPane_1.setViewportView(msg);
		frmChatting.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		frmChatting.setResizable(false);
		frmChatting.setVisible(true);
		frmChatting.setTitle("Saved Chats");
		frmChatting.setIconImage(
				Toolkit.getDefaultToolkit().getImage("Icons\\chat_96px.png"));
		frmChatting.setBounds(100, 100, 923, 555);
		GraphicsConfiguration config = frmChatting.getGraphicsConfiguration();
		Rectangle bounds = config.getBounds();
		Insets insets = Toolkit.getDefaultToolkit().getScreenInsets(config);

		int x = bounds.x + bounds.width - insets.right - frmChatting.getWidth();
		int y = bounds.y + insets.bottom + 40;
		frmChatting.setLocation(x, y);
	}
}
