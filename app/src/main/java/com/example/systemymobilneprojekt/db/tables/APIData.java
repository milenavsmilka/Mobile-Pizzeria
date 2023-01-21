package com.example.systemymobilneprojekt.db.tables;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.math.BigDecimal;

@Entity
public class APIData {
    @PrimaryKey(autoGenerate = true)
    public int apiDataId;
    @ColumnInfo(name = "temperature")
    public BigDecimal temperature;
    @ColumnInfo(name = "pressure")
    public BigDecimal pressure;
}
