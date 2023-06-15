package ui;

import model.Entry;
import model.SpendChecker;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        SpendCheckerActivate sca = new SpendCheckerActivate();
        sca.runSpendChecker();
    }
}