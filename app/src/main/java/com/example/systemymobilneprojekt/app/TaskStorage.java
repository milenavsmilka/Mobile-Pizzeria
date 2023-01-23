package com.example.systemymobilneprojekt.app;

import android.util.Log;

import com.example.systemymobilneprojekt.db.DatabaseOperations;
import com.example.systemymobilneprojekt.db.tables.Pizza;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TaskStorage {
    private static final TaskStorage taskStorage = new TaskStorage();

    private final List<Task> tasks;

    private TaskStorage() {
        tasks = new ArrayList<>();
        List<Pizza> pizzas = DatabaseOperations.getAllPizzas();
        for (int i = 0; i < pizzas.size(); i++) {
            Log.d("NaszeLogi","pizza nr: "+i+" "+pizzas.get(i));
            Task task = new Task();
            task.setName(pizzas.get(i).name);
            task.setDescription(pizzas.get(i).description);
            task.setPrice(pizzas.get(i).price);
            task.setPizzaId(pizzas.get(i).pizzaId);
            if (i % 3 == 0) {
                task.setCategory(Category.SEROWY);
            } else {
                task.setCategory(Category.POMIDOROWY);
            }
            tasks.add(task);
        }
    }

    public static TaskStorage getInstance() {
        return taskStorage;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task getTask(UUID id) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getId().equals(id)) {
                return tasks.get(i);
            }
        }
        return null;
    }
}
