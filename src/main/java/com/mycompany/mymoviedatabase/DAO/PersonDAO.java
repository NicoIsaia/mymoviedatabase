
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
    
    private Person person;
    
    public PersonDAO(Connection conn) {
        super(conn);
    }
    
    public void addPerson(Person person) throws SQLException {
        this.person = person;
        
        String name = this.person.getName();
        
        // Later I'll move this to own method
        PreparedStatement selectPerson = conn.prepareStatement("SELECT name FROM people WHERE name = ?");
        
        selectPerson.setString(1, name);
        
        ResultSet rs = selectPerson.executeQuery();
        
        if (rs.next()) {
            System.out.println("Person already in database.");
        } else {
            // Add person to database
        }
        
        System.out.println("Name: " + person.toString());
    }
}
