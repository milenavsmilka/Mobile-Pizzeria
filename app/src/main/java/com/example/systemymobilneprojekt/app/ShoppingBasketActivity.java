package com.example.systemymobilneprojekt.app;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.systemymobilneprojekt.R;

import java.math.BigDecimal;
import java.util.ArrayList;

public class ShoppingBasketActivity extends AppCompatActivity {

    private Button closeButton;
    private AlertDialog.Builder builder;
    private TextView listofPizzaView;
    private TextView totalPriceView;
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
