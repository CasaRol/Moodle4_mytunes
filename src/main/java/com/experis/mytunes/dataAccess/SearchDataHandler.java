package com.experis.mytunes.dataAccess;

import com.experis.mytunes.models.SearchResult;
import com.experis.mytunes.models.TopGenre;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class SearchDataHandler {

    private String URL = DataBaseConURL.URL;
    private Connection conn = DataBaseConURL.conn;

    public ArrayList<SearchResult> getSearch(String searchTerm) {
        ArrayList<SearchResult> results = new ArrayList<>();

        try {
            conn = DriverManager.getConnection(URL);

            //Query database
            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT Track.Name, Track.Composer, Album.Title, Genre.Name\n" +
                            "FROM Track\n" +
                            "JOIN Album ON Track.AlbumId=Album.AlbumId\n" +
                            "JOIN Genre ON Track.GenreId=Genre.GenreId\n" +
                            "WHERE Track.Name=?");

            preparedStatement.setString(1, searchTerm);

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                results.add(
                        new SearchResult(
                                searchTerm,
                                resultSet.getString("Name"),
                                resultSet.getString("Composer"),
                                resultSet.getString("Title"),
                                resultSet.getString("Name")
                        )
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
        return results;
    }
}
