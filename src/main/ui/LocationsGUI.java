package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * GUI for the Locations Menu. Mirrors the functionality of the corresponding console menu.
 */

public class LocationsGUI extends AbstractGUI implements ActionListener {

    JPanel panel = new JPanel();
    JPanel locationsPanel = new JPanel();
    JPanel toolsPanel = new JPanel();
    JScrollPane locationsScrollPane = generateLocationListScrollPane();

    JButton newLocationButton = new JButton("New Location");
    JButton modifyLocationButton = new JButton("Modify Location");

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

    private void logNewLocation() {
        frame.dispose();
        new LocationEntryGUI();
    }

    private void modifyLocation() {
        frame.dispose();
        new LocationModifyGUI();
    }


}
