package com.example.listycity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> datalist;

    int pickedCityNumber = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        cityList = findViewById(R.id.city_list);

        String []cities = {"Edmonton", "Vancouver", "Moscow", "Sydney", "Berlin", "Vienna", "Tokyo", "Beijing", "Osaka", "New Delhi"};

        datalist = new ArrayList<>();
        datalist.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this, R.layout.content, datalist);
        cityList.setAdapter(cityAdapter);

        cityList.setOnItemClickListener((parent, view, position, id) -> {
            pickedCityNumber = position;
        });
    }

    public void addCity(View view) {

    }


    public void deleteCity(View view) {
        if (pickedCityNumber != -1 && pickedCityNumber < datalist.size()) {
            datalist.remove(pickedCityNumber);
            pickedCityNumber = -1;
            cityAdapter.notifyDataSetChanged();
        }
    }


    public void confirmCity(View view) {
        EditText editCity = findViewById(R.id.edit_city);
        String newCity = editCity.getText().toString();

        if (!newCity.isEmpty()) {
            datalist.add(newCity);
            cityAdapter.notifyDataSetChanged();
            editCity.setText("");
        }

    }


}