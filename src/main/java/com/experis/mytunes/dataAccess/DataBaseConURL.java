package com.experis.mytunes.dataAccess;

import java.sql.Connection;

public class DataBaseConURL {

    public static String URL = "jdbc:sqlite::resource:Chinook_Sqlite.sqlite";
    public static Connection conn = null;
}
