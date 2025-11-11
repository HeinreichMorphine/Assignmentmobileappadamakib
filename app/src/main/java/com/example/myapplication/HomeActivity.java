package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button goToCalculationButton = findViewById(R.id.goToCalculationButton);
        goToCalculationButton.setOnClickListener(v -> {
            startActivity(new Intent(this, MainActivity.class));
        });

        Button goToAboutButton = findViewById(R.id.goToAboutButton);
        goToAboutButton.setOnClickListener(v -> {
            startActivity(new Intent(this, AboutActivity.class));
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.nav_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_home) {
            // Already on home screen
            return true;
        } else if (id == R.id.nav_calculation) {
            startActivity(new Intent(this, MainActivity.class));
            return true;
        } else if (id == R.id.nav_about) {
            startActivity(new Intent(this, AboutActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
