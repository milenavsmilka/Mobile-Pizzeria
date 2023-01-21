package com.example.systemymobilneprojekt.db;

import android.content.Context;

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
}
