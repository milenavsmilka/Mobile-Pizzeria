package com.example.systemymobilneprojekt.app;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.systemymobilneprojekt.R;

public class ShoppingBasketActivity extends AppCompatActivity {

    private Button closeButton;
    private AlertDialog.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.basket_layout);

        closeButton = findViewById(R.id.order_button);
        builder = new AlertDialog.Builder(this);

        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setTitle("Dziękujemy!")
                        .setMessage("Zamówienie złożone! Czas oczekiwania: 15 min. Życzymy smacznego!").
                        setCancelable(true).setNeutralButton("Zamknij", (dialog, which) -> {
                            finish();
                            System.exit(0);
                        }).show();
            }
        });
    }

    protected Fragment createFragment(Task task){
        return new TaskListFragment();
    }
}
