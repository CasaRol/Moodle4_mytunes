package com.experis.mytunes.models;

public class TopGenre {
    private String firstName;
    private String lastName;
    private String genreName;
    private int boughtGenres;

    public TopGenre(String firstName, String lastName, String genreName, int boughtGenres) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.genreName = genreName;
        this.boughtGenres = boughtGenres;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    public int getBoughtGenres() {
        return boughtGenres;
    }

    public void setBoughtGenres(int boughtGenres) {
        this.boughtGenres = boughtGenres;
    }
}
