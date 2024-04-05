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
public class GenreDAO extends DatabaseDAO {

    public GenreDAO(Connection conn) {
        super(conn);
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

    public boolean genreExists(Integer id) throws SQLException {
        PreparedStatement selectGenre = this.conn.prepareStatement("SELECT genre FROM genres WHERE id = ?");
        selectGenre.setInt(1, id);
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

    public String getById(Integer id) throws SQLException {
        String st = "SELECT genre FROM genres WHERE id = ?";
        PreparedStatement select = conn.prepareStatement(st);
        select.setInt(1, id);
        ResultSet rs = select.executeQuery();

        if (rs.next()) {
            return rs.getString("genre");
        } else {
            System.out.println("Genre does not exist.");
            return null;
        }
    }

    public ArrayList listGenres() throws SQLException {
        ArrayList<String> genres = new ArrayList<>();
        String st = "SELECT * FROM genres";
        PreparedStatement select = conn.prepareStatement(st);
        ResultSet rs = select.executeQuery();

        while (rs.next()) {
            genres.add(rs.getString("genre"));
        }

        return genres;
    }

    public void deleteGenre(Integer id) throws SQLException {
        if (genreExists(id)) {
            String genre = getById(id);
            String st = "DELETE FROM genres WHERE id = ?";
            PreparedStatement delete = conn.prepareStatement(st);
            delete.setInt(1, id);
            Integer rows = delete.executeUpdate();

            if (rows == 1) {
                System.out.println(genre + " deleted successfully.");
            } else {
                System.out.println("There was an error.");
            }
        } else {
            System.out.println("Invalid id.");
        }
    }
}
