package com.example.systemymobilneprojekt.app;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.systemymobilneprojekt.R;

public class ShoppingBasketActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.basket_layout);

    }

    protected Fragment createFragment(Task task){
        return new TaskListFragment();
    }
}
