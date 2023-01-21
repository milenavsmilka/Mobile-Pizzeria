package com.example.systemymobilneprojekt.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.systemymobilneprojekt.db.tables.Client;

import java.util.List;

@Dao
public interface ClientDao {

    @Query("SELECT * FROM client")
    List<Client> getAllClient();

    @Query("SELECT * FROM client WHERE username = :username AND password = :password")
    Client getCertainClient(String username, String password);

    @Insert
    void insertClient(Client... client);

    @Delete
    void deleteClient(Client client);
}
