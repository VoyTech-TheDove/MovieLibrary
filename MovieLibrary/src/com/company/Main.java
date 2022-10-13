package com.company;

import java.io.IOException;

import static com.company.UserInterface.Interface;

public class Main {

    public static void main(String[] args) throws IOException {
        try {
            Interface();
        } catch (MyException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}