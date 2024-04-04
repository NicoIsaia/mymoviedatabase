package com.mycompany.mymoviedatabase.DAO;

import com.mycompany.mymoviedatabase.model.Movie;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author NicoIsaia
 */
public class MovieDAO extends DatabaseDAO {

    public MovieDAO(Connection conn) {
        super(conn);
    }

    public void addMovie(Movie movie) throws SQLException {

        String title = movie.getTitle();
        int year = movie.getYear();
        Float score = movie.getScore();
        boolean watched = movie.isWatched();

        if (!movieExists(title, year)) {

            PreparedStatement insertMovie = this.conn.prepareStatement("INSERT INTO movies (title, year, score, watched)"
                    + "VALUES(?, ?, ?, ?)");

            insertMovie.setString(1, title);
            insertMovie.setInt(2, year);
            insertMovie.setFloat(3, score);
            insertMovie.setBoolean(4, watched);

            int rowsAffected = insertMovie.executeUpdate();
            System.out.println("");
            if (rowsAffected == 1) {
                System.out.println(title + " (" + year + ") added to the database.");
            } else {
                System.out.println("Insert failed.");
            }

        } else {
            System.out.println("Movie already in database.");
        }

    }

    public Movie getMovie(String title, Integer year) throws SQLException {
        PreparedStatement selectMovie = this.conn.prepareStatement("SELECT title, year, score, watched FROM movies WHERE title = ? AND year = ?");
        selectMovie.setString(1, title);
        selectMovie.setInt(2, year);
        ResultSet rs = selectMovie.executeQuery();
        int count = 0;
        String movieTitle = "";
        int movieYear = 0;
        float score = 0.0F;
        boolean watched = false;
        while (rs.next()) {
            count++;
            movieTitle = rs.getString("title");
            movieYear = rs.getInt("year");
            score = rs.getFloat("score");
            watched = rs.getBoolean("watched");
        }

        if (count == 1) {
            Movie movie = new Movie(movieTitle, movieYear);
            movie.setScore(score);
            movie.setWatched(watched);
            return movie;
        } else if (count >= 1) {
            System.out.println("The movie is repeated in the database.");
            Movie movie = new Movie(movieTitle, movieYear);
            movie.setScore(score);
            movie.setWatched(watched);
            return movie;
        } else {
            return null;
        }

    }

    public boolean movieExists(String title, Integer year) throws SQLException {
        PreparedStatement selectMovie = this.conn.prepareStatement("SELECT title, year FROM movies WHERE title = ? AND year = ?");
        selectMovie.setString(1, title);
        selectMovie.setInt(2, year);
        ResultSet rs = selectMovie.executeQuery();

        return rs.next();
    }

    public Integer getMovieId(String title, Integer year) throws SQLException {
        String statement = "SELECT id FROM movies WHERE title = ? AND year = ?";
        PreparedStatement selectId = this.conn.prepareStatement(statement);
        selectId.setString(1, title);
        selectId.setInt(2, year);
        ResultSet rs = selectId.executeQuery();
        if (rs.next()) {
            Integer id = rs.getInt("id");
            return id;
        } else {
            return null;
        }

    }

    public ArrayList getByYear(Integer year) throws SQLException {
        ArrayList<Movie> result = new ArrayList<>();
        String statement = "SELECT title, year, score, watched from Movies where year = ?";
        PreparedStatement selectByYear = conn.prepareStatement(statement);
        selectByYear.setInt(1, year);
        ResultSet rs = selectByYear.executeQuery();
        while (rs.next()) {
            String title = rs.getString("title");
            Integer movieYear = rs.getInt("year");
            Float score = rs.getFloat("score");
            Boolean watched = rs.getBoolean("watched");
            Movie movie = new Movie(title, movieYear);
            movie.setScore(score);
            movie.setWatched(watched);
            result.add(movie);
        }

        return result;
    }

    public ArrayList getByScore(Float score) throws SQLException {
        ArrayList<Movie> result = new ArrayList<>();
        String st = "SELECT title, year, score, watched FROM movies WHERE score > ?";
        PreparedStatement selectByScore = conn.prepareStatement(st);
        selectByScore.setFloat(1, score);
        ResultSet rs = selectByScore.executeQuery();
        while (rs.next()) {
            String title = rs.getString("title");
            Integer movieYear = rs.getInt("year");
            Float movieScore = rs.getFloat("score");
            Boolean watched = rs.getBoolean("watched");
            Movie movie = new Movie(title, movieYear);
            movie.setScore(movieScore);
            movie.setWatched(watched);
            result.add(movie);
        }

        return result;
    }

    public Movie getById(Integer id) throws SQLException {
        String statement = "SELECT title, year, score, watched FROM movies WHERE id = ?";
        PreparedStatement selectById = conn.prepareStatement(statement);
        selectById.setInt(1, id);
        ResultSet rs = selectById.executeQuery();
        if (rs.next()) {
            Movie movie = new Movie(rs.getString("title"), rs.getInt("year"));
            movie.setScore(rs.getFloat("score"));
            movie.setWatched(rs.getBoolean("watched"));
            return movie;
        } else {
            return null;
        }
    }

    public void modifyMovie(Integer id, Movie movie) throws SQLException {
        String statement = "UPDATE movies "
                + "SET title = ?, year = ?, score = ?, watched = ? "
                + "WHERE id = ?";

        PreparedStatement updateStatement = conn.prepareStatement(statement);
        updateStatement.setString(1, movie.getTitle());
        updateStatement.setInt(2, movie.getYear());
        updateStatement.setFloat(3, movie.getScore());
        updateStatement.setBoolean(4, movie.isWatched());
        updateStatement.setInt(5, id);

        updateStatement.execute();
    }

    public void deleteMovie(Integer id) throws SQLException {
        Movie movie = getById(id);
        System.out.println("Deleting " + movie.getTitle()
                + " (" + movie.getYear() + ")");

        String statement = "DELETE FROM movies WHERE id = ?";
        PreparedStatement deleteStatement = conn.prepareStatement(statement);
        deleteStatement.setInt(1, id);
        Integer rows = deleteStatement.executeUpdate();
        if (rows == 1) {
            System.out.println("Movie deleted successfully.");
            System.out.println("");
        } else {
            System.out.println(rows + " movies removed!");
        }

    }

    // TODO searchByTitleSoft, searchByTitleStrict, movieToGenre, movieToStar, movieToDirector
    // getMovieId, getPersonId, getGenreId, getByTitle, getByYear...
}
