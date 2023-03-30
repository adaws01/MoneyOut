package ui.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * GUI for the Transactions Menu. Mirrors the functionality of the corresponding console menu.
 */

public class TransactionsGUI extends AbstractGUI implements ActionListener {

    public TransactionsGUI() {
        windowSetup();



        frameSetup();
        frame.setTitle("Transactions");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
