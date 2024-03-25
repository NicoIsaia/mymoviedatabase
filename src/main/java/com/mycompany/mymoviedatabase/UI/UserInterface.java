package com.mycompany.mymoviedatabase.UI;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author NicoIsaia
 */
public class UserInterface {

    private Scanner scanner;
    private Connection conn;

    public UserInterface(Connection conn, Scanner scanner) {
        this.scanner = scanner;
        this.conn = conn;
    }

    public void run(Connection conn, Scanner scanner) throws SQLException {
        String option = "";
        while (true) {
            System.out.println("Options");
            System.out.println("==========");
            System.out.println("1) Load");
            System.out.println("2) Modify");
            System.out.println("3) List");
            System.out.println("4) Search");
            System.out.println("5) Delete");
            System.out.println("x) Close");

            System.out.print("Select option: ");
            option = scanner.nextLine();
            if (option.equals("1")) {
                loadMenu(conn, scanner);

            } else if (option.equals("2")) {
                listMovies(conn, scanner);

            } else if (option.equals("3")) {
                deleteMovie(conn, scanner);

            } else if (option.equals("4")) {
                searchByTitle(conn, scanner);

            } else if (option.equalsIgnoreCase("x")) {
                break;
            }

            /*
            
            
             */
        }
    }

    public static void loadMenu(Connection conn, Scanner scanner) throws SQLException {

        while (true) {
            System.out.println("Load options");
            System.out.println("==============");
            System.out.println("1) Load Movie");
            System.out.println("2) Load Person");
            System.out.println("3) Load Genre");
            System.out.println("4) Set Directed");
            System.out.println("5) Set Starred");
            System.out.println("6) Set Genre");
            System.out.println("x) Back");
            
            System.out.print("Select option: ");
            String option = scanner.nextLine();
            if (option.equals("x")) {
                break;
            } else if (option.equals("1")) {
                addMovie(conn, scanner);
            }
        }

    }

    public static void addMovie(Connection conn, Scanner scanner) throws SQLException {
        PreparedStatement insertStatement = conn.prepareStatement("INSERT INTO movies (title, year)"
                + "VALUES(?,?)");

        System.out.println("");
        System.out.print("Insert title: ");
        String title = scanner.nextLine();
        System.out.print("Insert year: ");
        Integer year = Integer.valueOf(scanner.nextLine());
        System.out.println("");

        insertStatement.setString(1, title);
        insertStatement.setInt(2, year);

        insertStatement.execute();
    }

    public static void listMovies(Connection conn, Scanner scanner) throws SQLException {
        System.out.println("");
        System.out.println("Printing movies: ");
        PreparedStatement selectStatement = conn.prepareStatement("SELECT title, year FROM movies");
        ResultSet rS = selectStatement.executeQuery();
        while (rS.next()) {
            String movieTitle = rS.getString("title");
            Integer movieYear = rS.getInt("year");
            System.out.println(movieTitle + " (" + movieYear + ")");
        }
        System.out.println(" ");
    }

    public static void deleteMovie(Connection conn, Scanner scanner) throws SQLException {
        PreparedStatement deleteStatement = conn.prepareStatement("DELETE FROM movies WHERE title = ?");
        System.out.print("Title of the movie to delete: ");
        String movieTitle = scanner.nextLine();
        deleteStatement.setString(1, movieTitle);
        deleteStatement.execute();
        System.out.println("");
    }

    public static void searchByTitle(Connection conn, Scanner scanner) throws SQLException {

        System.out.print("Search movie by title: ");
        String movieTitle = scanner.nextLine().toLowerCase();
        movieTitle = "%" + movieTitle + "%";
        PreparedStatement selectStatement = conn.prepareStatement("SELECT title, year, score FROM movies WHERE title ILIKE ?");
        selectStatement.setString(1, movieTitle);
        ResultSet rs = selectStatement.executeQuery();
        String title = "";
        Integer year = 0;
        Float score = 0.0F;
        System.out.println("");
        System.out.println("Result");
        System.out.println("-------");
        while (rs.next()) {
            title = rs.getString("title");
            year = rs.getInt("year");
            score = rs.getFloat("score");
            System.out.println(title + " (" + year + "), score: " + score);
        }
        System.out.println("");
    }

}
