package com.ht09;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int option = 0;
        Scanner scan = new Scanner(System.in);

        while (option != 4) {
            View.showMenu();
            option = scan.nextInt();    // Ask for the option that the user wants
        }
    }
}
