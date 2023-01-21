package com.example.systemymobilneprojekt.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Client {
    @PrimaryKey(autoGenerate = true)
    public int uid;
    @ColumnInfo(name = "username")
    public String username;
    @ColumnInfo(name = "password")
    public String password;
    @ColumnInfo(name = "promotionPizzaId")
    public int promotionPizzaId;
}
