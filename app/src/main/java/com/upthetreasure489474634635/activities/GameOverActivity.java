package com.upthetreasure489474634635.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.upthetreasure489474634635.R;
import com.upthetreasure489474634635.Ref;
import com.upthetreasure489474634635.databinding.ActivityGameOverBinding;
import com.upthetreasure489474634635.databinding.ActivityGameplayBinding;

public class GameOverActivity extends AppCompatActivity {

    ActivityGameOverBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGameOverBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //


        binding.scoreTxt.setText(String.valueOf(Ref.score));
        //
        binding.returnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GameOverActivity.this, GameplayActivity.class));
                finish();
            }
        });
        ///
        binding.menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GameOverActivity.this, MenuScreenActivity.class));
                finish();
            }
        });
    }
}