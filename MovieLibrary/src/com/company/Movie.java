package com.company;

import java.io.Serializable;

public class Movie implements Serializable {
    private int id;
    private String title;
    private int date;
    private static int nextID = 0;

    public Movie(String title, int date) {
        this.id =nextID++;
        this.title = title;
        this.date = date;
    }

    public Movie(int id, String title, int date) {
        this.id = id;
        this.title = title;
        this.date = date;
        if (id>=nextID){
            nextID+=id;
        }
    }
public void getDetails (){
    System.out.println(this.title +" ["+this.date+"]");
}
    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", date=" + date +
                '}';
    }

    public int getId() {
        return id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }
}
