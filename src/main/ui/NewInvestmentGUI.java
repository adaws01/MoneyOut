package ui;

import model.moneyoutprimitives.Date;
import model.transactions.Investment;
import model.transactions.Transaction;
import model.transactions.TransactionList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewInvestmentGUI extends AbstractGUI implements ActionListener {

    JPanel innerPanel = new JPanel();
    JPanel inputsPanel = new JPanel();

    JLabel cost = new JLabel("Cost ($):");
    JLabel date = new JLabel("Date (YYYYMMDD):");
    JLabel company = new JLabel("Company:");
    JLabel shares = new JLabel("Shares Purchased:");
    JLabel domain = new JLabel("Domain of Investment:");

    JTextArea costText = new JTextArea();
    JTextArea dateText = new JTextArea();
    JTextArea companyText = new JTextArea();
    JTextArea sharesText = new JTextArea();
    JTextArea domainText = new JTextArea();

    JButton enterButton = new JButton("Enter");

    public NewInvestmentGUI() {
        windowSetup();
        innerPanel.setLayout(new GridLayout(1, 2));
        inputsPanel.setLayout(new GridLayout(5, 2));

        enterButton.addActionListener(this);

        frame.add(innerPanel);
        innerPanel.add(inputsPanel);
        inputsPanel.add(cost);
        inputsPanel.add(costText);
        inputsPanel.add(date);
        inputsPanel.add(dateText);
        inputsPanel.add(company);
        inputsPanel.add(companyText);
        inputsPanel.add(shares);
        inputsPanel.add(sharesText);
        inputsPanel.add(domain);
        inputsPanel.add(domainText);
        innerPanel.add(enterButton);
        frameSetup();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Investment investment = new Investment(Double.parseDouble(costText.getText()),
                new Date(Integer.parseInt(dateText.getText())), companyText.getText(),
                Integer.parseInt(sharesText.getText()),
                domainText.getText());
        TransactionList.addTransaction((Transaction) investment);
        frame.dispose();
        new TransactionsGUI();
    }
}
