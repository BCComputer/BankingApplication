package org.bank.services;

import org.bank.dao.AccountsImpl;
import org.bank.dao.TransactionsImpl;
import org.bank.entities.Login;
import org.bank.entities.Transactions;

import java.util.*;

import static org.bank.controller.ControllerBankingApp.loginSuccessOptions;

public class TransactionService {
    AccountsImpl accounts = new AccountsImpl();
    AccountsService accountsService = new AccountsService();
    Scanner scanner = new Scanner(System.in);
    TransactionsImpl transactions = new TransactionsImpl();

    public void createDepositForAccount(Login login) {

        int option = userDepositChoice();
        boolean isValid = false;

        if (option == 1) {
            List<Integer> idList = new ArrayList<>();
            Map<Integer, String> accountIdList = accounts.getAccountByUserName(login.getUserId());
            for (Map.Entry<Integer, String> entry : accountIdList.entrySet()) {
                if (entry.getValue().contains("checking")) {
                    idList.add(entry.getKey());
                }
            }
            System.out.println("Checking Account Id :" + idList.toString());

            int accountId = getAccountId();

            double depositAmount = getDepositAmount();

            System.out.println("Enter the description: ");
            String depositDescription = getStringValue();

            accountsService.deposit(accountId, depositAmount, depositDescription);
            loginSuccessOptions(login);

        } else if (option == 2) {
            List<Integer> idList = new ArrayList<>();
            Map<Integer, String> accountIdList = accounts.getAccountByUserName(login.getUserId());
            for (Map.Entry<Integer, String> entry : accountIdList.entrySet()) {
                if (entry.getValue().contains("saving")) {
                    idList.add(entry.getKey());
                }
            }

            System.out.println("Saving Account Ids :" + idList.toString());

            int accountId = getAccountId();

            double depositAmount = getDepositAmount();
            System.out.println();
            System.out.print("Enter the deposit description: ");
            String depositDescription = getStringValue();

            accountsService.deposit(accountId, depositAmount, depositDescription);
            loginSuccessOptions(login);
        } else {
            loginSuccessOptions(login);
        }
    }

    public void withdraw(Login login) {
        System.out.println("Choose a account id for withdraw");
        Map<Integer, String> accountIdList = accounts.getAccountByUserName(login.getUserId());
        System.out.println("Account Id list:" + accountIdList.toString());

        int accountId = getAccountId();
        double withDrawAmount = getWithDrawAmount();

        System.out.print("Enter the withdraw description: ");
        String description = getStringValue();
        accountsService.withdraw(accountId, withDrawAmount, description);
    }

    public int userDepositChoice() {
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
                    System.out.print("Invalid choice. Please enter a number between 1 and 3.: ");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.next();
            }
        }

        return menuChoice;
    }

    public void getAllTransaction(Login login) {
        Map<Integer, String> accountIdList = accounts.getAccountByUserName(login.getUserId());
        System.out.println(accountIdList.toString());
        int accountId = getAccountId();

        List<Transactions> allTransactions = transactions.getAllTransactions(accountId);
        if (allTransactions != null) {
            System.out.println(allTransactions.toString());
        } else {
            System.out.println("There is no transaction.");
        }

    }

    public int getAccountId() {
        int accountId = 0;
        boolean validInput = false;
        while (!validInput) {
            try {
                System.out.print("Enter account Id: ");
                accountId = scanner.nextInt();

                if (accountId >= 0) {
                    validInput = true;
                } else {
                    System.out.println("Please enter the number.");

                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter the number");
                scanner.next();
            }
        }
        return accountId;
    }


    public double getWithDrawAmount() {
        double amount = 0;
        boolean validInput = false;
        while (!validInput) {
            try {
                System.out.print("Enter withdraw amount: ");
                amount =scanner.nextDouble();
                scanner.nextLine();

                if (amount != 0) {
                    validInput = true;
                } else {
                    System.out.println("Please enter the number.");

                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter the number");
            }
        }
        return amount;
    }

    public double getDepositAmount() {
        double amount = 0;
        boolean validInput = false;
        while (!validInput) {
            try {
                System.out.print("Enter deposit amount: ");
                amount = scanner.nextDouble();
                scanner.nextLine();

                if (amount != 0) {
                    validInput = true;
                } else {
                    System.out.println("Please enter the number.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter the number");

            }
        }
        return amount;
    }


    public String getStringValue() {
        String input = "";
        boolean validInput = false;
        while (!validInput) {
            try {
                input = scanner.nextLine();

                if(!input.equalsIgnoreCase("")){
                    validInput = true;
                }else{
                    System.out.println("Filed can not be blank.");
                    System.out.println("Please enter the value");
                }
            } catch (InputMismatchException e) {
                System.out.println("Filed can not be blank.");
            }
        }
        return input;
    }


}
