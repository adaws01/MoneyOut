package ui.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * GUI for the Transactions Menu. Mirrors the functionality of the corresponding console menu.
 */

public class TransactionsGUI extends AbstractGUI implements ActionListener {

    JScrollPane transactionsScrollPane = generateTransactionHistoryScrollPane();

    JPanel panel = new JPanel();
    JPanel transactionsPanel = new JPanel();
    JPanel toolsPanel = new JPanel();

    JButton logButton = new JButton("Log New");
    JButton modifyButton = new JButton("Modify");
    JButton deleteButton = new JButton("Delete");


    public TransactionsGUI() {
        windowSetup();

        panel.setLayout(new GridLayout(1, 2));
        transactionsPanel.setLayout(new GridLayout(2, 1));
        toolsPanel.setLayout(new GridLayout(1, 3));

        transactionsScrollPane.setPreferredSize(new Dimension(400, 20));


        frame.add(panel);
        panel.add(backButton);
        panel.add(transactionsPanel);
        transactionsPanel.add(transactionsScrollPane);
        transactionsPanel.add(toolsPanel);
        toolsPanel.add(logButton);
        toolsPanel.add(modifyButton);
        toolsPanel.add(deleteButton);
        frameSetup();
        frame.setTitle("Transactions");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
