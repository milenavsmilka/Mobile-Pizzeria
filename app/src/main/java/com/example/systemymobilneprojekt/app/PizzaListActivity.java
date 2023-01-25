package com.example.systemymobilneprojekt.app;

import androidx.fragment.app.Fragment;

public class PizzaListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new PizzaListFragment();
    }
}