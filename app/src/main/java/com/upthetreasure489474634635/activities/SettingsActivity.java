package com.upthetreasure489474634635.activities;

import static com.upthetreasure489474634635.Ref.currentIndex;

import static com.upthetreasure489474634635.Ref.isSoundEnabled;
import static com.upthetreasure489474634635.Ref.isVibrateEnabled;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;
import com.upthetreasure489474634635.R;
import com.upthetreasure489474634635.Ref;
import com.upthetreasure489474634635.SoundsClass;
import com.upthetreasure489474634635.VibrationEffect;
import com.upthetreasure489474634635.databinding.ActivitySettingsBinding;
import com.upthetreasure489474634635.services.BgMusicService;

import java.util.Locale;

import io.paperdb.Paper;

public class SettingsActivity extends AppCompatActivity {

    ActivitySettingsBinding binding;
    //
    private int[] drawableIds = {R.drawable.lang_en_btn, R.drawable.lang_pt_btn, R.drawable.lang_ru_btn};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySettingsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //
        Paper.init(this);
        //
        changeDrawables();
        //
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                // Back is pressed... Finishing the activity
                startActivity(new Intent(SettingsActivity.this, MenuScreenActivity.class));
                finish();

            }
        });
        //
        binding.soundBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isSoundEnabled) {
                    // If drawable is changed, set it back to the initial state
                    binding.soundBtn.setImageResource(R.drawable.sound_btn_off);
                    isSoundEnabled = false;
                    Paper.book().write("isSound", isSoundEnabled);
                    stopService(new Intent(SettingsActivity.this, BgMusicService.class));

                } else {
                    // If drawable is not changed, set it to the new drawable
                    binding.soundBtn.setImageResource(R.drawable.sound_btn);
                    isSoundEnabled = true;
                    Paper.book().write("isSound", isSoundEnabled);
                    Intent i = new Intent(SettingsActivity.this, BgMusicService.class);
                    startService(i);
                }
            }

        });
        binding.vibrateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isVibrateEnabled) {
                    // If drawable is changed, set it back to the initial state
                    binding.vibrateBtn.setImageResource(R.drawable.vibrate_btn_off);
                    isVibrateEnabled = false;
                    Paper.book().write("isVibrate", isVibrateEnabled);

                } else {
                    // If drawable is not changed, set it to the new drawable
                    binding.vibrateBtn.setImageResource(R.drawable.vibrate_btn);
                    isVibrateEnabled = true;
                    Paper.book().write("isVibrate", isVibrateEnabled);

                }
            }

        });
        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isSoundEnabled) {
                    SoundsClass.playButtonClickSound(SettingsActivity.this);
                }
//                if(Ref.isVibrateEnabled){
//                    VibrationEffect.VibrationEffect(SettingsActivity.this);
//                }
                startActivity(new Intent(SettingsActivity.this, MenuScreenActivity.class));
                finish();
            }
        });
        //
        binding.langBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isSoundEnabled) {
                    SoundsClass.playButtonClickSound(SettingsActivity.this);
                }
//                if(Ref.isVibrateEnabled){
//                    VibrationEffect.VibrationEffect(SettingsActivity.this);
//                }
                // Change the image to the next one in the cycle
                binding.langBtn.setImageResource(drawableIds[currentIndex]);

                // Increment the index for the next click
                currentIndex = (currentIndex + 1) % drawableIds.length;
                if (currentIndex == 1) {
                    Ref.lang = 1;
                    Paper.book().write("lang", Ref.lang);
                    changeLanguageApp("en", SettingsActivity.this);
                    finish();
                    startActivity(getIntent());
                } else if (currentIndex == 2) {
                    Ref.lang = 2;
                    Paper.book().write("lang", Ref.lang);
                    changeLanguageApp("pt", SettingsActivity.this);
                    finish();
                    startActivity(getIntent());
                } else if (currentIndex == 0) {
                    Ref.lang = 0;
                    Paper.book().write("lang", Ref.lang);
                    changeLanguageApp("ru", SettingsActivity.this);
                    finish();
                    startActivity(getIntent());
                }


            }

        });
    }

    private void changeDrawables() {
        //
        if (Ref.lang == 0) {
            binding.langBtn.setImageResource(R.drawable.lang_ru_btn);
        } else if (Ref.lang == 1) {
            binding.langBtn.setImageResource(R.drawable.lang_en_btn);
        } else if (Ref.lang == 2) {
            binding.langBtn.setImageResource(R.drawable.lang_pt_btn);
        }

        if (isVibrateEnabled) {
            // If drawable is changed, set it back to the initial state
            binding.vibrateBtn.setImageResource(R.drawable.vibrate_btn);
        } else {
            binding.vibrateBtn.setImageResource(R.drawable.vibrate_btn_off);

        }

        if (isSoundEnabled) {
            // If drawable is changed, set it back to the initial state
            binding.soundBtn.setImageResource(R.drawable.sound_btn);
        } else {
            binding.soundBtn.setImageResource(R.drawable.sound_btn_off);

        }
    }


    public static void changeLanguageApp(String lan, Activity a) {

        Locale locale = new Locale(lan);
        Locale.setDefault(locale);
        Resources resources = a.getResources();
        Configuration config = new Configuration(resources.getConfiguration());
        config.setLocale(locale);

        resources.updateConfiguration(config, resources.getDisplayMetrics());

    }
}