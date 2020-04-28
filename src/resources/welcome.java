package resources;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.Window.Type;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.TitledBorder;
import javax.swing.text.NumberFormatter;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.DatabaseMetaData;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import java.awt.BorderLayout;
import java.awt.Button;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.Enumeration;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.awt.event.ActionEvent;
import java.awt.Rectangle;
import javax.swing.ListSelectionModel;
import java.awt.ComponentOrientation;

public class welcome {

	public JFrame frmChatCheese;
	private JTextField em;
	private JButton btnNeedAnAccount;
	private JButton btnLogOut;
	String macad;
	private JLabel emic;
	private JLabel pwic;
	private JPasswordField pw;
	private JButton btnLogIn;
	private JLabel hilb;
	JOptionPane JOptionPanex;
	DefaultListModel list;
	int def = 0;
	private JList txtrHello;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					welcome window = new welcome();
					window.frmChatCheese.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public welcome() {
		initialize();
		new Thread(new Runnable() {
			public void run() {
				ToSystemTrayAction atr = new ToSystemTrayAction();
			}
		}).start();

		new Thread(new Runnable() {
			public void run() {
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

				if (macad != null) {
					Connection x;
					Statement y;
					ResultSet z;
					try {

						Class.forName("com.mysql.jdbc.Driver").newInstance();
						x = (Connection) DriverManager.getConnection("jdbc:mysql://db4free.net:3306/ytosko_1",
								"ytosko_1", "Psycho1990");
						String sql = "SELECT * FROM `LogINX` WHERE mac ='" + macad + "'";
						y = (Statement) x.createStatement();

						z = y.executeQuery(sql);

						try {
							Connection x12 = (Connection) DriverManager
									.getConnection("jdbc:mysql://db4free.net:3306/ytosko_1", "ytosko_1", "Psycho1990");
							Statement y12 = (Statement) x12.createStatement();

							String sql112 = "CREATE TABLE IF NOT EXISTS ytosko_1.`" + macad + "_Saved` ("
									+ "  `INDEX` int(10) NOT NULL AUTO_INCREMENT," + "  `CHATS` varchar(100) NOT NULL,"
									+ "  `MESSAGES` text NOT NULL," + "   PRiMARY KEY(`INDEX`)"
									+ ") ENGINE = InnoDB DEFAULT CHARSET = latin1 AUTO_INCREMENT =1";

							y12.executeUpdate(sql112);
							System.out.println("Saved directorry created!");
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						DatabaseMetaData dbm = (DatabaseMetaData) x.getMetaData();
						ResultSet tables = dbm.getTables(null, null, macad, null);

						if (tables.next()) {

						} else {

							Connection x1 = (Connection) DriverManager
									.getConnection("jdbc:mysql://db4free.net:3306/ytosko_1", "ytosko_1", "Psycho1990");
							Statement y1 = (Statement) x1.createStatement();

							String sql1 = "CREATE TABLE IF NOT EXISTS ytosko_1.`" + macad + "` ("
									+ "  `INDEX` int(10) NOT NULL AUTO_INCREMENT," + "  `CHATS` varchar(100) NOT NULL,"
									+ "   PRiMARY KEY(`INDEX`)"
									+ ") ENGINE = InnoDB DEFAULT CHARSET = latin1 AUTO_INCREMENT =1";

							y1.executeUpdate(sql1);

						}
						if (z.next()) {
							Font font1 = new Font("SansSerif", Font.BOLD, 32);
							emic.setVisible(false);
							pwic.setVisible(false);
							pw.setVisible(false);
							btnLogIn.setVisible(false);
							btnNeedAnAccount.setVisible(false);
							em.setBorder(null);
							em.setEditable(false);
							em.setText(z.getString("name"));
							em.setHorizontalAlignment(SwingConstants.CENTER);
							em.setBackground(null);
							em.setFont(font1);
							hilb.setVisible(true);
							hilb.setText("This is an automated log in based on your MAC address");
							x.close();
							y.close();

						} else {
							JOptionPane.showOptionDialog(frmChatCheese, "Wrong Credentials", "Error",
									JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[] {},
									null);
							x.close();
							y.close();
						}
						x.close();
						y.close();
					} catch (Exception e1) {
						e1.printStackTrace();
					}

				} else {

				}
			}
		}).start();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmChatCheese = new JFrame();
		frmChatCheese.getContentPane().setBackground(Color.WHITE);
		frmChatCheese.getContentPane().setLayout(null);

		JLabel lblWelcomeToChat = new JLabel("Welcome to Chat Cheese V1");
		lblWelcomeToChat.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeToChat.setForeground(Color.RED);
		lblWelcomeToChat.setFont(new Font("Yu Gothic", Font.BOLD, 18));
		lblWelcomeToChat.setEnabled(false);
		lblWelcomeToChat.setBounds(10, 11, 1184, 37);
		frmChatCheese.getContentPane().add(lblWelcomeToChat);

		JSeparator separator = new JSeparator();
		separator.setForeground(Color.WHITE);
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBackground(Color.GRAY);
		separator.setBounds(600, 90, 8, 395);
		frmChatCheese.getContentPane().add(separator);

		em = new JTextField();
		em.setToolTipText("Email");
		em.setName("Email");
		em.setSelectionColor(Color.BLUE);
		em.setForeground(Color.BLUE);
		em.setFont(new Font("Sitka Subheading", Font.PLAIN, 15));
		em.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.GRAY));
		em.setBounds(698, 90, 405, 37);
		frmChatCheese.getContentPane().add(em);
		em.setColumns(10);

		emic = new JLabel("");
		emic.setIcon(new ImageIcon("Icons\\email_30px.png"));
		emic.setBounds(654, 90, 48, 37);
		frmChatCheese.getContentPane().add(emic);

		pw = new JPasswordField();
		pw.setToolTipText("Email");
		pw.setSelectionColor(Color.BLUE);
		pw.setName("Password");
		pw.setForeground(Color.BLUE);
		pw.setFont(new Font("Sitka Subheading", Font.PLAIN, 15));
		pw.setColumns(10);
		pw.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.GRAY));
		pw.setBounds(698, 157, 405, 37);
		frmChatCheese.getContentPane().add(pw);

		pwic = new JLabel("");
		pwic.setIcon(new ImageIcon("Icons\\password_30px.png"));
		pwic.setBounds(654, 157, 48, 37);
		frmChatCheese.getContentPane().add(pwic);

		btnLogIn = new JButton("   Log In");
		btnLogIn.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				String email = em.getText().toString().trim();
				String password = new String(pw.getPassword());
				if (email != null && password != null) {

					new Thread(new Runnable() {
						public void run() {
							Connection x;
							Statement y;
							ResultSet z;
							String user1 = email;
							String pass1 = password;

							try {
								Class.forName("com.mysql.jdbc.Driver").newInstance();
								x = (Connection) DriverManager.getConnection("jdbc:mysql://db4free.net:3306/ytosko_1",
										"ytosko_1", "Psycho1990");
								String sql = "SELECT * FROM `LogINX` WHERE email ='" + user1 + "' AND password ='"
										+ pass1 + "'";
								y = (Statement) x.createStatement();

								z = y.executeQuery(sql);
								if (z.next()) {
									Font font1 = new Font("SansSerif", Font.BOLD, 32);
									emic.setVisible(false);
									pwic.setVisible(false);
									pw.setVisible(false);
									btnLogIn.setVisible(false);
									btnNeedAnAccount.setVisible(false);
									em.setBorder(null);
									em.setEditable(false);
									em.setText(z.getString("name"));
									em.setHorizontalAlignment(SwingConstants.CENTER);
									em.setBackground(null);
									em.setFont(font1);
									btnLogOut.setVisible(true);

									x.close();
									y.close();

								} else {
									JOptionPane.showOptionDialog(frmChatCheese, "Wrong Credentials", "Error",
											JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
											new Object[] {}, null);
									x.close();
									y.close();
								}
								x.close();
								y.close();
							} catch (Exception e1) {
								System.out.println("Error here!");
							}
						}
					}).start();

				} else {
					JOptionPane.showOptionDialog(frmChatCheese, "Wrong Credentials", "Error",
							JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[] {}, null);
				}

			}
		});

		list = new DefaultListModel();

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
							if (macad != null) {
								String sql = "SELECT * FROM `" + macad + "`";
								y.executeQuery(sql);
								z = y.getResultSet();
								while (z.next()) {
									String line = z.getString("CHATS");

									int flag = 1;
									for (int i = 0; i < list.getSize(); i++) {
										if (list.getElementAt(i).equals(line)) {
											flag++;
										}

									}

									if (flag == 1) {
										list.addElement(line);
										System.out.println("Added : " + line);
									}
								}
								txtrHello.setModel(list);
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

		btnLogIn.setRolloverEnabled(false);
		btnLogIn.setRequestFocusEnabled(false);
		btnLogIn.setIcon(new ImageIcon("Icons\\login_24px.png"));
		btnLogIn.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnLogIn.setForeground(Color.BLUE);
		btnLogIn.setFont(new Font("Consolas", Font.BOLD, 18));
		btnLogIn.setBackground(Color.WHITE);
		btnLogIn.setBounds(824, 232, 143, 45);
		frmChatCheese.getContentPane().add(btnLogIn);

		btnNeedAnAccount = new JButton("Need an account? Click here");
		btnNeedAnAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				regi az = new regi();
				az.frmChatcheeseRegistration.setVisible(true);
			}
		});
		btnNeedAnAccount.setFocusTraversalKeysEnabled(false);
		btnNeedAnAccount.setFocusPainted(false);
		btnNeedAnAccount.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnNeedAnAccount.setForeground(Color.GRAY);
		btnNeedAnAccount.setFont(new Font("Monospaced", Font.BOLD, 16));
		btnNeedAnAccount.setBackground(Color.WHITE);
		btnNeedAnAccount.setBounds(755, 337, 313, 37);
		frmChatCheese.getContentPane().add(btnNeedAnAccount);

		JButton btnJoinChat = new JButton("Join Chat");
		btnJoinChat.setHorizontalAlignment(SwingConstants.LEFT);
		btnJoinChat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JPanel panel = new JPanel(new BorderLayout(5, 5));

				JPanel label = new JPanel(new GridLayout(0, 1, 2, 2));
				label.add(new JLabel("Code", SwingConstants.RIGHT));
				label.add(new JLabel("Name", SwingConstants.RIGHT));
				panel.add(label, BorderLayout.WEST);

				JPanel controls = new JPanel(new GridLayout(0, 1, 2, 2));
				NumberFormat format = NumberFormat.getInstance();
				format.setGroupingUsed(false);
				NumberFormatter formatter = new NumberFormatter(format);
				formatter.setValueClass(Integer.class);
				formatter.setMinimum(0);
				formatter.setMaximum(9999);
				formatter.setAllowsInvalid(false);
				formatter.setCommitsOnValidEdit(true);

				JFormattedTextField username = new JFormattedTextField(formatter);
				controls.add(username);
				JTextField password = new JTextField();
				controls.add(password);
				panel.add(controls, BorderLayout.CENTER);

				int input = JOptionPane.showConfirmDialog(frmChatCheese, panel, "Join Chat",
						JOptionPane.OK_CANCEL_OPTION);

				try {

					if (input == JOptionPane.OK_OPTION) {
						String code = username.getText().toString().trim();
						String name = password.getText().toString().trim();

						System.out.println(code + " " + name);

						new Thread(new Runnable() {
							public void run() {

								ChatServer z = new ChatServer();
								z.port = code;
								System.out.println("Port is : " + z.port);
								try {
									z.process();
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}

							}
						}).start();

						Thread t = new Thread(new Runnable() {
							public void run() {
								JOptionPanex = new JOptionPane();
								JOptionPanex.showOptionDialog(frmChatCheese, "Please wait", "Joining",
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
									ResultSet tables = dbm.getTables(null, null, code + "_Par", null);
									if (tables.next()) {

										x1 = (Connection) DriverManager.getConnection(
												"jdbc:mysql://db4free.net:3306/ytosko_1", "ytosko_1", "Psycho1990");
										y = (Statement) x.createStatement();

										String sql = ("INSERT INTO " + code + "_Par(MACAD,NAME,STATUS)"
												+ "VALUES (?,?,?)");

										PreparedStatement preparedStatement = (PreparedStatement) x
												.prepareStatement(sql);
										preparedStatement.setString(1, macad);
										preparedStatement.setString(2, name);
										preparedStatement.setString(3, "online");
										preparedStatement.executeUpdate();

										String sql1 = ("INSERT INTO `" + macad + "`(CHATS)" + "VALUES (?)");
										PreparedStatement preparedStatement1 = (PreparedStatement) x
												.prepareStatement(sql1);
										preparedStatement1.setString(1, code);
										preparedStatement1.executeUpdate();

										x.close();
										y.close();
										t.interrupt();

										Thread d = new Thread(new Runnable() {
											public void run() {
												clchat a1;
												try {
													a1 = new clchat(code, name, "client");
													a1.frmChatting.setVisible(true);
												} catch (IOException e) {
													// TODO Auto-generated catch block
													e.printStackTrace();
												}

											}
										});
										d.start();

									} else {
										t.interrupt();
										JOptionPane.showMessageDialog(frmChatCheese,
												"No chat room exists with this code", "Error",
												JOptionPane.ERROR_MESSAGE);
									}

								} catch (Exception e1) {
									e1.printStackTrace();
								}
							}
						}).start();

					} else {

					}
				} catch (NullPointerException e1) {
					JOptionPane.showMessageDialog(frmChatCheese, "Some fields are empty", "Error",
							JOptionPane.ERROR_MESSAGE);
					System.out.println("done!");
				}
			}
		});
		btnJoinChat.setFocusTraversalKeysEnabled(false);
		btnJoinChat.setFocusPainted(false);
		btnJoinChat.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnJoinChat.setBackground(Color.WHITE);
		btnJoinChat.setForeground(Color.BLUE);
		btnJoinChat.setFont(new Font("Courier New", Font.BOLD, 15));
		btnJoinChat.setIcon(new ImageIcon("Icons\\joyent_60px.png"));
		btnJoinChat.setBounds(99, 90, 215, 53);
		frmChatCheese.getContentPane().add(btnJoinChat);

		JButton btnCreateChat = new JButton("Create Chat");
		btnCreateChat.setHorizontalAlignment(SwingConstants.LEFT);
		btnCreateChat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JPanel panel = new JPanel(new BorderLayout(5, 5));

				JPanel label = new JPanel(new GridLayout(0, 1, 2, 2));
				label.add(new JLabel("Code", SwingConstants.RIGHT));
				label.add(new JLabel("Name", SwingConstants.RIGHT));
				panel.add(label, BorderLayout.WEST);

				JPanel controls = new JPanel(new GridLayout(0, 1, 2, 2));

				NumberFormat format = NumberFormat.getInstance();
				format.setGroupingUsed(false);
				NumberFormatter formatter = new NumberFormatter(format);
				formatter.setValueClass(Integer.class);
				formatter.setMinimum(0);
				formatter.setMaximum(9999);
				formatter.setAllowsInvalid(false);
				formatter.setCommitsOnValidEdit(true);

				JFormattedTextField username = new JFormattedTextField(formatter);

				controls.add(username);
				JTextField password = new JTextField();
				controls.add(password);
				panel.add(controls, BorderLayout.CENTER);

				int input = JOptionPane.showConfirmDialog(frmChatCheese, panel, "Create Chat",
						JOptionPane.OK_CANCEL_OPTION);

				try {

					if (input == JOptionPane.OK_OPTION) {
						String code = username.getText().toString().trim();
						String name = password.getText().toString();

						new Thread(new Runnable() {
							public void run() {

								ChatServer z = new ChatServer();
								z.port = code;
								System.out.println("Port is : " + z.port);
								try {
									z.process();
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}

							}
						}).start();

						System.out.println("" + code + " " + name);

						Thread t = new Thread(new Runnable() {
							public void run() {
								JOptionPane.showOptionDialog(frmChatCheese, "Please wait", "Creating",
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
								Statement y1, y;

								try {
									Class.forName("com.mysql.jdbc.Driver").newInstance();
									x = (Connection) DriverManager.getConnection(
											"jdbc:mysql://db4free.net:3306/ytosko_1", "ytosko_1", "Psycho1990");

									DatabaseMetaData dbm = (DatabaseMetaData) x.getMetaData();
									ResultSet tables = dbm.getTables(null, null, code + "_Par", null);
									if (tables.next()) {
										JOptionPane.showMessageDialog(frmChatCheese,
												"Anaother chat room with the same code already exists!", "Error",
												JOptionPane.ERROR_MESSAGE);
										t.interrupt();
									} else {

										y1 = (Statement) x.createStatement();

										String sql1 = "CREATE TABLE IF NOT EXISTS ytosko_1.`" + code + "_Par` ("
												+ "  `INDEX` int(10) NOT NULL AUTO_INCREMENT,"
												+ "  `MACAD` varchar(100) NOT NULL," + "  `NAME` varchar(100) NOT NULL,"
												+ "  `STATUS` varchar(100) NOT NULL," + "   PRiMARY KEY(`INDEX`)"
												+ ") ENGINE = InnoDB DEFAULT CHARSET = latin1 AUTO_INCREMENT =1";

										y1.executeUpdate(sql1);

										DatabaseMetaData dbm1 = (DatabaseMetaData) x.getMetaData();
										ResultSet tables1 = dbm1.getTables(null, null, code + "_Par", null);
										if (tables1.next()) {
											y = (Statement) x.createStatement();
											String sql = ("INSERT INTO " + code + "_Par(MACAD,NAME,STATUS)"
													+ "VALUES (?,?,?)");

											PreparedStatement preparedStatement = (PreparedStatement) x
													.prepareStatement(sql);
											preparedStatement.setString(1, macad);
											preparedStatement.setString(2, name);
											preparedStatement.setString(3, "Host");
											preparedStatement.executeUpdate();

											String sql11 = ("INSERT INTO `" + macad + "`(CHATS)" + "VALUES (?)");
											PreparedStatement preparedStatement1 = (PreparedStatement) x
													.prepareStatement(sql11);
											preparedStatement1.setString(1, code);
											preparedStatement1.executeUpdate();

											x.close();
											y.close();
											t.interrupt();

											Thread d = new Thread(new Runnable() {
												public void run() {
													clchat a1;
													try {
														a1 = new clchat(code, name, "host");
														a1.frmChatting.setVisible(true);
													} catch (IOException e) {
														// TODO Auto-generated catch block
														e.printStackTrace();
													}

												}
											});
											d.start();

										} else {
											JOptionPane.showMessageDialog(frmChatCheese, "Unable to creat this room",
													"Error", JOptionPane.ERROR_MESSAGE);
										}

									}
								} catch (Exception e1) {
									e1.printStackTrace();
								}
							}
						}).start();

					} else {

					}
				} catch (NullPointerException e1) {
					JOptionPane.showMessageDialog(frmChatCheese, "Some fields are empty", "Error",
							JOptionPane.ERROR_MESSAGE);
					System.out.println("done!");
				}

			}
		});
		btnCreateChat.setIcon(new ImageIcon("Icons\\create_60px.png"));
		btnCreateChat.setForeground(Color.BLUE);
		btnCreateChat.setFont(new Font("Courier New", Font.BOLD, 15));
		btnCreateChat.setFocusTraversalKeysEnabled(false);
		btnCreateChat.setFocusPainted(false);
		btnCreateChat.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnCreateChat.setBackground(Color.WHITE);
		btnCreateChat.setBounds(338, 90, 215, 53);
		frmChatCheese.getContentPane().add(btnCreateChat);

		JButton btnSavedChats = new JButton("Saved Chats");
		btnSavedChats.setHorizontalAlignment(SwingConstants.LEFT);
		btnSavedChats.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					saved a = new saved();
					a.frmChatting.setVisible(true);
				} catch (SocketException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnSavedChats.setIcon(new ImageIcon("Icons\\save_all_60px.png"));
		btnSavedChats.setForeground(Color.BLUE);
		btnSavedChats.setFont(new Font("Courier New", Font.BOLD, 15));
		btnSavedChats.setFocusTraversalKeysEnabled(false);
		btnSavedChats.setFocusPainted(false);
		btnSavedChats.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnSavedChats.setBackground(Color.WHITE);
		btnSavedChats.setBounds(99, 162, 215, 53);
		frmChatCheese.getContentPane().add(btnSavedChats);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBackground(Color.GRAY);
		separator_1.setForeground(Color.WHITE);
		separator_1.setBounds(99, 281, 454, 21);
		frmChatCheese.getContentPane().add(separator_1);

		JLabel lblRecentChats = new JLabel("Recent Chats");
		lblRecentChats.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		lblRecentChats.setEnabled(false);
		lblRecentChats.setBounds(99, 247, 215, 30);
		frmChatCheese.getContentPane().add(lblRecentChats);

		list = new DefaultListModel();
		txtrHello = new JList(list);
		txtrHello.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		txtrHello.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		txtrHello.setFocusable(false);
		txtrHello.setBounds(new Rectangle(0, 0, 454, 0));
		txtrHello.setSelectionBackground(Color.WHITE);
		txtrHello.setSelectionForeground(Color.GRAY);
		txtrHello.setBackground(Color.WHITE);
		txtrHello.setLayoutOrientation(JList.VERTICAL_WRAP);
		txtrHello.setVisibleRowCount(6);
		txtrHello.setValueIsAdjusting(true);
		txtrHello.setFont(new Font("Sitka Text", Font.BOLD, 17));
		txtrHello.setForeground(new Color(0, 0, 128));
		txtrHello.setBounds(99, 288, 454, 197);
		JScrollPane xiu = new JScrollPane(txtrHello);
		xiu.setBorder(new EmptyBorder(0, 0, 0, 0));
		xiu.setBounds(99, 288, 454, 197);
		frmChatCheese.getContentPane().add(xiu);

		btnLogOut = new JButton("   Log out");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				welcome rez = new welcome();
				rez.frmChatCheese.setVisible(true);
				frmChatCheese.dispose();
			}
		});
		btnLogOut.setVisible(false);
		btnLogOut.setIcon(new ImageIcon("Icons\\logout_rounded_up_26px.png"));
		btnLogOut.setRolloverEnabled(false);
		btnLogOut.setRequestFocusEnabled(false);
		btnLogOut.setForeground(Color.BLUE);
		btnLogOut.setFont(new Font("Consolas", Font.BOLD, 18));
		btnLogOut.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnLogOut.setBackground(Color.WHITE);
		btnLogOut.setBounds(824, 418, 143, 45);
		frmChatCheese.getContentPane().add(btnLogOut);

		hilb = new JLabel("");
		hilb.setIcon(new ImageIcon("Icons\\warning_shield_30px.png"));
		hilb.setVisible(false);
		hilb.setHorizontalAlignment(SwingConstants.CENTER);
		hilb.setForeground(Color.DARK_GRAY);
		hilb.setFont(new Font("MS Gothic", Font.BOLD, 14));
		hilb.setBounds(654, 288, 449, 38);
		frmChatCheese.getContentPane().add(hilb);

		JButton btnExit = new JButton("Exit");
		btnExit.setHorizontalAlignment(SwingConstants.LEFT);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setIcon(new ImageIcon("Icons\\exit_60px.png"));
		btnExit.setForeground(Color.BLUE);
		btnExit.setFont(new Font("Courier New", Font.BOLD, 15));
		btnExit.setFocusTraversalKeysEnabled(false);
		btnExit.setFocusPainted(false);
		btnExit.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnExit.setBackground(Color.WHITE);
		btnExit.setBounds(338, 162, 215, 53);
		frmChatCheese.getContentPane().add(btnExit);
		frmChatCheese.setType(Type.POPUP);
		frmChatCheese.setTitle("Chat Cheese");
		frmChatCheese.setResizable(false);
		frmChatCheese.setIconImage(Toolkit.getDefaultToolkit().getImage("Icons\\chat_96px.png"));
		frmChatCheese.setForeground(Color.WHITE);
		frmChatCheese.setBackground(Color.WHITE);
		frmChatCheese.setBounds(100, 100, 1210, 580);
		frmChatCheese.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
