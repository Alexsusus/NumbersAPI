package com.example.numbersapi.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity
public class SearchQuery {

    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "number")
    public String number;

    @ColumnInfo(name = "fact")
    public String fact;

    @Override
    public String toString() {
        return "SearchQuery{" +
                "uid=" + uid +
                ", number='" + number + '\'' +
                ", fact='" + fact + '\'' +
                '}';
    }

    public SearchQuery() {
    }

}
