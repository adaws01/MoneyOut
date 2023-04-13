package ui;

import model.moneyoutprimitives.Location;
import model.moneyoutprimitives.LocationList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class represents a window which asks for all the information required to enter a location
 */

public class LocationEntryGUI extends AbstractGUI implements ActionListener {

    //JAVA Swing UI Elements
    JPanel panel = new JPanel();
    JPanel newLocationPanel = new JPanel();
    JPanel labelsPanel = new JPanel();
    JPanel fieldsPanel = new JPanel();

    JLabel nameLabel = new JLabel("Location Name or Address:");
    JLabel districtLabel = new JLabel("District:");
    JLabel distanceLabel = new JLabel("Distance From Home (in km):");

    JTextArea nameText = new JTextArea();
    JTextArea districtText = new JTextArea();
    JTextArea distanceText = new JTextArea();

    JButton enterButton = new JButton("Enter");

    //EFFECTS: Window setup for the Add Location dialogue
    public LocationEntryGUI() {
        windowSetup();

        panel.setLayout(new GridLayout(1, 2));
        newLocationPanel.setLayout(new GridLayout(1, 2));
        labelsPanel.setLayout(new GridLayout(3, 1));
        fieldsPanel.setLayout(new GridLayout(3, 1));

        enterButton.addActionListener(this);

        frame.add(panel);
        panel.add(newLocationPanel);
        panel.add(enterButton);
        newLocationPanel.add(labelsPanel);
        newLocationPanel.add(fieldsPanel);
        labelsPanel.add(nameLabel);
        labelsPanel.add(districtLabel);
        labelsPanel.add(distanceLabel);
        fieldsPanel.add(nameText);
        fieldsPanel.add(districtText);
        fieldsPanel.add(distanceText);
        frameSetup();
        frame.setTitle("Provide the Following:");
    }

    //EFFECTS: Enter button action; commits Location information to locationList
    @Override
    public void actionPerformed(ActionEvent e) {
        Location location = new Location(nameText.getText(), districtText.getText(),
                Integer.parseInt(distanceText.getText()));
        LocationList.addLocation(location);
        frame.dispose();
        new LocationsGUI();
    }
}
