package com.mycompany.mymoviedatabase;

import com.mycompany.mymoviedatabase.UI.UserInterface;
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
            UserInterface ui = new UserInterface(conn, scanner);
            ui.run(conn, scanner);
            
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
