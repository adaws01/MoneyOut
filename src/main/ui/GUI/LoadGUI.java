package ui.GUI;

import model.Account;
import persistence.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class LoadGUI extends AbstractGUI{

    Account account = Account.accessAccount(); //Accessor for account
    private static final String ACCOUNT_JSON_STORE = "./data/account.json";
    private static final String TRANSACTION_HISTORY_JSON_STORE = "./data/transactionHistory.json";
    private static final String LOCATION_LIST_JSON_STORE = "./data/locationList.json";

    JPanel panel = new JPanel();

    JLabel locationLoad = new JLabel();
    JLabel transactionLoad = new JLabel();
    JLabel accountLoad = new JLabel();

    private AccountReader accountReader;
    private LocationListReader locationListReader;
    private TransactionHistoryReader transactionHistoryReader;

    public LoadGUI() {
        accountReader = new AccountReader(ACCOUNT_JSON_STORE);
        locationListReader = new LocationListReader(LOCATION_LIST_JSON_STORE);
        transactionHistoryReader = new TransactionHistoryReader(TRANSACTION_HISTORY_JSON_STORE);

        windowSetup();
        panel.setLayout(new GridLayout(3, 1));

        locationLoad.setText(loadLocationList());
        transactionLoad.setText(loadTransactionHistory());
        accountLoad.setText(loadAccount());

        frame.add(panel);
        panel.add(locationLoad);
        panel.add(transactionLoad);
        panel.add(accountLoad);
        frame.setTitle("Load");
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: loads Account from file
    private String loadAccount() {
        try {
            AccountReader.readAccount();
            return "Loaded Account from " + ACCOUNT_JSON_STORE;
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + ACCOUNT_JSON_STORE);
        }
        return null;
    }

    // MODIFIES: this
    // EFFECTS: loads Location List from file
    private String loadLocationList() {
        try {
            LocationListReader.readLocationList();
            return "Loaded Location List from " + LOCATION_LIST_JSON_STORE;
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + ACCOUNT_JSON_STORE);
        }
        return null;
    }

    // MODIFIES: this
    // EFFECTS: loads Location List from file
    private String loadTransactionHistory() {
        try {
            TransactionHistoryReader.readTransactionHistory();
            return "Loaded Transaction History from " + TRANSACTION_HISTORY_JSON_STORE;
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + ACCOUNT_JSON_STORE);
        }
        return null;
    }

}
