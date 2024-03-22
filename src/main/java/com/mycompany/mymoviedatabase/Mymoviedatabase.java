package com.mycompany.mymoviedatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author NicoIsaia
 */
public class Mymoviedatabase {

    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        try (Connection conn = DriverManager.getConnection("jdbc:h2:./moviedb;INIT=RUNSCRIPT FROM 'classpath:sqlTemplate.sql';")) {
            String option = "";
            while (true) {
                System.out.println("Options");
                System.out.println("==========");
                System.out.println("1) Add movie");
                System.out.println("2) List movies");
                System.out.println("3) Delete movie");
                System.out.println("4) Search by title");
                System.out.println("x) Close");

                System.out.print("Select option: ");
                option = scanner.nextLine();
                if (option.equals("1")) {
                    addMovie(conn, scanner);

                } else if (option.equals("2")) {
                    listMovies(conn, scanner);

                } else if (option.equals("3")) {
                    deleteMovie(conn, scanner);

                } else if (option.equals("4")) {
                    searchByTitle(conn, scanner);

                } else if (option.equalsIgnoreCase("x")) {
                    break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
