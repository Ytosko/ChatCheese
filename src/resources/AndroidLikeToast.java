package resources;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.RoundRectangle2D;
import javax.swing.*;
 
 
public class AndroidLikeToast extends JDialog {
 
    String msg;
    JFrame frame;
    public static final int LENGTH_LONG = 5000;
    public static final int LENGTH_SHORT = 2000;
 
    public AndroidLikeToast(JFrame frame, boolean modal, String msg) {
        super(frame, modal);
        this.msg = msg;
        this.frame=frame;
        initComponents();        
    }
 
    private void initComponents() {
        setLayout(new FlowLayout(FlowLayout.CENTER));
        addComponentListener(new ComponentAdapter() {
 
            @Override
            public void componentResized(ComponentEvent e) {
                setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 30, 30));
            }
        });
        setUndecorated(true);
        setSize(300, 50);
        setLocationRelativeTo(frame);
        getContentPane().setBackground(Color.BLACK);
         

        GraphicsEnvironment ge =
                GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        final boolean isTranslucencySupported =
                gd.isWindowTranslucencySupported(GraphicsDevice.WindowTranslucency.TRANSLUCENT);
 

        if (!gd.isWindowTranslucencySupported(GraphicsDevice.WindowTranslucency.PERPIXEL_TRANSPARENT)) {
            System.err.println("Shaped windows are not supported");
        }

        if (isTranslucencySupported) {
            setOpacity(0.5f);
        } else {
            System.out.println("Translucency is not supported.");
        }
 
        JLabel label = new JLabel();
        label.setForeground(Color.WHITE);
        label.setText(msg);
        add(label);
    }
}