package com.example.listycity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
    ArrayList<String> dataList;
    Button AddCity;
    Button DeleteCity;
    int TempPosition=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cityList = findViewById(R.id.city_list);
        String[] cities = {"Edmonton", "Vancouver", "Moscow", "Sydney", "Berlin", "Vienna", "Tokyo", "Beijing", "Osaka", "New Delhi"};
        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));
        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);

        AddCity = findViewById(R.id.AddCity);
        DeleteCity = findViewById(R.id.DeleteCity);

        cityList.setOnItemClickListener((parent, view, position, id) -> {
            TempPosition = position;
        });
        DeleteCity.setOnClickListener(v -> {
            if (TempPosition >= 0 && TempPosition < dataList.size()) {
                dataList.remove(TempPosition);
                cityAdapter.notifyDataSetChanged();
                TempPosition = 0;
            }
        });
        AddCity.setOnClickListener(v -> {
            final android.widget.EditText GetInput = new android.widget.EditText(this);

            new android.app.AlertDialog.Builder(this)
                    .setTitle("Add New City")
                    .setMessage("Please enter the name of the city")
                    .setView(GetInput)
                    .setPositiveButton("Enter", (dialog, which) -> {
                        String CityName = GetInput.getText().toString();

                        if (!CityName.isEmpty()) {
                            dataList.add(CityName);
                            cityAdapter.notifyDataSetChanged();
                        }

                    })
                    .setNegativeButton("Escape", null)
                    .show();
        });




        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }
}