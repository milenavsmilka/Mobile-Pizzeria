package com.example.systemymobilneprojekt.db.relations;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.systemymobilneprojekt.db.tables.Client;
import com.example.systemymobilneprojekt.db.tables.Order;

import java.util.List;

public class ClientAndOrders {
    @Embedded
    Client client;
    @Relation(
            parentColumn = "clientId",
            entityColumn = "orderId"
    )
    public List<Order> orders;
}
