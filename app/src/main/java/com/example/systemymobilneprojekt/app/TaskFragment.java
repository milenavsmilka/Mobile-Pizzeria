package com.example.systemymobilneprojekt.app;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.systemymobilneprojekt.R;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

public class TaskFragment extends Fragment {
    Task task;
    int currentPizzaId;
    private static final String ARG_TASK_ID = "arg_task_id" ;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        assert getArguments() != null;
        UUID taskId = (UUID) getArguments().getSerializable(ARG_TASK_ID);
        currentPizzaId = (int) getArguments().getSerializable("pizzaId");
        task = TaskStorage.getInstance().getTask(taskId);
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
                task.setCategory(Category.values()[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        categorySpinner.setSelection(task.getCategory().ordinal());

        List<String> pizzaImageNames= Arrays.asList("pizza1", "pizza2", "pizza3",
                "pizza4", "pizza5", "pizza6", "pizza7", "pizza8", "pizza9", "pizza10", "pizza11");
        String pizzaImageName;
        if(task.getPizzaId()>pizzaImageNames.size())
        {
            pizzaImageName="pizza1";
        }
        else
        {
            pizzaImageName=pizzaImageNames.get(task.getPizzaId()-1);
        }
        File path= new File("src/main/res/drawable/");
        iconImageView.setImageResource(getResources().getIdentifier(pizzaImageName, "drawable", getActivity().getPackageName()));

        nameField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                task.setName(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        nameField.setText(task.getName());
        doneCheckBox.setChecked(task.isDone());

        doneCheckBox.setChecked(task.isDone());
        doneCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> task.setDone(isChecked));

        return view;
    }

}
