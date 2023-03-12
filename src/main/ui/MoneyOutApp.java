package ui;

import model.Account;
import model.MoneyOutPrimitives.Date;
import model.MoneyOutPrimitives.Location;
import model.Transactions.ETransfer;
import model.Transactions.Investment;
import model.Transactions.POSPurchase;
import model.Transactions.Transaction;

import java.util.List;
import java.util.Scanner;

public class MoneyOutApp {

    public static Account account;  //User Account to track balance, name, and address. Only one user account instantiated.
    private Scanner input;    //Instance of Java's Scanner library--for tracking console input
    private String step;      //Tracks what menu the user is in and redefines commands accordingly

    //EFFECTS: Runs the App
    public MoneyOutApp() {runApp();}

    //MODIFIES: this
    //EFFECTS: Performs necessary setup to begin
    private void initialize() {
        account = new Account(0, "Xander", Location.HOME_ADDRESS);
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    //EFFECTS: Processes console input (not case-sensitive)
    private void runApp() {
        boolean keepGoing = true;

        initialize();

        callStart();

        while (keepGoing) {
            String command = input.next();   //Tracks user's console inputs
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                commandHandler(command);
            }
        }

        System.out.println("We hope to see you again soon!");

    }

    //MENU INTERFACE --------------------------------------------------------------------------------------------------

    //EFFECTS: Sets up the Start menu
    private void callStart() {    //commands == t, a, l ,s, b
        step = "start";
        System.out.println("Welcome, " + account.getName() + ". How can I help you?");
        System.out.println("\tt -> Manage Transactions");
        System.out.println("\ta -> Manage Account");
        System.out.println("\tl -> Manage Locations");
        System.out.println("\ts -> Statistics & Insights");
        System.out.println("\tq -> Quit");
    }

    //EFFECTS: Sets up the Transactions Menu
    private void callTransaction() {    //commands == l, m, d, v, b
        step = "transaction";
        System.out.println("Transactions");
        System.out.println("\tl -> Log Transaction");
        System.out.println("\tm -> Modify Transaction");
        System.out.println("\td -> Delete Transaction");
        System.out.println("\tv -> View Transaction History");
        printNavigation();
    }

    private void callAccount() {    //commands == a, p, b
        step = "account";
        System.out.println("Your Account");
        System.out.println("\ta -> Account Balance");
        System.out.println("\tp -> Personal Information");
        printNavigation();
    }

    private void callBalance() {    //commands == d, w, b
        step = "balance";
        System.out.println("Your Account Balance is: " + account.getBalance());
        System.out.println("\td -> Deposit");
        System.out.println("\tw -> Withdraw");
        printNavigation();
    }

    private void callLocations() {    //commands == n, m, l, b
        step = "location";
        System.out.println("Locations");
        System.out.println("\tn -> New Location");
        System.out.println("\tm -> Modify Existing Location");
        System.out.println("\tl -> View All Recorded Locations");
        printNavigation();
    }

    private void callPersonalInfo() {    //commands == n, a, b
        step = "personal info";
        System.out.println("Personal Information");
        System.out.println(" ");
        System.out.println("Name: " + account.getName());
        System.out.println("Address on File: " + account.getAddress().getName());
        System.out.println(" ");
        System.out.println("\tn -> Change Name on File");
        System.out.println("\ta -> Modify Address");
        printNavigation();
    }

    //INPUT INTERFACE -------------------------------------------------------------------------------------------------

    /* IDEA for deposit and withdraw
     *  If the amount input for deposit/withdrawal is below zero, we need to throw an exception from within the account
     *  class. That exception needs to be caught in the callDeposit() method in the form of a console input which
     *  requests reentry of a value above or equal to zero.
     *  This is to prevent people from inputting negative values for deposit.
     *  Similar behaviour will need to be created for the withdrawal class.
     *
     *  We also need to force only two decimal places of input. Currently we can enter any decimal value.
     *  This is not the behaviour we are looking for. The above failure handling mechanism should
     *  also include this.
     */
    private void callDeposit() {
        step = "deposit";
        System.out.println("Input Deposit Amount:");

        double amount = Double.parseDouble(input.next());
        account.deposit(amount);
        callBalance();
    }

    private void callWithdraw() {
        System.out.println("Input Withdrawal Amount:");

        double amount = Double.parseDouble(input.next());
        account.withdraw(amount);
        callBalance();
    }

    private void logNewLocation() {
        String name = getStringAndPrint("Provide Location Name or Address:");
        String district = getStringAndPrint("Provide Location District:");
        System.out.println("Provide Distance from Home Address:");
        int distanceFromHome = Integer.parseInt(input.next());

        Location location = new Location(name, district, distanceFromHome);
        System.out.println("You have recorded...");
        printLocation(location);
        callLocations();
    }

    private void logNewTransaction() {
        System.out.println("Select Transaction Type:");
        System.out.println("\tp -> POS Purchase");
        System.out.println("\ti -> Investment");
        System.out.println("\te -> E-Transfer");
        String selection = input.next();

        selectNewTransaction(selection);
    }

    private void selectNewTransaction(String selection) {
        if (selection.equals("p")) {
            System.out.println("POS PURCHASE");
            logNewPOSPurchase();
        } else if (selection.equals("i")) {
            System.out.println("INVESTMENT");
            logNewInvestment();
        } else if (selection.equals("e")) {
            System.out.println("E-TRANSFER");
            logNewETransfer();
        } else {
            invalidCommand();
            logNewTransaction();
        }
    }

    //TODO: Organize logNew Methods and Helpers (the entire newTransaction section is a mess right now)

    private void logNewPOSPurchase() {
        double cost = getDoubleAndPrint("Input Cost ($):");
        Date processedDate = getDateAndPrint("Input Date of Purchase (YYYYMMDD):");
        String good = getStringAndPrint("Input Good Purchased:");
        int quantity = getIntAndPrint("Input Quantity Purchased");
        Location location = getLocationAndPrint("Select Location of Purchase:");
        POSPurchase purchase = new POSPurchase(cost, processedDate, good, quantity, location);
        logReturnNewTransaction(purchase);
    }

    private void logNewInvestment() {
        double cost = getDoubleAndPrint("Input Investment Amount ($):");
        Date processedDate = getDateAndPrint("Input Date of Investment (YYYYMMDD):");
        String company = getStringAndPrint("Input Company:");
        int shares = getIntAndPrint("Input Shares Bought:");
        String domain = getStringAndPrint("Input Domain of Investment (Ex. Technology):");
        Investment investment = new Investment(cost, processedDate, company, shares, domain);
        logReturnNewTransaction(investment);
    }

    private void logNewETransfer() {
        double amount = getDoubleAndPrint("Input Amount Transferred ($):");
        Date processedDate = getDateAndPrint("Input Date Transferred (YYYYMMDD):");
        String name = getStringAndPrint("Input Transferred to:");
        ETransfer eTransfer = new ETransfer(amount, processedDate, name);
        logReturnNewTransaction(eTransfer);
    }

    private void logReturnNewTransaction(Transaction transaction) {
        System.out.println("You have recorded...");
        printTransaction(transaction);
        callTransaction();
    }

    private double getDoubleAndPrint(String cOut) {
        System.out.println(cOut);
        double d = Double.parseDouble(input.next());
        return d;
    }

    private Date getDateAndPrint(String cOut) {
        System.out.println(cOut);
        String date = input.next();
        Date processedDate = new Date(Integer.parseInt(date));    //Failure Handling here for if date input wrong
        String parsedDate = parseDate(processedDate);
        System.out.println(parsedDate);
        return processedDate;
    }

    private String parseDate(Date date) {
        return date.getYear() + "/" + date.getMonth() + "/" + date.getDay();
    }

    private String getStringAndPrint(String cOut) {
        System.out.println(cOut);
        String string = input.next();
        return string;
    }

    private int getIntAndPrint(String cOut) {
        System.out.println(cOut);
        int i = Integer.parseInt(input.next());
        return i;
    }

    private Location getLocationAndPrint(String cOut) {
        System.out.println(cOut);
        printListLocation();
        int i = Integer.parseInt(input.next());
        Location location = accessLocationList().get(i - 1);
        return location;
    }

    private void modifyTransaction() {
        System.out.println("Select Transaction to Modify");
        printTransactionHistory();
        System.out.println("Input Index # of Transaction to Modify:");
        int index = (Integer.parseInt(input.next()) - 1);

        System.out.println("You have selected to modify...");
        Transaction transaction = accessTransactionHistory().get(index);
        printTransaction(transaction);

        transactionModifyHandler(transaction);
    }

    private void transactionModifyHandler(Transaction transaction) {
        String tranClass = String.valueOf(transaction.getClass());
        if (tranClass.equals( "class model.Transactions.POSPurchase")) {
            modifyPOSPurchase((POSPurchase) transaction);
        } else if (tranClass.equals("class model.Transactions.Investment")) {
            modifyInvestment((Investment) transaction);
        } else {
            modifyETransfer((ETransfer) transaction);
        }
    }

    private void modifyPOSPurchase(POSPurchase purchase) {
        double cost = getDoubleAndPrint("Input Cost ($):");
        purchase.setCost(cost);
        Date processedDate = getDateAndPrint("Input Date of Purchase (YYYYMMDD):");
        purchase.setDate(processedDate);
        String good = getStringAndPrint("Input Good Purchased:");
        purchase.setGood(good);
        int quantity = getIntAndPrint("Input Quantity Purchased");
        purchase.setQuantity(quantity);
        Location location = getLocationAndPrint("Select Location of Purchase:");
        purchase.setLocation(location);
        logReturnNewTransaction(purchase);
    }

    private void modifyInvestment(Investment investment) {
        double cost = getDoubleAndPrint("Input Investment Amount ($):");
        investment.setCost(cost);
        Date processedDate = getDateAndPrint("Input Date of Investment (YYYYMMDD):");
        investment.setDate(processedDate);
        String company = getStringAndPrint("Input Company:");
        investment.setCompany(company);
        int shares = getIntAndPrint("Input Shares Bought:");
        investment.setShares(shares);
        String domain = getStringAndPrint("Input Domain of Investment (Ex. Technology):");
        investment.setDomain(domain);
        logReturnNewTransaction(investment);
    }

    private void modifyETransfer(ETransfer eTransfer) {
        double amount = getDoubleAndPrint("Input Amount Transferred ($):");
        eTransfer.setCost(amount);
        Date processedDate = getDateAndPrint("Input Date Transferred (YYYYMMDD):");
        eTransfer.setDate(processedDate);
        String name = getStringAndPrint("Input Transferred to:");
        eTransfer.setName(name);
        logReturnNewTransaction(eTransfer);
    }

    private void deleteTransaction() {
        System.out.println("Select Transaction to Delete");
        printTransactionHistory();
        System.out.println("Input Index # of Transaction to Delete:");
        int index = (Integer.parseInt(input.next()) - 1);

        accessTransactionHistory().remove(index);
        System.out.println("Deleted Transaction #" + (index + 1) + ".");
        callTransaction();
    }

    private void modifyLocationList() {
        System.out.println("Choose one of the following...");
        printListLocation();
        System.out.println(" ");
        System.out.println("Input #:");

        int i = Integer.parseInt(input.next());
        Location location = accessLocationList().get(i - 1);

        modifyLocation(location);

        callLocations();
    }

    private void modifyLocation(Location location) {
        System.out.println("You have selected to Modify:");
        printLocation(location);
        System.out.println(" ");

        String name = getStringAndPrint("Enter Location Name/Address (re-enter if maintaining)");
        location.setName(name);

        String district = getStringAndPrint("Enter Location District (re-enter if maintaining)");
        location.setDistrict(district);

        System.out.println("Enter Location Distance from Home Address (re-enter if maintaining)");
        int distanceFromHome = Integer.parseInt(input.next());
        location.setDistanceFromHome(distanceFromHome);

        System.out.println("Your updated Location is:");
        printLocation(location);
        System.out.println(" ");
    }

    private void changeNameOnAccount() {
        step = "change name";
        String name = getStringAndPrint("Input Preferred Name:");
        account.setName(name);
        callPersonalInfo();
    }

    private void printNavigation() {
        System.out.println("\tb -> Back");
        System.out.println("\tq -> Quit");
    }

    private String writeLocation(Location location) {
        return location.getName() + ", " + location.getDistrict() + ", " + location.getDistanceFromHome() +
                "km from home.";
    }

    private void printLocation(Location location) {
        System.out.println(writeLocation(location));
    }

    private void printListLocation() {
        for (int i = 0; i <= accessLocationList().size() - 1; i++) {
            System.out.println((i + 1) + ". " + writeLocation(accessLocationList().get(i)));
        }
    }

    private String writeDate(int date) {
        Date processedDate = new Date(date);
        return parseDate(processedDate);
    }

    private String writeTransaction(Transaction transaction) {
        String tranClass = String.valueOf(transaction.getClass());

        return handleTransactionClass(transaction, tranClass);
    }

    private String handleTransactionClass(Transaction transaction, String tranClass) {
        if (tranClass.equals("class model.Transactions.POSPurchase")) {
            return writePOSPurchase((POSPurchase) transaction);
        } else if (tranClass.equals("class model.Transactions.Investment")) {
            return writeInvestment((Investment) transaction);
        } else {
            return writeETransfer((ETransfer) transaction);
        }
    }

    private String writePOSPurchase(POSPurchase posPurchase) {
        return "POS PURCHASE \n\tCost: $" + posPurchase.getCost() + ", Date of Purchase: " +
                writeDate(posPurchase.getDate()) + ", Good: " + posPurchase.getGood() + ", Quantity: " +
                posPurchase.getQuantity() + ", Purchased at: " + writeLocation(posPurchase.getLocation());
    }

    private String writeInvestment(Investment investment) {
        return "INVESTMENT \n\tCost: $" + investment.getCost() + ", Date of Investment: " +
                writeDate(investment.getDate()) + ", Company: " + investment.getCompany() +
                ", Shares: " + investment.getShares() + ", Domain: " + investment.getDomain() + ".";
    }

    private String writeETransfer(ETransfer eTransfer) {
        return "E-TRANSFER \n\tAmount: $" + eTransfer.getCost() + ", Date of E-Transfer: " +
                writeDate(eTransfer.getDate()) + ", To: " + eTransfer.getName() + ".";
    }

    private void printTransaction(Transaction transaction) {
        System.out.println(writeTransaction(transaction));
    }

    private void printTransactionHistory() {
        for (int i = 0; i <= accessTransactionHistory().size() - 1; i++) {
            System.out.println((i + 1) + ". " + writeTransaction(accessTransactionHistory().get(i)));
        }
    }

    //MENU CONTROLS ---------------------------------------------------------------------------------------------------

    //EFFECTS: input command handling for all possible inputs at any stage of the application
    private void commandHandler(String command) {
        if (command.equals("a")) {
            stepHandlerA();
        } else if (command.equals("b")) {
            stepHandlerB();
        } else if (command.equals("d")) {
            stepHandlerD();
        } else if (command.equals("l")) {
            stepHandlerL();
        } else if (command.equals("m")) {
            stepHandlerM();
        } else if (command.equals("n")) {
            stepHandlerN();
        } else if (command.equals("p")) {
            stepHandlerP();
        } else if (command.equals("s")) {
            stepHandlerS();
        } else if (command.equals("t")) {
            stepHandlerT();
        } else if (command.equals("v")) {
            stepHandlerV();
        } else if (command.equals("w")) {
            stepHandlerW();
        } else {
            invalidCommand();
        }
    }

    private void invalidCommand() {
        System.out.println("Invalid command. Please try again!");
    }
    private void constructionCommand() {
        System.out.println("This Feature is under construction!");
    }

    //EFFECTS: Helper Methods for the commandHandler() method that handle menus and redefining input commands
    private void stepHandlerA() {
        if (step.equals("start")) {
            callAccount();
        } else if (step.equals("account")) {
            callBalance();
        } else if (step.equals("personal info")) {
            modifyLocation(Location.HOME_ADDRESS);             //we need location entry interface
            callPersonalInfo();
        } else {
            invalidCommand();
        }
    }
    private void stepHandlerB() {
        if (step.equals("transaction") || step.equals("account") || step.equals("location")) {
            callStart();
        } else if (step.equals("balance") || step.equals("personal info")) {
            callAccount();
        } else if (step.equals("deposit")) {
            callBalance();
        } else {
            invalidCommand();
        }
    }
    private void stepHandlerD() {
        if (step.equals("transaction")) {
            deleteTransaction();
        } else if (step.equals("balance")) {
            callDeposit();
        } else {
            invalidCommand();
        }
    }
    private void stepHandlerL() {
        if (step.equals("start")) {
            callLocations();
        } else if (step.equals("transaction")) {
            logNewTransaction();
        } else if (step.equals("location")) {
            System.out.println("Locations on File:");
            printListLocation();
            System.out.println(" ");
            callLocations();
        } else {
            invalidCommand();
        }
    }
    private void stepHandlerM() {
        if (step.equals("transaction")) {
            modifyTransaction();
        } else if (step.equals("location")) {
            modifyLocationList();
        } else {
            invalidCommand();
        }
    }
    private void stepHandlerN() {
        if (step.equals("location")) {
            logNewLocation();                    //process that will return to location menu
        } else if (step.equals("personal info")) {
            changeNameOnAccount();
        } else {
            invalidCommand();
        }
    }
    private void stepHandlerP() {
        if (step.equals("account")) {
            callPersonalInfo();
        } else {
            invalidCommand();
        }
    }
    private void stepHandlerS() {
        if (step.equals("start")) {
            //TODO callStatsInsights             //separate menu
            constructionCommand(); //stub
        } else {
            invalidCommand();
        }
    }
    private void stepHandlerT() {
        if (step.equals("start")) {
            callTransaction();
        } else {
            invalidCommand();
        }
    }
    private void stepHandlerV() {
        if (step.equals("transaction")) {
            System.out.println("Transaction History");
            printTransactionHistory();
            callTransaction();
        } else {
            invalidCommand();
        }
    }
    private void stepHandlerW() {
        if (step.equals("balance")) {
            callWithdraw();
        } else {
            invalidCommand();
        }
    }

    //LIST METHODS ----------------------------------------------------------------------------------------------------

    private List<Location> accessLocationList() {
        return Location.locationList;
    }

    private List<Transaction> accessTransactionHistory() {return Transaction.transactionHistory;}

}
