package ui.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogTransactionGUI extends AbstractGUI implements ActionListener {

    JPanel panel = new JPanel();

    JButton pos = new JButton("New POS Purchase");
    JButton inv = new JButton("New Investment");
    JButton etr = new JButton("New E-Transfer");

    public LogTransactionGUI() {
        windowSetup();
        panel.setLayout(new GridLayout(3, 1));

        pos.addActionListener(this);
        inv.addActionListener(this);
        etr.addActionListener(this);

        frame.add(panel);
        panel.add(pos);
        panel.add(inv);
        panel.add(etr);
        frameSetup();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == pos) {
            frame.dispose();
            new NewPosPurchaseGUI();
        } else if (e.getSource() == inv) {
            frame.dispose();
            new NewInvestmentGUI();
        } else {
            frame.dispose();
            new NewETransferGUI();
        }
    }
}
