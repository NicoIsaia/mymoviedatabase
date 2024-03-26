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
            
            /*

            PreparedStatement insertDirector = conn.prepareStatement("INSERT INTO people (name)"
                    + "VALUES(?)");

            insertDirector.setString(1, "James Cameron");

            insertDirector.execute();

            PreparedStatement selectDirector = conn.prepareStatement("SELECT id, name FROM people WHERE name ILIKE ?");

            selectDirector.setString(1, "%Cameron%");

            ResultSet result = selectDirector.executeQuery();

            while (result.next()) {
                String name = result.getString("name");
                System.out.println(name);
            }

            selectDirector.setString(1, "%cameron%");

            result = selectDirector.executeQuery();

            while (result.next()) {
                String name = result.getString("name");
                System.out.println("Lowercase search: " + name);
            }

            PreparedStatement deleteDirector = conn.prepareStatement("DELETE FROM PEOPLE WHERE name LIKE ?");

            deleteDirector.setString(1, "%Cameron%");

            deleteDirector.execute();
            */

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
