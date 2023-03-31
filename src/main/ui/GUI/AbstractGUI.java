package ui.GUI;

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
    JFrame frame;
    JButton backButton = new JButton("Back");

    public void windowSetup() {
        frame = new JFrame();
        frame.setSize(400, 200);
    }

    public void frameSetup() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Money Out");
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public String generateLocationString(Location location) {
        return location.getName() + ", " + location.getDistrict() + ", " + location.getDistanceFromHome()
                + "km from home.";
    }

    public String generateTransactionString(Transaction transaction) {
        String tranClass = String.valueOf(transaction.getClass());

        return handleTransactionClass(transaction, tranClass);
    }

    //EFFECTS: Generates a String that formats Date to be more easily read by user
    private String parseDate(Date date) {
        return date.getYear() + "/" + date.getMonth() + "/" + date.getDay();
    }

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
