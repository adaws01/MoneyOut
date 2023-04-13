package ui;

import model.Account;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Update Balance GUI. Allows the user to either deposit to or withdraw from their account balance.
 */

public class UpdateBalanceGUI extends AbstractGUI implements ActionListener {

    String process;  //stores deposit or withdraw (for use in evaluating new account balance)

    //JAVA Swing UI Elements
    JPanel panel = new JPanel();
    JPanel entryPanel = new JPanel();

    JLabel text = new JLabel();

    JButton enterButton = new JButton("Enter");

    JTextArea amount = new JTextArea();

    //REQUIRES: process == either "Deposit" or "Withdraw"
    //EFFECTS: Window setup for Update Balance window
    public UpdateBalanceGUI(String process) {
        this.process = process;

        windowSetup();

        panel.setLayout(new GridLayout(2, 1));

        enterButton.addActionListener(this);

        text.setText(process);

        frame.add(panel);
        panel.add(entryPanel);
        panel.add(enterButton);
        entryPanel.add(text);
        entryPanel.add(amount);
        frameSetup();
    }

    //EFFECTS: Button Setup; Handles updating account balance based on user input
    @Override
    public void actionPerformed(ActionEvent e) {
        Double previousBalance = Account.accessAccount().getBalance();
        if (process.equals("Deposit")) {
            Account.accessAccount().setBalance(previousBalance + Double.parseDouble(amount.getText()));
        } else {
            Account.accessAccount().setBalance(previousBalance - Double.parseDouble(amount.getText()));
        }
        frame.dispose();
        new AccountGUI();
    }
}
