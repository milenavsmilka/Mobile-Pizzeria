package com.example.systemymobilneprojekt.app;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.systemymobilneprojekt.R;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class TaskFragment extends Fragment {
    Pizza pizza;
    int currentPizzaId;
    private static final String ARG_TASK_ID = "arg_task_id" ;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        assert getArguments() != null;
        UUID taskId = (UUID) getArguments().getSerializable(ARG_TASK_ID);
        currentPizzaId = (int) getArguments().getSerializable("pizzaId");
        pizza = TaskStorage.getInstance().getTask(taskId);
    }

    public static TaskFragment newInstance(UUID taskId, int pizzaId){
        Bundle bundle = new Bundle();
        bundle.putSerializable(ARG_TASK_ID, taskId);
        bundle.putSerializable("pizzaId",pizzaId);
        TaskFragment taskFragment = new TaskFragment();
        taskFragment.setArguments(bundle);
        return taskFragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_task, container, false);

        TextView nameField = view.findViewById(R.id.task_name);
        CheckBox doneCheckBox = view.findViewById(R.id.task_done);
        Spinner categorySpinner = view.findViewById(R.id.dip_category);
        ImageView iconImageView = view.findViewById(R.id.task_img);
        TextView pizzaPriceView = view.findViewById(R.id.pizzaPrice);
        categorySpinner.setAdapter(new ArrayAdapter<>(this.getContext(), android.R.layout.simple_spinner_item, Category.values()));
        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                pizza.setDipCategory(Category.values()[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        categorySpinner.setSelection(pizza.getDipCategory().ordinal());

        List<String> pizzaImageNames= Arrays.asList("pizza1", "pizza2", "pizza3",
                "pizza4", "pizza5", "pizza6", "pizza7", "pizza8", "pizza9", "pizza10", "pizza11");
        String pizzaImageName;
        if(pizza.getPizzaId()>pizzaImageNames.size())
        {
            pizzaImageName="pizza1";
        }
        else
        {
            pizzaImageName=pizzaImageNames.get(pizza.getPizzaId()-1);
        }
        File path= new File("src/main/res/drawable/");
        iconImageView.setImageResource(getResources().getIdentifier(pizzaImageName, "drawable", getActivity().getPackageName()));

        nameField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                pizza.setName(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        nameField.setText(pizza.getName());
        doneCheckBox.setChecked(pizza.isInBasket());

        doneCheckBox.setChecked(pizza.isInBasket());
        doneCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> pizza.setInBasket(isChecked));

        return view;
    }

}
