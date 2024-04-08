/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
public class StarredDAO extends DatabaseDAO {

    public StarredDAO(Connection conn) {
        super(conn);
    }

    public void create(Integer movieID, Integer personID) throws SQLException {
        if (!searchActorsByMovie(movieID).contains(personID)) {
            String statement = "INSERT INTO starred (movie_id, people_id) "
                    + "VALUES(?, ?)";
            
            PreparedStatement st = conn.prepareStatement(statement);
            st.setInt(1, movieID);
            st.setInt(2, personID);
            
            st.execute();
            System.out.println("Relation created successfully.");
            
        } else {
            System.out.println("Relation already exists.");
        }
    }

    public ArrayList searchActorsByMovie(Integer movieID) throws SQLException {
        ArrayList<Integer> actors = new ArrayList<>();
        String statement = "SELECT people_id FROM starred WHERE movie_id = ?";
        PreparedStatement st = conn.prepareStatement(statement);
        st.setInt(1, movieID);
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            actors.add(rs.getInt("people_id"));
        }

        return actors;
    }
    
    public ArrayList searchMoviesByActor(Integer actorID) throws SQLException {
        ArrayList<Integer> movies = new ArrayList<>();
        String statement = "SELECT movie_id FROM starred WHERE people_id = ?";
        PreparedStatement st = conn.prepareStatement(statement);
        st.setInt(1, actorID);
        ResultSet rs = st.executeQuery();
        
        while (rs.next()) {
            movies.add(rs.getInt("movie_id"));
        }
        
        return movies;
    }
    
    public void delete(Integer movieID, Integer actorID) throws SQLException {
        if (searchActorsByMovie(movieID).contains(actorID)) {
            String statement = "DELETE FROM starred WHERE movie_id = ? AND people_id = ?";
            PreparedStatement st = conn.prepareCall(statement);
            st.setInt(1, movieID);
            st.setInt(2, actorID);
            Integer rows = st.executeUpdate();
            if (rows == 1) {
                System.out.println("Relation removed successfully.");
            } else {
                System.out.println("There was an error.");
            }
        } else {
            System.out.println("Relation does not exist.");
        }
    }
}
