package com.example.gian.piaobau;

import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {
    private AnimationDrawable anima;
    private AnimationUtils an01, an02;
    public MediaPlayer player;
    public SeekBar skb;
    public int num=0;
    private CountDownTimer timer;
    private ImageView img;
    private Button btGira;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btGira = findViewById(R.id.btnGirar);
        skb = findViewById(R.id.skbTempo);
        img = findViewById(R.id.imagemPiao);
        player = MediaPlayer.create(MainActivity.this, R.raw.zapzap);
        botao();
    }

    private void botao(){
        btGira.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //num = skb.getProgress();
                zapZap();
                //tempo(num*1000);
            }
        });
    }

    private void tempo(int seg){
        timer = new CountDownTimer(seg,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                num = skb.getProgress();
                num--;
                skb.setProgress(num);
                zapZap();

            }

            @Override
            public void onFinish() {
                anima.stop();

            }
        }.start();
    }
    private void zapZap(){
        tocarSom();
        img.setBackgroundResource(R.drawable.zapzap);
        anima = (AnimationDrawable) img.getBackground();
        anima.start();
    }

    private void tocarSom(){
        try{
            if (player.isPlaying()){
                player.release();
                player = MediaPlayer.create(MainActivity.this, R.raw.zapzap);
            }
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    player.release();
                }
            });
            player.start();
        }
        catch (Exception e){
            player.release();
        }
    }
}
