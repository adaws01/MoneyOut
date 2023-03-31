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

import static model.transactions.TransactionList.getPosPurchaseHistory;

public class ModifyPosPurchaseGUI extends AbstractGUI implements ActionListener {

    JScrollPane locationListScrollPane = generateLocationListScrollPane();
    JScrollPane transactionHistoryScrollPane = generatePosPurchaseHistoryScrollPane();

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

    public ModifyPosPurchaseGUI() {
        windowSetup();
        panel.setLayout(new GridLayout(4, 1));
        indexPanel.setLayout(new GridLayout(1, 2));
        innerPanel.setLayout(new GridLayout(1, 2));
        inputsPanel.setLayout(new GridLayout(5, 2));
        locationListScrollPane.setPreferredSize(new Dimension(400, 20));
        transactionHistoryScrollPane.setPreferredSize(new Dimension(400, 20));

        enterButton.addActionListener(this);

        frame.add(panel);
        panel.add(locationListScrollPane);
        panel.add(transactionHistoryScrollPane);
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
        frameSetup();
    }

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
