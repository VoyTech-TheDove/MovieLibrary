package com.company;

public class Utils {
    public static void incorrectCommand(String string) {
        System.out.println("The command is incorrect - error "+string);
    }
    public static void showUserMenu() {
        System.out.println("---------User-----------");
        System.out.println("What would you like to do: ");
        System.out.println("(1) get a detailed list of movies ");
        System.out.println("(2) review a movie ");
        System.out.println("(3) show all reviews - sorted by time added");
        System.out.println("(4) show all reviews - sorted by ID ");
        System.out.println("(9) exit the program ");
        System.out.println("----------------------------");
    }
    public static void showGuestMenu() {
        System.out.println("---------Guest----------");
        System.out.println("What would you like to do: ");
        System.out.println("(1) get a detailed list of movies ");
        System.out.println("(2) review a movie ");
        System.out.println("(3) show all reviews - sorted by time added");
        System.out.println("(4) show all reviews - sorted by ID ");
        System.out.println("(9) exit the program ");
        System.out.println("----------------------------");
    }
    public static void showWelcomeMenu() {
        System.out.println("----------------------------");
        System.out.println("Welcome to our database of Movies!");
        System.out.println("Choose what would you like to do:");
        System.out.print("(1) Login ");
        System.out.print("(2) Register a new user");
        System.out.print("(3) Browse as a guest");
        System.out.println("(9) exit the program ");
        System.out.println("----------------------------");
    }
    public static void showAdminMenu() {
        System.out.println("--------Admin----------");
        System.out.println("What would you like to do: ");
        System.out.print("(1) add a movie ");
        System.out.print("(2) check the library size ");
        System.out.println("(3) get a detailed list of movies ");
        System.out.print("(5) delete a movie ");
        System.out.println("(6) list all the movies ");
        System.out.print("(7) save the list ");
        System.out.println("(9) exit the program ");
        System.out.println("----------------------------");
    }
}
