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
public class MovieGenresDAO extends DatabaseDAO {
    
    public MovieGenresDAO(Connection conn) {
        super(conn);
    }
    
    public void create(Integer movieID, Integer genreID) throws SQLException {
        if(!searchMoviesByGenre(genreID).contains(movieID)) {
            String statement = "INSERT INTO movie_genres (movie_id, genre_id) "
                    + "VALUES (?, ?)";
            
            PreparedStatement st = conn.prepareStatement(statement);
            st.setInt(1, movieID);
            st.setInt(2, genreID);
            
            Integer rows = st.executeUpdate();
            
            if(rows == 1) {
                System.out.println("Relation created successfully.");
            } else {
                System.out.println("An error ocurred.");
            }
        } else {
            System.out.println("Relation already exists.");
        }
    }
    
    public ArrayList searchMoviesByGenre(Integer genreID) throws SQLException {
        ArrayList<Integer> movies = new ArrayList<>();
        String statement = "SELECT movie_id FROM movie_genres WHERE genre_id = ?";
        PreparedStatement st = conn.prepareStatement(statement);
        
        st.setInt(1, genreID);
        
        ResultSet rs = st.executeQuery();
        
        while(rs.next()) {
            movies.add(rs.getInt("movie_id"));
        }
        
        return movies;
    }
    
    public ArrayList searchGenresByMovie(Integer movieID) throws SQLException {
        ArrayList<Integer> genres = new ArrayList<>();
        String statement = "SELECT genre_id FROM movie_genres WHERE movie_id = ?";
        PreparedStatement st = conn.prepareStatement(statement);
        
        st.setInt(1, movieID);
        
        ResultSet rs = st.executeQuery();
        
        while(rs.next()) {
            genres.add(rs.getInt("genre_id"));
        }
        
        return genres;
    }
    
    public void delete(Integer movieID, Integer genreID) throws SQLException {
        if (searchMoviesByGenre(genreID).contains(movieID)) {
            String statement = "DELETE FROM movie_genres WHERE movie_id = ? AND genre_id = ?";
            PreparedStatement st = conn.prepareStatement(statement);
            st.setInt(1, movieID);
            st.setInt(2, genreID);
            Integer rows = st.executeUpdate();
            if(rows == 1) {
                System.out.println("Relation removed successfully.");
            } else {
                System.out.println("An error ocurred.");
            }
        } else {
            System.out.println("Relation does not exist.");
        }
    }
}
