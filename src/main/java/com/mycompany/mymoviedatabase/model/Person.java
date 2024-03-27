
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
    private List<Movie> filmography;

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
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
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.name);
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
        return Objects.equals(this.name, other.name);
    }
    
    
    
}
