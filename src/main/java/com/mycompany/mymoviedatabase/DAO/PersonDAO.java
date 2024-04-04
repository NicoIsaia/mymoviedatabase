package com.mycompany.mymoviedatabase.DAO;

import com.mycompany.mymoviedatabase.model.Person;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

    public Person getPerson(Integer id) throws SQLException {

        PreparedStatement selectPerson = conn.prepareStatement("SELECT name FROM people WHERE id = ?");

        selectPerson.setInt(1, id);

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

    public boolean personExists(Integer id) throws SQLException {
        PreparedStatement selectPerson = conn.prepareStatement("SELECT name FROM people WHERE id = ?");

        selectPerson.setInt(1, id);

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

    public void modifyPerson(Integer id, String name) throws SQLException {
        if (!personExists(name)) {
            if (personExists(id)) {

                String st = "UPDATE people "
                        + "SET name = ? WHERE id = ?";
                PreparedStatement updateSt = conn.prepareStatement(st);

                updateSt.setString(1, name);
                updateSt.setInt(2, id);

                Integer rows = updateSt.executeUpdate();

                if (rows == 1) {
                    System.out.println("One row updated correctly.");
                } else {
                    System.out.println("There was a problem.");
                }
            } else {
                System.out.println("Invalid ID.");
            }
        } else {
            System.out.println("Name already in database.");
        }
    }

    public ArrayList listPeople() throws SQLException {
        String st = "SELECT * FROM people";
        PreparedStatement select = conn.prepareStatement(st);
        ResultSet rs = select.executeQuery();
        ArrayList<String> people = new ArrayList<>();
        while (rs.next()) {
            String person = "id: " + rs.getInt("id") + " - " + rs.getString("name");
            people.add(person);
        }

        return people;
    }

    public void deletePerson(Integer id) throws SQLException {
        if (personExists(id)) {
            Person person = getPerson(id);
            String st = "DELETE FROM people WHERE id = ?";
            PreparedStatement deleteSt = conn.prepareStatement(st);
            deleteSt.setInt(1, id);
            Integer rows = deleteSt.executeUpdate();
            if (rows == 1) {
                System.out.println(person.getName() + " deleted successfully.");
            } else {
                System.out.println(rows + " people removed!");
            }
        } else {
            System.out.println("Invalid ID.");
        }
    }
}
