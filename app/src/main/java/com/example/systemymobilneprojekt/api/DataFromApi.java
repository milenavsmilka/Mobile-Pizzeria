package com.example.systemymobilneprojekt.api;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataFromApi extends AsyncTask {

    private Exception exception;

    @Override
    public Object doInBackground(Object... objects) {
        /*
        try {
        URL url = new URL("https://api.open-meteo.com/v1/forecast?latitude=53.13&longitude=23.16&hourly=surface_pressure,temperature_2m");
        HttpURLConnection conn = null;

            conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod("GET");
        conn.connect();
        if (conn.getResponseCode() == 200) {
            Scanner scan = new Scanner(url.openStream());
            while (scan.hasNext()) {
                String temp = scan.nextLine();
                Log.d("NaszeLogi", "datafromapi: "+temp);
                //parse json here


            }
        }
        else
        {
            Log.d("NaszeLogi", "wrongresponse: "+conn.getResponseCode());
        }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
        */
        try {
            URL url = new URL("http://api.weatherapi.com/v1/current.json?key=50bc46cbc9b1482fa7004917232101 &q=Warsaw&aqi=no");
            URLConnection request = url.openConnection();
            Log.d("NaszeLogi", "data fetch connection established");
            request.setRequestProperty("Content-Type", "application/json; utf-8");

            BufferedReader input = new BufferedReader(new InputStreamReader(url.openStream()));
            JSONObject hourlyObject = new JSONObject(input.readLine()).getJSONObject("current");
            Double pressure = hourlyObject.getDouble("pressure_mb");
            Double temperature = hourlyObject.getDouble("temp_c");
            Log.d("NaszeLogi", "datafromapi: " + pressure + "hPa " + temperature + " Celsius");
            ArrayList<Double> list = new ArrayList<>();
            list.add(pressure);
            list.add(temperature);
            return list;

        } catch (IOException | JSONException e) {
            Log.d("NaszeLogi", "data from api didnt load: " + e);
            e.printStackTrace();
        }
        return null;
    }

    public String getTemperatureRelatedText(BigDecimal temperature) {
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
}