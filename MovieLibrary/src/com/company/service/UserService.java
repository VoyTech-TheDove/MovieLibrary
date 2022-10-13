package com.company.service;


import com.company.User;
import com.company.UserRole;

import java.io.FileNotFoundException;
import java.util.List;


import static com.company.FileUtils.saveProgress;
import static com.company.IOUtils.readIntFromUser;
import static com.company.IOUtils.readStringFromUser;
import static com.company.Utils.*;

public class UserService {

    private static final String ADMIN_USERNAME = "admin";
    private static final String GUEST_USERNAME = "guest";
    public static UserRole getRole(User user) {

        return user.getLogin().equals(ADMIN_USERNAME) ? UserRole.ADMIN : (user.getLogin().equals(GUEST_USERNAME) ? UserRole.GUEST : UserRole.USER);
    }

    public static boolean  isAdmin(User user) {

        return getRole(user).equals(UserRole.ADMIN);
    }
    public static boolean  isGuest(User user) {

        return getRole(user).equals(UserRole.GUEST);
    }

    public static User loginOrRegisterSwitch(List<User> users) {
            User user;
            int x;
            do {
                showWelcomeMenu();
                x = readIntFromUser("option");
                switch (x) {
                    case 1:
                        user = login(users);
                        return user;
                    case 2:
                        try {
                            user = addUser(users);
                            try{
                                saveProgress(users,"users.dat");
                            } catch (FileNotFoundException e) {
                                System.out.println(e.getMessage());
                            }
                            return user;
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 3:
                        user = new User(0,"guest","guest");
                        return user;
                    case 9:
                        break;
                    default:
                        System.out.println("Enter a number corresponding to selected action");
                }
            } while (x!=1 && x!=2 && x!=9);
            return new User(0,"guest","guest");
            }

    public static User login(List<User> users) {

        String loginFromInput;
        String passwordFromInput;
        User guest = new User(0,"guest","guest");
        try {
                loginFromInput = readStringFromUser("Input login: \n [\"0\" to cancel]");
                if (loginFromInput.equals("0")) {
                    System.out.println("cancel - guest");
                    return guest;
                }
                if (loginFromInput.equals("admin")) {
                    passwordFromInput = readStringFromUser("password: \n [\"0\" to cancel]");
                    if (passwordFromInput.equals("0")) {
                        System.out.println("cancel - guest");
                        return guest;
                    }
                    if (passwordFromInput.equals("admin")) {
                         User admin = new User(0, "admin", "admin");
                         return admin;
                    }
                } else
                    try {
                        if (loginFromInput.length() > 2) {
                            User userWithGivenLogin = users.stream()
                                    .filter(user -> user.getLogin().equals(loginFromInput))
                                    .findFirst()
                                    .orElseThrow();

                                passwordFromInput = readStringFromUser("password: \n [\"0\" to cancel]");
                            if (passwordFromInput.equals("0")) {
                                System.out.println("cancel - guest");
                                return guest;
                            }
                                if (userWithGivenLogin.getPassword().equals(passwordFromInput)) {
                                    User user = userWithGivenLogin;
                                    System.out.println("login: " + userWithGivenLogin.getLogin());
                                    return user;
                                } else
                                    System.out.println("incorrect password");

                        } else
                            incorrectCommand( "logincheck");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        System.out.println("wrong login");
                    }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return guest;
    }
    public static User addUser(List<User> users) {
        String dataLogin;
        String dataPassword;
        String dataPasswordCheck;
        int id;
        boolean isLoginAvailable;
        do {
            dataLogin = readStringFromUser("Enter login (min 3 characters): ");
        } while (dataLogin.length() < 3);
        final String finaldatalogin = dataLogin;
        isLoginAvailable = (users.stream().noneMatch(User -> User.getLogin().equals(finaldatalogin)));
        if (isLoginAvailable) {
            do {
                dataPassword = readStringFromUser("Enter password (min. 5 characters): ");
            } while (dataPassword.length() < 5);
            dataPasswordCheck = readStringFromUser("Re-enter password: ");
            if (dataPassword.equals(dataPasswordCheck)) {
                if (users.size() >= 1) {
                    id = users.get(users.size() - 1).getId() + 1;
                } else {
                    id = 1;
                }
                users.add(new User(id, dataLogin, dataPassword));
                System.out.println("ID: " + id);
                System.out.println("Successfully added user: " + users.get(users.size() - 1));
                return users.get(users.size() - 1);
            } else
                incorrectCommand("password not the same");
        } else
            incorrectCommand("login unavailable");
        return new User (0, "guest", "guest");
    }
}
