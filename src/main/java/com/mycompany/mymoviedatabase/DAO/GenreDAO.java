package com.mycompany.mymoviedatabase.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author NicoIsaia
 */
public class GenreDAO {

    private Connection conn;

    public GenreDAO(Connection conn) {
        this.conn = conn;
    }

    public void addGenre(String genre) throws SQLException {

        if (!genreExists(genre)) {

            PreparedStatement insertGenre = conn.prepareCall("INSERT INTO genres (genre)"
                    + "VALUES (?)");

            insertGenre.setString(1, genre);

            Integer affectedRows = insertGenre.executeUpdate();

            if (affectedRows == 1) {
                System.out.println("Genre " + genre + " added successfully.");
            } else {
                System.out.println("There was a problem");
            }
        } else {
            System.out.println("Genre already in database.");
        }
    }

    public boolean genreExists(String genre) throws SQLException {
        PreparedStatement selectGenre = this.conn.prepareStatement("SELECT genre FROM genres WHERE genre = ?");
        selectGenre.setString(1, genre);
        ResultSet rs = selectGenre.executeQuery();

        return rs.next();
    }

    public Integer getGenreId(String genre) throws SQLException {
        if (genreExists(genre)) {
            PreparedStatement selectGenre = this.conn.prepareStatement("SELECT id FROM genres WHERE genre = ?");
            selectGenre.setString(1, genre);
            ResultSet rs = selectGenre.executeQuery();

            if (rs.next()) {
                Integer id = rs.getInt("id");
                return id;
            } else {
                return -2;
            }
        } else {
            return -1;
        }
    }
}
