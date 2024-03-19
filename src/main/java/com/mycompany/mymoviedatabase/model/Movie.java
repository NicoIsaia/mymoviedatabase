package com.mycompany.mymoviedatabase.model;

import java.util.Objects;

/**
 *
 * @author NicoIsaia
 */
public class Movie implements Comparable<Movie>{
    private String title;
    private int year;
    private float score;
    private String directedBy;
    private String starring;
    
    public Movie(String title, int year) {
        this.title = title;
        this.year = year;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setYear(int year) {
        this.year = year;
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