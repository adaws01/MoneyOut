package ui.GUI;

import model.moneyoutprimitives.Location;

import javax.swing.*;
import java.awt.*;

/**
 * AbstractGUI contains the window setup information for all GUI windows.
 */

public abstract class AbstractGUI {
    JFrame frame;
    JButton backButton = new JButton("Back");

    public void windowSetup() {
        frame = new JFrame();
        frame.setSize(400, 200);
    }

    public void frameSetup() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Money Out");
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public String generateLocationString(Location location) {
        return location.getName() + ", " + location.getDistrict() + ", " + location.getDistanceFromHome()
                + "km from home.";
    }
}
