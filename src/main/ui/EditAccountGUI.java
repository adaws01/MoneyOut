package ui;

import model.Account;
import model.moneyoutprimitives.Location;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Edit Account Window. Provides User options to update their account details
 */

public class EditAccountGUI extends AbstractGUI implements ActionListener {

    //JAVA Swing UI Elements
    JPanel panel = new JPanel();
    JPanel infoPanel = new JPanel();
    JPanel newInfoPanel = new JPanel();

    JLabel nameLabel = new JLabel(Account.accessAccount().getName());
    JLabel locationLabel = new JLabel(generateLocationString(Account.accessAccount().getAddress()));

    JLabel name = new JLabel("Name:");
    JLabel locationName = new JLabel("Location Name or Address:");
    JLabel district = new JLabel("District:");
    JLabel distance = new JLabel("Distance from Home (in km):");

    JTextArea nameText = new JTextArea();
    JTextArea locationNameText = new JTextArea();
    JTextArea districtText = new JTextArea();
    JTextArea distanceText = new JTextArea();


    JButton enterButton = new JButton("Enter");

    //EFFECTS: Window setup for EditAccount window
    public EditAccountGUI() {
        windowSetup();

        panel.setLayout(new GridLayout(3, 1));
        infoPanel.setLayout(new GridLayout(2, 1));
        newInfoPanel.setLayout(new GridLayout(4, 2));

        enterButton.addActionListener(this);

        frame.add(panel);
        panel.add(infoPanel);
        infoPanel.add(nameLabel);
        infoPanel.add(locationLabel);
        panel.add(newInfoPanel);
        newInfoPanel.add(name);
        newInfoPanel.add(nameText);
        newInfoPanel.add(locationName);
        newInfoPanel.add(locationNameText);
        newInfoPanel.add(district);
        newInfoPanel.add(districtText);
        newInfoPanel.add(distance);
        newInfoPanel.add(distanceText);
        panel.add(enterButton);
        frameSetup();
    }

    //EFFECTS: Setup for enter Button; commits changes to the user Account to memory
    @Override
    public void actionPerformed(ActionEvent e) {
        Account acc = Account.accessAccount();
        acc.setName(nameText.getText());
        acc.setAddress(new Location(locationNameText.getText(), districtText.getText(),
                Integer.parseInt(distanceText.getText())));
        frame.dispose();
        new AccountGUI();
    }
}
