package com.example.systemymobilneprojekt.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.math.BigDecimal;

@Entity
public class APIData {
    @PrimaryKey(autoGenerate = true)
    public int uid;
    @ColumnInfo(name = "temperature")
    public Float temperature;
    @ColumnInfo(name = "pressure")
    public Float pressure;
    @ColumnInfo(name = "menuId")
    public int menuId;
}
