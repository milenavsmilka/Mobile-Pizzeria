package com.example.systemymobilneprojekt.app;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.systemymobilneprojekt.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PizzaListFragment extends Fragment {
    public static final String KEY_SHAKEOMAT_ID = "com.example.zadanie3sm.task_id";
    public static final String KEY_TOTALPRICE_ID = "com.example.zadanie3sm.totalprice_id";
    public static final String KEY_LISTOFPIZZAS_ID = "com.example.zadanie3sm.pizzastobasket_id";
    public static String nameOfPizzaKEY = "No pizza";
    private RecyclerView recyclerView;
    private TaskAdapter adapter = null;
    private boolean subtitleVisible;
    public static final String KEY_SUBTITLE = "subtitle";
    private String username;
    private String password;
    private FloatingActionButton shoppingBasket;
    private BigDecimal totalPriceOfPizza = BigDecimal.ZERO;
    private ArrayList<String> listofPizzasToBasket = new ArrayList<>();
    private TextView priceTextView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        Intent myIntent = getActivity().getIntent();
        username = myIntent.getExtras().getString("username");
        password = myIntent.getExtras().getString("password");
        Log.d("NaszeLogi", "received: " + username + " " + password);
        if (savedInstanceState != null) {
            subtitleVisible = savedInstanceState.getBoolean(KEY_SUBTITLE);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.button_main, container, false);
        recyclerView = view.findViewById(R.id.task_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        shoppingBasket = (FloatingActionButton) view.findViewById(R.id.fab);
        shoppingBasket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listofPizzasToBasket!=null && listofPizzasToBasket.size()!=0)
                {
                Intent gotoBasket = new Intent(getActivity(), ShoppingBasketActivity.class);
                gotoBasket.putExtra(KEY_TOTALPRICE_ID, totalPriceOfPizza);
                gotoBasket.putExtra(KEY_LISTOFPIZZAS_ID, listofPizzasToBasket);
                startActivity(gotoBasket);
                }
                else
                {
                    Toast.makeText(getActivity().getApplicationContext(), "Puste zamówienie!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        updateView();
        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(KEY_SUBTITLE, subtitleVisible);
    }

    private class TaskHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private Pizza pizza;
        private final TextView nameTextView;
        private final TextView dateTextView;
        private ImageView iconImageView;


        CheckBox doneCheckBoxCateg;

        public TaskHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_pizza, parent, false));
            itemView.setOnClickListener(this);

            nameTextView = itemView.findViewById(R.id.task_item_name);
            dateTextView = itemView.findViewById(R.id.task_item_date);
            doneCheckBoxCateg = itemView.findViewById(R.id.task_done_categ);
            iconImageView = itemView.findViewById(R.id.task_img);

            priceTextView = itemView.findViewById(R.id.pizzaPrice);

        }

        public void bind(Pizza pizza) {
            this.pizza = pizza;

            nameTextView.setText(pizza.getName());

            dateTextView.setText(pizza.getDescription());
            priceTextView.setText(pizza.getPrice().toString());




            List<String> pizzaImageNames = Arrays.asList("pizza1", "pizza2", "pizza3",
                    "pizza4", "pizza5", "pizza6", "pizza7", "pizza8", "pizza9", "pizza10", "pizza11");
            String pizzaImageName;
            if (pizza.getPizzaId() > pizzaImageNames.size()) {
                pizzaImageName = "pizza1";
            } else {
                pizzaImageName = pizzaImageNames.get(pizza.getPizzaId() - 1);
            }
            File path = new File("src/main/res/drawable/");
            iconImageView.setImageResource(getResources().getIdentifier(pizzaImageName, "drawable", getActivity().getPackageName()));
            /*
            int imageResource = getResources().getIdentifier("@drawable/"
                    +pizzaImageNames.get(task.getPizzaId()-1),null,getActivity().getPackageName());
            iconImageView.setImageResource(imageResource);
             */
            doneCheckBoxCateg.setChecked(pizza.isInBasket());

        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getActivity(), MainActivity.class);
            intent.putExtra(KEY_SHAKEOMAT_ID, pizza.getId());
            intent.putExtra("pizzaId", pizza.getPizzaId());
            startActivity(intent);
        }

        public CheckBox getCheckBox() {
            return this.doneCheckBoxCateg;
        }

        public TextView getTextView() {
            return nameTextView;
        }
    }

    private class TaskAdapter extends RecyclerView.Adapter<TaskHolder> {
        private final List<Pizza> pizzas;

        public TaskAdapter(List<Pizza> pizzas) {
            this.pizzas = pizzas;
        }

        @NonNull
        @Override
        public TaskHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new TaskHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull TaskHolder holder, int position) {
            Pizza pizza = pizzas.get(position);
            holder.bind(pizza);

            CheckBox checkBox = holder.getCheckBox();
            checkBox.setChecked(pizzas.get(position).isInBasket());

            TextView nameTextView = holder.getTextView();

            checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
                        pizzas.get(holder.getAdapterPosition()).setInBasket(isChecked);
                        updateSubtitle();
                    }
            );
        }

        @Override
        public int getItemCount() {
            return pizzas.size();
        }
    }

    public void updateSubtitle() {
        System.out.println(nameOfPizzaKEY);
        PizzaStorage pizzaStorage = PizzaStorage.getInstance();
        List<Pizza> pizzas = pizzaStorage.getTasks();
        int toDoTasksCount = 0;
        totalPriceOfPizza = BigDecimal.ZERO;
        listofPizzasToBasket = new ArrayList<>();
        for (Pizza pizza : pizzas) {
            if (pizza.isInBasket()) {
                toDoTasksCount++;
                totalPriceOfPizza = totalPriceOfPizza.add(pizza.getPrice());
                listofPizzasToBasket.add(pizza.getName());
            }
        }
        String subtitle;
        if (toDoTasksCount % 10 > 4 || toDoTasksCount % 10 == 0) {
            subtitle = getString(R.string.subtitle_format_many, toDoTasksCount);
        } else if (toDoTasksCount % 10 == 1) {
            subtitle = getString(R.string.subtitle_format_one, toDoTasksCount);
        } else {
            subtitle = getString(R.string.subtitle_format, toDoTasksCount);
        }

        if (!subtitleVisible) {
            subtitle = null;
        }
        AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();
        appCompatActivity.getSupportActionBar().setSubtitle(subtitle);

    }

    @Override
    public void onResume() {
        super.onResume();
        updateView();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.fragment_task_menu, menu);

        MenuItem subtitleItem = menu.findItem(R.id.show_subtitle);
        if (subtitleVisible) {
            subtitleItem.setTitle(R.string.hide_subtitle);
        } else {
            subtitleItem.setTitle(R.string.show_subtitle);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case (R.id.go_to_shake):
                Intent intent = new Intent(getActivity(), ShakeomatActivity.class);
                startActivity(intent);
                return true;
            case (R.id.show_subtitle):
                subtitleVisible = !subtitleVisible;
                getActivity().invalidateOptionsMenu();

                updateSubtitle();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @SuppressLint("NotifyDataSetChanged")
    private void updateView() {
        PizzaStorage pizzaStorage = PizzaStorage.getInstance();
        List<Pizza> pizzas = pizzaStorage.getTasks();

        if (adapter == null) {
            adapter = new TaskAdapter(pizzas);
            recyclerView.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
        updateSubtitle();
    }
}
