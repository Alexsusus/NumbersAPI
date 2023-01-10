package com.example.numbersapi;

import android.app.Application;

import androidx.room.Room;

import com.example.numbersapi.data.AppDatabase;
import com.example.numbersapi.data.SearchQueryDao;

public class App extends Application {

    private AppDatabase database;

    private SearchQueryDao sqDao;

    private static App instance;

    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        database = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "app-database")
                .allowMainThreadQueries()
                .build();

        sqDao = database.searchQueryDao();

    }

    public AppDatabase getDatabase() {
        return database;
    }

    public void setDatabase(AppDatabase database) {
        this.database = database;
    }

    public SearchQueryDao getSqDao() {
        return sqDao;
    }

    public void setSqDao(SearchQueryDao sqDao) {
        this.sqDao = sqDao;
    }
}
