package ui;

import model.Account;
import model.moneyoutprimitives.LocationList;
import model.transactions.TransactionList;
import persistence.AccountWriter;
import persistence.LocationListWriter;
import persistence.TransactionHistoryWriter;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

public class SaveGUI extends AbstractGUI {

    Account account = Account.accessAccount(); //Accessor for account
    private static final String ACCOUNT_JSON_STORE = "./data/account.json";
    private static final String TRANSACTION_HISTORY_JSON_STORE = "./data/transactionHistory.json";
    private static final String LOCATION_LIST_JSON_STORE = "./data/locationList.json";

    JPanel panel = new JPanel();

    JLabel locationSave = new JLabel();
    JLabel transactionSave = new JLabel();
    JLabel accountSave = new JLabel();

    private LocationListWriter locationListWriter;
    private TransactionHistoryWriter transactionHistoryWriter;
    private AccountWriter accountWriter;

    public SaveGUI() {
        accountWriter = new AccountWriter(ACCOUNT_JSON_STORE);
        transactionHistoryWriter = new TransactionHistoryWriter(TRANSACTION_HISTORY_JSON_STORE);
        locationListWriter = new LocationListWriter(LOCATION_LIST_JSON_STORE);

        windowSetup();
        panel.setLayout(new GridLayout(3, 1));

        locationSave.setText(saveLocationList());
        transactionSave.setText(saveTransactionHistory());
        accountSave.setText(saveAccount());

        frame.add(panel);
        panel.add(locationSave);
        panel.add(transactionSave);
        panel.add(accountSave);
        frame.setTitle("Save");
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // EFFECTS: saves LocationList to file
    private String saveLocationList() {
        try {
            LocationListWriter.open();
            LocationListWriter.writeLocationList(LocationList.accessLocationListAsLocationList());
            LocationListWriter.close();
            return "Saved Locations to " + LOCATION_LIST_JSON_STORE;
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + LOCATION_LIST_JSON_STORE);
        }
        return null;
    }

    // REQUIRES: At least one Transaction in transactionHistory
    // EFFECTS: saves transactionHistory to file
    private String saveTransactionHistory() {
        try {
            TransactionHistoryWriter.open();
            TransactionHistoryWriter.writeTransactionHistory(TransactionList.accessTransactionHistoryAsTranList());
            TransactionHistoryWriter.close();
            return "Saved Transaction History to " + TRANSACTION_HISTORY_JSON_STORE;
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + TRANSACTION_HISTORY_JSON_STORE);
        }
        return null;
    }

    //EFFECTS: saves account to file
    private String saveAccount() {
        try {
            AccountWriter.open();
            AccountWriter.writeAccount(account);
            AccountWriter.close();
            return "Saved Account Information to " + ACCOUNT_JSON_STORE;
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + ACCOUNT_JSON_STORE);
        }
        return null;
    }

}
