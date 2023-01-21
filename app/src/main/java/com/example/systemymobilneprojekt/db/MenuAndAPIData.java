package com.example.systemymobilneprojekt.db;

import androidx.room.Embedded;
import androidx.room.Relation;

public class MenuAndAPIData {
    @Embedded Menu menu;
    @Relation(
            parentColumn = "uid",
            entityColumn = "menuId"
    )
    APIData apiData;
}
