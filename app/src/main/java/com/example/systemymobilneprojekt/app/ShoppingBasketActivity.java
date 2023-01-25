package com.example.systemymobilneprojekt.app;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.systemymobilneprojekt.R;
import com.example.systemymobilneprojekt.api.DataFromApi;

import java.math.BigDecimal;
import java.util.ArrayList;

public class ShoppingBasketActivity extends AppCompatActivity {

    private Button closeButton;
    private AlertDialog.Builder builder;
    private TextView listofPizzaView;
    private TextView totalPriceView;
    private TextView temperatureTextView;
    private TextView temperatureTextTextView;
    private TextView pressureTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Koszyk");
        setContentView(R.layout.basket_layout);

        listofPizzaView = findViewById(R.id.list_of_pizzas);
        totalPriceView = findViewById(R.id.sum_prices);

        closeButton = findViewById(R.id.order_button);
        builder = new AlertDialog.Builder(this);
        BigDecimal totalPrice = (BigDecimal) getIntent().getSerializableExtra(PizzaListFragment.KEY_TOTALPRICE_ID);
        ArrayList<String> nameOfPizzas = (ArrayList<String>) getIntent().getSerializableExtra(PizzaListFragment.KEY_LISTOFPIZZAS_ID);

        temperatureTextView = (TextView) findViewById(R.id.temperature);
        temperatureTextTextView = (TextView) findViewById(R.id.temperatureText);
        pressureTextView = (TextView) findViewById(R.id.pressure);
        if(!(DataFromApi.getPressure().compareTo(BigDecimal.valueOf(-100))==0))
        {
            temperatureTextTextView.setText(DataFromApi.getTemperatureText());
            temperatureTextView.setText(DataFromApi.getTemperatureRelatedText());
            pressureTextView.setText(DataFromApi.getPressureText());
        }


        listofPizzaView.setText(nameOfPizzas.toString());
        totalPriceView.setText(totalPrice.toString());
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

}
