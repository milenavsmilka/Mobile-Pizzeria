package com.example.systemymobilneprojekt.api;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

    public class DataFromApi implements Runnable {

    private Exception exception;
    private static BigDecimal pressure=new BigDecimal(-100);
    private static BigDecimal temperature=new BigDecimal(-100);


    public static String getTemperatureRelatedText() {
        if (temperature.compareTo(BigDecimal.valueOf(30)) > 0) {
            return "Na tak ciepłą pogodę idealna jest Hawajska!:)";
        } else if (temperature.compareTo(BigDecimal.valueOf(20)) > 0) {
            return "Nie jest ciepło, ale zimno też nie, więc proponujemy Capriciosę!:)";
        } else if (temperature.compareTo(BigDecimal.valueOf(10)) > 0) {
            return "Ubierz się ciepło i weź ze sobą Wiejską pizzę!:)";
        } else {
            return "Lepiej nie wychodź z domu i zamów Wegetarianę!:)";
        }
    }
    public static String getPressureText() {
        return "Ciśnienie: "+pressure.toString()+" hPa";
    }

    public static BigDecimal getPressure() {
        return pressure;
    }

        @Override
        public void run() {
            try {
                URL url = new URL("http://api.weatherapi.com/v1/current.json?key=50bc46cbc9b1482fa7004917232101&q=Warsaw&aqi=no");
                URLConnection request = url.openConnection();
                Log.d("NaszeLogi", "data fetch connection established");
                request.setRequestProperty("Content-Type", "application/json; utf-8");

                BufferedReader input = new BufferedReader(new InputStreamReader(url.openStream()));
                JSONObject hourlyObject = new JSONObject(input.readLine()).getJSONObject("current");
                Double pressure = hourlyObject.getDouble("pressure_mb");
                Double temperature = hourlyObject.getDouble("temp_c");
                Log.d("NaszeLogi", "datafromapi: " + pressure + "hPa " + temperature + " Celsius");
                DataFromApi.pressure =new BigDecimal(pressure);
                DataFromApi.temperature =new BigDecimal(temperature);

            } catch (IOException | JSONException e) {
                Log.d("NaszeLogi", "data from api didnt load: " + e);
                e.printStackTrace();
            }
        }

        public static String getTemperatureText() {
            return "Temperatura: "+ temperature.toString()+"°C";
        }
    }