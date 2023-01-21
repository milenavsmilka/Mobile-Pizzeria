package com.example.systemymobilneprojekt.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface OrderDao{
    @Query("SELECT * FROM `order`")
    List<Order> getAllOrder();

    @Insert
    void insertOrder(Order... order);

    @Delete
    void deleteOrder(Order order);
}
