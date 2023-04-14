package ui;

import model.Event;
import model.EventLog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;


/**
 * This class manages all the functionality of a GUI based UI for the MoneyOut application.
 * Instantiate a new ConsoleMoneyOutApp in the Main class to launch the console based application
 * Contains all GUI Menus, and methods requiring user inputs.
 * Makes calls that modify or manipulate the lists of data recorded in the model package.
 */

public class MoneyOutAppGUI extends AbstractGUI implements ActionListener, WindowListener {
    //JAVA Swing UI Elements
    JButton transactionsButton;
    JButton accountButton;
    JButton statsAndInsightsButton;
    JButton locationsButton;
    JButton saveButton;
    JButton loadButton;

    JPanel panel = new JPanel();

    //EFFECTS: Window setup for Main window (Entry point for the entire app)
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

        frame.addWindowListener(this);

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

    //EFFECTS: Button Setup; Calls each of the 6 subsequent windows
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == transactionsButton) {
            frame.setVisible(false);
            new TransactionsGUI();
        } else if (e.getSource() == accountButton) {
            frame.setVisible(false);
            new AccountGUI();
        } else if (e.getSource() == statsAndInsightsButton) {
            frame.setVisible(false);
            new StatsAndInsightsGUI();
        } else if (e.getSource() == locationsButton) {
            frame.setVisible(false);
            new LocationsGUI();
        } else if (e.getSource() == saveButton) {
            new SaveGUI();
        } else {
            new LoadGUI();
        }
    }

    //EFFECTS: Runs when application is closed (using the close window Button)
    @Override
    public void windowClosing(WindowEvent e) {
        System.out.println("Event Log: ");
        printEventLogToConsole();
    }

    //EFFECTS: Writes EventLog To Console when app is closed
    private void printEventLogToConsole() {
        EventLog log = EventLog.getInstance();
        for (Event next : log) {
            System.out.println(next.toString());
        }
    }

    //WindowListener Methods (Unused)
    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
