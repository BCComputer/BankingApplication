package org.bank.services;

import org.bank.dao.AccountsImpl;
import org.bank.dao.TransactionsImpl;
import org.bank.entities.Login;
import org.bank.entities.Transactions;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import static org.bank.controller.ControllerBankingApp.loginSuccessOptions;

public class TransactionService {
    AccountsImpl accounts = new AccountsImpl();
   // TransactionsImpl transactionsImpl = new TransactionsImpl();
    AccountsService accountsService = new AccountsService();

    public void createDepositOptions(Login login){
        //AccountsImpl accounts1 = new AccountsImpl();
        Scanner scanner = new Scanner(System.in);
        int option = userDepositChoice();
        if(option==1) {
            System.out.println("Enter the deposit amount.");
            double depositAmount = scanner.nextDouble();
            scanner.nextLine();
            System.out.println("Enter the deposit description");
            String depositDescription = scanner.nextLine();

            List<Integer> accountIdList = accounts.getAccountByUserName(1);
            System.out.println("Account Id list:" + accountIdList.toString());

            System.out.println("Enter the account Id.");
            int accountId = scanner.nextInt();
            scanner.nextLine();

            accountsService.deposit(accountId, depositAmount, depositDescription);
        }else if (option==2){
            System.out.println("Enter the deposit amount.");
            double depositAmount = scanner.nextDouble();
            scanner.nextLine();
            System.out.println("Enter the deposit description");
            String depositDescription = scanner.nextLine();

            List<Integer> accountIdList = accounts.getAccountByUserName(1);
            System.out.println("Account Id list:" + accountIdList.toString());

            System.out.println("Enter the account Id.");
            int accountId = scanner.nextInt();
            scanner.nextLine();

            accountsService.deposit(accountId, depositAmount, depositDescription);
        }else{
            loginSuccessOptions(login);
        }
    }
    public void withdraw(Login login) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the withdraw amount.");
        double withdrawAmount = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Enter the withdraw description");
        String description = scanner.nextLine();

        List<Integer> accountIdList = accounts.getAccountByUserName(1);
        System.out.println("Account Id list:" + accountIdList.toString());

        System.out.println("Enter the account Id.");
        int accountId = scanner.nextInt();
        scanner.nextLine();

        accountsService.withdraw(accountId, withdrawAmount, description);
    }
    public static int userDepositChoice() {
        Scanner scanner = new Scanner(System.in);

        final int MIN_OPTION = 1;
        final int MAX_OPTION = 3;
        int menuChoice = -1;
        boolean validInput = false;

        while (!validInput) {
            try {
                System.out.println("Enter your selection:"
                        + "\n[1] Deposit on Checking"
                        + "\n[2] Deposit on Saving"
                        + "\n[3] Exit");

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

}
