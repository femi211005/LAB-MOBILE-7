package com.example.tp02;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView rvHome = findViewById(R.id.rv_home);
        rvHome.setLayoutManager(new LinearLayoutManager(this));

        com.example.tp02.HomeAdapter adapter = new com.example.tp02.HomeAdapter(com.example.tp02.DataSource.generateHomePosts());
        rvHome.setAdapter(adapter);

        adapter.setOnItemClickCallback(data -> {
            Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
            intent.putExtra("EXTRA_POST", data);
            startActivity(intent);
        });
    }
}