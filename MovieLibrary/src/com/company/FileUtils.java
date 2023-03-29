package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

    private static final String COMMA_DELIMITER = ",";
    private static final String CSV_MOVIE_FILE_NAME = "csvMovieList";

    public static <T> List<T> readFromFileStream(String fileName) {
        List<T> result = new ArrayList<>();
        try {
            FileInputStream fileInputStream = new FileInputStream(fileName);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            while (fileInputStream.available() > 0) {
                T readObject = (T) objectInputStream.readObject();
                result.add(readObject);
            }
            fileInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return result;
    }

    public static boolean fileExists(String fileName) {
        File file = new File(fileName);
        return file.exists();
    }

    public static boolean createFileIfDoesntExist(String fileName) {
        File file = new File(fileName);
        try {
            return file.createNewFile();
        } catch (IOException e) {
            System.out.println("cannot create file: " + fileName);
        }
        return false;
    }

    public static void writeMoviesIntoCSV(List<Movie> movies) throws IOException {
        BufferedWriter out = new BufferedWriter(new FileWriter(CSV_MOVIE_FILE_NAME), 512);
        out.write("id,title,date" + System.lineSeparator());
        for (Movie movie : movies) {
            out.write(movie.getId() + "," + movie.getTitle() + "," + movie.getDate() + System.lineSeparator());
        }
        out.close();
    }

    public static List<Movie> readMoviesFromCSV() throws IOException, MyException {
        List<Movie> movies = new ArrayList<>();
        if (!fileExists(CSV_MOVIE_FILE_NAME)) {
            createFileIfDoesntExist(CSV_MOVIE_FILE_NAME);
        }
        BufferedReader br = new BufferedReader(new FileReader(CSV_MOVIE_FILE_NAME));
        String line;
        br.readLine();
        while ((line = br.readLine()) != null) {
            String[] movieDetails = line.split(COMMA_DELIMITER);
            if (movieDetails.length > 0) {
                Movie movie = new Movie(Integer.parseInt(movieDetails[0]),
                        movieDetails[1], Integer.parseInt(movieDetails[2]));
                movies.add(movie);
            }
        }
        return movies;
    }

    public static List<User> readFromStreamUser(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        List<User> users = new ArrayList<>();
        Object read = objectInputStream.readObject();
        User userRead = (User) read;
        users.add(userRead);
        return users;
    }

    public static <T> List<T> saveProgress(List<T> list, String fileName) throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(fileName));
        for (T result : list) {
            objectOutputStream.writeObject(result);
        }
        System.out.println("... " + fileName + " updated.");
        return list;
    }
}


