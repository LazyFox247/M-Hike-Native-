package com.example.m_hike;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {

    EditText name, location, date, parking, length, duration, weather, difficulty, description;
    Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        name = findViewById(R.id.Name);
        location = findViewById(R.id.Location);
        date = findViewById(R.id.Date);
        parking = findViewById(R.id.Parking);
        length = findViewById(R.id.Length);
        duration = findViewById(R.id.Duration);
        weather = findViewById(R.id.Weather);
        difficulty = findViewById(R.id.Difficulty);
        description = findViewById(R.id.Description);
        add = findViewById(R.id.Add);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper dbh = new DatabaseHelper(AddActivity.this);
                dbh.addHike(name.getText().toString().trim(),
                        location.getText().toString().trim(),
                        date.getText().toString().trim(),
                        parking.getText().toString().trim(),
                        length.getText().toString().trim(),
                        duration.getText().toString().trim(),
                        weather.getText().toString().trim(),
                        difficulty.getText().toString().trim(),
                        description.getText().toString().trim());
            }
        });
    }
}