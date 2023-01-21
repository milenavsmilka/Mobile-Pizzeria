package com.example.systemymobilneprojekt.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface APIDataDao {

    @Query("SELECT * FROM apidata")
    List<APIData> getAllAPIData();

    @Insert
    void insertAPIData(APIData... apidata);

    @Delete
    void deleteAPIData(APIData apidata);
}
