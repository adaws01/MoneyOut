package ui;

import model.moneyoutprimitives.Date;
import model.transactions.Investment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static model.transactions.TransactionList.getInvestmentHistory;

public class ModifyInvestmentGUI extends AbstractGUI implements ActionListener {

    JScrollPane investmentHistoryScrollPane = generateInvestmentHistoryScrollPane();

    JPanel panel = new JPanel();
    JPanel indexPanel = new JPanel();
    JPanel innerPanel = new JPanel();
    JPanel inputsPanel = new JPanel();

    JLabel cost = new JLabel("Cost ($):");
    JLabel date = new JLabel("Date (YYYYMMDD):");
    JLabel company = new JLabel("Company:");
    JLabel shares = new JLabel("Shares Purchased:");
    JLabel domain = new JLabel("Domain of Investment:");
    JLabel indexLabel = new JLabel("Index of Investment to Modify:");

    JTextArea costText = new JTextArea();
    JTextArea dateText = new JTextArea();
    JTextArea companyText = new JTextArea();
    JTextArea sharesText = new JTextArea();
    JTextArea domainText = new JTextArea();
    JTextArea indexText = new JTextArea();

    JButton enterButton = new JButton("Enter");

    public ModifyInvestmentGUI() {
        windowSetup();
        panel.setLayout(new GridLayout(3, 1));
        indexPanel.setLayout(new GridLayout(1, 2));
        innerPanel.setLayout(new GridLayout(1, 2));
        inputsPanel.setLayout(new GridLayout(5, 2));
        investmentHistoryScrollPane.setPreferredSize(new Dimension(400, 20));
        enterButton.addActionListener(this);
        addElements();
        frameSetup();
    }

    private void addElements() {
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
        inputsPanel.add(company);
        inputsPanel.add(companyText);
        inputsPanel.add(shares);
        inputsPanel.add(sharesText);
        inputsPanel.add(domain);
        inputsPanel.add(domainText);
        innerPanel.add(enterButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Investment investment = getInvestmentHistory().get(Integer.parseInt(indexText.getText()) - 1);
        investment.setCost(Double.parseDouble(costText.getText()));
        investment.setDate(new Date(Integer.parseInt(dateText.getText())));
        investment.setCompany(companyText.getText());
        investment.setShares(Integer.parseInt(sharesText.getText()));
        investment.setDomain(domainText.getText());
        frame.dispose();
        new TransactionsGUI();
    }
}
