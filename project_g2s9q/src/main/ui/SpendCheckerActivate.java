package ui;

import model.EntryList;
import model.SpendChecker;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class SpendCheckerActivate {

    private static final String JSON_STORE = "./data/workroom.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;


    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    public void runSpendChecker() {
        SpendChecker sc = new SpendChecker();

        char option = '\0';
        Scanner scanner = new Scanner(System.in);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        System.out.println("Welcome to the spendChecker App");
        System.out.println("A. Check Balance");
        System.out.println("B. Set a spending limit for yourself: ");
        System.out.println("C. Make a new Entry");
        System.out.println("D. Delete an entry");
        System.out.println("E. Make a new Deposit");
        System.out.println("F. Show the summaries of all entries you made");
        System.out.println("G. Show account summary");
        System.out.println("H. Save Your Current Information");
        System.out.println("I. Load Your Past Information");
        System.out.println("J. Exit");


        do {
            System.out.println("Enter an option: ");
            option = scanner.next().charAt(0);

            switch (option) {

                case 'A':

                    System.out.println("You account balance is: ");
                    System.out.println(sc.getBalance());

                    break;

                case 'B':

                    System.out.println("Enter an amount you want to set your limit to: ");
                    int amt = scanner.nextInt();
                    boolean b = sc.setLimit(amt);
                    if (b) {
                        System.out.println("You have successfully set a limit for yourself");
                    } else {
                        System.out.println("Cannot set the limit as it extends the deposit!");
                    }
                    break;

                case 'C':

                    System.out.println("To make a new Entry, First enter the category and also specify the amounnt");
                    System.out.println("What is the category?");
                    String cat = scanner.next();
                    System.out.println("What is the amount?");
                    int amt3 = scanner.nextInt();
                    boolean add = sc.addToList(cat, amt3);

                    if (add) {
                        System.out.println("Item successfully added in the list");
                    } else {
                        System.out.println("Item cannot be added in the list as it extends your deposit");
                    }

                    if (sc.getLimitWarningBoolean()) {
                        System.out.println("Warning! You are going over the limit");
                    }

                    break;

                case 'D':

                    EntryList e = sc.getEntryList();
                    if (!e.isEmpty()) {
                        for (int i = 0; i < e.size(); i++) {
                            System.out.println(e.getElem(i).printEntrySummary());
                        }
                    } else {
                        System.out.println("You have no summaries to show!");
                    }

                    System.out.println("Please specify the serial number of the entry that you "
                            + "want to delete(Starts from 1)");
                    int siNo = scanner.nextInt();
                    sc.deleteEntry(siNo);
                    break;

                case 'E':

                    System.out.println("Please select the amount you want to make deposit:");
                    int amt1 = scanner.nextInt();
                    boolean dep = sc.setDeposit(amt1);
                    if (dep) {
                        System.out.println("The deposit has been successfully sent");
                    }
                    break;

                case 'F':

                    EntryList el = sc.getEntryList();
                    if (!el.isEmpty()) {
                        for (int i = 0; i < el.size(); i++) {
                            System.out.println(el.getElem(i).printEntrySummary());
                        }
                    } else {
                        System.out.println("You have no summaries to show!");
                    }
                    break;

                case 'G':

                    System.out.println("Here is your account summary");
                    System.out.println(sc.printAccountSummary());
                    break;

                case 'H':

                    try {
                        jsonWriter.open();
                        jsonWriter.write(sc);
                        jsonWriter.close();
                        System.out.println("Saved successfully to" + JSON_STORE);
                    } catch (FileNotFoundException f) {
                        System.out.println("Unable to write to file: " + JSON_STORE);
                    }

                    break;

                case 'I':

                    try {
                        sc = jsonReader.read();
                        System.out.println("Loaded from " + JSON_STORE);
                    } catch (IOException f) {
                        System.out.println("Unable to read from file: " + JSON_STORE);
                    }
                    break;

                default:
                    System.out.println("Invalid Option! Please enter again!");
                    break;


            }
        } while (option != 'J');
        System.out.println("Thank you for using our services");

    }
}