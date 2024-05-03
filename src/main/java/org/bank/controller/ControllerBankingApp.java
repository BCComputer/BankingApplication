package org.bank.controller;

import org.bank.entities.Login;
import org.bank.services.AccountsService;
import org.bank.services.AuthenticationService;
import org.bank.services.LoginService;
import org.bank.services.TransactionService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ControllerBankingApp {

    public static void main(String[] args) {
        System.out.println("***************************************************************************************");
        System.out.println("***************************************************************************************");
        System.out.println("***************************************************************************************");
        System.out.println("***************************************************************************************");
        System.out.println("*********************Welcome to the Banking App!!!*************************************");
        System.out.println("***************************************************************************************");
        System.out.println("***************************************************************************************");
        System.out.println("***************************************************************************************");
        System.out.println("***************************************************************************************");
        System.out.println();
        int choice = userChoice();
        mainMenuChoice(choice);
    }

    public static void mainMenuChoice(int choice) {
        AuthenticationService authService = new AuthenticationService();
        LoginService loginService = new LoginService();
        Login login = null;

        switch (choice) {
            case 1:
                authService.signup();
                break;
            case 2:
                login = loginService.userLogin();
                System.out.println("Login Successful!!!");
                loginSuccessOptions(login);
                break;
            case 3:
                System.out.println("Thank You for visiting ABC Bank.");
                break;
            default:
                System.out.println("Please enter valid input.");
        }
    }

    public static int userChoice() {
        Scanner scanner = new Scanner(System.in);

        final int MIN_OPTION = 1;
        final int MAX_OPTION = 3;
        int menuChoice = -1;
        boolean validInput = false;

        while (!validInput) {
            try {
                System.out.println("Enter your selection:"
                        + "\n[1] SignUp"
                        + "\n[2] Login"
                        + "\n[3] Log Out");

                menuChoice = scanner.nextInt();
                scanner.nextLine();

                if (menuChoice >= MIN_OPTION && menuChoice <= MAX_OPTION) {
                    validInput = true;
                } else {
                    System.out.println("Invalid choice. Please enter a number between 1 and 3.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.next();
            }
        }
        return menuChoice;
    }

    public static void loginSuccessOptions(Login login) {
        AccountsService accountsService = new AccountsService();
        LoginService loginService = new LoginService();
        TransactionService transactionService = new TransactionService();
        if (login != null) {
            System.out.println("****  Welcome to ABC Bank   *****");
            System.out.println();
            System.out.println();
            switch (loginService.userChoice()) {
                case 1:
                    accountsService.createAccounts(login);
                    break;
                case 2:
                    transactionService.createDepositForAccount(login);
                    break;
                case 3:
                    transactionService.withdraw(login);
                    loginSuccessOptions(login);
                    break;
                case 4:
                    transactionService.getAllTransaction(login);
                    loginSuccessOptions(login);
                    break;
                case 5:
                    System.out.println("Thank you for choosing ABC Bank.");
                    break;
                default:
                    System.out.println("Thank you for choosing ABC Bank.");
                    System.out.println();
            }
        }
    }

}

