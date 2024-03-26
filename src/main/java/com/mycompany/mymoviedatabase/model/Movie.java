package com.mycompany.mymoviedatabase.model;

import java.util.Objects;
import java.util.ArrayList;

/**
 *
 * @author NicoIsaia
 */
public class Movie implements Comparable<Movie> {

    private String title;
    private int year;
    private ArrayList<String> genres;
    private float score;
    private String directedBy;
    private String starring;
    private boolean watched;

    public Movie(String title, int year) {
        this.title = title;
        this.year = year;
        this.genres = new ArrayList<>();
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setGenre(String genre) {
        if (!this.genres.contains(genre)) {
            this.genres.add(genre);
        }
    }

    public boolean findGenre(String genre) {
        return this.genres.contains(genre);
    }
    
    public ArrayList<String> getGenres() {
        return genres;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public void setDirectedBy(String directedBy) {
        this.directedBy = directedBy;
    }

    public void setStarring(String starring) {
        this.starring = starring;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public float getScore() {
        return score;
    }

    public String getDirectedBy() {
        return directedBy;
    }

    public String getStarring() {
        return starring;
    }

    public void setWatched(boolean watched) {
        this.watched = watched;
    }

    public boolean isWatched() {
        return watched;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.title);
        hash = 97 * hash + this.year;
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
        final Movie other = (Movie) obj;
        if (this.year != other.year) {
            return false;
        }
        return Objects.equals(this.title, other.title);
    }

    @Override
    public int compareTo(Movie o) {
        if (this.getScore() == o.getScore()) {
            return 0;
        } else if (this.getScore() > o.getScore()) {
            return 1;
        } else {
            return -1;
        }
    }

}
