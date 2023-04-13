package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * GUI for the Locations Menu. Mirrors the functionality of the corresponding console menu.
 */

public class LocationsGUI extends AbstractGUI implements ActionListener {

    //JAVA Swing UI Elements
    JPanel panel = new JPanel();
    JPanel locationsPanel = new JPanel();
    JPanel toolsPanel = new JPanel();
    JScrollPane locationsScrollPane = generateLocationListScrollPane();

    JButton newLocationButton = new JButton("New Location");
    JButton modifyLocationButton = new JButton("Modify Location");

    //EFFECTS: Window setup for Locations window
    public LocationsGUI() {
        windowSetup();
        frame.setMaximumSize(new Dimension(800, 300));

        panel.setLayout(new GridLayout(1, 2));
        locationsPanel.setLayout(new GridLayout(2, 1));
        toolsPanel.setLayout(new GridLayout(1, 2));
        locationsScrollPane.setPreferredSize(new Dimension(400, 20));

        newLocationButton.addActionListener(this);
        modifyLocationButton.addActionListener(this);
        backButton.addActionListener(this);

        frame.add(panel);
        panel.add(backButton);
        panel.add(locationsPanel);
        locationsPanel.add(locationsScrollPane);
        locationsPanel.add(toolsPanel);
        toolsPanel.add(newLocationButton);
        toolsPanel.add(modifyLocationButton);
        frameSetup();
        frame.setTitle("Locations");
    }

    //EFFECTS: Button setup for all buttons in window: Back, new Location, update Location
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            frame.dispose();
            new MoneyOutAppGUI();
        } else if (e.getSource() == newLocationButton) {
            logNewLocation();
        } else if (e.getSource() == modifyLocationButton) {
            modifyLocation();
        }
    }

    //EFFECTS: Closes Locations window and opens the Location Entry Window
    private void logNewLocation() {
        frame.dispose();
        new LocationEntryGUI();
    }

    //EFFECTS: Closes Locations window and opens the Location update Window
    private void modifyLocation() {
        frame.dispose();
        new LocationModifyGUI();
    }


}
