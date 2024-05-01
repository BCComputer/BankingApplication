package org.bank.controller;
import org.bank.dao.UserDaoImpl;
import org.bank.entities.Login;
import org.bank.entities.User;
import org.bank.services.AccountsService;
import org.bank.services.AuthenticationService;
import org.bank.services.LoginService;
import org.bank.services.TransactionService;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ControllerBankingApp {

    public static void main(String[] args) {
        System.out.println("Welcome to the Banking App!!!");
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
                System.out.println("Signup Successful!!!");
                loginSuccessOptions(login);
                break;
            case 3:
               login= loginService.AdminLogin();
                System.out.println("Signup Successful!!!");
                adminLoginSuccessOptions(login);

                break;
            case 4:
                System.out.println("Thank You for visiting.");
            default:
                System.out.println("Please enter valid input");
        }
    }

    public static int userChoice() {
        Scanner scanner = new Scanner(System.in);

        final int MIN_OPTION = 1;
        final int MAX_OPTION = 4;
        int menuChoice = -1;
        boolean validInput = false;

        while (!validInput) {
            try {
                System.out.println("Enter your selection:"
                        + "\n[1] SignUp"
                        + "\n[2] Login as User"
                        + "\n[3] Login as Admin"
                        + "\n[4] Exit");

                menuChoice = scanner.nextInt();
                scanner.nextLine();

                if (menuChoice >= MIN_OPTION && menuChoice <= MAX_OPTION) {
                    validInput = true;
                } else {
                    System.out.println("Invalid choice. Please enter a number between 1 and 4.");
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
            System.out.println("Welcome to ABC Bank");
            switch (loginService.userChoice()) {
                case 1:
                    accountsService.createAccounts(login);
                    break;
                case 2:
                    transactionService.createDepositOptions(login);
                    break;
                case 3:
                    transactionService.withdraw(login);
                    break;
                default:
                    System.out.println("Thank you");
            }
        }
    }

    public static void adminLoginSuccessOptions(Login  login) {
        AccountsService accountsService = new AccountsService();
        LoginService loginService = new LoginService();
        UserDaoImpl userDao = new UserDaoImpl();
        if (login != null) {
            System.out.println("Welcome to ABC Bank Admin Page");
            User user = userDao.getUserByUserName(login.getUsername());
            switch (loginService.adminUserChoice()) {
                case 1:
                    accountsService.createAccounts(login);
                    break;
                case 2:

                    userDao.updateUser(user.getUser_id(), user);
                    break;
                case 3:
                    userDao.deleteUser(user.getUser_id());
                    break;
                case 4:
                    System.out.println(user.toString());
                    break;
                case 5:
                    loginService.adminUserChoice();
                    break;
                default:
                    System.out.println("Thank you");
            }
        }
    }
}

