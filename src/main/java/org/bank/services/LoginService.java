package org.bank.services;

import org.bank.dao.LoginImpl;
import org.bank.dao.UserDaoImpl;
import org.bank.entities.Login;
import org.bank.entities.User;

import java.util.*;

public class LoginService {
    LoginImpl createLogin = new LoginImpl();
    Scanner scanner = new Scanner(System.in);
    UserDaoImpl userDao = new UserDaoImpl();

    public Login userLogin() {

        HashMap<String, String> userPasswordList = new HashMap<>();
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
                if(!password.equalsIgnoreCase("") && !userName.equalsIgnoreCase("")){
                    if (!userPasswordList.containsKey(userName) && !userPasswordList.containsKey(password)) {
                        System.out.println("User name Password does not match. Please Type correct username and password");
                    } else {
                        isValidLogin = true;
                        return createLogin.login(userName, password);
                    }
                }else{
                    System.out.println("Username and Password can not be blank.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Type correct format");
            }
        }
        return null;
    }


    public int userChoice() {
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
                        + "\n[4] View Transaction"
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

