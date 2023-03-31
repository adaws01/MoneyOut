package ui.GUI;

import model.moneyoutprimitives.Location;
import model.moneyoutprimitives.LocationList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class represents a window which asks for all the information required to modify a location. It then sends the
 * user to a Location Entry GUI.
 */

public class LocationModifyGUI extends AbstractGUI implements ActionListener {

    JScrollPane locationsScrollPane = generateLocationListScrollPane();

    JPanel panel = new JPanel();
    JPanel indexPanel = new JPanel();
    JPanel modifyPanel = new JPanel();
    JPanel newLocationPanel = new JPanel();
    JPanel labelsPanel = new JPanel();
    JPanel fieldsPanel = new JPanel();

    JLabel nameLabel = new JLabel("Location Name or Address:");
    JLabel districtLabel = new JLabel("District:");
    JLabel distanceLabel = new JLabel("Distance From Home (in km):");
    JLabel indexLabel = new JLabel("Input Location Index");

    JTextArea nameText = new JTextArea();
    JTextArea districtText = new JTextArea();
    JTextArea distanceText = new JTextArea();
    JTextArea indexText = new JTextArea();

    JButton enterButton = new JButton("Enter");

    public LocationModifyGUI() {
        windowSetup();

        panel.setLayout(new GridLayout(3, 1));
        indexPanel.setLayout(new GridLayout(1, 2));
        modifyPanel.setLayout(new GridLayout(1, 2));
        newLocationPanel.setLayout(new GridLayout(1, 2));
        labelsPanel.setLayout(new GridLayout(3, 1));
        fieldsPanel.setLayout(new GridLayout(3, 1));

        enterButton.addActionListener(this);

        frame.add(panel);
        panel.add(locationsScrollPane);
        panel.add(indexPanel);
        indexPanel.add(indexLabel);
        indexPanel.add(indexText);
        panel.add(modifyPanel);
        modifyPanel.add(newLocationPanel);
        modifyPanel.add(enterButton);
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

    @Override
    public void actionPerformed(ActionEvent e) {
        Location location = LocationList.accessLocationList().get(Integer.parseInt(indexText.getText()) - 1);
        location.setName(nameText.getText());
        location.setDistrict(districtText.getText());
        location.setDistanceFromHome(Integer.parseInt(distanceText.getText()));
        frame.dispose();
        new LocationsGUI();
    }
}
