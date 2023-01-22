package com.example.systemymobilneprojekt.app;

import androidx.fragment.app.Fragment;

import com.example.systemymobilneprojekt.db.DatabaseOperations;
import com.example.systemymobilneprojekt.db.PizzeriaDatabase;

public class TaskListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        PizzeriaDatabase.setInstance(this.getApplicationContext());
        //DatabaseOperations.addPizzasToDb();
        return new TaskListFragment();
    }
}