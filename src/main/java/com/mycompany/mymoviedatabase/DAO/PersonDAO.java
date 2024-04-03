package com.mycompany.mymoviedatabase.DAO;

import com.mycompany.mymoviedatabase.model.Person;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author NicoIsaia
 */
public class PersonDAO extends DatabaseDAO {

    public PersonDAO(Connection conn) {
        super(conn);
    }

    public void addPerson(Person person) throws SQLException {

        String name = person.getName();

        if (personExists(name)) {
            System.out.println("Person already in database.");
        } else {
            PreparedStatement insertPerson = conn.prepareStatement("INSERT INTO people (name)"
                    + "VALUES (?)");

            insertPerson.setString(1, name);
            Integer rowsAffected = insertPerson.executeUpdate();
            System.out.println("");
            if (rowsAffected == 1) {
                System.out.println(name + " added to the database.");
            } else {
                System.out.println("Insert failed.");
            }

        }
    }

    public Person getPerson(String name) throws SQLException {

        PreparedStatement selectPerson = conn.prepareStatement("SELECT name FROM people WHERE name ILIKE ?");

        selectPerson.setString(1, name);

        ResultSet rs = selectPerson.executeQuery();

        if (rs.next()) {
            return new Person(rs.getString("name"));
        } else {
            return null;
        }
    }

    public boolean personExists(String name) throws SQLException {
        PreparedStatement selectPerson = conn.prepareStatement("SELECT name FROM people WHERE name = ?");

        selectPerson.setString(1, name);

        ResultSet rs = selectPerson.executeQuery();

        return rs.next();
    }
    
    public Integer getPersonId(Person person) throws SQLException {
        if (personExists(person.getName())) {
            String statement = "SELECT id FROM people WHERE name = ?";
            PreparedStatement selectStatement = conn.prepareStatement(statement);
            selectStatement.setString(1, person.getName());
            ResultSet rs = selectStatement.executeQuery();
            if (rs.next()) {
                Integer id = rs.getInt("id");
                return id;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
    
    public Integer getPersonId(String name) throws SQLException {
        if (personExists(name)) {
            String statement = "SELECT id FROM people WHERE name = ?";
            PreparedStatement selectStatement = conn.prepareStatement(statement);
            selectStatement.setString(1, name);
            ResultSet rs = selectStatement.executeQuery();
            if (rs.next()) {
                Integer id = rs.getInt("id");
                return id;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
}
