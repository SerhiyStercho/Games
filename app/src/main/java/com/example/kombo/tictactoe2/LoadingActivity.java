package com.example.kombo.tictactoe2;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class LoadingActivity extends AppCompatActivity {
      ProgressBar loading;
      TextView  loadText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        loading = (ProgressBar) findViewById(R.id.loading);
        loadText = (TextView) findViewById(R.id.loadText);
        loading.setMax(100);
        loading.getProgressDrawable().setColorFilter(Color.parseColor("#7B68EE"), PorterDuff.Mode.SRC_IN);
        final Thread loadThread = new Thread() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i <= 100; i++) {
                        loading.setProgress(i);
                        sleep(30);
                    }

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Помилка", Toast.LENGTH_SHORT).show();
                } finally {
                    Intent mainScreen = new Intent(getApplicationContext(), GameScreenActivity.class);
                    startActivity(mainScreen);
                    finish();
                }

            }

        };
        loadThread.start();

    }
    @Override
    public void onBackPressed() {

    }
}
