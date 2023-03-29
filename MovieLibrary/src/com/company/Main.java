package com.company;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        try {
            UserGui.userGui();
        } catch (MyException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}