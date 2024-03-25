
package com.mycompany.mymoviedatabase.DAO;

import com.mycompany.mymoviedatabase.model.Movie;
import java.sql.Connection;

/**
 *
 * @author NicoIsaia
 */
public class MovieDAO extends DatabaseDAO {
    
    public MovieDAO(Connection conn) {
        super(conn);
    }
    
    public void addMovie(Movie movie) {
        
    }
    
    
}
