package ui;

import model.Account;
import model.moneyoutprimitives.Date;
import model.moneyoutprimitives.Location;
import model.moneyoutprimitives.LocationList;
import model.transactions.*;
import persistence.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

//import static model.Account.account;

/**
 * This class manages all the functionality of a Console based UI for the MoneyOut application.
 * Instantiate a new ConsoleMoneyOutApp in the Main class to launch the console based application
 * Contains all Console Menus, and methods requiring user inputs.
 * Makes calls that modify or manipulate the lists of data recorded in the model package.
 */

public class ConsoleMoneyOutApp {

    /**
     * SETUP --------------------------------------------------------------------------------------------------------
     */
    // Methods run at start that initialize the functionality of the Console based app.

    private Scanner input;    //Instance of Java's Scanner library--for tracking console input
    private String step;      //Tracks what menu the user is in and redefines commands accordingly
    Account account = Account.accessAccount(); //Accessor for account
    private static final String ACCOUNT_JSON_STORE = "./data/account.json";
    private static final String TRANSACTION_HISTORY_JSON_STORE = "./data/transactionHistory.json";
    private static final String LOCATION_LIST_JSON_STORE = "./data/locationList.json";
    private LocationListWriter locationListWriter;
    private TransactionHistoryWriter transactionHistoryWriter;
    private AccountWriter accountWriter;
    private AccountReader accountReader;
    private LocationListReader locationListReader;
    private TransactionHistoryReader transactionHistoryReader;

    //EFFECTS: Runs the App
    public ConsoleMoneyOutApp() throws FileNotFoundException {
        runApp();
    }

    //MODIFIES: this
    //EFFECTS: Performs necessary setup to begin
    private void initialize() {
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        accountWriter = new AccountWriter(ACCOUNT_JSON_STORE);
        transactionHistoryWriter = new TransactionHistoryWriter(TRANSACTION_HISTORY_JSON_STORE);
        locationListWriter = new LocationListWriter(LOCATION_LIST_JSON_STORE);
        accountReader = new AccountReader(ACCOUNT_JSON_STORE);
        locationListReader = new LocationListReader(LOCATION_LIST_JSON_STORE);
        transactionHistoryReader = new TransactionHistoryReader(TRANSACTION_HISTORY_JSON_STORE);
    }

    //EFFECTS: Processes console input (not case-sensitive). Directly handles Quit. Else: calls MENU CONTROL methods.
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

    /**
     * CONSOLE INTERFACE --------------------------------------------------------------------------------------------
     */
    // Methods called to return the user to sections of the application's main menu

    //MODIFIES: step
    //EFFECTS: Opens the Main menu
    private void callStart() {    //commands == t, a, v ,i, b
        step = "start";
        System.out.println("Welcome, " + account.getName() + ". How can I help you?");
        System.out.println("\tt -> Manage Transactions");
        System.out.println("\ta -> Manage Account");
        System.out.println("\tv -> Manage Locations");
        System.out.println("\ti -> Statistics & Insights");
        System.out.println("\ts -> Save to File");
        System.out.println("\tl -> Load from File");
        System.out.println("\tq -> Quit");
    }

    //MODIFIES: step
    //EFFECTS: Opens the Transactions Menu
    private void callTransaction() {    //commands == l, m, d, v, b
        step = "transaction";
        System.out.println("Transactions");
        System.out.println("\tl -> Log Transaction");
        System.out.println("\tm -> Modify Transaction");
        System.out.println("\td -> Delete Transaction");
        System.out.println("\tv -> View Transaction History");
        printNavigation();
    }

    //MODIFIES: step
    //EFFECTS: Opens the Account Menu
    private void callAccount() {    //commands == a, p, b
        step = "account";
        System.out.println("Your Account");
        System.out.println("\ta -> Account Balance");
        System.out.println("\tp -> Personal Information");
        printNavigation();
    }

    //MODIFIES: step
    //EFFECTS: Opens the Statistics and Insights Menu
    private void callStatsInsights() { //commands == c, f, b
        step = "stats insights";
        System.out.println("Statistics and Insights");
        System.out.println("\tc -> Count Transactions over the last month");
        System.out.println("\tf -> Find Best Shop to Purchase a Good");
        printNavigation();
    }

    //MODIFIES: step
    //EFFECTS: Opens the Account Balance Menu
    private void callBalance() {    //commands == d, w, b
        step = "balance";
        System.out.println("Your Account Balance is: " + account.getBalance());
        System.out.println("\td -> Deposit");
        System.out.println("\tw -> Withdraw");
        printNavigation();
    }

    //MODIFIES: step
    //EFFECTS: Opens the Locations Menu
    private void callLocations() {    //commands == n, m, l, b
        step = "location";
        System.out.println("Locations");
        System.out.println("\tn -> New Location");
        System.out.println("\tm -> Modify Existing Location");
        System.out.println("\tl -> View All Recorded Locations");
        printNavigation();
    }

    //MODIFIES: step
    //EFFECTS: Opens the Personal Information Menu
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

    /**
     * INPUT INTERFACE ----------------------------------------------------------------------------------------------
     */
    // Methods called to request user input, modify data within the model package

    //MODIFIES: step, account
    //EFFECTS: Facilitates making a deposit to account (increase account balance). Returns to Balance Menu
    private void callDeposit() {
        step = "deposit";
        System.out.println("Input Deposit Amount:");

        double amount = Double.parseDouble(input.next());
        account.deposit(amount);
        callBalance();
    }

    //MODIFIES: account
    //EFFECTS: Facilitates making a withdrawal from account (decrease account balance without a transaction).
    //         Returns to Balance Menu
    private void callWithdraw() {
        System.out.println("Input Withdrawal Amount:");

        double amount = Double.parseDouble(input.next());
        account.withdraw(amount);
        callBalance();
    }

    //REQUIRES: good entered exists within transactionHistory and matches spelling and case of recorded instances.
    //EFFECTS: Prints a statement telling user at which location they should shop for an input item to minimize spending
    //         Returns to Statistics and Insights Menu
    private void optimizePurchaseByLocation() {
        System.out.println("Find Best Shop to Purchase a Good");
        System.out.println("\tEnter an item:");
        String good = input.next();
        Location bestShop = TransactionList.locateBestShopFor(good);
        System.out.println("You should opt to shop for " + good + " at :");
        printLocation(bestShop);
        callStatsInsights();
    }

    //EFFECTS: Prints a list of all transactions recorded up to a month before the input date.
    //         Prints the number of transactions recorded up to a month before the input date.
    //         Returns to Statistics and Insights Menu
    private void transactionsLastMonth() {
        System.out.println("Transactions Last Month");
        System.out.println("\tInput Today's Date:");
        Date today = new Date(Integer.parseInt(input.next()));
        System.out.println("Transactions Last Month");
        printTransactionList(TransactionList.getTransactionsOverLastMonth(today));
        System.out.println("You recorded " + TransactionList.countTransactionsOverLastMonth(today)
                + " transactions over the last month.");
        callStatsInsights();
    }

    //EFFECTS: Records a new Location from user input
    //         Reads the newly recorded Location back to user
    //         Returns to Locations Menu
    private void logNewLocation() {
        String name = getStringAndPrint("Provide Location Name or Address:");
        String district = getStringAndPrint("Provide Location District:");
        System.out.println("Provide Distance from Home Address:");
        int distanceFromHome = Integer.parseInt(input.next());

        Location location = new Location(name, district, distanceFromHome);
        accessLocationList().add(location);
        System.out.println("You have recorded...");
        printLocation(location);
        callLocations();
    }

    //EFFECTS: Asks user to select transaction subclass
    //         Records new transaction from user input
    private void logNewTransaction() {
        System.out.println("Select Transaction Type:");
        System.out.println("\tp -> POS Purchase");
        System.out.println("\ti -> Investment");
        System.out.println("\te -> E-Transfer");
        String selection = input.next();

        selectNewTransaction(selection);
    }

    //EFFECTS: Records a new POSPurchase from user input
    //         Reads the newly recorded POSPurchase back to user
    private void logNewPosPurchase() {
        double cost = getDoubleAndPrint("Input Cost ($):");
        Date processedDate = getDateAndPrint("Input Date of Purchase (YYYYMMDD):");
        String good = getStringAndPrint("Input Good Purchased:");
        int quantity = getIntAndPrint("Input Quantity Purchased");
        Location location = getLocationAndPrint("Select Location of Purchase:");
        PosPurchase purchase = new PosPurchase(cost, processedDate, good, quantity, location);
        TransactionList.accessTransactionHistory().add(purchase);
        Account.accessAccount().setBalance(Account.accessAccount().getBalance() - cost);
        logReturnNewTransaction(purchase);
    }

    //EFFECTS: Records a new Investment from user input
    //         Reads the newly recorded Investment back to user
    private void logNewInvestment() {
        double cost = getDoubleAndPrint("Input Investment Amount ($):");
        Date processedDate = getDateAndPrint("Input Date of Investment (YYYYMMDD):");
        String company = getStringAndPrint("Input Company:");
        int shares = getIntAndPrint("Input Shares Bought:");
        String domain = getStringAndPrint("Input Domain of Investment (Ex. Technology):");
        Investment investment = new Investment(cost, processedDate, company, shares, domain);
        TransactionList.accessTransactionHistory().add(investment);
        Account.accessAccount().setBalance(Account.accessAccount().getBalance() - cost);
        logReturnNewTransaction(investment);
    }

    //EFFECTS: Records a new ETransfer from user input
    //         Reads the newly recorded ETransfer back to user
    private void logNewETransfer() {
        double amount = getDoubleAndPrint("Input Amount Transferred ($):");
        Date processedDate = getDateAndPrint("Input Date Transferred (YYYYMMDD):");
        String name = getStringAndPrint("Input Transferred to:");
        ETransfer etransfer = new ETransfer(amount, processedDate, name);
        TransactionList.accessTransactionHistory().add(etransfer);
        Account.accessAccount().setBalance(Account.accessAccount().getBalance() - amount);
        logReturnNewTransaction(etransfer);
    }

    //EFFECTS: Lists transactionHistory and prompts user to select Transaction to modify
    //         updates Transaction information in transactionHistory
    private void modifyTransaction() {
        System.out.println("Select Transaction to Modify");
        printTransactionList(accessTransactionHistory());
        System.out.println("Input Index # of Transaction to Modify:");
        int index = (Integer.parseInt(input.next()) - 1);

        System.out.println("You have selected to modify...");
        Transaction transaction = accessTransactionHistory().get(index);
        printTransaction(transaction);

        transactionModifyHandler(transaction);
    }

    //EFFECTS: Updates a POSPurchase in transactionHistory based on user input
    private void modifyPosPurchase(PosPurchase purchase) {
        double oldCost = purchase.getCost();
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
        Account.accessAccount().setBalance(Account.accessAccount().getBalance() + oldCost - cost);
        logReturnNewTransaction(purchase);
    }

    //EFFECTS: Updates an Investment in transactionHistory based on user input
    private void modifyInvestment(Investment investment) {
        double oldCost = investment.getCost();
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
        Account.accessAccount().setBalance(Account.accessAccount().getBalance() + oldCost - cost);
        logReturnNewTransaction(investment);
    }

    //EFFECTS: Updates an ETransfer in transactionHistory based on user input
    private void modifyETransfer(ETransfer etransfer) {
        double oldCost = etransfer.getCost();
        double amount = getDoubleAndPrint("Input Amount Transferred ($):");
        etransfer.setCost(amount);
        Date processedDate = getDateAndPrint("Input Date Transferred (YYYYMMDD):");
        etransfer.setDate(processedDate);
        String name = getStringAndPrint("Input Transferred to:");
        etransfer.setName(name);
        Account.accessAccount().setBalance(Account.accessAccount().getBalance() + oldCost - amount);
        logReturnNewTransaction(etransfer);
    }

    //EFFECTS: Lists transactionHistory and prompts user to select Transaction to delete
    //         removes Transaction from transactionHistory
    private void deleteTransaction() {
        System.out.println("Select Transaction to Delete");
        printTransactionList(accessTransactionHistory());
        System.out.println("Input Index # of Transaction to Delete:");
        int index = (Integer.parseInt(input.next()) - 1);

        accessTransactionHistory().remove(index);
        System.out.println("Deleted Transaction #" + (index + 1) + ".");
        callTransaction();
    }

    //EFFECTS: Lists locationList and prompts user to select Location to modify
    //         calls modifyLocation() method to handle input
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

    //MODIFIES: location
    //EFFECTS: updates Location information in locationList based on user input
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

    //MODIFIES account
    //EFFECTS: updates name on account based on user input
    private void changeNameOnAccount() {
        step = "change name";
        String name = getStringAndPrint("Input Preferred Name:");
        account.setName(name);
        callPersonalInfo();
    }

    /**
     * INPUT HELPER FUNCTIONS (by category) -------------------------------------------------------------------------
     */
    // Methods that facilitate the functionality of processing and outputting data.

    //PROCESS DATA METHODS

    //REQUIRES: User input is a real number
    //EFFECTS: Abstract Helper Method. Prints double request statement and processes user input
    private double getDoubleAndPrint(String cout) {
        System.out.println(cout);
        double d = Double.parseDouble(input.next());
        return d;
    }

    //REQUIRES: User input is consistent with format of Date class
    //EFFECTS: Abstract Helper Method. Prints Date request statement and processes user input
    private Date getDateAndPrint(String cout) {
        System.out.println(cout);
        String date = input.next();
        Date processedDate = new Date(Integer.parseInt(date));
        String parsedDate = parseDate(processedDate);
        System.out.println(parsedDate);
        return processedDate;
    }

    //EFFECTS: Generates a String that formats Date to be more easily read by user
    private String parseDate(Date date) {
        return date.getYear() + "/" + date.getMonth() + "/" + date.getDay();
    }

    //EFFECTS: Abstract Helper Method. Prints String request statement and processes user input
    private String getStringAndPrint(String cout) {
        System.out.println(cout);
        String string = input.next();
        return string;
    }

    //REQUIRES: User input is an integer
    //EFFECTS: Abstract Helper Method. Prints int request statement and processes user input
    private int getIntAndPrint(String cout) {
        System.out.println(cout);
        int i = Integer.parseInt(input.next());
        return i;
    }

    //REQUIRES: User input is an integer within the range of 0 to locationList.size() - 1
    //EFFECTS: Abstract Helper Method. Prints locationList and index request statement and processes user input
    private Location getLocationAndPrint(String cout) {
        System.out.println(cout);
        printListLocation();
        int i = Integer.parseInt(input.next());
        Location location = accessLocationList().get(i - 1);
        return location;
    }

    //EFFECTS: Handles calling of the correct method based on the subclass of the input Transaction
    private void transactionModifyHandler(Transaction transaction) {
        String tranClass = String.valueOf(transaction.getClass());
        if (tranClass.equals("class model.transactions.PosPurchase")) {
            modifyPosPurchase((PosPurchase) transaction);
        } else if (tranClass.equals("class model.transactions.Investment")) {
            modifyInvestment((Investment) transaction);
        } else {
            modifyETransfer((ETransfer) transaction);
        }
    }

    //EFFECTS: Handles calling of the correct method based on user input regarding Transaction subclass
    private void selectNewTransaction(String selection) {
        if (selection.equals("p")) {
            System.out.println("POS PURCHASE");
            logNewPosPurchase();
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

    //OUTPUT DATA METHODS

    //EFFECTS: Prints back and quit statements prompting user input
    private void printNavigation() {
        System.out.println("\tb -> Back");
        System.out.println("\tq -> Quit");
    }

    //EFFECTS: Generates a String containing a formatted representation of a Location
    private String writeLocation(Location location) {
        return location.getName() + ", " + location.getDistrict() + ", " + location.getDistanceFromHome()
                + "km from home.";
    }

    //EFFECTS: Prints a formatted representation of a Location
    private void printLocation(Location location) {
        System.out.println(writeLocation(location));
    }

    //EFFECTS: Prints a formatted list of Location Strings
    private void printListLocation() {
        for (int i = 0; i <= accessLocationList().size() - 1; i++) {
            System.out.println((i + 1) + ". " + writeLocation(accessLocationList().get(i)));
        }
    }

    //EFFECTS: Generates a String containing a formatted representation of a Date
    private String writeDate(int date) {
        Date processedDate = new Date(date);
        return parseDate(processedDate);
    }

    //EFFECTS: Makes a call to handleTransactionClass()
    private String writeTransaction(Transaction transaction) {
        String tranClass = String.valueOf(transaction.getClass());

        return handleTransactionClass(transaction, tranClass);
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
                + posPurchase.getQuantity() + ", Purchased at: " + writeLocation(posPurchase.getLocation());
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

    //EFFECTS: Prints a formatted String representation of a Transaction to console, based on transaction subclass
    private void printTransaction(Transaction transaction) {
        System.out.println(writeTransaction(transaction));
    }

    //EFFECTS: Prints a list of formatted Transactions to console.
    private void printTransactionList(List<Transaction> transactionList) {
        for (int i = 0; i <= transactionList.size() - 1; i++) {
            System.out.println((i + 1) + ". " + writeTransaction(transactionList.get(i)));
        }
    }

    //EFFECTS: Reads a Transaction that was recently recorded back to the user
    private void logReturnNewTransaction(Transaction transaction) {
        System.out.println("You have recorded...");
        printTransaction(transaction);
        callTransaction();
    }


    /**
     * MENU CONTROL -------------------------------------------------------------------------------------------------
     */
    // Facilitates the menus and updates input controls based on where in the application the user is.

    //EFFECTS: input command handling for all inputs at any stage of the application
    private void commandHandler(String command) {
        if (command.equals("a")) {
            stepHandlerA();
        } else if (command.equals("b")) {
            stepHandlerB();
        } else if (command.equals("c")) {
            stepHandlerC();
        } else if (command.equals("d")) {
            stepHandlerD();
        } else if (command.equals("f")) {
            stepHandlerF();
        } else if (command.equals("i")) {
            stepHandlerI();
        } else if (command.equals("l")) {
            stepHandlerL();
        } else {
            commandHandlerContinued(command);
        }
    }

    //EFFECTS: input command handling for all inputs m through z at any stage of the application
    private void commandHandlerContinued(String command) {
        if (command.equals("m")) {
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

    //EFFECTS: Prints a statement prompting the user to try a different console input
    private void invalidCommand() {
        System.out.println("Invalid command. Please try again!");
    }

    //EFFECTS: Helper Methods for the commandHandler() method that handle menus and redefining input commands
    //         Each Helper Method corresponds to a single key input. The input's behaviour is redefined depending on
    //         what menu the user is currently at (bottom of the console).
    private void stepHandlerA() {
        if (step.equals("start")) {
            callAccount();
        } else if (step.equals("account")) {
            callBalance();
        } else if (step.equals("personal info")) {
            modifyLocation(Location.accessHomeAddress());             //we need location entry interface
            callPersonalInfo();
        } else {
            invalidCommand();
        }
    }

    private void stepHandlerB() {
        if (step.equals("transaction") || step.equals("account")
                || step.equals("stats insights") || step.equals("location")) {
            callStart();
        } else if (step.equals("balance") || step.equals("personal info")) {
            callAccount();
        } else if (step.equals("deposit")) {
            callBalance();
        } else {
            invalidCommand();
        }
    }

    private void stepHandlerC() {
        if (step.equals("stats insights")) {
            transactionsLastMonth();
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

    private void stepHandlerF() {
        if (step.equals("stats insights")) {
            optimizePurchaseByLocation();
        } else {
            invalidCommand();
        }
    }

    private void stepHandlerI() {
        if (step.equals("start")) {
            callStatsInsights();
        } else {
            invalidCommand();
        }
    }

    private void stepHandlerL() {
        if (step.equals("start")) {
            loadAccount();
            loadLocationList();
            loadTransactionHistory();
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
            saveLocationList();
            saveTransactionHistory();
            saveAccount();
            callStart();
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
        if (step.equals("start")) {
            callLocations();
        } else if (step.equals("transaction")) {
            System.out.println("Transaction History");
            printTransactionList(accessTransactionHistory());
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

    /**
     * ACCESSOR METHODS ---------------------------------------------------------------------------------------------
     */
    // Provides access to the lists of data in the model package

    //EFFECTS: Accessor Method for locationList
    private List<Location> accessLocationList() {
        return LocationList.accessLocationList();
    }

    //EFFECTS: Accessor Method for transactionHistory
    private List<Transaction> accessTransactionHistory() {
        return TransactionList.accessTransactionHistory();
    }

    /**
     * JSON Methods --------------------------------------------------------------------------------------------------
     */
    // Read and Write

    // EFFECTS: saves LocationList to file
    private void saveLocationList() {
        try {
            LocationListWriter.open();
            LocationListWriter.writeLocationList(LocationList.accessLocationListAsLocationList());
            LocationListWriter.close();
            System.out.println("Saved Locations to " + LOCATION_LIST_JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + LOCATION_LIST_JSON_STORE);
        }
    }

    // REQUIRES: At least one Transaction in transactionHistory
    // EFFECTS: saves transactionHistory to file
    private void saveTransactionHistory() {
        try {
            TransactionHistoryWriter.open();
            TransactionHistoryWriter.writeTransactionHistory(TransactionList.accessTransactionHistoryAsTranList());
            //for (int i = 0; i < accessTransactionHistory().size(); i ++) {
            //    Transaction transaction = accessTransactionHistory().get(i);
            //    TransactionHistoryWriter.writeTransactionHistory(transaction);
            //}
            TransactionHistoryWriter.close();
            System.out.println("Saved Transaction History to " + TRANSACTION_HISTORY_JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + TRANSACTION_HISTORY_JSON_STORE);
        }
    }

    //EFFECTS: saves account to file
    private void saveAccount() {
        try {
            AccountWriter.open();
            AccountWriter.writeAccount(account);
            AccountWriter.close();
            System.out.println("Saved Account Information to " + ACCOUNT_JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + ACCOUNT_JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads Account from file
    private void loadAccount() {
        try {
            AccountReader.readAccount();
            System.out.println("Loaded Account from " + ACCOUNT_JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + ACCOUNT_JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads Location List from file
    private void loadLocationList() {
        try {
            LocationListReader.readLocationList();
            System.out.println("Loaded Location List from " + LOCATION_LIST_JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + ACCOUNT_JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads Location List from file
    private void loadTransactionHistory() {
        try {
            TransactionHistoryReader.readTransactionHistory();
            System.out.println("Loaded Transaction History from " + TRANSACTION_HISTORY_JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + ACCOUNT_JSON_STORE);
        }
    }

}
