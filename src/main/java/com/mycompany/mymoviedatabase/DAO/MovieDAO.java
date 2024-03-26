
package com.mycompany.mymoviedatabase.DAO;

import com.mycompany.mymoviedatabase.model.Movie;
import java.sql.Connection;

/**
 *
 * @author NicoIsaia
 */
public class MovieDAO extends DatabaseDAO {
    
    private Movie movie;
    
    public MovieDAO(Connection conn) {
        super(conn);
    }
    
    public void addMovie(Movie movie) {
        this.movie = movie;
    }
    
    public String getTitle() {
        return this.movie.getTitle();
    }
    
    
}
