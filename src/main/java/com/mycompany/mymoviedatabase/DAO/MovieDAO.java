package com.mycompany.mymoviedatabase.DAO;

import com.mycompany.mymoviedatabase.model.Movie;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author NicoIsaia
 */
public class MovieDAO extends DatabaseDAO {

    private Movie movie;

    public MovieDAO(Connection conn) {
        super(conn);
    }

    public void addMovie(Movie movie) throws SQLException {
        this.movie = movie;

        String title = movie.getTitle();
        int year = movie.getYear();
        Float score = movie.getScore();
        boolean watched = movie.isWatched();

        PreparedStatement selectMovie = this.conn.prepareStatement("SELECT title, year FROM movies WHERE title = ? AND year = ?");
        selectMovie.setString(1, title);
        selectMovie.setInt(2, year);
        ResultSet rs = selectMovie.executeQuery();

        if (!rs.next()) {

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

    public String getTitle() {
        return this.movie.getTitle();
    }

}
