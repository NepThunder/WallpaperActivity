package com.example.wallpaperactivity;

import androidx.appcompat.app.AppCompatActivity;
import android.app.WallpaperManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    Button wallpaperChange;
    WallpaperManager wpm;
    int[] drawables = {R.drawable.i1, R.drawable.i2, R.drawable.i3, R.drawable.i4, R.drawable.i5};
    int next = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wpm = WallpaperManager.getInstance(this);
        wallpaperChange = findViewById(R.id.button1);
        wallpaperChange.setOnClickListener(view -> setWallpaper());
    }

    private void setWallpaper() {
        Toast.makeText(this, "Changing Wallpaper", Toast.LENGTH_SHORT).show();

        Timer myTimer = new Timer();
        myTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                int resourceId = drawables[next];
                next = (next + 1) % drawables.length;
                Bitmap wallpaper = ((BitmapDrawable) getResources().getDrawable(resourceId)).getBitmap();

                try {
                    wpm.setBitmap(wallpaper);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }, 0, 30000);
    }
}
