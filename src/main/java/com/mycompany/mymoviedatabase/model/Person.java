
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
    private List<Integer> filmography;

    public Person(String Name) {
        this.Name = Name;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    
    public void addMovie(Integer movieId) {
        this.filmography.add(movieId);
    }
    
    public void removeMovie(Integer movieId) {
        this.filmography.remove(Integer.valueOf(movieId));
    }
}
