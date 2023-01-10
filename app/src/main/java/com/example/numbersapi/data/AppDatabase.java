package com.example.numbersapi.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.numbersapi.model.SearchQuery;

@Database(entities = {SearchQuery.class}, version = 1, exportSchema = false)

public abstract class AppDatabase extends RoomDatabase {
    public abstract SearchQueryDao searchQueryDao();
}
