package ui;

import model.Account;
import model.Loading;
import persistence.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Load Data GUI. Handles loading data from memory and outputs to user where data was loaded from.
 */

public class LoadGUI extends AbstractGUI {

    //JSON Data access information
    Account account = Account.accessAccount(); //Accessor for account
    private static final String ACCOUNT_JSON_STORE = "./data/account.json";
    private static final String TRANSACTION_HISTORY_JSON_STORE = "./data/transactionHistory.json";
    private static final String LOCATION_LIST_JSON_STORE = "./data/locationList.json";

    //JAVA Swing UI Elements
    JPanel panel = new JPanel();

    JLabel locationLoad = new JLabel();
    JLabel transactionLoad = new JLabel();
    JLabel accountLoad = new JLabel();

    //Sets up JSON Data reader objects
    private AccountReader accountReader;
    private LocationListReader locationListReader;
    private TransactionHistoryReader transactionHistoryReader;

    //EFFECTS: Window setup for Load Data window
    public LoadGUI() {
        accountReader = new AccountReader(ACCOUNT_JSON_STORE);
        locationListReader = new LocationListReader(LOCATION_LIST_JSON_STORE);
        transactionHistoryReader = new TransactionHistoryReader(TRANSACTION_HISTORY_JSON_STORE);

        windowSetup();
        panel.setLayout(new GridLayout(3, 1));

        Loading.setLoading(true);

        locationLoad.setText(loadLocationList());
        transactionLoad.setText(loadTransactionHistory());
        accountLoad.setText(loadAccount());

        Loading.setLoading(false);

        frame.add(panel);
        panel.add(locationLoad);
        panel.add(transactionLoad);
        panel.add(accountLoad);
        frame.setTitle("Load");
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // MODIFIES: Account
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

    // MODIFIES: locationList
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

    // MODIFIES: transactionHistory
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
