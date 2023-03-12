package model.Transactions;

import model.MoneyOutPrimitives.Date;
import model.MoneyOutPrimitives.Location;
import model.Transactions.Helpers.AverageCost;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListOfTransaction {
    public static List<Transaction> transactionHistory = new ArrayList<>();

    public static int countTransactionsOverLastMonth(Date date) {
        return getTransactionsOverLastMonth(date).size();
    }

    public static List<Transaction> getTransactionsOverLastMonth(Date date) {
        Date monthAgo = date.getMonthAgo();
        List<Transaction> lastMonthTransactions = new ArrayList<>();

        for (int i = 0; i < transactionHistory.size(); i ++) {
            Transaction transaction = transactionHistory.get(i);
            if (transaction.afterDate(monthAgo)) {
                lastMonthTransactions.add(transaction);
            }
        }

        return lastMonthTransactions;
    }

    public static Location locateBestShopFor(String good) {
        List<POSPurchase> goodPurchases = getListOfGoodPurchase(good);

        return optimizeLocation(goodPurchases);
    }

    public static List<POSPurchase> getListOfGoodPurchase(String good) {
        List<POSPurchase> listOfGoodPurchase = new ArrayList<>();

        for (int i = 0; i < getPOSPurchaseHistory().size(); i ++) {
            POSPurchase posPurchase = getPOSPurchaseHistory().get(i);
            if (posPurchase.getGood().equals(good)) {
                listOfGoodPurchase.add(posPurchase);
            }
        }
        return listOfGoodPurchase;
    }

    public static Location optimizeLocation(List<POSPurchase> goodPurchases) {
        List<Location> listOfLocation = new ArrayList<>();
        List<AverageCost> listOfAverageCost = new ArrayList<>();

        for (int i = 0; i < goodPurchases.size(); i ++) {
            POSPurchase curPurchase = goodPurchases.get(i);
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

    public static int parseAverageCostList(List<AverageCost> listOfAverageCost) {
        List<Double> parsedListOfAverageCost = new ArrayList<>();

        for (int i = 0; i < listOfAverageCost.size(); i ++) {
            AverageCost current = listOfAverageCost.get(i);
            double average = current.getCost() / current.getQuantity();
            parsedListOfAverageCost.add(average);
        }

        return parsedListOfAverageCost.indexOf(Collections.min(parsedListOfAverageCost));
    }

    public static List<POSPurchase> getPOSPurchaseHistory() {
        List<POSPurchase> posPurchaseHistory = new ArrayList<>();

        for (int i = 0; i < transactionHistory.size(); i ++) {
            Transaction transaction = transactionHistory.get(i);
            String classString = transaction.getClass().toString();
            if (classString.equals("class model.Transactions.POSPurchase")) {
                posPurchaseHistory.add((POSPurchase) transaction);
            }
        }

        return posPurchaseHistory;
    }
}
