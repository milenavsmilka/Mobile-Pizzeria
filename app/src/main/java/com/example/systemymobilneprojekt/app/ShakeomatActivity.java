package com.example.systemymobilneprojekt.app;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.systemymobilneprojekt.R;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class ShakeomatActivity extends AppCompatActivity implements SensorEventListener {

    private ImageView imageView;
    private TextView textView;
    private SensorManager sensorManager;
    private Sensor mySensor;
    private long lastUpdate, actualTime;
    private int countfOfShake = 0;
    private int znacznik = 0;

    private int getRandomColor() {
        Random nr = new Random();
        int r = nr.nextInt(256);
        int g = nr.nextInt(256);
        int b = nr.nextInt(256);

        return Color.argb(255, r, g, b);
    }

    private String getRandomAward() {
        znacznik++;
        Random nr = new Random();
        int awardNr = nr.nextInt(10);
        String nameOfPizzaAward;
        String award;

        switch (awardNr) {
            case 1:
                award = "Vesuvio za pół ceny!";
                nameOfPizzaAward = "Vesuvio";
                break;
            case 2:
                award = "Salami za pół ceny!";
                nameOfPizzaAward = "Salami";
                break;
            case 3:
                award = "Grecka za pół ceny!";
                nameOfPizzaAward = "Grecka";
                break;
            case 4:
                award = "Swojska za pół ceny!";
                nameOfPizzaAward = "Swojska";
                break;
            case 5:
                award = "Hawajska za pół ceny!";
                nameOfPizzaAward = "Hawajska";
                break;
            default:
                nameOfPizzaAward = "No pizza";
                award = nameOfPizzaAward;
                break;
        }

        TaskListFragment.nameOfPizzaKEY = nameOfPizzaAward;
        return award;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Shakeomat");
        setContentView(R.layout.sensor);

        imageView = findViewById(R.id.imageView);
        textView = findViewById(R.id.textView);
        textView.setBackgroundColor(getRandomColor());
        lastUpdate = System.currentTimeMillis();

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mySensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        if (mySensor == null) {
            Toast.makeText(this, "Brak czujnika :(", Toast.LENGTH_LONG).show();
            finish();
        } else {
            sensorManager.registerListener(this, mySensor, SensorManager.SENSOR_DELAY_NORMAL);
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float[] values = sensorEvent.values;
            float x = values[0];
            float y = values[1];
            float z = values[2];

            float EG = SensorManager.GRAVITY_EARTH;
            float devAccel = (x * x + y * y + z * z) / (EG * EG);


            if (countfOfShake == 100) {
                if (znacznik == 0) {
                    textView.setText(getRandomAward());

                    TaskStorage taskStorage = TaskStorage.getInstance();
                    List<Pizza> pizzas = taskStorage.getTasks();

                    for (int i = 0; i < pizzas.size(); i++) {
                        if (pizzas.get(i).getName().equals(TaskListFragment.nameOfPizzaKEY)) {
//                            priceTextView.setText((pizzas.get(i).getPrice().divide(BigDecimal.valueOf(2.0))).toString());
                            //priceTextView.setTextColor(ColorStateList.valueOf(4));
                            pizzas.get(i).setPrice(pizzas.get(i).getPrice().divide(BigDecimal.valueOf(2.0)));
                        }
                    }
                }
                Handler handler = new Handler();
                handler.postDelayed(this::finish, 4000);
            } else {
                textView.setText(countfOfShake + "%");
            }

            if (devAccel >= 1.5) {
                actualTime = System.currentTimeMillis();
                if ((actualTime - lastUpdate) > 1000) {
                    lastUpdate = actualTime;
                    textView.setBackgroundColor(getRandomColor());

                    Random nr = new Random();
                    WindowManager windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);

                    int height = nr.nextInt(windowManager.getDefaultDisplay().getHeight() - imageView.getHeight());
                    int width = nr.nextInt(windowManager.getDefaultDisplay().getWidth() - imageView.getWidth());
                    imageView.setX(width);
                    imageView.setY(height);

                    imageView.setRotation(width / 10 + height);

                    if (countfOfShake == 100) {
                        Handler handler = new Handler();
                        handler.postDelayed(this::finish, 4000);
                    } else if (countfOfShake != 90) {
                        countfOfShake += 15;
                    } else {
                        countfOfShake += 10;
                    }
                }
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
