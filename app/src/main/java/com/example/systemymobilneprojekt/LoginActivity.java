package com.example.systemymobilneprojekt;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.systemymobilneprojekt.api.DataFromApi;
import com.example.systemymobilneprojekt.app.PizzaListActivity;
import com.example.systemymobilneprojekt.db.DatabaseOperations;
import com.example.systemymobilneprojekt.db.PizzeriaDatabase;
import com.example.systemymobilneprojekt.db.tables.Client;

import java.util.List;


public class LoginActivity extends Activity {
    Button logButton, gotoYTButton;
    EditText userEditText, passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        DataFromApi dataFromApi = new DataFromApi();
        Thread t1 = new Thread(dataFromApi);
        t1.start();
        super.onCreate(savedInstanceState);
        PizzeriaDatabase.setInstance(this.getApplicationContext());
        //DatabaseOperations.addPizzasToDb();
        setContentView(R.layout.login_activity);


        NotificationChannel channel = new NotificationChannel("Witaj w pizzerii!", "Witaj w pizzerii!", NotificationManager.IMPORTANCE_DEFAULT);
        NotificationManager manager = getSystemService(NotificationManager.class);
        manager.createNotificationChannel(channel);

        logButton = findViewById(R.id.loginButton);
        userEditText = findViewById(R.id.usernameInput);
        passwordEditText = findViewById(R.id.passwordInput);
        gotoYTButton = findViewById(R.id.rickRollButton);

        logButton.setOnClickListener(v -> {
            String username = userEditText.getText().toString();
            String password = passwordEditText.getText().toString();
            if (!username.equals("") &&
                    !password.equals("")) {
                List<Client> clients = DatabaseOperations.getALlClients();
                boolean alreadyInDB = false;
                sendLoginNotification();

                Client user;
                if (clients != null) {
                    for (Client client : clients) {
                        if (client.username.equals(username) && client.password.equals(password)) {
                            alreadyInDB = true;
                            break;
                        }
                    }
                }
                if (!alreadyInDB) {
                    user = new Client();
                    user.username = username;
                    user.password = password;
                    DatabaseOperations.saveNewClient(user);
                }
                Log.d("NaszeLogi", "Zalogowano na: " + username + " " + password);
                Intent goMenuIntent = new Intent(LoginActivity.this, PizzaListActivity.class);
                goMenuIntent.putExtra("username", username);
                goMenuIntent.putExtra("password", password);
                startActivity(goMenuIntent);
            } else {
                Toast.makeText(getApplicationContext(), "Nie podano danych", Toast.LENGTH_SHORT).show();
            }
        });
        gotoYTButton.setOnClickListener(v -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=dQw4w9WgXcQ&ab_channel=RickAstley"));
            startActivity(browserIntent);
        });
    }

    private void sendLoginNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(LoginActivity.this, "Witaj w pizzerii!");
        builder.setContentTitle("Witaj w pizzerii!");
        builder.setContentText("Życzymy Ci jak najsmaczniejszych chwil!:)");
        builder.setSmallIcon(R.drawable.ic_launcher_background);
        builder.setAutoCancel(true);

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(LoginActivity.this);
        managerCompat.notify(1, builder.build());
    }
}