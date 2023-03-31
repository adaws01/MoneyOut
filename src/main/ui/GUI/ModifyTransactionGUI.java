package ui.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModifyTransactionGUI extends AbstractGUI implements ActionListener {

    JPanel panel = new JPanel();

    JButton pos = new JButton("Modify POS Purchase");
    JButton inv = new JButton("Modify Investment");
    JButton etr = new JButton("Modify E-Transfer");

    public ModifyTransactionGUI() {
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
            new ModifyPosPurchaseGUI();
        } else if (e.getSource() == inv) {
            frame.dispose();
            //new ModifyInvestmentGUI();
        } else {
            frame.dispose();
            //new ModifyETransferGUI();
        }
    }
}
