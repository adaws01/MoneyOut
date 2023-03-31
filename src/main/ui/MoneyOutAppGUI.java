package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * This class manages all the functionality of a GUI based UI for the MoneyOut application.
 * Instantiate a new ConsoleMoneyOutApp in the Main class to launch the console based application
 * Contains all GUI Menus, and methods requiring user inputs.
 * Makes calls that modify or manipulate the lists of data recorded in the model package.
 */

public class MoneyOutAppGUI extends AbstractGUI implements ActionListener {

    JButton transactionsButton;
    JButton accountButton;
    JButton statsAndInsightsButton;
    JButton locationsButton;
    JButton saveButton;
    JButton loadButton;

    JPanel panel = new JPanel();

    public MoneyOutAppGUI() {
        windowSetup();

        panel.setLayout(new GridLayout(0, 2));

        //Instantiation of Main Menu Buttons
        transactionsButton = new JButton("Transactions");
        accountButton = new JButton("Account");
        statsAndInsightsButton = new JButton("Statistics and Insights");
        locationsButton = new JButton("Locations");
        saveButton = new JButton("Save to File");
        loadButton = new JButton("Load from File");

        transactionsButton.addActionListener(this);
        accountButton.addActionListener(this);
        statsAndInsightsButton.addActionListener(this);
        locationsButton.addActionListener(this);
        saveButton.addActionListener(this);
        loadButton.addActionListener(this);

        //Placement of Main Menu Buttons in Window
        panel.add(transactionsButton);
        panel.add(accountButton);
        panel.add(statsAndInsightsButton);
        panel.add(locationsButton);
        panel.add(saveButton);
        panel.add(loadButton);


        frame.add(panel, BorderLayout.CENTER);
        frameSetup();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == transactionsButton) {
            frame.dispose();
            new TransactionsGUI();
        } else if (e.getSource() == accountButton) {
            frame.dispose();
            new AccountGUI();
        } else if (e.getSource() == statsAndInsightsButton) {
            frame.dispose();
            new StatsAndInsightsGUI();
        } else if (e.getSource() == locationsButton) {
            frame.dispose();
            new LocationsGUI();
        } else if (e.getSource() == saveButton) {
            new SaveGUI();
        } else {
            new LoadGUI();
        }
    }
}
