
package com.mycompany.mymoviedatabase.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

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

    public String getName() {
        return name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public List<Movie> getFilmography() {
        return filmography;
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

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + Objects.hashCode(this.name);
        hash = 23 * hash + Objects.hashCode(this.dateOfBirth);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Person other = (Person) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return Objects.equals(this.dateOfBirth, other.dateOfBirth);
    }    
    
}
