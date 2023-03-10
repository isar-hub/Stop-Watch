package com.example.stopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.TextureView;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;


public class MainActivity extends AppCompatActivity {
    private int miliSec = 0;
    private boolean running;
    private boolean wasRunning;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState!=null){
            miliSec = savedInstanceState.getInt("miliSec");
            running = savedInstanceState.getBoolean("running");
            wasRunning= savedInstanceState.getBoolean("wasRunning");

        }
        runTimer();


    }
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("miliSec", miliSec);
        savedInstanceState.putBoolean("running", running);
        savedInstanceState.putBoolean("wasRunning", wasRunning);

    }
    protected void onPause(){
        super.onPause();
        wasRunning = running;
        running=false;
    }
    protected void onResume() {
        super.onResume();
        if (wasRunning) {
            running = true;
        }
    }
    public void onClickStart(View view){

        running = true;

    }




    public void onClickStop(View view){
        running = false;
        miliSec=0;
    }
    public void onClickReset(View view){
        running = false;

    }
    private void runTimer(){
        final TextView timeView =(TextView) findViewById(R.id.timer);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {

                int minutes = ((miliSec/3600));
                int secs = (miliSec%3600)/60;
                int mil = miliSec%60;
                String time = String.format(Locale.getDefault(),"%02d:%02d:%02d",minutes,secs,mil);
                timeView.setText(time);
                if(running){
                    miliSec++;
                }
                handler.postDelayed(this,10);
            }
        });
    }



}