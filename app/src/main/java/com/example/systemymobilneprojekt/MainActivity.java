package com.example.systemymobilneprojekt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.systemymobilneprojekt.db.tables.Client;
import com.example.systemymobilneprojekt.db.DatabaseOperations;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DatabaseOperations databaseOperations = new DatabaseOperations();
        // przyklad dodania uzytkownika i wyswietlenia go w logcat
        databaseOperations.saveNewClient("adam","spadam",this.getApplicationContext());
        List<Client> list = databaseOperations.getAllClients(this.getApplicationContext());
        for (Client client: list) {
            Log.d("NaszeLogi", client.clientId+" "+client.username+" "+client.password+" "+client.promotionPizzaId);
        }

        new DataFromApi().execute();
    }
}