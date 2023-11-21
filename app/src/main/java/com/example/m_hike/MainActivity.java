package com.example.m_hike;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton Add;

    DatabaseHelper dbh;

    ArrayList<String> id, name, location, date, parking, length, duration, weather, difficulty, description;
    CustomAdapter customAdapter;

    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        Add = findViewById(R.id.btnAdd);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        searchView = findViewById(R.id.searchView);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });

        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });

        dbh = new DatabaseHelper(MainActivity.this);
        id = new ArrayList<>();
        name = new ArrayList<>();
        location = new ArrayList<>();
        date = new ArrayList<>();
        parking = new ArrayList<>();
        length = new ArrayList<>();
        duration = new ArrayList<>();
        weather = new ArrayList<>();
        difficulty = new ArrayList<>();
        description = new ArrayList<>();

        storeData();

        customAdapter = new CustomAdapter(MainActivity.this, this, id, name, location, date,
                parking, length, duration, weather, difficulty, description);

        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            recreate();
        }
    }

    void storeData() {
        Cursor cursor = dbh.readAllData();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                id.add(cursor.getString(0));
                name.add(cursor.getString(1));
                location.add(cursor.getString(2));
                date.add(cursor.getString(3));
                parking.add(cursor.getString(4));
                length.add(cursor.getString(5));
                duration.add(cursor.getString(6));
                weather.add(cursor.getString(7));
                difficulty.add(cursor.getString(8));
                description.add(cursor.getString(9));
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.delete_all) {
            confirmDialog();
        }
        return super.onOptionsItemSelected(item);
    }

    void confirmDialog() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Are you sure to Delete All?");
        alert.setMessage("All of the data will be permanently deleted.");
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DatabaseHelper dbh = new DatabaseHelper(MainActivity.this);
                dbh.deleteAllHike();

                // Refresh the page
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alert.create().show();
    }

    void filterList(String text) {
        ArrayList<String> Id = new ArrayList<>();
        ArrayList<String> Name = new ArrayList<>();
        ArrayList<String> Location = new ArrayList<>();
        ArrayList<String> Date = new ArrayList<>();
        ArrayList<String> Parking = new ArrayList<>();
        ArrayList<String> Length = new ArrayList<>();
        ArrayList<String> Duration = new ArrayList<>();
        ArrayList<String> Weather = new ArrayList<>();
        ArrayList<String> Difficulty = new ArrayList<>();
        ArrayList<String> Description = new ArrayList<>();

        for (int i = 0; i < name.size(); i++) {
            if (name.get(i).toLowerCase().contains(text.toLowerCase())) {
                Id.add(id.get(i));
                Name.add(name.get(i));
                Location.add(location.get(i));
                Date.add(date.get(i));
                Parking.add(parking.get(i));
                Length.add(length.get(i));
                Duration.add(duration.get(i));
                Weather.add(weather.get(i));
                Difficulty.add(difficulty.get(i));
                Description.add(description.get(i));
            }
        }

        customAdapter.setFilteredList(Id, Name, Location, Date, Parking, Length, Duration, Weather, Difficulty, Description);
    }
}