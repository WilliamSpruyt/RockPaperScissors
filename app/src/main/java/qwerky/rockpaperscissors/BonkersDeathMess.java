package qwerky.rockpaperscissors;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

public class BonkersDeathMess extends AppCompatActivity {
    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bonkers_death_mess);
        //Get the bundle
        Bundle bundle = getIntent().getExtras();

//Extract the data…
        String stuff = bundle.getString("Stuff");
        int picky = bundle.getInt("Picky");
        int wicky = bundle.getInt("Wicky", 0);

        boolean bonkers = bundle.getBoolean("bonk");

        mp = MediaPlayer.create(this, R.raw.backmuzik);
        final BonkersSurfaceView death = (BonkersSurfaceView) findViewById(R.id.surfacebonkoid);


        TextView mess = (TextView) findViewById(
                R.id.bonkDeathMess);

        mess.setText(stuff);
        death.setBm(picky, 200, 200);
        death.setBmWicky(wicky);


        death.setOnTouchListener(new OnSwipeTouchListener(BonkersDeathMess.this) {
            public void onSwipeUp() {


            }

            public void onSwipeRight() {
                death.setSwipeDirection("right");
                releaseMediaPlayer(mp);
                doWaking();

            }

            public void onSwipeLeft() {
                death.setSwipeDirection("left");
                releaseMediaPlayer(mp);
                doWaking();
            }

            public void onSwipeBottom() {

            }

        });
        if (mp != null) {
            mp.start();
        }
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

    public void toaster(String message) {
        Context context = getApplicationContext();
        CharSequence text = "Hello toast!";
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(context, message, duration);
        toast.show();
    }
}
