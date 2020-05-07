package resources;

import java.awt.EventQueue;
import java.awt.GraphicsConfiguration;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.JFrame;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.awt.Window.Type;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextPane;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.border.MatteBorder;
import javax.swing.text.DefaultCaret;
import javax.swing.text.NumberFormatter;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.DatabaseMetaData;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.border.EmptyBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.SocketException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.Collection;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Vector;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.ScrollPane;
import java.awt.SystemTray;
import java.awt.ComponentOrientation;
import java.awt.Desktop;

import javax.swing.ListSelectionModel;
import javax.swing.DropMode;
import javax.swing.JTree;
import javax.swing.ListModel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import java.awt.Component;
import javax.swing.event.MenuKeyListener;
import javax.swing.event.MenuKeyEvent;

public class clchat {

	public JFrame frmChatting;
	public JTextArea msg;
	private ObjectOutputStream output;
	private ObjectInputStream input;
	private String message = "";
	private String serverIP;
	private Socket connection;
	String uname;
	PrintWriter pw;
	BufferedReader br;
	Vector<String> users;
	String macad;

	Socket client;

	String port;
	private JTextArea txtpnWriteSomething;
	private JList avai;
	String towho = " [ to everyone ] : ";

	DefaultListModel listModel, l1;
	LinkedHashSet<String> axe;
	private JList list;
	private JButton sent;
	BufferedWriter o;
	String f;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					clchat window = new clchat(null, null, null);
					window.frmChatting.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * 
	 * @throws IOException
	 */
	public clchat(final String s, final String k, String status) throws IOException {
		System.out.println(s + " " + k + " " + status);
		InetAddress inetAddress = InetAddress.getLocalHost();
		String servername = inetAddress.getHostAddress();
		System.out.println("IP Address:- " + servername);
		this.uname = k;
		int r = Integer.valueOf(s);
		client = new Socket(servername, r);
		br = new BufferedReader(new InputStreamReader(client.getInputStream()));
		pw = new PrintWriter(client.getOutputStream(), true);
		pw.println(uname);
		initialize(s, k, status);
		new MessagesThread().start();
		pw.println("pslvmark42");
		l1 = new DefaultListModel();
		f = status;

	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws IOException
	 * @throws UnknownHostException
	 * @throws NumberFormatException
	 */
	private void initialize(String s, String k, String status)
			throws NumberFormatException, UnknownHostException, IOException {

		frmChatting = new JFrame();
		frmChatting.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		frmChatting.setLocation(new Point(300, 300));
		frmChatting.getContentPane().setBackground(Color.WHITE);
		frmChatting.getContentPane().setLayout(null);

		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(204, 11, 2, 504);
		frmChatting.getContentPane().add(separator);

		JLabel lblJoinedUsers = new JLabel("Joined users");
		lblJoinedUsers.setHorizontalAlignment(SwingConstants.CENTER);
		lblJoinedUsers.setForeground(Color.DARK_GRAY);
		lblJoinedUsers.setFont(new Font("Sitka Text", Font.BOLD, 18));
		lblJoinedUsers.setBounds(10, 11, 184, 29);
		frmChatting.getContentPane().add(lblJoinedUsers);

		ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();

		ses.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				pw.println("updateUI");
			}
		}, 0, 5, TimeUnit.SECONDS);

		msg = new JTextArea();
		msg.setTabSize(5);
		DefaultCaret caret = (DefaultCaret) msg.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		msg.setForeground(Color.DARK_GRAY);
		msg.setFont(new Font("Sitka Subheading", Font.BOLD, 16));
		msg.setEditable(false);
		msg.setBorder(null);
		msg.setBounds(230, 11, 661, 422);
		msg.setWrapStyleWord(true);
		msg.setLineWrap(true);
		// frmChatting.getContentPane().add(msg);
		JScrollPane xiu = new JScrollPane(msg);
		xiu.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
		xiu.setBounds(230, 11, 661, 422);
		frmChatting.getContentPane().add(xiu);

		txtpnWriteSomething = new JTextArea();
		DefaultCaret caret1 = (DefaultCaret) txtpnWriteSomething.getCaret();
		caret1.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		txtpnWriteSomething.setForeground(Color.GRAY);
		txtpnWriteSomething.setFont(new Font("Sitka Subheading", Font.BOLD, 14));
		txtpnWriteSomething.setText("Write something . . .");
		txtpnWriteSomething.setWrapStyleWord(true);
		txtpnWriteSomething.setLineWrap(true);

		txtpnWriteSomething.addMouseListener(new MouseAdapter() {
			int x = 0;

			@Override
			public void mousePressed(MouseEvent e) {
				x++;
				if (x == 1)
					txtpnWriteSomething.setText(null);
			}
		});
		txtpnWriteSomething.setBorder(new EmptyBorder(0, 0, 0, 0));
		txtpnWriteSomething.setBounds(230, 444, 521, 58);
		JScrollPane xiuz = new JScrollPane(txtpnWriteSomething);
		xiuz.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
		xiuz.setBounds(230, 444, 521, 58);
		frmChatting.getContentPane().add(xiuz);

		l1 = new DefaultListModel();

		sent = new JButton("  Sent");
		sent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (txtpnWriteSomething.getText().equals("") || txtpnWriteSomething.getText() == null
						|| txtpnWriteSomething.getText().equals("Write something . . .")) {
					new Thread(new Runnable() {
						public void run() {
							JOptionPane.showOptionDialog(frmChatting, "Message is empty\nCan not sent the message",
									"Warning", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
									new Object[] { "OK" }, null);
						}
					}).start();
				} else {
					System.out.println(axe);
					pw.println(towho + txtpnWriteSomething.getText());
					txtpnWriteSomething.setText("");
					axe.removeAll(axe);
					towho = " [ to everyone ] : ";
					l1.removeAllElements();
					list.setModel(l1);
				}

			}
		});

		sent.setRolloverEnabled(false);
		sent.setRequestFocusEnabled(false);
		sent.setBorder(new EmptyBorder(0, 0, 0, 0));
		sent.setBackground(Color.WHITE);
		sent.setIcon(new ImageIcon("Icons\\sent_30pxa.png"));
		sent.setFont(new Font("Sitka Text", Font.BOLD, 14));
		sent.setBounds(761, 444, 130, 58);
		frmChatting.getContentPane().add(sent);

		listModel = new DefaultListModel();

		axe = new LinkedHashSet<String>();
		avai = new JList(listModel);
		avai.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		avai.setDropMode(DropMode.ON);
		avai.setValueIsAdjusting(true);
		avai.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				axe.add(avai.getSelectedValue().toString());
				towho = " to " + axe + " : ";
				int flag = 1;
				for (int i = 0; i < l1.getSize(); i++) {
					if (l1.getElementAt(i).equals(avai.getSelectedValue().toString())) {
						flag++;
					}

				}

				if (flag == 1) {
					l1.addElement(avai.getSelectedValue().toString());
				}
				list.setModel(l1);
			}
		});

		avai.setForeground(new Color(153, 51, 51));
		avai.setFont(new Font("Sitka Text", Font.BOLD, 16));
		avai.setBounds(10, 51, 184, 404);
		JScrollPane xiuza = new JScrollPane(avai);
		xiuza.setBorder(new EmptyBorder(0, 0, 0, 0));
		xiuza.setBounds(10, 51, 184, 451);
		frmChatting.getContentPane().add(xiuza);

		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(918, 11, 2, 504);
		frmChatting.getContentPane().add(separator_1);

		list = new JList(l1);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setValueIsAdjusting(true);
		list.setForeground(new Color(153, 51, 51));
		list.setFont(new Font("Sitka Text", Font.BOLD, 16));
		list.setDropMode(DropMode.ON);
		list.setBounds(930, 51, 184, 451);
		frmChatting.getContentPane().add(list);

		JLabel lblSelectedUsers = new JLabel("Selected users");
		lblSelectedUsers.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelectedUsers.setForeground(Color.DARK_GRAY);
		lblSelectedUsers.setFont(new Font("Sitka Text", Font.BOLD, 18));
		lblSelectedUsers.setBounds(929, 12, 184, 29);
		frmChatting.getContentPane().add(lblSelectedUsers);

		frmChatting.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		frmChatting.setResizable(false);
		frmChatting.setVisible(true);
		frmChatting.setTitle(k);
		frmChatting.setIconImage(Toolkit.getDefaultToolkit().getImage("Icons\\chat_96px.png"));
		frmChatting.setBounds(100, 100, 1128, 582);
		frmChatting.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		GraphicsConfiguration config = frmChatting.getGraphicsConfiguration();
		Rectangle bounds = config.getBounds();
		Insets insets = Toolkit.getDefaultToolkit().getScreenInsets(config);

		int x = bounds.x + bounds.width - insets.right - frmChatting.getWidth();
		int y = bounds.y + insets.bottom + 40;
		frmChatting.setLocation(x, y);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(Color.WHITE);
		menuBar.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.LIGHT_GRAY));
		frmChatting.setJMenuBar(menuBar);

		JMenu mnOperations = new JMenu("Operations");
		mnOperations.setForeground(Color.DARK_GRAY);
		mnOperations.setFont(new Font("Segoe UI", Font.BOLD, 12));
		menuBar.add(mnOperations);

		JMenuItem mntmSaveChat = new JMenuItem("Save Chat");

		mntmSaveChat.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String xat = msg.getText().toString();
				System.out.println(xat);

				if (xat.equals("")) {
					int dialouge = JOptionPane.showOptionDialog(frmChatting, "No message to save", "Error",
							JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[] { "OK" },
							xat);
					if (dialouge == JOptionPane.OK_OPTION) {
						JOptionPane.getRootFrame().dispose();
					}
				} else {

					JPanel panel = new JPanel(new BorderLayout(5, 5));

					JPanel label = new JPanel(new GridLayout(0, 1, 2, 2));
					label.add(new JLabel("Name of the chat", SwingConstants.RIGHT));
					panel.add(label, BorderLayout.WEST);

					JPanel controls = new JPanel(new GridLayout(0, 1, 2, 2));
					JTextField password = new JTextField();
					controls.add(password);
					panel.add(controls, BorderLayout.CENTER);

					int input = JOptionPane.showConfirmDialog(frmChatting, panel, "Save Chat",
							JOptionPane.OK_CANCEL_OPTION);
					if (input == JOptionPane.OK_OPTION) {
						String name = password.getText().toString();
						if (name != null) {
							Thread t = new Thread(new Runnable() {
								public void run() {
									JOptionPane JOptionPanex = new JOptionPane();
									JOptionPanex.showOptionDialog(frmChatting, "Please wait", "Saving",
											JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
											new Object[] {}, null);
								}
							});
							t.start();
							try {

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

							} catch (SocketException e11) {
								e11.printStackTrace();
							}
							new Thread(new Runnable() {
								public void run() {
									Connection x, x1;
									Statement y, y1;

									try {
										Class.forName("com.mysql.jdbc.Driver").newInstance();
										x = (Connection) DriverManager.getConnection(
												"jdbc:mysql://db4free.net:3306/ytosko_1", "ytosko_1", "Psycho1990");

										DatabaseMetaData dbm = (DatabaseMetaData) x.getMetaData();
										ResultSet tables = dbm.getTables(null, null, macad + "_Saved", null);
										if (tables.next()) {

											x1 = (Connection) DriverManager.getConnection(
													"jdbc:mysql://db4free.net:3306/ytosko_1", "ytosko_1", "Psycho1990");
											y = (Statement) x.createStatement();

											String sql = "INSERT INTO `" + macad + "_Saved`(CHATS,MESSAGES)"
													+ "VALUES (?,?)";

											PreparedStatement preparedStatement = (PreparedStatement) x
													.prepareStatement(sql);
											preparedStatement.setString(1, name);
											preparedStatement.setString(2, xat);
											preparedStatement.executeUpdate();

											x.close();
											y.close();
											t.interrupt();

										} else {
											t.interrupt();
											JOptionPane.showMessageDialog(frmChatting, "Error saving messages", "Error",
													JOptionPane.ERROR_MESSAGE);
										}

									} catch (Exception e1) {
										e1.printStackTrace();
									}
								}
							}).start();
						} else {
							JOptionPane.showMessageDialog(frmChatting, "Unable to save this room", "Error",
									JOptionPane.ERROR_MESSAGE);
						}
					}

				}
			}
		});
		mntmSaveChat.setIcon(new ImageIcon("Icons\\save_as_20px.png"));
		mntmSaveChat.setForeground(Color.DARK_GRAY);
		mntmSaveChat.setFont(new Font("Sitka Subheading", Font.BOLD, 12));
		mnOperations.add(mntmSaveChat);

		Thread finalx = new Thread(new Runnable() {

			@Override
			public void run() {
				Connection x;
				Connection x1;
				Statement y;

				try {
					Class.forName("com.mysql.jdbc.Driver").newInstance();
					x = (Connection) DriverManager.getConnection("jdbc:mysql://db4free.net:3306/ytosko_1", "ytosko_1",
							"Psycho1990");

					DatabaseMetaData dbm = (DatabaseMetaData) x.getMetaData();
					ResultSet tables = dbm.getTables(null, null, s + "_Par", null);
					if (tables.next()) {

						try {
							x1 = (Connection) DriverManager.getConnection("jdbc:mysql://db4free.net:3306/ytosko_1",
									"ytosko_1", "Psycho1990");
							y = (Statement) x.createStatement();

							String sql = "DROP TABLE " + s + "_Par";

							y.executeUpdate(sql);

							x.close();
							y.close();
							
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

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
		});

		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < listModel.getSize(); i++) {
					if (listModel.getElementAt(i).equals(" " + k)) {
						listModel.remove(i);
						System.out.println("removed " + i + " : " + uname + "\n");
					}
				}
				pw.println("updateUI");
				if (status.equals("host")) {
					pw.println("saheb");
					finalx.start();
					System.out.println("exiting");
				}
				pw.println("end");
				

				frmChatting.dispose();
			}
		});

		JMenuItem mntmSaveAs = new JMenuItem("Save as");
		mntmSaveAs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String xat = msg.getText().toString();
				System.out.println(xat);

				if (xat.equals("")) {
					int dialouge = JOptionPane.showOptionDialog(frmChatting, "No message to save", "Error",
							JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[] { "OK" },
							xat);
					if (dialouge == JOptionPane.OK_OPTION) {
						JOptionPane.getRootFrame().dispose();
					}
				} else {

					JFileChooser fileChooser = new JFileChooser();
					int retval = fileChooser.showSaveDialog(mntmSaveAs);
					if (retval == JFileChooser.APPROVE_OPTION) {
						File file = fileChooser.getSelectedFile();
						if (file == null) {
							return;
						}
						if (!file.getName().toLowerCase().endsWith(".txt")) {
							file = new File(file.getParentFile(), file.getName() + ".txt");
						}
						try {
							FileWriter fw = new FileWriter(file.getAbsoluteFile());
							BufferedWriter bw = new BufferedWriter(fw);
							bw.write(msg.getText().toString());
							bw.close();
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				}
			}
		});
		mntmSaveAs.setIcon(new ImageIcon("Icons\\download_20px.png"));
		mntmSaveAs.setForeground(Color.DARK_GRAY);
		mntmSaveAs.setFont(new Font("Sitka Subheading", Font.BOLD, 12));
		mnOperations.add(mntmSaveAs);
		mntmExit.setIcon(new ImageIcon("Icons\\exit_sign_20px.png"));
		mntmExit.setForeground(Color.DARK_GRAY);
		mntmExit.setFont(new Font("Sitka Subheading", Font.BOLD, 12));
		mnOperations.add(mntmExit);

		JMenu mnInsert = new JMenu("Refresh");
		mnInsert.setFont(new Font("Segoe UI", Font.BOLD, 12));

		mnInsert.setForeground(Color.DARK_GRAY);
		menuBar.add(mnInsert);

		JMenuItem mntmRefreshConnectedClient = new JMenuItem("Refresh connected client list");
		mntmRefreshConnectedClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					TrayIconDemo td = new TrayIconDemo();
				} catch (AWTException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println("Done printing");
				pw.println("updateUI");
			}
		});
		mntmRefreshConnectedClient.setIcon(new ImageIcon("Icons\\refresh_20px.png"));
		mntmRefreshConnectedClient.setForeground(Color.DARK_GRAY);
		mntmRefreshConnectedClient.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mnInsert.add(mntmRefreshConnectedClient);

		JMenu mnHelp = new JMenu("Help");
		mnHelp.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mnHelp.setForeground(Color.DARK_GRAY);
		menuBar.add(mnHelp);

		JMenuItem mntmReadme = new JMenuItem("Readme");
		mntmReadme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProcessBuilder pb = new ProcessBuilder("Notepad.exe", "resources\\readme.txt");
				try {
					pb.start();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showOptionDialog(frmChatting, "Node pad can not find readme file", "Warning",
							JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[] { "OK" },
							null);
				}
			}
		});
		mntmReadme.setIcon(new ImageIcon("Icons\\read_20px.png"));
		mntmReadme.setForeground(Color.DARK_GRAY);
		mntmReadme.setFont(new Font("Sitka Subheading", Font.BOLD, 12));
		mnHelp.add(mntmReadme);

		JMenuItem mntmContactUs = new JMenuItem("Contact us");
		mntmContactUs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Desktop desktop;
				if (Desktop.isDesktopSupported() && (desktop = Desktop.getDesktop()).isSupported(Desktop.Action.MAIL)) {
					try {
						URI mailto = new URI("mailto:ytosko@hotmail.com?subject=");
						desktop.mail(mailto);
					} catch (URISyntaxException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {

					throw new RuntimeException("desktop doesn't support mailto; mail is dead anyway ;)");
				}
			}
		});
		mntmContactUs.setIcon(new ImageIcon("Icons\\contact_20px.png"));
		mntmContactUs.setForeground(Color.DARK_GRAY);
		mntmContactUs.setFont(new Font("Sitka Subheading", Font.BOLD, 12));
		mnHelp.add(mntmContactUs);
		frmChatting.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {

				int zey = JOptionPane.showOptionDialog(frmChatting, "You are about to exit the chat", "Warning",
						JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[] { "OK" }, null);

				if (zey == JOptionPane.OK_OPTION) {
					for (int i = 0; i < listModel.getSize(); i++) {
						if (listModel.getElementAt(i).equals(" " + k)) {
							listModel.remove(i);
							System.out.println("removed " + i + " : " + uname + "\n");
						}
					}
					if (status.equals("host")) {
						pw.println("saheb");
						finalx.start();
						System.out.println("exiting");
					}
					pw.println("end");
					
					frmChatting.dispose();
					pw.println("updateUI");

				} else {
					JOptionPane.getRootFrame().dispose();
				}
			}
		});

	}

	@SuppressWarnings("unused")
	private void add(JScrollPane scroll) {
		// TODO Auto-generated method stub

	}

	class MessagesThread extends Thread {
		public void run() {
			String line;
			try {
				while (true) {

					line = br.readLine();
					if (line.equals(" CleareAll")) {
						listModel.removeAllElements();
					} else if (line.startsWith(" ") && !line.equals(" CleareAll")) {

						int flag = 1;
						for (int i = 0; i < listModel.getSize(); i++) {
							if (listModel.getElementAt(i).equals(line)) {
								flag++;
							}

						}

						if (flag == 1) {
							listModel.addElement(line);
						}

					} else if (line.startsWith("update")) {
						for (int i = 0; i < listModel.getSize(); i++) {

							System.out.println("User " + i + " : " + listModel.get(i) + "\n");

						}
						avai.setModel(listModel);
					}
					else if (line.startsWith("init")) {
						System.out.println("this");
						if(!f.equals("host")){
						JOptionPane.showOptionDialog(frmChatting,
								"Server distroyed\nCan not sent the message anymore\nPress ok to save chat",
								"Warning", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
								new Object[] { "OK" }, null);
						txtpnWriteSomething.setEditable(false);
						sent.setEnabled(false);
						txtpnWriteSomething.setEnabled(false);
					}
					}

					else {

						msg.append(line + "\n");
					}

				}
			} catch (Exception ex) {
				ex.printStackTrace();
				if (frmChatting.isDisplayable()) {
					new Thread(new Runnable() {
						public void run() {
							
							JOptionPane.showOptionDialog(frmChatting,
									"Server distroyed\nCan not sent the message anymore\nPress ok to save chat",
									"Warning", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
									new Object[] { "OK" }, null);
							txtpnWriteSomething.setEditable(false);
							sent.setEnabled(false);
							txtpnWriteSomething.setEnabled(false);

						}
					}).start();
				}
			}
			System.out.println("Done...");
		}

	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
