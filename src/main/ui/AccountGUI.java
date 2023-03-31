package ui;

import model.moneyoutprimitives.Location;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static model.Account.accessAccount;

/**
 * GUI for the Account Menu. Mirrors the functionality of the corresponding console menu.
 */

public class AccountGUI extends AbstractGUI implements ActionListener {

    JPanel panel = new JPanel();
    JPanel accountPanel = new JPanel();
    JPanel balancePanel = new JPanel();
    JPanel personalInfoEditPanel = new JPanel();
    JPanel personalInfoPanel = new JPanel();

    JLabel name = new JLabel(accessAccount().getName());
    Location address = accessAccount().getAddress();
    JLabel location = new JLabel("Address: " + generateLocationString(address));
    JLabel balance = new JLabel("Balance: $" + accessAccount().getBalance());

    JButton depositButton = new JButton("Deposit");
    JButton withdrawButton = new JButton("Withdraw");
    JButton editButton = new JButton("Edit");


    public AccountGUI() {
        windowSetup();
        panel.setLayout(new GridLayout(1, 2));
        accountPanel.setLayout(new GridLayout(2, 1));
        balancePanel.setLayout(new GridLayout(3, 1));
        personalInfoEditPanel.setLayout(new GridLayout(1, 2));
        personalInfoPanel.setLayout(new GridLayout(2, 1));
        addActionListeners();
        frame.add(panel);
        panel.add(backButton);
        panel.add(accountPanel);
        accountPanel.add(personalInfoEditPanel);
        accountPanel.add(balancePanel);
        personalInfoEditPanel.add(personalInfoPanel);
        personalInfoPanel.add(name);
        personalInfoPanel.add(location);
        personalInfoEditPanel.add(editButton);
        balancePanel.add(balance);
        balancePanel.add(depositButton);
        balancePanel.add(withdrawButton);
        frameSetup();
        frame.setTitle("Account");
    }

    private void addActionListeners() {
        depositButton.addActionListener(this);
        withdrawButton.addActionListener(this);
        editButton.addActionListener(this);
        backButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            frame.dispose();
            new MoneyOutAppGUI();
        } else if (e.getSource() == depositButton) {
            frame.dispose();
            new UpdateBalanceGUI("Deposit");
        } else if (e.getSource() == withdrawButton) {
            frame.dispose();
            new UpdateBalanceGUI("Withdraw");
        } else if (e.getSource() == editButton) {
            frame.dispose();
            new EditAccountGUI();
        }
    }
}
