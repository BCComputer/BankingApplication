package org.bank.services;

import org.bank.dao.UserDaoImpl;
import org.bank.entities.User;

import java.util.*;


public class AuthenticationService {
    Scanner scanner = new Scanner(System.in);
    public void signup() {

        System.out.print("Enter the first name: ");
        String firstName =getStringValue();

        System.out.print ("Enter the last name: ");
        String lastName = getStringValue();

        System.out.print("Enter the address: ");
        String address = getStringValue();

        String phoneNumber = setPhone(scanner);

        String email = setEmail(scanner);

        String userName = getUserName();

        System.out.print("Enter password: ");
        String password_hash = getStringValue();

        UserDaoImpl userDao = new UserDaoImpl();
        User user = new User(firstName, lastName, address, email, phoneNumber, userName, password_hash); // constructor used.
        int success = userDao.createUser(user); //Called createUser(user) function.
        if (success == 0) {
            System.out.println("Signup successful!");
            System.out.println();
            System.out.println("Please, login!!!");
        } else {
            System.out.println("Signup failed. Username might already be in use.");
        }
    }

    public String setEmail(Scanner scanner) {
        String email = "";
        boolean valid = false;
        while (!valid){
            try {
                System.out.print("Enter the valid email. eg. abc@gmail.com : " );
                email = scanner.nextLine();

                if (!((email == null) && (email.equals("")))) {
                    if (countChar(email, '@') == 1) {
                        if (Character.isLetter(email.charAt(0)) || Character.isDigit(email.charAt(0))) {
                            int positionAt = email.indexOf('@');
                            String afterAt = email.substring(positionAt + 1);
                            if (countChar(afterAt, '.') == 1) {
                                int positionDot = afterAt.indexOf('.');
                                String afterDot = afterAt.substring(positionDot + 1);
                                if (afterDot.length() >= 2) {
                                    valid = true;
                                }
                            }

                        }
                    }
                }


            } catch (IllegalArgumentException e) {
                System.out.println("Invalid email. please  enter valid email.");
            }
        }

        return email;
    }

    public static boolean validIsDigit(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static int countChar(String str, char ch) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ch) {
                count++;
            }
        }
        return count;
    }

    public static String setPhone(Scanner scanner) {
        String phone = "";
        boolean valid = false;
        final int PHONE_LENGTH = 13;
        while (!valid) {
            try {
                System.out.println("Enter phone number. eg. (571)213-2345");
                phone = scanner.nextLine();
                if (phone.length() == PHONE_LENGTH) {
                    if (phone.charAt(0) == '(' && phone.charAt(4) == ')' && phone.charAt(8) == '-') {
                        String firstPart = phone.substring(1, 3);
                        String secondPart = phone.substring(5, 7);
                        String thirdPart = phone.substring(9, PHONE_LENGTH);
                        if (validIsDigit(firstPart)) {
                            if (validIsDigit(secondPart)) {
                                if (validIsDigit(thirdPart)) {
                                    valid = true;
                                }
                            }
                        }
                    }
                }
            }catch (InputMismatchException e){
                System.out.println("Please enter the valid phone.");
            }
        }
        return phone;
    }

    public String getUserName() {
        String username = "";
        boolean validInput = false;
        List<String> userNameList = new ArrayList<>();

        while (!validInput) {
            try {
                System.out.print("Enter the username : ");
                username = scanner.nextLine();
                UserDaoImpl userDao = new UserDaoImpl();
                for (User user : userDao.getAllUser()) {
                    userNameList.add(user.getUsername());
                }
                if (userNameList.contains(username)) {
                    System.out.println("User name  " + username + " already exists.");
                    System.out.print("Please enter different username : ");
                } else if(!username.isEmpty()) {
                    validInput = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Enter Valid userName.");
                scanner.next();
            }
        }
        return username;
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
