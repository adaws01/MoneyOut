package ui;

import model.Account;
import model.MoneyOutPrimitives.Location;

import java.util.Scanner;

public class MoneyOutApp {
    private final Location WATERY_LANE = new Location("6 Watery Lane", "Downtown", 0);

    private Account account;  //User Account to track balance, name, and address. Only one user account instantiated.
    private Scanner input;    //Instance of Java's Scanner library--for tracking console input
    private String step;      //Tracks what menu the user is in and redefines commands accordingly

    //EFFECTS: Runs the App
    public MoneyOutApp() {runApp();}

    //MODIFIES: this
    //EFFECTS: Performs necessary setup to begin
    private void initialize() {
        account = new Account(0, "Xander", WATERY_LANE);
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

    //EFFECTS: Sets up the Start menu
    private void callStart() {    //commands == t, a, l ,s
        step = "start";
        System.out.println("Welcome, " + account.getName() + ". How can I help you?");
        System.out.println("\tt -> Manage Transactions");
        System.out.println("\ta -> Manage Account");
        System.out.println("\tl -> Manage Locations");
        System.out.println("\ts -> Statistics & Insights");
        System.out.println("\tq -> Quit");
    }

    //EFFECTS: Sets up the Transactions Menu
    private void callTransaction() {    //commands == l, m, d, v
        step = "transaction";
        System.out.println("Transactions");
        System.out.println("\tl -> Log Transaction");
        System.out.println("\tm -> Modify Transaction");
        System.out.println("\td -> Delete Transaction");
        System.out.println("\tv -> View Transaction History");
        printNavigation();
    }

    private void callAccount() {    //commands == a, p
        step = "account";
        System.out.println("Your Account");
        System.out.println("\ta -> Account Balance");
        System.out.println("\tp -> Personal Information");
        printNavigation();
    }

    private void callBalance() {    //commands == d, w
        step = "balance";
        System.out.println("Your Account Balance is: " + account.getBalance());
        System.out.println("\td -> Deposit");
        System.out.println("\tw -> Withdraw");
        printNavigation();
    }

    private void callLocations() {    //commands == n, m, l
        step = "location";
        System.out.println("Locations");
        System.out.println("\tn -> New Location");
        System.out.println("\tm -> Modify Existing Location");
        System.out.println("\tl -> View All Recorded Locations");
        printNavigation();
    }

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
        step = "withdraw";
        System.out.println("Input Withdrawal Amount:");

        double amount = Double.parseDouble(input.next());
        account.withdraw(amount);
        callBalance();
    }

    private void printNavigation() {
        System.out.println("\tb -> Back");
        System.out.println("\tq -> Quit");
    }

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
        } else {
            invalidCommand();
        }
    }
    private void stepHandlerB() {
        if (step.equals("transaction") || step.equals("account") || step.equals("location")) {
            callStart();
        } else if (step.equals("balance")) {
            callAccount();
        } else if (step.equals("deposit")) {
            callBalance();
        } else {
            invalidCommand();
        }
    }
    private void stepHandlerD() {
        if (step.equals("transaction")) {
            //TODO Delete Transaction            //process that will return to transaction menu
            constructionCommand(); //stub
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
            //TODO Log Transaction                //process that will return to transaction menu
            constructionCommand(); //stub
        } else if (step.equals("location")) {
            //TODO viewLocations            //process that will return to location menu
            constructionCommand(); //stub
        } else {
            invalidCommand();
        }
    }
    private void stepHandlerM() {
        if (step.equals("transaction")) {
            //TODO Modify Transaction          //process that will return to transaction menu
            constructionCommand(); //stub
        } else if (step.equals("location")) {
            //TODO Modify Location         //process that will return to location menu
            constructionCommand();
        } else {
            invalidCommand();
        }
    }
    private void stepHandlerN() {
        if (step.equals("location")) {
            //TODO newLocation                    //process that will return to location menu
            constructionCommand(); //stub
        } else {
            invalidCommand();
        }
    }
    private void stepHandlerP() {
        if (step.equals("account")) {
            //TODO callPersonalInformation       //separate menu
            constructionCommand();
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
            //TODO View Transaction History    //print statement, not separate menu
            constructionCommand(); //stub
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
}
