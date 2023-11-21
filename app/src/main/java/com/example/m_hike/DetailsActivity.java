package com.example.m_hike;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DetailsActivity extends AppCompatActivity {

    TextView name, location, date, parking, length, duration, weather, difficulty, description;
    Button back, edit;
    String id, Name, Location, Date, Parking, Length, Duration, Weather, Difficulty, Description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        name = findViewById(R.id.Name3);
        location = findViewById(R.id.Location4);
        date = findViewById(R.id.Date4);
        parking = findViewById(R.id.Parking4);
        length = findViewById(R.id.Length4);
        duration = findViewById(R.id.Duration4);
        weather = findViewById(R.id.Weather4);
        difficulty = findViewById(R.id.Difficulty4);
        description = findViewById(R.id.Description4);

        back = findViewById(R.id.Back);
        edit = findViewById(R.id.Edit);

        getAndSetIntentData();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailsActivity.this, UpdateActivity.class);
                intent.putExtra("name", name.getText().toString());
                intent.putExtra("location", location.getText().toString());
                intent.putExtra("date", date.getText().toString());
                intent.putExtra("parking", parking.getText().toString());
                intent.putExtra("length", length.getText().toString());
                intent.putExtra("duration", duration.getText().toString());
                intent.putExtra("weather", weather.getText().toString());
                intent.putExtra("difficulty", difficulty.getText().toString());
                intent.putExtra("description", description.getText().toString());
                startActivity(intent);
            }
        });
    }

    void getAndSetIntentData() {
        if (getIntent().hasExtra("id") && getIntent().hasExtra("name") &&
                getIntent().hasExtra("location") && getIntent().hasExtra("date") &&
                getIntent().hasExtra("parking") && getIntent().hasExtra("length") &&
                getIntent().hasExtra("duration") && getIntent().hasExtra("weather") &&
                getIntent().hasExtra("difficulty") && getIntent().hasExtra("description") ) {

            id = getIntent().getStringExtra("id");
            Name = getIntent().getStringExtra("name");
            Location = getIntent().getStringExtra("location");
            Date = getIntent().getStringExtra("date");
            Parking = getIntent().getStringExtra("parking");
            Length = getIntent().getStringExtra("length");
            Duration = getIntent().getStringExtra("duration");
            Weather = getIntent().getStringExtra("weather");
            Difficulty = getIntent().getStringExtra("difficulty");
            Description = getIntent().getStringExtra("description");

            name.setText(Name);
            location.setText(Location);
            date.setText(Date);
            parking.setText(Parking);
            length.setText(Length);
            duration.setText(Duration);
            weather.setText(Weather);
            difficulty.setText(Difficulty);
            description.setText(Description);

        } else {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }
    }
}