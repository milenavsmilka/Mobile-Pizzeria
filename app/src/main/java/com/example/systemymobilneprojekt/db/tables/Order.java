package com.example.systemymobilneprojekt.db.tables;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.math.BigDecimal;

@Entity
public class Order {
    @PrimaryKey(autoGenerate = true)
    public int orderId;
    @ColumnInfo(name = "sum")
    public BigDecimal sum;
    @ColumnInfo(name = "status")
    public String status;
    @ColumnInfo(name = "date")
    public String date;
}
