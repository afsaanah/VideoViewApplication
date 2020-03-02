package com.example.videoviewapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.VideoView;
import android.widget.Button;

import static android.view.View.GONE;

public class MainActivity extends AppCompatActivity {
    private VideoView videoView;
    private ImageView playIv,pauseIv;
    private MediaController mediaController;
    private String url = "https://videocdn.bodybuilding.com/video/mp4/62000/62792m.mp4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        videoView=findViewById(R.id.videoView);
        playIv=findViewById(R.id.playIV);
        pauseIv = findViewById(R.id.pauseIV);

        Uri uri = Uri.parse(url);

        videoView.setVideoURI(uri);

/*        videoView.start()*/;
        mediaController = new MediaController(this);


        mediaController.setMediaPlayer(videoView);
        videoView.setMediaController(mediaController);
        videoView.requestFocus();

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                LinearLayout viewGroupLevel1 = (LinearLayout)  mediaController.getChildAt(0);
                LinearLayout viewGroupLevel2 = (LinearLayout) viewGroupLevel1.getChildAt(0);
                View v1 = viewGroupLevel1.getChildAt(0);
                v1.setVisibility(View.GONE);

            /*    View view = viewGroupLevel2.getChildAt(2);
                view.setVisibility(View.GONE);*/
            }
        });

        playIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                videoView.start();
                playIv.setVisibility(GONE);
                pauseIv.setVisibility(View.VISIBLE);
                pauseIv.postDelayed(new Runnable() {
                    public void run() {
                        pauseIv.setVisibility(View.INVISIBLE);
                    }
                }, 3000);

                videoView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        pauseIv.setVisibility(View.VISIBLE);
                        pauseIv.postDelayed(new Runnable() {
                            public void run() {
                                pauseIv.setVisibility(View.INVISIBLE);
                            }
                        }, 3000);
                    }
                });


            }
        });
        pauseIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                videoView.pause();
                pauseIv.setVisibility(GONE);
                playIv.setVisibility(View.VISIBLE);
                videoView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        playIv.setVisibility(View.VISIBLE);
                        pauseIv.setVisibility(GONE);
                    }
                });

            }
        });
    }
}
