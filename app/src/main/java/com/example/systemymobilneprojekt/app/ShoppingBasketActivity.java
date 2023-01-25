package com.example.systemymobilneprojekt.app;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.systemymobilneprojekt.R;
import com.example.systemymobilneprojekt.api.DataFromApi;

import java.math.BigDecimal;
import java.util.ArrayList;

public class ShoppingBasketActivity extends AppCompatActivity {

    private AlertDialog.Builder builder;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Koszyk");
        setContentView(R.layout.basket_layout);

        TextView listofPizzaView = findViewById(R.id.list_of_pizzas);
        TextView totalPriceView = findViewById(R.id.sum_prices);

        Button closeButton = findViewById(R.id.order_button);
        builder = new AlertDialog.Builder(this);
        BigDecimal totalPrice = (BigDecimal) getIntent().getSerializableExtra(PizzaListFragment.KEY_TOTALPRICE_ID);
        ArrayList<String> nameOfPizzas = (ArrayList<String>) getIntent().getSerializableExtra(PizzaListFragment.KEY_LISTOFPIZZAS_ID);

        TextView temperatureTextView = findViewById(R.id.temperature);
        TextView temperatureTextTextView = findViewById(R.id.temperatureText);
        TextView pressureTextView = findViewById(R.id.pressure);
        if(!(DataFromApi.getPressure().compareTo(BigDecimal.valueOf(-100))==0))
        {
            temperatureTextTextView.setText(DataFromApi.getTemperatureText());
            temperatureTextView.setText(DataFromApi.getTemperatureRelatedText());
            pressureTextView.setText(DataFromApi.getPressureText());
        }


        listofPizzaView.setText(nameOfPizzas.toString());
        totalPriceView.setText(totalPrice.toString());
        closeButton.setOnClickListener(v -> builder.setTitle("Dziękujemy!")
                .setMessage("Zamówienie złożone! Czas oczekiwania: 15 min. Życzymy smacznego!").
                setCancelable(true).setNeutralButton("Zamknij", (dialog, which) -> {
                    finish();
                    System.exit(0);
                }).show());
    }

}
