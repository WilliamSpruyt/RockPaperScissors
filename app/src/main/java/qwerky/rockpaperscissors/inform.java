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
    private MediaPlayer muzak;
    boolean musicWanted=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inform);
        //Bundle bundle = getIntent().getExtras();
       // Random rand = new Random();
        //Extract the data…
        //Thingy set1 = (Thingy)bundle.getSerializable("Stuff");
        //These are all the diffent rock paper scissors thingies.
         Thingy rock = new Thingy("Rock", "Scissors", R.drawable.smashesscisors, R.drawable.smashesscisorsthumb, "smashes");
         Thingy paper = new Thingy("Paper", "Rock",R.drawable.beatsrock,R.drawable.beatsrockthumb, "wraps");
         Thingy scissors = new Thingy("Scissors", "Paper",  R.drawable.cutspaper, R.drawable.cutspaperthumb, "cut");

         Thingy monkey = new Thingy("Monkey", "Banana", R.drawable.eatsbanana, R.drawable.eatsbananathumb, "eats");
         Thingy clown = new Thingy("Clown", "Monkey", R.drawable.beatsmonkey,R.drawable.beatsmonkeythumb, "shoots");
         Thingy banana = new Thingy("Banana", "Clown",R.drawable.slipsclown, R.drawable.slipsclownthumb,"slips up");

         Thingy knife = new Thingy("Knife", "Fork", R.drawable.beatsfork, R.drawable.beatsforkthumb, "stabs");
         Thingy spoon = new Thingy("Spoon", "Knife",R.drawable.beatsknife,  R.drawable.beatsknifethumb, "reflects and shrinks");
         Thingy fork = new Thingy("Fork", "Spoon", R.drawable.beatsspoon, R.drawable.beatsspoonthumb,"forks");

         Thingy humanity = new Thingy("Humanity", "DDT",  R.drawable.beatsddt, R.drawable.beatsddtthumb, "outlaws");
         Thingy mosquito = new Thingy("Mosquito", "Humanity",R.drawable.beatshumanity,R.drawable.beatshumanitythumb, "plagues");
         Thingy ddt = new Thingy("DDT", "Mosquito", R.drawable.beastmosquito,R.drawable.beastmosquitothumb, "exterminates");

       Thingy fire = new Thingy("Fire", "Air",R.drawable.beatsair, R.drawable.beatsairthumb, "consumes");
       Thingy water = new Thingy("Water", "Fire", R.drawable.beatsfire2,R.drawable.beatsfire2thumb, "extinguishes");
         Thingy air = new Thingy("Air", "Water",  R.drawable.beatswater, R.drawable.beatswaterthumb, "evaporates");

         Thingy slug = new Thingy("Slug", "Snake",R.drawable.beatsssnake, R.drawable.beatsssnakethumb, "slimes");
         Thingy frog = new Thingy("Frog", "Slug",R.drawable.beatslug2,R.drawable.beatslugthumb, "tongue-grabs");
          Thingy snake = new Thingy("Snake", "Frog",  R.drawable.beatsfrog,R.drawable.beatsfrogthumb, "swallows whole");

        Thingy fox = new Thingy("Fox", "Chief", R.drawable.beatschief, R.drawable.beatschiefthumb, "Outsmarts");
         Thingy hunter = new Thingy("Hunter", "Fox", R.drawable.beatsfox,R.drawable.beatsfoxthumb, "Guts");
          Thingy chief = new Thingy("Chief", "Hunter", R.drawable.beatshunter,R.drawable.beatshunterthumb, "Pulls rank on");

         Thingy beer = new Thingy("Beer", "Spirits", R.drawable.beatsclown,R.mipmap.ic_launcher, "lowers");
         Thingy wines = new Thingy("Wines", "Beer", R.drawable.beatsclown,R.mipmap.ic_launcher, "make you feel queer after");
         Thingy spirits = new Thingy("Spirits", "Wines", R.drawable.beatsclown, R.mipmap.ic_launcher, "are stronger than");

        Thingy[] set1 = new Thingy[]{rock, paper, scissors, humanity, mosquito, ddt, monkey, clown, banana, knife,
                spoon, fork, slug, frog, snake, beer, wines, spirits, fire, water, air, fox, hunter, chief};

        final ArrayList<Thingy> set2 = new ArrayList<Thingy>(Arrays.asList(set1));

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


//Create the bundle
                          Bundle bundle = new Bundle();

//Add your data to bundle
                          bundle.putInt("Stuff", set2.get(i).getBeatsPic());



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
    }}







