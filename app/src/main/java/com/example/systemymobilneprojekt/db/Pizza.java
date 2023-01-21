package com.example.systemymobilneprojekt.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity
public class Pizza {
    @PrimaryKey(autoGenerate = true)
    public int uid;
    @ColumnInfo(name = "name")
    public String name;
    @ColumnInfo(name = "price")
    public String price;
    @ColumnInfo(name = "dip")
    public String dip;
    @ColumnInfo(name = "description")
    public String description;
}
