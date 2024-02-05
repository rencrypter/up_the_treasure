package com.upthetreasure489474634635.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.upthetreasure489474634635.R;
import com.upthetreasure489474634635.databinding.ActivityMenuScreenBinding;

public class MenuScreenActivity extends AppCompatActivity {

    ActivityMenuScreenBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMenuScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //
        binding.playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuScreenActivity.this, GameplayActivity.class));
            }
        });
    }
}