package com.example.systemymobilneprojekt.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity
public class Menu {
    @PrimaryKey(autoGenerate = true)
    public int uid;
//    @ColumnInfo(name = "pizzas")
//    public List<Pizza> pizzas;
//    @ColumnInfo(name = "ingredients")
//    public List<String> ingredients;
}
