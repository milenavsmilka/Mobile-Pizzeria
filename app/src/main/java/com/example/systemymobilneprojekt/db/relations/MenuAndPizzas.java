package com.example.systemymobilneprojekt.db.relations;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.systemymobilneprojekt.db.tables.Menu;
import com.example.systemymobilneprojekt.db.tables.Pizza;

import java.util.List;


public class MenuAndPizzas {
    @Embedded
    Menu menu;
    @Relation(
            parentColumn = "menuId",
            entityColumn = "pizzaId"
    )
    public List<Pizza> pizzas;
}
