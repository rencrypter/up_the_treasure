package com.upthetreasure489474634635.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.upthetreasure489474634635.R;
import com.upthetreasure489474634635.Ref;
import com.upthetreasure489474634635.SoundsClass;
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
                if (Ref.isSoundEnabled) {
                    SoundsClass.playButtonClickSound(MenuScreenActivity.this);
                }
                startActivity(new Intent(MenuScreenActivity.this, GameplayActivity.class));
            }
        });

        binding.rulesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Ref.isSoundEnabled) {
                    SoundsClass.playButtonClickSound(MenuScreenActivity.this);
                }
                startActivity(new Intent(MenuScreenActivity.this, RulesActivity.class));
            }
        });

        binding.settingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Ref.isSoundEnabled) {
                    SoundsClass.playButtonClickSound(MenuScreenActivity.this);
                }
                startActivity(new Intent(MenuScreenActivity.this, SettingsActivity.class));
                finish();
            }
        });

        binding.storeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Ref.isSoundEnabled) {
                    SoundsClass.playButtonClickSound(MenuScreenActivity.this);
                }
                startActivity(new Intent(MenuScreenActivity.this, StoreActivity.class));
            }
        });
        //
        binding.achieBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Ref.isSoundEnabled) {
                    SoundsClass.playButtonClickSound(MenuScreenActivity.this);
                }
                startActivity(new Intent(MenuScreenActivity.this, AchievementActivity.class));
            }
        });
    }
}