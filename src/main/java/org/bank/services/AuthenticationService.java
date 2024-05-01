package org.bank.services;

import org.bank.dao.UserDaoImpl;
import org.bank.entities.User;

import java.util.*;


public class AuthenticationService {
    public void signup() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the first name");
        String firstName = scanner.nextLine();

        System.out.println("Enter the last name");
        String lastName = scanner.nextLine();

        System.out.println("Enter the address");
        String address = scanner.nextLine();

        System.out.println("Enter phone number. eg. (571)213-2345");
        String phoneNumber = setPhone(scanner);

        System.out.println("Enter the valid email=. eg. abc@gmail.com");
        String email = setEmail(scanner);

        String userName = getUserName();

        System.out.print("Enter password: ");
        String password_hash = scanner.nextLine();

        UserDaoImpl userDao = new UserDaoImpl();
        User user = new User(firstName, lastName, address, email, phoneNumber, userName, password_hash);
        int success = userDao.createUser(user);
        if (success == 0) {
            System.out.println("Signup successful!");
            System.out.println("Please, login!!!");
        } else {
            System.out.println("Signup failed. Username might already be in use.");
        }
    }

    public void signupUpdate() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the first name");
        String firstName = scanner.nextLine();

        System.out.println("Enter the last name");
        String lastName = scanner.nextLine();

        System.out.println("Enter the address");
        String address = scanner.nextLine();

        String phoneNumber = setPhone(scanner);

        String email = setEmail(scanner);
        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password_hash = scanner.nextLine();

        UserDaoImpl userDao = new UserDaoImpl();
        User user = new User(firstName, lastName, address, email, phoneNumber, username, password_hash);

        System.out.println("Enter Id");
        int id = scanner.nextInt();

        int success = userDao.updateUser(id, user);

        if (success == 0) {
            System.out.println("Signup successful!");
        } else {
            System.out.println("Signup failed. Username might already be in use.");
        }
    }

    public void deleteUser() {
        Scanner scanner = new Scanner(System.in);


        UserDaoImpl userDao = new UserDaoImpl();

        System.out.println("Enter Id");
        int id = scanner.nextInt();

        int success = userDao.deleteUser(id);

        if (success == 0) {
            System.out.println("Signup successful!");
        } else {
            System.out.println("Signup failed. Username might already be in use.");
        }
    }

    public void getAllUsers() {
        UserDaoImpl userDao = new UserDaoImpl();
        System.out.println(userDao.getAllUser().toString());
    }

    public String setEmail(Scanner scanner) {
        String email = "";
        boolean valid = false;
        while (!valid){
            try {
                System.out.println("Enter the email.");
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
                System.out.println("Enter the valid email.");


            } catch (IllegalArgumentException e) {
                System.out.println("Invalid email.");
            }
        }

        return email;
    }

    // This is a helper Method to check if the passed string contains all numeric characters.
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
                System.out.println("Enter the phone.");
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

    public static String getUserName() {
        Scanner scanner = new Scanner(System.in);
        String username = "";
        boolean validInput = false;
        List<String> userNameList = new ArrayList<>();

        while (!validInput) {
            try {
                System.out.println("Enter the username");
                username = scanner.nextLine();
                UserDaoImpl userDao = new UserDaoImpl();
                for (User user : userDao.getAllUser()) {
                    userNameList.add(user.getUsername());
                }
                if (userNameList.contains(username)) {
                    System.out.println("User name " + username + "already exists.");
                    System.out.println("Please enter different name");
                } else {
                    validInput = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Enter Valid userName");
                scanner.next();
            }
        }
        return username;
    }
}
