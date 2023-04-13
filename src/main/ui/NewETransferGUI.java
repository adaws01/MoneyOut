package ui;

import model.moneyoutprimitives.Date;
import model.transactions.ETransfer;
import model.transactions.Transaction;
import model.transactions.TransactionList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Handles creating a new ETransfer based on user input
 */

public class NewETransferGUI extends AbstractGUI implements ActionListener {

    //JAVA Swing UI Elements
    JPanel innerPanel = new JPanel();
    JPanel inputsPanel = new JPanel();

    JLabel cost = new JLabel("Cost ($):");
    JLabel date = new JLabel("Date (YYYYMMDD):");
    JLabel name = new JLabel("Name of Recipient:");

    JTextArea costText = new JTextArea();
    JTextArea dateText = new JTextArea();
    JTextArea nameText = new JTextArea();

    JButton enterButton = new JButton("Enter");

    //EFFECTS: Window setup for New ETransfer window
    public NewETransferGUI() {
        windowSetup();
        innerPanel.setLayout(new GridLayout(1, 2));
        inputsPanel.setLayout(new GridLayout(3, 2));

        enterButton.addActionListener(this);

        frame.add(innerPanel);
        innerPanel.add(inputsPanel);
        inputsPanel.add(cost);
        inputsPanel.add(costText);
        inputsPanel.add(date);
        inputsPanel.add(dateText);
        inputsPanel.add(name);
        inputsPanel.add(nameText);
        innerPanel.add(enterButton);
        frameSetup();
    }

    //EFFECTS: Enter button setup; commits input ETransfer information to a new object in memory.
    //         Returns to previous window
    @Override
    public void actionPerformed(ActionEvent e) {
        ETransfer etransfer = new ETransfer(Double.parseDouble(costText.getText()),
                new Date(Integer.parseInt(dateText.getText())), nameText.getText());
        TransactionList.addTransaction((Transaction) etransfer);
        frame.dispose();
        new TransactionsGUI();
    }
}
