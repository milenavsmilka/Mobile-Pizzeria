package com.example.systemymobilneprojekt.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ClientDao {

    @Query("SELECT * FROM client")
    List<Client> getAllClient();

    @Insert
    void insertClient(Client... client);

    @Delete
    void deleteClient(Client client);
}
