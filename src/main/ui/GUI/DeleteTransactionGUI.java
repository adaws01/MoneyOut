package ui.GUI;

import model.transactions.Transaction;
import model.transactions.TransactionList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class DeleteTransactionGUI extends AbstractGUI implements ActionListener {

    JScrollPane transactionScrollPane = generateTransactionHistoryScrollPane();

    JPanel panel = new JPanel();
    JPanel controlPanel = new JPanel();
    JPanel inputPanel = new JPanel();

    JButton deleteButton = new JButton("Delete");

    JLabel indexLabel = new JLabel("Input Index of Transaction to Delete:");

    JTextArea indexText = new JTextArea();

    public DeleteTransactionGUI() {
        windowSetup();
        panel.setLayout(new GridLayout(2, 1));
        controlPanel.setLayout(new GridLayout(1, 2));
        inputPanel.setLayout(new GridLayout(1, 2));

        deleteButton.addActionListener(this);

        frame.add(panel);
        panel.add(transactionScrollPane);
        panel.add(controlPanel);
        controlPanel.add(inputPanel);
        inputPanel.add(indexLabel);
        inputPanel.add(indexText);
        controlPanel.add(deleteButton);
        frameSetup();
        frame.setTitle("Delete Transaction");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        List<Transaction> transactions = TransactionList.accessTransactionHistory();
        transactions.remove(Integer.parseInt(indexText.getText()) - 1);
        frame.dispose();
        new TransactionsGUI();
    }
}
