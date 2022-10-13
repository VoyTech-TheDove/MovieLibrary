package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {


    private static final String COMMA_DELIMITER = ",";

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

    public static boolean createFile(String fileName) {
        File file = new File(fileName);
        try {
            return file.createNewFile();
        } catch (IOException e) {
            System.out.println("cannot create file: " + fileName);
        }
        return false;
    }

    public static void csvWriter(List<Movie> movies) throws IOException {
        BufferedWriter out = new BufferedWriter(new FileWriter("csvMovieList"), 512);
        out.write("id,title,date" + System.lineSeparator());
        for (Movie movie : movies)
            out.write(movie.getId() + "," + movie.getTitle() + "," + movie.getDate() + System.lineSeparator());
        out.close();
    }

    public static void csvReader(List<Movie> movies) throws IOException, MyException {

        if (!fileExists("csvMovieList)")) {
            createFile("csvMovieList");
        }
        BufferedReader br = new BufferedReader(new FileReader("csvMovieList"));
        movies.clear();
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
    }


    public static void readFromStreamUser(List<User> users, ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        Object read = objectInputStream.readObject();
        User userRead = (User) read;
        users.add(userRead);
    }

    public static <T> List <T> saveProgress(List<T> list, String fileName) throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(fileName));
        for (T result : list) {
            objectOutputStream.writeObject(result);
        }
        System.out.println("... "+fileName+" updated.");
        return list;
    }
}


