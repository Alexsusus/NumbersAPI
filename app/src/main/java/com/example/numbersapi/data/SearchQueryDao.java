package com.example.numbersapi.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.numbersapi.model.SearchQuery;

import java.util.List;

import io.reactivex.Observable;


@Dao
public interface SearchQueryDao {

    @Query("SELECT * FROM SearchQuery")
    Observable<List<SearchQuery>> getAll();
    //List<SearchQuery> getAll();

    @Query("SELECT * FROM SearchQuery")
    LiveData<List<SearchQuery>> getAllLiveData();

    @Query("SELECT * FROM SearchQuery WHERE uid IN (:sqIds)")
    List<SearchQuery> loadAllByIds(int[] sqIds);

    @Query("SELECT * FROM SearchQuery WHERE uid = :uid LIMIT 1")
    SearchQuery findById(int uid);
    //Flowable<SearchQuery> getById(int uid);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(SearchQuery sq);

    @Update
    void update(SearchQuery sq);

    @Delete
    void delete(SearchQuery sq);


}
