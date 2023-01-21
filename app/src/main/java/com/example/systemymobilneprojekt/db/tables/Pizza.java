package com.example.systemymobilneprojekt.db.tables;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity
public class Pizza {
    @PrimaryKey(autoGenerate = true)
    public int pizzaId;
    @ColumnInfo(name = "name")
    public String name;
    @ColumnInfo(name = "price")
    public String price;
    @ColumnInfo(name = "description")
    public String description;
}
