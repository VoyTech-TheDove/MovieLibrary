package com.company;

import com.company.service.UserService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.company.FileUtils.*;
import static com.company.Menus.adminMenuSwitch;
import static com.company.Menus.userMenuSwitch;
import static com.company.service.UserService.loginOrRegisterSwitch;

public class UserInterface {

 public static void Interface () throws MyException, IOException {
     createFile("users.dat");
     createFile("reviews.dat");
     List<User> users = readFromFileStream("users.dat");
     List<Movie> movies = new ArrayList<>();
     List<Review> reviews = readFromFileStream("reviews.dat");

     csvReader(movies);
     User loggedUser = loginOrRegisterSwitch(users);

     if ( UserService.isAdmin(loggedUser) ) {
         adminMenuSwitch(movies);
     } else {
         userMenuSwitch(movies, users, reviews, loggedUser);
     }
 }
}
