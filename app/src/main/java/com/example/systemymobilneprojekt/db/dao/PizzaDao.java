package com.example.systemymobilneprojekt.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.systemymobilneprojekt.db.tables.Pizza;

import java.util.List;

@Dao
public interface PizzaDao {
    @Query("SELECT * FROM pizza")
    List<Pizza> getAllPizza();

    @Insert
    void insertPizza(Pizza... pizza);

    @Delete
    void deletePizza(Pizza pizza);
}
