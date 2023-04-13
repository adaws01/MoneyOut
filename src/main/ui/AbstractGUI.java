package ui;

import model.moneyoutprimitives.Date;
import model.moneyoutprimitives.Location;
import model.transactions.ETransfer;
import model.transactions.Investment;
import model.transactions.PosPurchase;
import model.transactions.Transaction;

import javax.swing.*;
import java.awt.*;

import static model.moneyoutprimitives.LocationList.accessLocationList;
import static model.transactions.TransactionList.*;

/**
 * AbstractGUI contains the window setup information for all GUI windows.
 */

public abstract class AbstractGUI {
    JFrame frame;  //Defines the outer container used in any GUI Window
    JButton backButton = new JButton("Back");  //Defines the back button (defined in each individual window)

    //EFFECTS: Instantiates the most basic elements of a GUI window. First GUI code to run in calling any window
    public void windowSetup() {
        frame = new JFrame();
        frame.setSize(400, 200);
    }

    //EFFECTS: The final setup of a GUI window. Sets a default title, places all elements in the window, centers the
    //         window on the screen, and reveals the window.
    public void frameSetup() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Money Out");
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    //EFFECTS: Returns a String containing all of a Location's information written in plain English.
    public String generateLocationString(Location location) {
        return location.getName() + ", " + location.getDistrict() + ", " + location.getDistanceFromHome()
                + "km from home.";
    }

    //EFFECTS: Returns a String containing all of a Transaction's information written in plain English
    public String generateTransactionString(Transaction transaction) {
        String tranClass = String.valueOf(transaction.getClass());

        return handleTransactionClass(transaction, tranClass);
    }

    //EFFECTS: Generates a String that formats a Date to be more easily read by user
    private String parseDate(Date date) {
        return date.getYear() + "/" + date.getMonth() + "/" + date.getDay();
    }

    //EFFECTS: Processes an integer as a Date, then returns a string that formats the Date to be more easily read.
    public String writeDate(int date) {
        Date processedDate = new Date(date);
        return parseDate(processedDate);
    }

    //EFFECTS: Handles selecting String format of Transaction based on subclass
    private String handleTransactionClass(Transaction transaction, String tranClass) {
        if (tranClass.equals("class model.transactions.PosPurchase")) {
            return writePosPurchase((PosPurchase) transaction);
        } else if (tranClass.equals("class model.transactions.Investment")) {
            return writeInvestment((Investment) transaction);
        } else {
            return writeETransfer((ETransfer) transaction);
        }
    }

    //EFFECTS: Generates a String containing a formatted representation of a POSPurchase
    private String writePosPurchase(PosPurchase posPurchase) {
        return "POS PURCHASE \n\tCost: $" + posPurchase.getCost() + ", Date of Purchase: "
                + writeDate(posPurchase.getDate()) + ", Good: " + posPurchase.getGood() + ", Quantity: "
                + posPurchase.getQuantity() + ", Purchased at: " + generateLocationString(posPurchase.getLocation());
    }

    //EFFECTS: Generates a String containing a formatted representation of an Investment
    private String writeInvestment(Investment investment) {
        return "INVESTMENT \n\tCost: $" + investment.getCost() + ", Date of Investment: "
                + writeDate(investment.getDate()) + ", Company: " + investment.getCompany()
                + ", Shares: " + investment.getShares() + ", Domain: " + investment.getDomain() + ".";
    }

    //EFFECTS: Generates a String containing a formatted representation of an ETransfer
    private String writeETransfer(ETransfer etransfer) {
        return "E-TRANSFER \n\tAmount: $" + etransfer.getCost() + ", Date of E-Transfer: "
                + writeDate(etransfer.getDate()) + ", To: " + etransfer.getName() + ".";
    }

    //EFFECTS: Generates a Scroll Pane UI Element containing all Locations in the static locationList. Text is
    //         not overlapping, and scrolls when long enough.
    public JScrollPane generateLocationListScrollPane() {
        JPanel locationListPanel = new JPanel();
        locationListPanel.setLayout(new GridLayout(0, 1));

        for (int i = 0; i <= accessLocationList().size() - 1; i++) {
            JLabel label = new JLabel((i + 1) + ". " + generateLocationString(accessLocationList().get(i)));
            locationListPanel.add(label);
        }

        JScrollPane locationsScrollPane = new JScrollPane();
        locationsScrollPane.setViewportView(locationListPanel);

        return locationsScrollPane;
    }

    //EFFECTS: Generates a Scroll Pane UI Element containing all Transactions in the static transactionHistory list.
    //         Text is not overlapping, and scrolls when long enough.
    public JScrollPane generateTransactionHistoryScrollPane() {
        JPanel transactionHistoryPanel = new JPanel();
        transactionHistoryPanel.setLayout(new GridLayout(0, 1));

        for (int i = 0; i <= accessTransactionHistory().size() - 1; i++) {
            JLabel label = new JLabel((i + 1) + ". " + generateTransactionString(accessTransactionHistory().get(i)));
            transactionHistoryPanel.add(label);
        }

        JScrollPane transactionsScrollPane = new JScrollPane();
        transactionsScrollPane.setViewportView(transactionHistoryPanel);

        return transactionsScrollPane;
    }

    //EFFECTS: Generates a Scroll Pane UI Element containing all PosPurchases in the posPurchaseHistory list.
    //         Text is not overlapping, and scrolls when long enough.
    public JScrollPane generatePosPurchaseHistoryScrollPane() {
        JPanel posPurchaseHistoryPanel = new JPanel();
        posPurchaseHistoryPanel.setLayout(new GridLayout(0, 1));

        for (int i = 0; i <= getPosPurchaseHistory().size() - 1; i++) {
            JLabel label = new JLabel((i + 1) + ". " + generateTransactionString(getPosPurchaseHistory().get(i)));
            posPurchaseHistoryPanel.add(label);
        }

        JScrollPane posPurchasesScrollPane = new JScrollPane();
        posPurchasesScrollPane.setViewportView(posPurchaseHistoryPanel);

        return posPurchasesScrollPane;
    }

    //EFFECTS: Generates a Scroll Pane UI Element containing all Investments in the investmentHistory list.
    //         Text is not overlapping, and scrolls when long enough.
    public JScrollPane generateInvestmentHistoryScrollPane() {
        JPanel investmentHistoryPanel = new JPanel();
        investmentHistoryPanel.setLayout(new GridLayout(0, 1));

        for (int i = 0; i <= getInvestmentHistory().size() - 1; i++) {
            JLabel label = new JLabel((i + 1) + ". " + generateTransactionString(getInvestmentHistory().get(i)));
            investmentHistoryPanel.add(label);
        }

        JScrollPane investmentsScrollPane = new JScrollPane();
        investmentsScrollPane.setViewportView(investmentHistoryPanel);

        return investmentsScrollPane;
    }

    //EFFECTS: Generates a Scroll Pane UI Element containing all ETransfers in the etransferHistory list.
    //         Text is not overlapping, and scrolls when long enough.
    public JScrollPane generateETransferHistoryScrollPane() {
        JPanel etransferHistoryPanel = new JPanel();
        etransferHistoryPanel.setLayout(new GridLayout(0, 1));

        for (int i = 0; i <= getETransferHistory().size() - 1; i++) {
            JLabel label = new JLabel((i + 1) + ". " + generateTransactionString(getETransferHistory().get(i)));
            etransferHistoryPanel.add(label);
        }

        JScrollPane etransferScrollPane = new JScrollPane();
        etransferScrollPane.setViewportView(etransferHistoryPanel);

        return etransferScrollPane;
    }
}
