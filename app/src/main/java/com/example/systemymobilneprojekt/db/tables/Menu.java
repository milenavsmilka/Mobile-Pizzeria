package com.example.systemymobilneprojekt.db.tables;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.systemymobilneprojekt.db.tables.APIData;

@Entity
public class Menu {
    @PrimaryKey(autoGenerate = true)
    public int menuId;
    @Embedded
    public APIData apiData;

}
