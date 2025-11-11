package com.example.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        TextView githubLink = findViewById(R.id.githubLink);
        SpannableString content = new SpannableString("GitHub Repository");
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        githubLink.setText(content);

        githubLink.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/your-repo"));
            startActivity(intent);
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
            startActivity(new Intent(this, HomeActivity.class));
            return true;
        } else if (id == R.id.nav_calculation) {
            startActivity(new Intent(this, MainActivity.class));
            return true;
        } else if (id == R.id.nav_about) {
            // Already here
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
