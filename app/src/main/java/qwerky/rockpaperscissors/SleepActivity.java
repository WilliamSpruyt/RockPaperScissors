package qwerky.rockpaperscissors;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class SleepActivity extends AppCompatActivity {
    MediaPlayer mp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep);


        //Get the bundle
        Bundle bundle = getIntent().getExtras();

//Extract the data…
        String stuff = bundle.getString("Stuff");
        int picky = bundle.getInt("Picky");
        mp = MediaPlayer.create(this, R.raw.backmuzik);
        ImageView death = (ImageView) findViewById(R.id.deathScreen);

        TextView mess = (TextView) findViewById(
                R.id.deathMess);

        mess.setText(stuff);
        death.setImageResource(picky);



        death.setOnTouchListener(new OnSwipeTouchListener(SleepActivity.this) {
            public void onSwipeUp() {


            }

            public void onSwipeRight() {
                releaseMediaPlayer(mp);
                doWaking();

            }

            public void onSwipeLeft() {
               releaseMediaPlayer(mp);
                doWaking();
            }

            public void onSwipeBottom() {

            }

        });
        if(mp!=null){
            mp.start();}
    }

    protected void doWaking() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    private void releaseMediaPlayer(MediaPlayer mp) {
        // If the media player is not null, then it may be currently playing a sound.
        if (mp != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mp.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mp = null;
        }


    }
    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer(mp);

    }
}
