package qwerky.rockpaperscissors;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
public class inform extends AppCompatActivity {
    boolean musicWanted=true;
    private MediaPlayer muzak;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inform);
        //Bundle bundle = getIntent().getExtras();
       // Random rand = new Random();
        //Extract the data…
        //Thingy set1 = (Thingy)bundle.getSerializable("Stuff");
        //These are all the diffent rock paper scissors thingies.


        final ArrayList<Thingy> set2 = new ArrayList<Thingy>(Arrays.asList(ThingyArsenal.ALL_THINGS));

        // Create an {@link WordAdapter}, whose data source is a list of {@link Word}s. The
        // adapter knows how to create list items for each item in the list.
        WordAdapter adapter = new WordAdapter(this, set2);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // word_list.xml file.
        ListView listView = (ListView) findViewById(R.id.list);

        // Make the {@link ListView} use the {@link WordAdapter} we created above, so that the
        // {@link ListView} will display list items for each {@link Word} in the list.
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                      @Override
                         public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                          Intent goGallery = new Intent(inform.this, gallery.class);
                          int theIWeWant = getTheIWeWant(i);


//Create the bundle
                          Bundle bundle = new Bundle();

//Add your data to bundle
                          bundle.putInt("Stuff", set2.get(theIWeWant).getBeatsPic());



//Add the bundle to the intent
                          goGallery.putExtras(bundle);

//Fire that second activity
                          startActivity(goGallery);
                      }

                     });
    }
    @Override
    public void onStart() {
        super.onStart();
        if (musicWanted) {
            muzak = MediaPlayer.create(this, R.raw.wavmuzakshort);


        }
        if(muzak!=null){

        muzak.start();}




    }@Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer(muzak);

    }  private void releaseMediaPlayer(MediaPlayer mp) {
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

    private int getTheIWeWant(int i) {


        if (i % 3 == 0) return i + 2;
        if (i % 3 == 1) return i - 1;
        if (i % 3 == 2) return i - 1;
        return 0;
    }
}







