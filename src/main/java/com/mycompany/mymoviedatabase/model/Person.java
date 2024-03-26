
package com.mycompany.mymoviedatabase.model;

import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author NicoIsaia
 */
public class Person {
    private String name;
    private LocalDate dateOfBirth;
    private List<Movie> filmography;

    public Person(String name) {
        this.name = name;
    }

    // As yyyy-mm-dd
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    
    public void addMovie(Movie movie) {
        this.filmography.add(movie);
    }
    
    public void removeMovie(Integer movieId) {
        this.filmography.remove(Integer.valueOf(movieId));
    }

    @Override
    public String toString() {
        return this.name.toString();
    }
    
    
}
