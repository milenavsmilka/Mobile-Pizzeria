package com.example.systemymobilneprojekt.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.systemymobilneprojekt.db.tables.Order;

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
