package com.example.systemymobilneprojekt.db;

import android.content.Context;

import androidx.room.Room;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.systemymobilneprojekt.LoginActivity;
import com.example.systemymobilneprojekt.db.tables.Client;
import com.example.systemymobilneprojekt.db.tables.Pizza;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class DatabaseOperations {


    public static List<Client> getALlClients() {
        return null;
    }

    public static void addClientToDb() {
    }

    public static void saveNewClient(Client client) {
        PizzeriaDatabase db  = PizzeriaDatabase.getInstance();
        client.promotionPizzaId=-1;
        db.clientDao().insertClient(client);
    }

    public List<Client> getAllClients() {
        PizzeriaDatabase db  = PizzeriaDatabase.getInstance();
        return db.clientDao().getAllClient();
    }
    public static List<Pizza> getAllPizzas() {
        PizzeriaDatabase db  = PizzeriaDatabase.getInstance();
        return db.pizzaDao().getAllPizza();
    }
    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE client RENAME COLUMN user TO username");
        }
    };
    public void migrateDatabaseAlterTable(Context context){
        Room.databaseBuilder(context, PizzeriaDatabase.class, "pizzeriadatabase")
                .addMigrations(MIGRATION_1_2).build();
    }
    public static void addPizzasToDb()
    {
        PizzeriaDatabase db  = PizzeriaDatabase.getInstance();
        List<String> pizzaNames= Arrays.asList("Vesuvio",
                "Salami",
                "Grecka",
                "Swojska",
                "Podlaska",
                "Pepperoni",
                "Hawajska",
                "Capriciosa",
                "BBQ Chicken",
                "Contadini",
                "Pollo");
        List<String> pizzaDetails=Arrays.asList("ser mozzarella, szynka",
                "ser mozzarella, salami",
                "ser mozzarella, ser feta, pomidor, oliwki zielone, czosnek",
                "ser mozarella, kiełbasa żywiecka, ogórek kiszony",
                "ser mozarella, pomidor, cebula suszona, szynka",
                "ser mozarella, pestki słonecznika, cebula mieszana",
                "ser mozarella, ananas, kokos, szynka",
                "ser mozarella, szynka, pieczarki",
                "ser mozarella, kurczak, cebula czerwona",
                "ser mozarella, burak, rukola, ser camembert",
                "ser mozarella, kukurydza, kurczak");
        List<BigDecimal> pizzaPrices=Arrays.asList(new BigDecimal(25),
                new BigDecimal(25),
                new BigDecimal(25),
                new BigDecimal(25),
                new BigDecimal(25),
                new BigDecimal(25),
                new BigDecimal(25),
                new BigDecimal(25),
                new BigDecimal(25),
                new BigDecimal(25),
                new BigDecimal(25));
        for(int i=0;i<pizzaNames.size();i++) {
            Pizza pizza = new Pizza();
            pizza.name = pizzaNames.get(i);
            pizza.price = pizzaPrices.get(i);
            pizza.description = pizzaDetails.get(i);
            db.pizzaDao().insertPizza(pizza);
        }
    }

}
