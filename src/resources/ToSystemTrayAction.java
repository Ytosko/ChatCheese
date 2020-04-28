package resources;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.SocketException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class ToSystemTrayAction {
	
	public ToSystemTrayAction() {
		if(!SystemTray.isSupported()){
	        System.out.println("System tray is not supported !!! ");
	        System.exit(0);
	        return ;
	    }

	    SystemTray systemTray = SystemTray.getSystemTray();


	    Image image = Toolkit.getDefaultToolkit().getImage("Icons/chat_96px.png");


	    PopupMenu trayPopupMenu = new PopupMenu();


	    MenuItem action = new MenuItem("Open");
	    action.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	welcome window = new welcome();
				window.frmChatCheese.setVisible(true);
	        }
	    });     
	    trayPopupMenu.add(action);
	    
	    MenuItem action1 = new MenuItem("Saved chat");
	    action1.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	try {
					saved a = new saved();
					
					a.frmChatting.setVisible(true);
				} catch (SocketException e1) {
					e1.printStackTrace();
				}
	        }
	    });     
	    trayPopupMenu.add(action1);


	    MenuItem close = new MenuItem("Exit");
	    close.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            System.exit(0);             
	        }
	    });
	    trayPopupMenu.add(close);


	    TrayIcon trayIcon = new TrayIcon(image, "Chat Cheese", trayPopupMenu);

	    trayIcon.setImageAutoSize(true);

	    try{
	        systemTray.add(trayIcon);
	    }catch(AWTException awtException){
	        awtException.printStackTrace();
	    }
	    System.out.println("end of main");

	}

	
}