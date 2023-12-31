package model.transactions;

import model.moneyoutprimitives.Date;
import model.moneyoutprimitives.Location;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a List of Transactions or a List of a specific Transaction subclass.
 * Instantiates transactionHistory for use in numerous other sections of the program. Each time a new Transaction of
 * any type is instantiated, it is automatically added to transactionHistory.
 * Includes methods for calculating statistics across a variety of different transactions.
 */

public class TransactionList implements Writable {

    private ArrayList<Transaction> list;  //ArrayList<Transaction> parameter for TransactionList Constructor

    //EFFECTS: TransactionList constructor. Contains an ArrayList<Transaction>
    public TransactionList(ArrayList<Transaction> list) {
        this.list = list;
    }

    //Instantiation of Transaction History: A record of all Transactions logged to this application.
    private static TransactionList transactionHistory = new TransactionList(new ArrayList<>());

    //EFFECTS: returns the number of transactions with a date up to a month before the input date.
    public static int countTransactionsOverLastMonth(Date date) {
        return getTransactionsOverLastMonth(date).size();
    }

    //EFFECTS: returns a list of all transactions with a date up to a month before the input date.
    public static List<Transaction> getTransactionsOverLastMonth(Date date) {
        Date monthAgo = date.getMonthAgo();
        List<Transaction> lastMonthTransactions = new ArrayList<>();

        for (int i = 0; i < transactionHistory.list.size(); i++) {
            Transaction transaction = transactionHistory.list.get(i);
            if (transaction.afterDate(monthAgo)) {
                lastMonthTransactions.add(transaction);
            }
        }

        return lastMonthTransactions;
    }

    //REQUIRES: - At least one POSPurchase of good is recorded in transactionHistory.
    //          - Good is in grammatical singular form and properly spelled with proper capitalization (Example)
    //EFFECTS: returns the optimal location for the user to purchase a given item based on their previous
    //         POSPurchase history.
    public static Location locateBestShopFor(String good) {
        List<PosPurchase> goodPurchases = getListOfGoodPurchase(good);

        return optimizeLocation(goodPurchases);
    }

    //REQUIRES: - At least one POSPurchase of good is recorded in transactionHistory.
    //          - Good is in grammatical singular form and properly spelled with proper capitalization (Example)
    //EFFECTS: scans a list of all recorded POSPurchases and returns a list of all the purchases of the item good.
    //Helper function for locateBestShopFor()
    public static List<PosPurchase> getListOfGoodPurchase(String good) {
        List<PosPurchase> listOfGoodPurchase = new ArrayList<>();

        for (int i = 0; i < getPosPurchaseHistory().size(); i++) {
            PosPurchase posPurchase = getPosPurchaseHistory().get(i);
            if (posPurchase.getGood().equals(good)) {
                listOfGoodPurchase.add(posPurchase);
            }
        }
        return listOfGoodPurchase;
    }

    //REQUIRES: goodPurchases is filtered to represent only purchases of a single item good. Though this is not vital
    //          for the function to work properly, it would not represent anything meaningful if we are not comparing
    //          like goods.
    //EFFECTS: Extracts Locations from a list of purchases of a specific good
    //         and calculates the average cost of the good at each location.
    //         Returns the Location at which the average cost of the good is lowest.
    //Helper function for locateBestShopFor()
    public static Location optimizeLocation(List<PosPurchase> goodPurchases) {
        List<Location> listOfLocation = new ArrayList<>();
        List<AverageCost> listOfAverageCost = new ArrayList<>();

        for (int i = 0; i < goodPurchases.size(); i++) {
            PosPurchase curPurchase = goodPurchases.get(i);
            if (listOfLocation.contains(curPurchase.getLocation())) {
                AverageCost corAvCost = listOfAverageCost.get(listOfLocation.indexOf(curPurchase.getLocation()));
                corAvCost.setCost(corAvCost.getCost() + curPurchase.getCost());
                corAvCost.setQuantity(corAvCost.getQuantity() + curPurchase.getQuantity());
            } else {
                listOfLocation.add(curPurchase.getLocation());
                listOfAverageCost.add(new AverageCost(curPurchase.getCost(), curPurchase.getQuantity()));
            }
        }

        int indexOfLocation = parseAverageCostList(listOfAverageCost);
        return listOfLocation.get(indexOfLocation);
    }

    //REQUIRES: listOfAverageCost is not empty.
    //EFFECTS: Lists the average cost of a good by index of Location list in optimizeLocation().
    //         Returns the index of the Location that minimizes the cost of specific good.
    //Helper function for optimizeLocation()
    public static int parseAverageCostList(List<AverageCost> listOfAverageCost) {
        List<Double> parsedListOfAverageCost = new ArrayList<>();

        for (int i = 0; i < listOfAverageCost.size(); i++) {
            AverageCost current = listOfAverageCost.get(i);
            double average = current.getCost() / current.getQuantity();
            parsedListOfAverageCost.add(average);
        }

        return parsedListOfAverageCost.indexOf(Collections.min(parsedListOfAverageCost));
    }

    //EFFECTS: Returns a list of all POSPurchases recorded in transactionHistory.
    public static List<PosPurchase> getPosPurchaseHistory() {
        List<PosPurchase> posPurchaseHistory = new ArrayList<>();

        for (int i = 0; i < transactionHistory.list.size(); i++) {
            Transaction transaction = transactionHistory.list.get(i);
            String classString = transaction.getClass().toString();
            if (classString.equals("class model.transactions.PosPurchase")) {
                posPurchaseHistory.add((PosPurchase) transaction);
            }
        }

        return posPurchaseHistory;
    }

    //EFFECTS: Returns a list of all Investments recorded in transactionHistory.
    public static List<Investment> getInvestmentHistory() {
        List<Investment> investmentHistory = new ArrayList<>();

        for (int i = 0; i < transactionHistory.list.size(); i++) {
            Transaction transaction = transactionHistory.list.get(i);
            String classString = transaction.getClass().toString();
            if (classString.equals("class model.transactions.Investment")) {
                investmentHistory.add((Investment) transaction);
            }
        }

        return investmentHistory;
    }

    //EFFECTS: Returns a list of all E-Transfers recorded in transactionHistory.
    public static List<ETransfer> getETransferHistory() {
        List<ETransfer> etransferHistory = new ArrayList<>();

        for (int i = 0; i < transactionHistory.list.size(); i++) {
            Transaction transaction = transactionHistory.list.get(i);
            String classString = transaction.getClass().toString();
            if (classString.equals("class model.transactions.ETransfer")) {
                etransferHistory.add((ETransfer) transaction);
            }
        }

        return etransferHistory;
    }

    //EFFECTS: returns global transaction history as ArrayList object
    public static List<Transaction> accessTransactionHistory() {
        return transactionHistory.list;
    }

    //EFFECTS: returns global transaction history as TransactionList object
    public static TransactionList accessTransactionHistoryAsTranList() {
        return transactionHistory;
    }

    //EFFECTS: adds a transaction to end of transaction history
    public static void addTransaction(Transaction transaction) {
        transactionHistory.list.add(transaction);
    }

    //MODIFIES: transactionHistory
    //EFFECTS: Wipes Transaction History. Used in testing to refresh transactionHistory for each individual test.
    public static void wipeTransactionHistory() {
        transactionHistory.list.clear();
    }

    //Getter
    public ArrayList<Transaction> getList() {
        return this.list;
    }

    //JSON
    //EFFECTS: Returns global transaction history in JSON format
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("TransactionHistory", transactionsToJson());
        return json;
    }

    // EFFECTS: returns Transactions in transactionHistory as a JSON array
    private JSONArray transactionsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Transaction t : transactionHistory.list) {
            jsonArray.put(t.toJson());
        }

        return jsonArray;
    }
}
