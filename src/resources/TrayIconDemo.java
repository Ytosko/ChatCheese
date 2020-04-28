package resources;

import java.awt.*;
import java.awt.TrayIcon.MessageType;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class TrayIconDemo {

    public static void main(String[] args) throws AWTException {
        if (SystemTray.isSupported()) {
            TrayIconDemo td = new TrayIconDemo();
        } else {
            System.err.println("System tray not supported!");
        }
    }
    TrayIconDemo() throws AWTException{
    	displayTray();
    }

    public void displayTray() throws AWTException {

         SystemTray tray = SystemTray.getSystemTray();

         Image image = Toolkit.getDefaultToolkit().createImage("icon.png");

         TrayIcon trayIcon = new TrayIcon(image, "Tray Demo");

         trayIcon.setImageAutoSize(true);
 
         trayIcon.setToolTip("System tray icon demo");
         tray.add(trayIcon);
         trayIcon.displayMessage("ChatCheese", "Client list refreshed", MessageType.INFO);
    }
}