package ui.GUI;

import model.moneyoutprimitives.Date;
import model.moneyoutprimitives.LocationList;
import model.transactions.PosPurchase;
import model.transactions.Transaction;
import model.transactions.TransactionList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewPosPurchaseGUI extends AbstractGUI implements ActionListener {

    JScrollPane locationListScrollPane = generateLocationListScrollPane();

    JPanel panel = new JPanel();
    JPanel innerPanel = new JPanel();
    JPanel inputsPanel = new JPanel();

    JLabel cost = new JLabel("Cost ($):");
    JLabel date = new JLabel("Date (YYYYMMDD):");
    JLabel good = new JLabel("Good:");
    JLabel quantity = new JLabel("Quantity Purchased:");
    JLabel location = new JLabel("Index of Location:");

    JTextArea costText = new JTextArea();
    JTextArea dateText = new JTextArea();
    JTextArea goodText = new JTextArea();
    JTextArea quantityText = new JTextArea();
    JTextArea locationText = new JTextArea();

    JButton enterButton = new JButton("Enter");

    public NewPosPurchaseGUI() {
        windowSetup();
        panel.setLayout(new GridLayout(2, 1));
        innerPanel.setLayout(new GridLayout(1, 2));
        inputsPanel.setLayout(new GridLayout(5, 2));
        locationListScrollPane.setPreferredSize(new Dimension(400, 20));

        enterButton.addActionListener(this);

        frame.add(panel);
        panel.add(locationListScrollPane);
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
        frameSetup();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        PosPurchase purchase = new PosPurchase(Double.parseDouble(costText.getText()),
                new Date(Integer.parseInt(dateText.getText())), goodText.getText(),
                Integer.parseInt(quantityText.getText()),
                LocationList.accessLocationList().get(Integer.parseInt(locationText.getText()) - 1));
        TransactionList.addTransaction((Transaction) purchase);
        frame.dispose();
        new TransactionsGUI();
    }
}
