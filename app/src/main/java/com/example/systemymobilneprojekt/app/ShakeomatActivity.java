package com.example.systemymobilneprojekt.app;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.systemymobilneprojekt.R;

import java.util.Random;

public class ShakeomatActivity extends AppCompatActivity implements SensorEventListener {

    private ImageView imageView;
    private TextView textView;
    private SensorManager sensorManager;
    private Sensor mySensor;
    private long lastUpdate, actualTime;
    private int countfOfShake = 0;

    private int getRandomColor() {
        Random nr = new Random();
        int r = nr.nextInt(256);
        int g = nr.nextInt(256);
        int b = nr.nextInt(256);

        return Color.argb(255, r, g, b);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
                textView.setText("Wygrałeś pizze!");
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
                        textView.setText("Wygrałeś pizze!");
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
