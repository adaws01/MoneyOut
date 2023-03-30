package ui.GUI;

import model.moneyoutprimitives.Location;

import javax.swing.*;
import java.awt.*;

import static model.moneyoutprimitives.LocationList.accessLocationList;

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

    public JScrollPane generateLocationListPanel() {
        JPanel locationListPanel = new JPanel();
        locationListPanel.setLayout(new GridLayout(0, 1));

        for (int i = 0; i <= accessLocationList().size() - 1; i++) {
            JLabel label = new JLabel((i + 1) + ". " + generateLocationString(accessLocationList().get(i)));
            locationListPanel.add(label);
        }

        JScrollPane locationsScrollPane = new JScrollPane();
        locationsScrollPane.setViewportView(locationListPanel);

        return locationsScrollPane;
    }
}
