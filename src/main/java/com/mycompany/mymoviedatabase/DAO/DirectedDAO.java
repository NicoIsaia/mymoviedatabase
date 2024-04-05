package com.mycompany.mymoviedatabase.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author NicoIsaia
 */
public class DirectedDAO extends DatabaseDAO {

    public DirectedDAO(Connection conn) {
        super(conn);
    }

    public void create(Integer movieID, Integer personID) throws SQLException {
        if (!searchMoviesByDirector(personID).contains(movieID)) {
            String st = "INSERT INTO directed (movie_id, people_id) "
                    + "VALUES (?, ?)";

            PreparedStatement insertStatement = conn.prepareStatement(st);
            insertStatement.setInt(1, movieID);
            insertStatement.setInt(2, personID);

            Integer rows = insertStatement.executeUpdate();

            if (rows == 1) {
                System.out.println("Relation created successfully.");
            } else {
                System.out.println("There was an error.");
            }
        } else {
            System.out.println("Relation already exists.");
        }
    }

    public ArrayList searchMoviesByDirector(Integer directorID) throws SQLException {
        ArrayList<Integer> movies = new ArrayList<>();
        String statement = "SELECT movie_id FROM directed WHERE people_id = ?";
        PreparedStatement st = conn.prepareStatement(statement);
        st.setInt(1, directorID);
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            movies.add(rs.getInt("movie_id"));
        }

        return movies;
    }
    
    public ArrayList searchDirectorsByMovie(Integer movieID) throws SQLException {
        ArrayList<Integer> directors = new ArrayList<>();
        String statement = "SELECT people_id FROM directed WHERE movie_id = ?";
        PreparedStatement st = conn.prepareStatement(statement);
        st.setInt(1, movieID);
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            directors.add(rs.getInt("people_id"));
        }

        return directors;
    }
}
