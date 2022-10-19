package com.company;

import java.util.Scanner;

public class IOUtils {
    private static final Scanner SCANNER = new Scanner(System.in);

        public static int readIntFromUser(String nameOfInt) {
        boolean inputReceivedSuccessfully;
        String line;
        int n = 0;
        do{
            System.out.println("Please enter "+nameOfInt+" as a number");
            line = SCANNER.nextLine();
            try{
                n=Integer.parseInt(line);
                inputReceivedSuccessfully = false;
            } catch (NumberFormatException e) {
                System.out.println("Enter only Integer Value");
                inputReceivedSuccessfully = true;
                SCANNER.reset();
            }
        }while(inputReceivedSuccessfully);
        System.out.println("you have entered: "+line+ " for "+nameOfInt);
        return n;
    }

    public static String readStringFromUser(String nameOfString) {
        boolean inputReceivedSuccessfully;
        String line = null;
        do{
            System.out.println("Please enter "+nameOfString);
            if(SCANNER.hasNextLine()) {
                line = SCANNER.nextLine();
                inputReceivedSuccessfully = false;
            } else{
                System.out.println("Write something...");
                inputReceivedSuccessfully = true;
            }
        }while(inputReceivedSuccessfully);
        System.out.println("you have entered: "+line);
        return line;
    }
}