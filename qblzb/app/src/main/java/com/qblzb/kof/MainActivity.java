package com.qblzb.kof;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity implements CharacterAdapter.OnItemClickListener {

    private Button btn97, btn98;
    private RecyclerView recyclerView;
    private CharacterAdapter adapter;
    private String currentVersion = "97";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn97 = findViewById(R.id.btn97);
        btn98 = findViewById(R.id.btn98);
        recyclerView = findViewById(R.id.recyclerView);

        adapter = new CharacterAdapter(this);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setAdapter(adapter);

        btn97.setOnClickListener(v -> switchVersion("97"));
        btn98.setOnClickListener(v -> switchVersion("98"));

        switchVersion("97");
    }

    private void switchVersion(String version) {
        currentVersion = version;
        if (version.equals("97")) {
            btn97.setBackgroundTintList(android.content.res.ColorStateList.valueOf(0xFFe94560));
            btn98.setBackgroundTintList(android.content.res.ColorStateList.valueOf(0xFF0f3460));
            adapter.setData(CharacterData.get97Characters());
        } else {
            btn98.setBackgroundTintList(android.content.res.ColorStateList.valueOf(0xFFe94560));
            btn97.setBackgroundTintList(android.content.res.ColorStateList.valueOf(0xFF0f3460));
            adapter.setData(CharacterData.get98Characters());
        }
    }

    @Override
    public void onItemClick(Character ch) {
        Intent intent = new Intent(this, CharacterDetailActivity.class);
        intent.putExtra("id", ch.id);
        intent.putExtra("version", ch.version);
        startActivity(intent);
    }
}
