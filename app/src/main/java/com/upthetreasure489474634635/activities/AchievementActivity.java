package com.upthetreasure489474634635.activities;

import static com.upthetreasure489474634635.SplashScreenActivity.isMyServiceRunning;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.upthetreasure489474634635.R;
import com.upthetreasure489474634635.Ref;
import com.upthetreasure489474634635.SoundsClass;
import com.upthetreasure489474634635.VibrationEffect;
import com.upthetreasure489474634635.adapters.ImageAdapter;
import com.upthetreasure489474634635.databinding.ActivityAchievementBinding;
import com.upthetreasure489474634635.services.BgMusicService;

public class AchievementActivity extends AppCompatActivity {

    ActivityAchievementBinding binding;
    ImageAdapter adapter;
    private Integer[] images = {
            R.drawable.ic_lock_gallery,
            R.drawable.ic_lock_gallery,
            R.drawable.ic_lock_gallery,
            R.drawable.ic_lock_gallery,
            R.drawable.ic_lock_gallery,
            R.drawable.ic_lock_gallery,
            R.drawable.ic_lock_gallery,
            R.drawable.ic_lock_gallery,
            R.drawable.ic_lock_gallery,
            R.drawable.ic_lock_gallery,
            R.drawable.ic_lock_gallery,
            R.drawable.ic_lock_gallery
    };

    @Override
    protected void onPause() {
        super.onPause();
        if (isMyServiceRunning(BgMusicService.class, AchievementActivity.this)) {
            BgMusicService.onPause();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isMyServiceRunning(BgMusicService.class, AchievementActivity.this)) {
            BgMusicService.onResume();
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAchievementBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //
        adapter = new ImageAdapter(this, images);
        binding.gridView.setAdapter(adapter);


        dataLoad();
        binding.gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Handle grid item click if needed
                // For example, you can display the clicked image in a larger ImageView
                if (Ref.isSoundEnabled) {
                    SoundsClass.playButtonClickSound(AchievementActivity.this);
                }
            }
        });

        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Ref.isSoundEnabled) {
                    SoundsClass.playButtonClickSound(AchievementActivity.this);
                }
//                if(Ref.isVibrateEnabled){
//                    VibrationEffect.VibrationEffect(AchievementActivity.this);
//                }
                finish();
            }
        });

        //

    }


    private void dataLoad() {
        if (Ref.achi1) {
            // Change the image at a specific index (for example, index 2).
            int indexToChange = 0;
            int newImageResource = R.drawable.ic_achie_gallery_1; // Replace 'new_image' with your desired image resource

            if (indexToChange >= 0 && indexToChange < images.length) {
                images[indexToChange] = newImageResource;
                adapter.notifyDataSetChanged(); // Notify the adapter that the data has changed
            } else {
                // Handle invalid index
            }
        }
        if (Ref.achi2) {
            // Change the image at a specific index (for example, index 2).
            int indexToChange = 1;
            int newImageResource = R.drawable.ic_achie_gallery_2; // Replace 'new_image' with your desired image resource

            if (indexToChange >= 1 && indexToChange < images.length) {
                images[indexToChange] = newImageResource;
                adapter.notifyDataSetChanged(); // Notify the adapter that the data has changed
            } else {
                // Handle invalid index
            }
        }
        if (Ref.achi3) {
            // Change the image at a specific index (for example, index 2).
            int indexToChange = 2;
            int newImageResource = R.drawable.ic_achie_gallery_3; // Replace 'new_image' with your desired image resource

            if (indexToChange >= 2 && indexToChange < images.length) {
                images[indexToChange] = newImageResource;
                adapter.notifyDataSetChanged(); // Notify the adapter that the data has changed
            } else {
                // Handle invalid index
            }
        }
        if (Ref.achi4) {
            // Change the image at a specific index (for example, index 2).
            int indexToChange = 3;
            int newImageResource = R.drawable.ic_achie_gallery_4; // Replace 'new_image' with your desired image resource

            if (indexToChange >= 3 && indexToChange < images.length) {
                images[indexToChange] = newImageResource;
                adapter.notifyDataSetChanged(); // Notify the adapter that the data has changed
            } else {
                // Handle invalid index
            }
        }
        if (Ref.achi5) {
            // Change the image at a specific index (for example, index 2).
            int indexToChange = 4;
            int newImageResource = R.drawable.ic_achie_gallery_5; // Replace 'new_image' with your desired image resource

            if (indexToChange >=4 && indexToChange < images.length) {
                images[indexToChange] = newImageResource;
                adapter.notifyDataSetChanged(); // Notify the adapter that the data has changed
            } else {
                // Handle invalid index
            }
        }
        if (Ref.achi6) {
            // Change the image at a specific index (for example, index 2).
            int indexToChange = 5;
            int newImageResource = R.drawable.ic_achie_gallery_6; // Replace 'new_image' with your desired image resource

            if (indexToChange >= 5 && indexToChange < images.length) {
                images[indexToChange] = newImageResource;
                adapter.notifyDataSetChanged(); // Notify the adapter that the data has changed
            } else {
                // Handle invalid index
            }
        }
        if (Ref.achi7) {
            // Change the image at a specific index (for example, index 2).
            int indexToChange = 6;
            int newImageResource = R.drawable.ic_achie_gallery_7; // Replace 'new_image' with your desired image resource

            if (indexToChange >= 6 && indexToChange < images.length) {
                images[indexToChange] = newImageResource;
                adapter.notifyDataSetChanged(); // Notify the adapter that the data has changed
            } else {
                // Handle invalid index
            }
        }
        if (Ref.achi8) {
            // Change the image at a specific index (for example, index 2).
            int indexToChange = 7;
            int newImageResource = R.drawable.ic_achie_gallery_8; // Replace 'new_image' with your desired image resource

            if (indexToChange >= 7 && indexToChange < images.length) {
                images[indexToChange] = newImageResource;
                adapter.notifyDataSetChanged(); // Notify the adapter that the data has changed
            } else {
                // Handle invalid index
            }
        }
        if (Ref.achi9) {
            // Change the image at a specific index (for example, index 2).
            int indexToChange = 8;
            int newImageResource = R.drawable.ic_achie_gallery_9; // Replace 'new_image' with your desired image resource

            if (indexToChange >= 8 && indexToChange < images.length) {
                images[indexToChange] = newImageResource;
                adapter.notifyDataSetChanged(); // Notify the adapter that the data has changed
            } else {
                // Handle invalid index
            }
        }
        if (Ref.achi10) {
            // Change the image at a specific index (for example, index 2).
            int indexToChange = 9;
            int newImageResource = R.drawable.ic_achie_gallery_10; // Replace 'new_image' with your desired image resource

            if (indexToChange >= 9 && indexToChange < images.length) {
                images[indexToChange] = newImageResource;
                adapter.notifyDataSetChanged(); // Notify the adapter that the data has changed
            } else {
                // Handle invalid index
            }
        }
        if (Ref.achi11) {
            // Change the image at a specific index (for example, index 2).
            int indexToChange = 10;
            int newImageResource = R.drawable.ic_achie_gallery_11; // Replace 'new_image' with your desired image resource

            if (indexToChange >= 10 && indexToChange < images.length) {
                images[indexToChange] = newImageResource;
                adapter.notifyDataSetChanged(); // Notify the adapter that the data has changed
            } else {
                // Handle invalid index
            }
        }
        if (Ref.achi12) {
            // Change the image at a specific index (for example, index 2).
            int indexToChange = 11;
            int newImageResource = R.drawable.ic_achie_gallery_12; // Replace 'new_image' with your desired image resource

            if (indexToChange >= 11 && indexToChange < images.length) {
                images[indexToChange] = newImageResource;
                adapter.notifyDataSetChanged(); // Notify the adapter that the data has changed
            } else {
                // Handle invalid index
            }
        }

    }

}