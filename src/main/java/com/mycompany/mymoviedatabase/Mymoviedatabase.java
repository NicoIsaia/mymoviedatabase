package com.mycompany.mymoviedatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author NicoIsaia
 */
public class Mymoviedatabase {

    public static void main(String[] args) throws SQLException {
        try (Connection conn = DriverManager.getConnection("jdbc:h2:./moviedb")) {
            boolean isValid = conn.isValid(0);
            System.out.println("Do we have a valid connection: " + isValid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
