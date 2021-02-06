package com.experis.mytunes.dataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class RandomDataHandler {

    private String URL = DataBaseConURL.URL;
    private Connection conn = DataBaseConURL.conn;

    public ArrayList<String> getRandomArtists() {
        ArrayList<String> artists = new ArrayList<>();

        try {
            conn = DriverManager.getConnection(URL);

            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT Artist.Name FROM Artist ORDER BY random() LIMIT 5;");

            ResultSet result = preparedStatement.executeQuery();

            while(result.next()) {
                artists.add(
                        result.getString("Name")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }

        return artists;
    }

    public ArrayList<String> getRandomSongs() {
        ArrayList<String> songs = new ArrayList<>();

        try {
            conn = DriverManager.getConnection(URL);

            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT Track.Name FROM Track ORDER BY random() LIMIT 5;");

            ResultSet result = preparedStatement.executeQuery();

            while(result.next()) {
                songs.add(
                        result.getString("Name")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }

        return songs;
    }

    public Object getRandomGenres() {
        ArrayList<String> genres = new ArrayList<>();

        try {
            conn = DriverManager.getConnection(URL);

            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT Genre.Name FROM Genre ORDER BY random() LIMIT 5;");

            ResultSet result = preparedStatement.executeQuery();

            while(result.next()) {
                genres.add(
                        result.getString("Name")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }

        return genres;
    }
}
