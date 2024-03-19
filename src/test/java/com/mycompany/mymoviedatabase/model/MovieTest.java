/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.mymoviedatabase.model;

import java.util.Objects;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author NicoIsaia
 */
public class MovieTest {

    public MovieTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        Movie movie = new Movie("name", 2024);
        Movie differentMovie = new Movie("otherName", 2023);
        Movie sameMovie = new Movie("name", 2024);
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of setTitle method, of class Movie.
     */
    @Test
    public void testSetTitle() {
        System.out.println("setTitle");
        String title = "title";
        Movie instance = new Movie("name", 2024);
        instance.setTitle(title);
        assertEquals(instance.getTitle(), title);
    }

    /**
     * Test of setYear method, of class Movie.
     */
    @Test
    public void testSetYear() {
        System.out.println("setYear");
        int year = 0;
        Movie instance = new Movie("name", 2024);
        instance.setYear(year);
        assertEquals(instance.getYear(), 0);
    }

    /**
     * Test of setScore method, of class Movie.
     */
    @Test
    public void testSetScore() {
        System.out.println("setScore");
        float score = 10.0F;
        Movie instance = new Movie("name", 2024);
        instance.setScore(score);
        assertEquals(instance.getScore(), 10.0F);
    }

    /**
     * Test of setDirectedBy method, of class Movie.
     */
    @Test
    public void testSetDirectedBy() {
        System.out.println("setDirectedBy");
        String directedBy = "director";
        Movie instance = new Movie("name", 2024);
        instance.setDirectedBy(directedBy);
        assertEquals(instance.getDirectedBy(), "director");
    }

    /**
     * Test of setStarring method, of class Movie.
     */
    @Test
    public void testSetStarring() {
        System.out.println("setStarring");
        String starring = "actor";
        Movie instance = new Movie("name", 2024);
        instance.setStarring(starring);
        assertEquals(instance.getStarring(), "actor");
    }

    /**
     * Test of getTitle method, of class Movie.
     */
    @Test
    public void testGetTitle() {
        System.out.println("getTitle");
        Movie instance = new Movie("name", 2024);
        String expResult = "name";
        String result = instance.getTitle();
        assertEquals(expResult, result);
    }

    /**
     * Test of getYear method, of class Movie.
     */
    @Test
    public void testGetYear() {
        System.out.println("getYear");
        Movie instance = new Movie("name", 2024);
        int expResult = 2024;
        int result = instance.getYear();
        assertEquals(expResult, result);
    }

    /**
     * Test of getScore method, of class Movie.
     */
    @Test
    public void testGetScore() {
        System.out.println("getScore");
        Movie instance = new Movie("name", 2024);
        instance.setScore(6.0F);
        float expResult = 6.0F;
        float result = instance.getScore();
        assertEquals(expResult, result, 0);
    }

    /**
     * Test of getDirectedBy method, of class Movie.
     */
    @Test
    public void testGetDirectedBy() {
        System.out.println("getDirectedBy");
        Movie instance = new Movie("name", 2024);
        instance.setDirectedBy("director person");
        String expResult = "director person";
        String result = instance.getDirectedBy();
        assertEquals(expResult, result);
    }

    /**
     * Test of getStarring method, of class Movie.
     */
    @Test
    public void testGetStarring() {
        System.out.println("getStarring");
        Movie instance = new Movie("name", 2024);
        instance.setStarring("an actor or actress");
        String expResult = "an actor or actress";
        String result = instance.getStarring();
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class Movie.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Movie instance = new Movie("name", 2024);
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(instance.getTitle());
        hash = 97 * hash + instance.getYear();
        int expResult = hash;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Movie.
     * Same movie.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = new Movie("name", 2024);
        Movie instance = new Movie("name", 2024);
        boolean expResult = true;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Movie.
     * Different movie.
     */
    @Test
    public void testEqualsDifferent() {
        System.out.println("equals");
        Object obj = new Movie("title", 2024);
        Movie instance = new Movie("name", 2024);
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of compareTo method, of class Movie.
     */
    @Test
    public void testCompareTo() {
        System.out.println("compareTo");
        Movie o = new Movie("name", 2024);
        o.setScore(1.0F);
        Movie instance = new Movie("title", 2024);
        instance.setScore(7.0F);
        int expResult = 1;
        int result = instance.compareTo(o);
        assertEquals(expResult, result);
    }

}
