package ui;

import model.moneyoutprimitives.Date;
import model.moneyoutprimitives.LocationList;
import model.transactions.PosPurchase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static model.transactions.TransactionList.getPosPurchaseHistory;

/**
 * Handles modifying an existing PosPurchase based on user input
 */

public class ModifyPosPurchaseGUI extends AbstractGUI implements ActionListener {

    //JAVA Swing UI Elements
    JScrollPane locationListScrollPane = generateLocationListScrollPane();
    JScrollPane posPurchaseHistoryScrollPane = generatePosPurchaseHistoryScrollPane();

    JPanel panel = new JPanel();
    JPanel indexPanel = new JPanel();
    JPanel innerPanel = new JPanel();
    JPanel inputsPanel = new JPanel();

    JLabel cost = new JLabel("Cost ($):");
    JLabel date = new JLabel("Date (YYYYMMDD):");
    JLabel good = new JLabel("Good:");
    JLabel quantity = new JLabel("Quantity Purchased:");
    JLabel location = new JLabel("Index of Location:");
    JLabel indexLabel = new JLabel("Index of POS Purchase to Modify:");

    JTextArea costText = new JTextArea();
    JTextArea dateText = new JTextArea();
    JTextArea goodText = new JTextArea();
    JTextArea quantityText = new JTextArea();
    JTextArea locationText = new JTextArea();
    JTextArea indexText = new JTextArea();

    JButton enterButton = new JButton("Enter");

    //EFFECTS: Window setup for modify PosPurchase window
    public ModifyPosPurchaseGUI() {
        windowSetup();
        panel.setLayout(new GridLayout(4, 1));
        indexPanel.setLayout(new GridLayout(1, 2));
        innerPanel.setLayout(new GridLayout(1, 2));
        inputsPanel.setLayout(new GridLayout(5, 2));
        locationListScrollPane.setPreferredSize(new Dimension(400, 20));
        posPurchaseHistoryScrollPane.setPreferredSize(new Dimension(400, 20));
        enterButton.addActionListener(this);
        addElements();
        frameSetup();
    }

    //EFFECTS: Adds all UI elements to window.
    private void addElements() {
        frame.add(panel);
        panel.add(locationListScrollPane);
        panel.add(posPurchaseHistoryScrollPane);
        panel.add(indexPanel);
        indexPanel.add(indexLabel);
        indexPanel.add(indexText);
        panel.add(innerPanel);
        innerPanel.add(inputsPanel);
        inputsPanel.add(cost);
        inputsPanel.add(costText);
        inputsPanel.add(date);
        inputsPanel.add(dateText);
        inputsPanel.add(good);
        inputsPanel.add(goodText);
        inputsPanel.add(quantity);
        inputsPanel.add(quantityText);
        inputsPanel.add(location);
        inputsPanel.add(locationText);
        innerPanel.add(enterButton);
    }

    //EFFECTS: Enter button setup; commits input PosPurchase information to all instances of original object
    @Override
    public void actionPerformed(ActionEvent e) {
        PosPurchase purchase = getPosPurchaseHistory().get(Integer.parseInt(indexText.getText()) - 1);
        purchase.setCost(Double.parseDouble(costText.getText()));
        purchase.setDate(new Date(Integer.parseInt(dateText.getText())));
        purchase.setGood(goodText.getText());
        purchase.setQuantity(Integer.parseInt(quantityText.getText()));
        purchase.setLocation(LocationList.accessLocationList().get(Integer.parseInt(locationText.getText()) - 1));
        frame.dispose();
        new TransactionsGUI();
    }
}
