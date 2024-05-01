package org.bank.services;

import org.bank.dao.LoginImpl;
import org.bank.dao.UserDaoImpl;
import org.bank.entities.Login;
import org.bank.entities.User;

import java.util.*;

public class LoginService {
    public Login userLogin() {
        LoginImpl createLogin = new LoginImpl();
        Scanner scanner = new Scanner(System.in);
        HashMap<String, String> userPasswordList = new HashMap<>();
        UserDaoImpl userDao = new UserDaoImpl();
        boolean isValidLogin = false;
        while (!isValidLogin) {
            try {
                System.out.println("Enter the username");
                String userName = scanner.nextLine();

                System.out.println("Enter the password");
                String password = scanner.nextLine();
                List<User> allUser = userDao.getAllUser();
                for (User user : allUser) {
                    userPasswordList.put(user.getUsername(), user.getPassword_hash());
                }
                if (!userPasswordList.containsKey(userName) && !userPasswordList.containsKey(password)) {
                    System.out.println("User name Password does not match. Please Type correct username and password");
                } else {
                    isValidLogin = true;
                    return createLogin.login(userName, password);
                }
            } catch (InputMismatchException e) {
                System.out.println("Type correct format");
            }
        }
        return null;
    }

    public Login AdminLogin() {
        LoginImpl createLogin = new LoginImpl();
        Scanner scanner = new Scanner(System.in);
        HashMap<String, String> userPasswordList = new HashMap<>();
        UserDaoImpl userDao = new UserDaoImpl();
        boolean isValidLogin = false;
        while (!isValidLogin) {
            try {
                System.out.println("Enter the username");
                String userName = scanner.nextLine();

                System.out.println("Enter the password");
                String password = scanner.nextLine();
                List<User> allUser = userDao.getAllUser();
                for (User user : allUser) {
                    userPasswordList.put(user.getUsername(), user.getPassword_hash());
                }
                if (!userPasswordList.containsKey(userName) && !userPasswordList.containsKey(password)) {
                    System.out.println("User name Password does not match. Please Type correct username and password");
                } else {
                    isValidLogin = true;
                    return createLogin.login(userName, password);
                }
            } catch (InputMismatchException e) {
                System.out.println("Type correct format");
            }
        }
        return null;
    }

    public int adminUserChoice() {
        Scanner scanner = new Scanner(System.in);

        final int MIN_OPTION = 1;
        final int MAX_OPTION = 5;
        int menuChoice = -1;
        boolean validInput = false;

        while (!validInput) {
            try {
                System.out.println("Enter your selection:"
                        + "\n[1] Create user"
                        + "\n[2] Update user"
                        + "\n[3] Delete user"
                        + "\n[4] View all user"
                        + "\n[5] Exit");

                menuChoice = scanner.nextInt();
                scanner.nextLine();

                if (menuChoice >= MIN_OPTION && menuChoice <= MAX_OPTION) {
                    validInput = true;
                } else {
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.next();
            }
        }
        return menuChoice;
    }


    public int userChoice() {
        Scanner scanner = new Scanner(System.in);

        final int MIN_OPTION = 1;
        final int MAX_OPTION = 5;
        int menuChoice = -1;
        boolean validInput = false;

        while (!validInput) {
            try {
                System.out.println("Enter your selection:"
                        + "\n[1] Create an Account"
                        + "\n[2] Deposit"
                        + "\n[3] Withdraw"
                        + "\n[4] Transfer"
                        + "\n[5] Exit");

                menuChoice = scanner.nextInt();
                scanner.nextLine();

                if (menuChoice >= MIN_OPTION && menuChoice <= MAX_OPTION) {
                    validInput = true;
                } else {
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.next();
            }
        }
        return menuChoice;
    }

}

