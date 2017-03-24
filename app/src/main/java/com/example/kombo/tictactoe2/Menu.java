package com.example.kombo.tictactoe2;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class Menu extends AppCompatActivity {

    TextView newgame,tour,settings,quit;
    Animation newGameAnim,tourAnim,setAnim,quitAnim;
    MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        player=MediaPlayer.create(this,R.raw.royalgame);
        player.setLooping(true);
        player.start();
        //player.setVolume(0.0f,1.0f);

        ///Set Animation
        newGameAnim= AnimationUtils.makeInAnimation(this,true);
        tourAnim= AnimationUtils.makeInAnimation(this,true);
        setAnim= AnimationUtils.makeInAnimation(this,true);
        quitAnim= AnimationUtils.makeInAnimation(this,true);
        //SetDelay
        newGameAnim.setDuration(500);
        tourAnim.setDuration(900);
        setAnim.setDuration(1300);
        quitAnim.setDuration(1700);
        //find Id
        newgame=(TextView) findViewById(R.id.newgame);
        tour=(TextView) findViewById(R.id.tour);
        settings=(TextView) findViewById(R.id.settings);
        quit=(TextView) findViewById(R.id.quit);
        //setColor
        newgame.setTextColor(Color.parseColor("#696969"));
        tour.setTextColor(Color.parseColor("#696969"));
        settings.setTextColor(Color.parseColor("#696969"));
        quit.setTextColor(Color.parseColor("#696969"));
        //////
        newgame.startAnimation(newGameAnim);
        tour.startAnimation(tourAnim);
        settings.startAnimation(setAnim);
        quit.startAnimation(quitAnim);
        //////
        newgame.setClickable(true);

         // New Game listener
        newgame.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    newgame.setTextColor(Color.parseColor("#7B68EE"));
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                   newgame.setTextColor(Color.parseColor("#696969"));
                }

                return false;
            }
        });
        newgame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newIntent=new Intent(getApplicationContext(),LoadingActivity.class);
                startActivity(newIntent);
            }
        });
        // Quit Listener
        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                player.stop();
                finish();
            }
        });
        quit.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    quit.setTextColor(Color.parseColor("#7B68EE"));
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    quit.setTextColor(Color.parseColor("#696969"));
                }
                return false;
            }
        });
        //Settings Listener
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent optio=new Intent(getApplicationContext(),SettingsActivity.class);
                startActivity(optio);

            }
        });
        settings.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    settings.setTextColor(Color.parseColor("#7B68EE"));
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    settings.setTextColor(Color.parseColor("#696969"));
                }
                return false;
            }
        });
        //Tour Listener
        tour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tournament=new Intent(getApplicationContext(),Tournament.class);
                startActivity(tournament);

            }
        });
        tour.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    tour.setTextColor(Color.parseColor("#7B68EE"));
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    tour.setTextColor(Color.parseColor("#696969"));
                }
                return false;
            }
        });



    }

    @Override
    public void onBackPressed() {
        finish();
       player.stop();

    }


}
