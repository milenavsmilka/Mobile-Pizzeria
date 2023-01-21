package com.example.systemymobilneprojekt.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Order {
    @PrimaryKey(autoGenerate = true)
    public int uid;
    @ColumnInfo(name = "sum")
    public Float sum;
    @ColumnInfo(name = "status")
    public String status;
    @ColumnInfo(name = "date")
    public String date;
}
