package com.example.systemymobilneprojekt;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.systemymobilneprojekt.app.TaskListActivity;
import com.example.systemymobilneprojekt.db.DatabaseOperations;
/*
public class MainActivity extends AppCompatActivity {
    EditText username = (EditText)findViewById(R.id.usernameInput);
    EditText password = (EditText)findViewById(R.id.passwordInput);
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

    public void login(View view) {
        if(username.getText().toString().equals("admin") && password.getText().toString().equals("admin")){
            Log.d("NaszeLogi", "Poprawnie zalogowano");
            //correcct password
        }else{
            Log.d("NaszeLogi", "Nie podano danych");
        }
            //wrong password
    }
}
*/

public class LoginActivity extends Activity {
    Button logButton, gotoYTButton;
    EditText userEditText, passwordEditText;

    TextView errorTextView;
    int counter = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("Witaj w pizzerii!", "Witaj w pizzerii!", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
        logButton = (Button) findViewById(R.id.loginButton);
        userEditText = (EditText) findViewById(R.id.usernameInput);
        passwordEditText = (EditText) findViewById(R.id.passwordInput);
        gotoYTButton = (Button) findViewById(R.id.rickRollButton);
        errorTextView = (TextView) findViewById(R.id.errorText);
        errorTextView.setVisibility(View.GONE);

        logButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!userEditText.getText().toString().equals("") &&
                        !passwordEditText.getText().toString().equals("")) {
                    //tu jest logowanie
                    sendLoginNotification();
                    Intent goMenuIntent = new Intent(LoginActivity.this, TaskListActivity.class);
                    startActivity(goMenuIntent);
                } else {
                    Toast.makeText(getApplicationContext(), "Nie podano danych", Toast.LENGTH_SHORT).show();
                }
            }
        });
        gotoYTButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=dQw4w9WgXcQ&ab_channel=RickAstley"));
                startActivity(browserIntent);
            }
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