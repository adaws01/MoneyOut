package ui;

import model.moneyoutprimitives.Date;
import model.transactions.ETransfer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static model.transactions.TransactionList.getETransferHistory;

/**
 * Handles modifying an existing ETransfer based on user input
 */

public class ModifyETransferGUI extends AbstractGUI implements ActionListener {

    //JAVA Swing UI Elements
    JScrollPane investmentHistoryScrollPane = generateETransferHistoryScrollPane();

    JPanel panel = new JPanel();
    JPanel indexPanel = new JPanel();
    JPanel innerPanel = new JPanel();
    JPanel inputsPanel = new JPanel();

    JLabel cost = new JLabel("Cost ($):");
    JLabel date = new JLabel("Date (YYYYMMDD):");
    JLabel name = new JLabel("Name of Recipient:");
    JLabel indexLabel = new JLabel("Index of E-Transfer to Modify:");

    JTextArea costText = new JTextArea();
    JTextArea dateText = new JTextArea();
    JTextArea nameText = new JTextArea();
    JTextArea indexText = new JTextArea();

    JButton enterButton = new JButton("Enter");

    //EFFECTS: Window setup for Modify ETransfer window
    public ModifyETransferGUI() {
        windowSetup();
        panel.setLayout(new GridLayout(3, 1));
        indexPanel.setLayout(new GridLayout(1, 2));
        innerPanel.setLayout(new GridLayout(1, 2));
        inputsPanel.setLayout(new GridLayout(3, 2));
        investmentHistoryScrollPane.setPreferredSize(new Dimension(400, 20));

        enterButton.addActionListener(this);

        frame.add(panel);
        panel.add(investmentHistoryScrollPane);
        panel.add(indexPanel);
        indexPanel.add(indexLabel);
        indexPanel.add(indexText);
        panel.add(innerPanel);
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

    //EFFECTS: Setup for Enter button; commits updated ETransfer information to all instances of the original
    //         ETransfer object
    @Override
    public void actionPerformed(ActionEvent e) {
        ETransfer etransfer = getETransferHistory().get(Integer.parseInt(indexText.getText()) - 1);
        etransfer.setCost(Double.parseDouble(costText.getText()));
        etransfer.setDate(new Date(Integer.parseInt(dateText.getText())));
        etransfer.setName(nameText.getText());
        frame.dispose();
        new TransactionsGUI();
    }
}
