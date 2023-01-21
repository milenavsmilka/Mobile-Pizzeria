package com.example.systemymobilneprojekt.db.relations;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Relation;

import com.example.systemymobilneprojekt.db.tables.Order;
import com.example.systemymobilneprojekt.db.tables.Pizza;

import java.util.List;

@Entity
public class PizzaAndOrder {
    @Embedded
    Order order;
    @Relation(
            parentColumn = "orderId",
            entityColumn = "pizzaId"
    )
    public List<Pizza> pizzas;
}
