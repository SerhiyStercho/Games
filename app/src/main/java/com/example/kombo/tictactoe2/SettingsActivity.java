package com.example.kombo.tictactoe2;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {
    int last;
    Menu men;
    SeekBar regular;
    float change;
    ImageButton speaker;
    Menu menu=new Menu();
    private AudioManager audioManager;
    private Vibrator vibro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        vibro=(Vibrator) getSystemService(VIBRATOR_SERVICE);
        regular=(SeekBar)findViewById(R.id.regular);
        regular.getProgressDrawable().setColorFilter(Color.parseColor("#7B68EE"), PorterDuff.Mode.SRC_IN);

        speaker=(ImageButton)findViewById(R.id.speaker);
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        regular.setMax(audioManager
                .getStreamMaxVolume(AudioManager.STREAM_MUSIC));
        regular.setProgress(audioManager
                .getStreamVolume(AudioManager.STREAM_MUSIC));
        standSeek(regular.getProgress());
        //regular.setMax(10);
        //regular.setProgress(10);
        speaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (regular.getProgress() == 0) {
                    regular.setProgress(last);
                } else if (regular.getProgress() != 0) {

                    Thread lower = new Thread() {
                        @Override
                        public void run() {
                            try {

                                for (int i = regular.getProgress(); i >= 0; i--) {
                                    regular.setProgress(i);
                                    sleep(40);
                                }
                                vibro.vibrate(300);

                            } catch (Exception e) {

                            }
                        }
                    };
                    lower.start();
                }
            }
        });
        regular.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                try {
                    last=progress;
                    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,
                            progress, 0);
                    standSeek(progress);

                }catch (Exception e){

                }
               //onChange();
                //Toast.makeText(getApplicationContext(),""+change,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void standSeek(int progress) {
        last=progress;
        if(progress==0){

            speaker.setImageResource(R.drawable.volume_none);
            Toast.makeText(this,"Silence mode enabled",Toast.LENGTH_SHORT).show();
        }else if(progress>0 && progress<=3){
            speaker.setImageResource(R.drawable.volume_min);
        }else if (progress>3 && progress<=8){
            speaker.setImageResource(R.drawable.volume_midle);
        }else if (progress>8 && progress<=10){
            speaker.setImageResource(R.drawable.volume_max);
        }


    }




}
