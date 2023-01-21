package com.example.systemymobilneprojekt.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Client.class, Order.class,Pizza.class,Menu.class,APIData.class},version = 2, exportSchema = true)
public abstract class PizzeriaDatabase extends RoomDatabase {

    public abstract ClientDao clientDao();
    public abstract MenuDao menuDao();
    public abstract OrderDao orderDao();
    public abstract PizzaDao pizzaDao();
    public abstract APIDataDao apiDataDao();

    private static PizzeriaDatabase INSTANCE;

    public static PizzeriaDatabase getInstance(Context context) {
        if(INSTANCE == null) INSTANCE = Room.databaseBuilder(context.getApplicationContext(), PizzeriaDatabase.class, "DB_TRAINLY").allowMainThreadQueries().build();

        return INSTANCE;
    }
    public static PizzeriaDatabase resetInstance(Context context) {
        if(INSTANCE == null) INSTANCE = Room.databaseBuilder(context, PizzeriaDatabase.class, "DB_TRAINLY")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
        return INSTANCE;
    }


}
