
package com.mycompany.mymoviedatabase.DAO;

import com.mycompany.mymoviedatabase.model.Person;
import java.sql.Connection;
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
        
        
        
        System.out.println("Name: " + person.toString());
    }
}
