package com.company;

import java.util.Scanner;

public class IOUtils {

    private static final Scanner SCANNER = new Scanner(System.in);


        public static int readIntFromUser(String nameOfInt) {
        boolean flag;
        String line;
        int n = 0;
        do{
            System.out.println("Please enter "+nameOfInt+" as a number");
            line = SCANNER.nextLine();
            try{
                n=Integer.parseInt(line);
                flag = false;
            } catch (NumberFormatException e) {
                System.out.println("Enter only Integer Value");
                flag = true;
                SCANNER.reset();
            }
        }while(flag);
        System.out.println("you have entered: "+line+ " for "+nameOfInt);
        return n;

    }
    public static String readStringFromUser(String nameOfString) {
        boolean flag;
        String line = null;
        do{
            System.out.println("Please enter "+nameOfString);
            if(SCANNER.hasNextLine()) {
                line = SCANNER.nextLine();
                flag = false;
            } else{
                System.out.println("Write something...");
                flag = true;
            }
        }while(flag);
        System.out.println("you have entered: "+line);
        return line;

    }

}