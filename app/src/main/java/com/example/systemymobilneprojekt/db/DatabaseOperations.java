package com.example.systemymobilneprojekt.db;

import android.content.Context;

import androidx.room.Room;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.systemymobilneprojekt.db.tables.Client;

import java.util.List;

public class DatabaseOperations {

    public void saveNewClient(String username, String password, Context context) {
        PizzeriaDatabase db  = PizzeriaDatabase.resetInstance(context);
        //TODO getInstance
        Client client = new Client();
        client.username = username;
        client.password = password;
        client.promotionPizzaId=-1;
        db.clientDao().insertClient(client);
    }

    public List<Client> getAllClients(Context context) {
        PizzeriaDatabase db  = PizzeriaDatabase.resetInstance(context);
        //TODO getInstance
        return db.clientDao().getAllClient();
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
}
