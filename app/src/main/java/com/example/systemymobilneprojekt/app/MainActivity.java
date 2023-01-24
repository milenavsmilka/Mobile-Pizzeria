package com.example.systemymobilneprojekt.app;

import androidx.fragment.app.Fragment;

import java.io.Serializable;
import java.util.UUID;

public class MainActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        UUID taskId = (UUID) getIntent().getSerializableExtra(TaskListFragment.KEY_SHAKEOMAT_ID);
        int pizzaId = (int) getIntent().getSerializableExtra("pizzaId");
        return TaskFragment.newInstance(taskId,pizzaId);
    }
}