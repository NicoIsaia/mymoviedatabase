
package com.mycompany.mymoviedatabase.model;

import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author NicoIsaia
 */
public class Person {
    private String Name;
    private LocalDate dateOfBirth;
    private List<Movie> filmography;

    public Person(String Name) {
        this.Name = Name;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    
    public void addMovie(Movie movie) {
        this.filmography.add(movie);
        // Test this and make a List or ArrayList of movies and try to remove
        // one to make a removeMovie Class
    }
}
