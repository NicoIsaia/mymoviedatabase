package com.mycompany.mymoviedatabase.UI;

import com.mycompany.mymoviedatabase.DAO.GenreDAO;
import com.mycompany.mymoviedatabase.DAO.MovieDAO;
import com.mycompany.mymoviedatabase.DAO.PersonDAO;
import com.mycompany.mymoviedatabase.model.Movie;
import com.mycompany.mymoviedatabase.model.Person;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
            System.out.println("4) Search/Filter");
            System.out.println("5) Delete");
            System.out.println("x) Close");

            System.out.print("Select option: ");
            option = scanner.nextLine().toLowerCase();
            if (option.equals("1")) {
                loadMenu(conn, scanner);

            } else if (option.equals("2")) {
                modifyMenu(conn, scanner);

            } else if (option.equals("3")) {
                listMenu(conn, scanner);

            } else if (option.equals("4")) {
                searchMenu(conn, scanner);

            } else if (option.equals("5")) {
                deleteMenu(conn, scanner);

            } else if (option.equalsIgnoreCase("x")) {
                break;
            } else if (option.equalsIgnoreCase("t")) {
                // field to test stuff -*-*-*-*-*-*-*-*-*-*-*-*-*-*-*====
                PersonDAO personDAO = new PersonDAO(conn);
                ArrayList<String> people = personDAO.listPeople();
                for (String person : people) {
                    System.out.println(person);
                }
                
                personDAO.deletePerson(12);
                
                people = personDAO.listPeople();
                for (String person : people) {
                    System.out.println(person);
                }                

            } else {
                System.out.println("Not a valid option.");
            }

            /*

            deleteMovie(conn, scanner);
            searchByTitle(conn, scanner);
             */
        }
    }

    public void loadMenu(Connection conn, Scanner scanner) throws SQLException {

        while (true) {
            System.out.println("");
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
            String option = scanner.nextLine().toLowerCase();
            if (option.equals("x")) {
                break;
            } else if (option.equals("1")) {
                addMovie(conn, scanner);
            } else if (option.equals("2")) {
                addPerson(conn, scanner);
            } else if (option.equals("3")) {
                addGenre();
            }
        }

    }

    public static void modifyMenu(Connection conn, Scanner scanner) throws SQLException {

        while (true) {
            System.out.println("");
            System.out.println("Modify Options");
            System.out.println("==================");
            System.out.println("1) Modify Movie");
            System.out.println("2) Modify Person");
            System.out.println("3) Modify Genre");
            System.out.println("4) Modify Directed");
            System.out.println("5) Modify Starring");
            System.out.println("6) Modify Genre of a Movie");
            System.out.println("x) Back");

            System.out.print("Select option: ");
            String option = scanner.nextLine().toLowerCase();
            if (option.equals("x")) {
                break;
            }
        }
    }

    public static void listMenu(Connection conn, Scanner scanner) throws SQLException {

        while (true) {
            System.out.println("");
            System.out.println("List Menu");
            System.out.println("==============");
            System.out.println("1) List Movies");
            System.out.println("2) List People");
            System.out.println("3) List Genres");
            System.out.println("x) Back");

            System.out.print("Select option: ");
            String option = scanner.nextLine().toLowerCase();
            if (option.equals("x")) {
                break;
            } else if (option.equals("1")) {
                listMovies(conn, scanner);
            } else if (option.equals("2")) {
                listPeople(conn, scanner);
            } else if (option.equals("3")) {
                System.out.println("TODO");
            }
        }
    }

    public static void searchMenu(Connection conn, Scanner scanner) throws SQLException {

        while (true) {
            System.out.println("");
            System.out.println("Search Menu");
            System.out.println("================");
            System.out.println("1) Search movie by title");
            System.out.println("2) Filter movies by year");
            System.out.println("3) Filter movies by director");
            System.out.println("4) Filter movies by star");
            System.out.println("5) Filter movies by score");
            System.out.println("6) Filter movies by genre");
            System.out.println("7) Filter movies by watched status");
            System.out.println("x) back");

            System.out.print("Select option: ");
            String option = scanner.nextLine().toLowerCase();
            if (option.equals("x")) {
                break;
            } else if (option.equals("1")) {
                searchByTitle(conn, scanner);
            } else if (option.equals("3")) {
                filterDirector(conn, scanner);
            }
        }
    }

    public static void deleteMenu(Connection conn, Scanner scanner) throws SQLException {

        while (true) {
            System.out.println("");
            System.out.println("Delete Options");
            System.out.println("==================");
            System.out.println("1) Delete Movie");
            System.out.println("2) Delete Person");
            System.out.println("3) Delete Genre");
            System.out.println("4) Delete Directed by");
            System.out.println("5) Delete Starring");
            System.out.println("6) Delete genre of movie");
            System.out.println("x) Back");

            System.out.print("Select option: ");
            String option = scanner.nextLine();
            if (option.equalsIgnoreCase("x")) {
                break;
            } else {
                System.out.println("Not a valid option.");
            }
        }
    }

    public static void addMovie(Connection conn, Scanner scanner) throws SQLException {

        System.out.println("");
        System.out.print("Insert title: ");
        String title = scanner.nextLine();
        System.out.println("");
        System.out.print("Insert year: ");
        Integer year = Integer.valueOf(scanner.nextLine());
        System.out.println("");
        System.out.print("Insert Score: ");
        Float score = Float.valueOf(scanner.nextLine());
        System.out.println("");
        System.out.print("Set watched status (type 'yes'/'y' or 'no'/'n'): ");
        String watched = scanner.nextLine().toLowerCase();

        Movie movie = new Movie(title, year);

        movie.setScore(score);

        if (watched.contains("y")) {
            movie.setWatched(true);
        } else {
            movie.setWatched(false);
        }

        MovieDAO movieDAO = new MovieDAO(conn);
        movieDAO.addMovie(movie);

    }

    private static void addPerson(Connection conn, Scanner scanner) throws SQLException {
        System.out.println("");
        System.out.print("Insert full name: ");
        String name = scanner.nextLine();

        Person person = new Person(name);

        PersonDAO personDAO = new PersonDAO(conn);

        personDAO.addPerson(person);
    }

    public void addGenre() throws SQLException {
        System.out.println("");
        System.out.print("Insert genre: ");
        String genre = scanner.nextLine();
        GenreDAO genreDAO = new GenreDAO(conn);

        genreDAO.addGenre(genre);
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

    private static void listPeople(Connection conn, Scanner scanner) throws SQLException {
        PreparedStatement selectPeople = conn.prepareStatement("SELECT name FROM people");
        ResultSet rs = selectPeople.executeQuery();
        System.out.println("");
        System.out.println("Printing people: ");
        while (rs.next()) {
            String name = rs.getString("name");
            System.out.println(name);
        }
    }

    public static void deleteMovie(Connection conn, Scanner scanner) throws SQLException {
        PreparedStatement deleteStatement = conn.prepareStatement("DELETE FROM movies WHERE title = ?");
        System.out.print("Title of the movie to delete: ");
        String movieTitle = scanner.nextLine();
        deleteStatement.setString(1, movieTitle);
        deleteStatement.execute();
        System.out.println("");
    }

    private static void filterDirector(Connection conn, Scanner scanner) throws SQLException {

        System.out.println("");
        System.out.println("Insert name of director: ");
        String name = scanner.nextLine();
        PersonDAO personDAO = new PersonDAO(conn);
        Person director = personDAO.getPerson(name);

        if (director != null) {
            System.out.println(director.getName() + " found!");
        } else {
            System.out.println("Director not found!");
        }

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
