package org.bank.services;

import org.bank.dao.AccountsImpl;
import org.bank.dao.TransactionsImpl;
import org.bank.dao.UserDaoImpl;
import org.bank.entities.Account;
import org.bank.entities.Login;
import org.bank.entities.User;

import java.util.InputMismatchException;
import java.util.Scanner;

import static org.bank.controller.ControllerBankingApp.loginSuccessOptions;

public class AccountsService {
    TransactionsImpl transactionsImpl = new TransactionsImpl();
    AccountsImpl accountsImpl = new AccountsImpl();
    Scanner scanner = new Scanner(System.in);
    AccountsImpl accounts = new AccountsImpl();
    UserDaoImpl userDao = new UserDaoImpl();


    public void createAccounts(Login login) {
        int option = userAccountChoice();
        boolean isValid = false;
        while (!isValid) {
            if (option == 1) {
                int accountCreationStatus = createCheckingAccount(login);
                if (accountCreationStatus == 1) {
                    System.out.println("Checking account created.");
                    System.out.println("Please choose your next option");
                }
                loginSuccessOptions(login);
            } else if (option == 2) {
                createSavingAccount(login);
                isValid = true;
                loginSuccessOptions(login);
            } else {
                loginSuccessOptions(login);
                isValid = true;
            }
        }
    }

    public int userAccountChoice() {

        final int MIN_OPTION = 1;
        final int MAX_OPTION = 3;
        int menuChoice = -1;
        boolean validInput = false;

        while (!validInput) {
            try {
                System.out.println("Enter your selection:"
                        + "\n[1] Checking"
                        + "\n[2] Saving"
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

    public int createCheckingAccount(Login login) {

        User userByUserName = userDao.getUserByUserName(login.getUsername());
        int checkingAccountCreatedStatus = accounts.createAnAccount(userByUserName.getUser_id(), "checking", 0);
        return checkingAccountCreatedStatus;
    }

    public void createSavingAccount(Login login) {
        User userByUserName = userDao.getUserByUserName(login.getUsername());
        int savingAccountCreatedStatus = accountsImpl.createAnAccount(userByUserName.getUser_id(), "savings", 0);
        if (savingAccountCreatedStatus == 1) {
            System.out.println("Account Created");
        } else {
            System.out.println("Fail to create account");
        }
    }

    public void deposit(int accountId, double amount, String depositDescription) {
        Account account = accountsImpl.getAccountById(accountId);
        if (account != null && amount > 0) {
            account.deposit(amount);
            accountsImpl.updateAccountBalance(account);
            transactionsImpl.addTransaction(accountId, "deposit", amount, depositDescription);
        }
    }
    public void withdraw(int accountId, double amount, String depositDescription) {
        Account account = accountsImpl.getAccountById(accountId);
        if (account != null && amount > 0) {
            System.out.println("Before withdrawal balance:" + account.getBalance());
            if(amount>account.getBalance()) {
                System.out.println("Low balance: " + account.getBalance());
            }else{
                System.out.println("After withdrawal balance:" + (account.getBalance()-amount));
                account.withdraw(amount);
            }
            accountsImpl.updateAccountBalance(account);
            transactionsImpl.addTransaction(accountId, "withdrawal", amount, depositDescription);
        }

    }

}
