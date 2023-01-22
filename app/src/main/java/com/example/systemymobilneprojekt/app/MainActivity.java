package com.example.systemymobilneprojekt.app;

import androidx.fragment.app.Fragment;

import java.util.UUID;

public class MainActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        UUID taskId = (UUID) getIntent().getSerializableExtra(TaskListFragment.KEY_SHAKEOMAT_ID);
        return TaskFragment.newInstance(taskId);
    }
}