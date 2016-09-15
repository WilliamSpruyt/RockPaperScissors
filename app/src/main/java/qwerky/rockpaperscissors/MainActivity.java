package qwerky.rockpaperscissors;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity {
    // counter for objects in array set1
    int thingCount = 0;

    Boolean musicWanted = true;
    int level = 0;
    int streak = 0;
    int chances = 0;
    int hiStreak = 0;
    int hiLevel = 0;
    // initialized to send string to SleepActivity
    String sender = "";
    //Will send the res id to SleepActivity
    int outcomePic;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    private GoogleApiClient client;
    //These are all the diffent rock paper scissors thingies.
    private Thingy rock = new Thingy("Rock", "Scissors", R.drawable.beatsrock, R.drawable.smashesscisorsthumb, "smashes");
    private Thingy paper = new Thingy("Paper", "Rock", R.drawable.cutspaper, R.drawable.beatsrockthumb, "wraps");
    private Thingy scissors = new Thingy("Scissors", "Paper", R.drawable.smashesscisors, R.drawable.cutspaperthumb, "cut");

    private Thingy monkey = new Thingy("Monkey", "Banana", R.drawable.beatsmonkey, R.drawable.eatsbananathumb, "eats");
    private Thingy clown = new Thingy("Clown", "Monkey", R.drawable.slipsclown, R.drawable.beatsmonkeythumb, "shoots");
    private Thingy banana = new Thingy("Banana", "Clown", R.drawable.eatsbanana, R.drawable.slipsclownthumb, "slips up");

    private Thingy knife = new Thingy("Knife", "Fork", R.drawable.beatsknife, R.drawable.beatsforkthumb, "stabs");
    private Thingy spoon = new Thingy("Spoon", "Knife", R.drawable.beatsspoon, R.drawable.beatsknifethumb, "reflects and shrinks");
    private Thingy fork = new Thingy("Fork", "Spoon", R.drawable.beatsfork, R.drawable.beatsspoonthumb, "forks");

    private Thingy humanity = new Thingy("Humanity", "DDT", R.drawable.beatshumanity, R.drawable.beatsddtthumb, "outlaws");
    private Thingy mosquito = new Thingy("Mosquito", "Humanity", R.drawable.beastmosquito, R.drawable.beatshumanitythumb, "plagues");
    private Thingy ddt = new Thingy("DDT", "Mosquito", R.drawable.beatsddt, R.drawable.beastmosquitothumb, "exterminates");

    private Thingy fire = new Thingy("Fire", "Air", R.drawable.beatsfire2, R.drawable.beatsairthumb, "consumes");
    private Thingy water = new Thingy("Water", "Fire", R.drawable.beatswater, R.drawable.beatsfire2thumb, "extinguishes");
    private Thingy air = new Thingy("Air", "Water", R.drawable.beatsair, R.drawable.beatswaterthumb, "evaporates");

    private Thingy slug = new Thingy("Slug", "Snake", R.drawable.beatslug2, R.drawable.beatsssnakethumb, "slimes");
    private Thingy frog = new Thingy("Frog", "Slug", R.drawable.beatsfrog, R.drawable.beatslugthumb, "tongue-grabs");
    private Thingy snake = new Thingy("Snake", "Frog", R.drawable.beatsssnake, R.drawable.beatsfrogthumb, "swallows whole");

    private Thingy fox = new Thingy("Fox", "Chief", R.drawable.beatsfox, R.drawable.beatschiefthumb, "outsmarts");
    private Thingy hunter = new Thingy("Hunter", "Fox", R.drawable.beatshunter, R.drawable.beatsfoxthumb, "guts");
    private Thingy chief = new Thingy("Chief", "Hunter", R.drawable.beatschief, R.drawable.beatshunterthumb, "bosses");

    private Thingy beer = new Thingy("Beer", "Spirits", R.drawable.beatsclown, R.mipmap.ic_launcher, "raises");
    private Thingy wines = new Thingy("Wines", "Beer", R.drawable.beatsclown, R.mipmap.ic_launcher, "make you feel queer after");
    private Thingy spirits = new Thingy("Spirits", "Wines", R.drawable.beatsclown, R.mipmap.ic_launcher, "are stronger than");

    Thingy[] set1 = new Thingy[]{rock, paper, scissors, humanity, mosquito, ddt, monkey, clown, banana, knife,
            spoon, fork, slug, frog, snake, beer, wines, spirits, fire, water, air, fox, hunter, chief};
    private MediaPlayer muzak;

    private Cpu computer = new Cpu();
    //records player choice;
    private int playerChoice = 7;
    private Boolean yourTurn = true;
    private String result = "draw";

    public String getSender() {
        return sender;
    }

    public void setSender(String sendit) {
        sender = sendit;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

        // Restore preferences
        SharedPreferences scores = getSharedPreferences("scores", MODE_PRIVATE);
        hiLevel = scores.getInt("toplevel", 0);
        hiStreak = scores.getInt("histreak", 0);


        final TextView rockView = (TextView) findViewById(R.id.rock_button);
        final TextView paperView = (TextView) findViewById(R.id.paper_button);
        final TextView scissorsView = (TextView) findViewById(R.id.scissors_button);
        final TextView theI = (TextView) findViewById(R.id.infomazione);


        if (rockView != null) {
            rockView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    rockMme(v);
                }
            });
        }
        if (paperView != null) {
            paperView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    papMme(v);
                }
            });
        }
        if (scissorsView != null) {
            scissorsView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    sciMme(v);
                }
            });
        }

    }


    public void rockMme(View view) {
        if (yourTurn) {
            TextView pressedView = (TextView) findViewById(R.id.rock_button);
            if (pressedView != null) {
                pressedView.setBackgroundColor(Color.rgb(11, 188, 201));
            }

            playerChoice = 1;
            result = inPlay(set1[thingCount], set1[thingCount + 1], set1[thingCount + 2], computer);
            if (result.equals("win")) {
                final MediaPlayer ding = MediaPlayer.create(this, R.raw.ding);
                ding.start();
                outcomePic = set1[thingCount].beatsPic;
                thingCount += 3;

            } else if (result.equals("lose")) {
                if (hiStreak < streak) {
                    hiStreak = streak;
                }
                if (hiLevel < level) {
                    hiLevel = level;
                }
                releaseMediaPlayer(muzak);
                chances = Math.max(0, chances - 1);
                level = 1;


                setSender("You chose " + set1[thingCount].getName() + ".\nThe computer chose " + set1[thingCount + 1].getName() + ".\nYou lose.\n"
                        + set1[thingCount + 1].getName() + " " + set1[thingCount + 1].getVerb() + " " + set1[thingCount].getName() + ".");

                thingCount = 0;
                Intent i = new Intent(this, SleepActivity.class);


//Create the bundle
                Bundle bundle = new Bundle();

//Add your data to bundle
                bundle.putString("Stuff", getSender());
                Log.i("getBeatsrbun ", "" + outcomePic);
                bundle.putInt("Picky", outcomePic);


//Add the bundle to the intent
                i.putExtras(bundle);

//Fire that second activity
                startActivity(i);
            }


        } else {
            doStarting();
        }
    }

    public void papMme(View view) {

        if (yourTurn) {
            TextView pressedView = (TextView) findViewById(R.id.paper_button);
            if (pressedView != null) {
                pressedView.setBackgroundColor(Color.rgb(255, 105, 12));
            }
            playerChoice = 2;
            result = inPlay(set1[thingCount], set1[thingCount + 1], set1[thingCount + 2], computer);
            if (result.equals("win")) {
                final MediaPlayer ding = MediaPlayer.create(this, R.raw.ding);
                ding.start();
                outcomePic = set1[thingCount + 1].beatsPic;
                thingCount += 3;

            } else if (result.equals("lose")) {
                if (hiStreak < streak) {
                    hiStreak = streak;
                }
                if (hiLevel < level) {
                    hiLevel = level;
                }
                releaseMediaPlayer(muzak);
                chances = Math.max(0, chances - 1);
                level = 1;


                setSender("You chose " + set1[thingCount + 1].getName() + ".\nThe computer chose " + set1[thingCount + 2].getName() + ".\nYou lose.\n"
                        + set1[thingCount + 2].getName() + " " + set1[thingCount + 2].getVerb() + " " + set1[thingCount + 1].getName() + ".");

                thingCount = 0;

                Intent i = new Intent(this, SleepActivity.class);


//Create the bundle
                Bundle bundle = new Bundle();

//Add your data to bundle
                bundle.putString("Stuff", getSender());
                Log.i("getBeatsrbun ", "" + outcomePic);
                bundle.putInt("Picky", outcomePic);
//Add the bundle to the intent
                i.putExtras(bundle);

//Fire that second activity
                startActivity(i);
            }
        } else {
            doStarting();
        }
    }

    public void sciMme(View view) {

        if (yourTurn) {
            TextView pressedView = (TextView) findViewById(R.id.scissors_button);
            if (pressedView != null) {
                pressedView.setBackgroundColor(Color.rgb(0, 20, 196));
            }
            playerChoice = 3;
            result = inPlay(set1[thingCount], set1[thingCount + 1], set1[thingCount + 2], computer);
            if (result.equals("win")) {
                final MediaPlayer ding = MediaPlayer.create(this, R.raw.ding);
                ding.start();
                outcomePic = set1[thingCount + 2].beatsPic;
                thingCount += 3;

            } else if (result.equals("lose")) {
                if (hiStreak < streak) {
                    hiStreak = streak;
                }
                if (hiLevel < level) {
                    hiLevel = level;
                }
                releaseMediaPlayer(muzak);
                chances = Math.max(0, chances - 1);
                level = 1;


                setSender("You chose " + set1[thingCount + 2].getName() + ".\nThe computer chose " + set1[thingCount].getName() + ".\nYou lose.\n"
                        + set1[thingCount].getName() + " " + set1[thingCount].getVerb() + " " + set1[thingCount + 2].getName() + ".");

                thingCount = 0;

                Intent i = new Intent(this, SleepActivity.class);


//Create the bundle
                Bundle bundle = new Bundle();

//Add your data to bundle
                bundle.putString("Stuff", getSender());
                Log.i("getBeatsrbun ", "" + outcomePic);
                bundle.putInt("Picky", outcomePic);
//Add the bundle to the intent
                i.putExtras(bundle);

//Fire that second activity
                startActivity(i);
            }
        } else {
            doStarting();
        }
    }


    public void doStarting() {
        if (thingCount > set1.length - 1) {
            thingCount = 0;
        }

        TextView sciView = (TextView) findViewById(R.id.scissors_button);

        if (sciView == null) throw new AssertionError();
        sciView.setBackgroundColor(Color.rgb(255, 235, 59));

        TextView rocView = (TextView) findViewById(R.id.rock_button);
        if (rocView != null) {
            rocView.setBackgroundColor(Color.rgb(244, 67, 54));
        }
        TextView papView = (TextView) findViewById(R.id.paper_button);
        if (papView != null) {
            papView.setBackgroundColor(Color.rgb(33, 150, 243));
        }

        display(set1[thingCount].getName(), R.id.rock_button, R.id.rock_image, set1[thingCount].getThumb());

        display(set1[thingCount + 1].getName(), R.id.paper_button, R.id.paper_image, set1[thingCount + 1].getThumb());
        display(set1[thingCount + 2].getName(), R.id.scissors_button, R.id.scissors_image, set1[thingCount + 2].getThumb());
        yourTurn = true;


        TextView lev = (TextView) findViewById(
                R.id.level);
        TextView cha = (TextView) findViewById(
                R.id.chancez);
        TextView toplev = (TextView) findViewById(
                R.id.hi_level);
        TextView histreak = (TextView) findViewById(
                R.id.hi_streak);
        TextView stree = (TextView) findViewById(
                R.id.ztreak);
        if (lev != null) {
            lev.setText("Level " + level);
        }
        if (cha != null) {
            cha.setText("Likes " + chances);
        }
        if (stree != null) {
            stree.setText("Streak " + streak);
        }
        if (histreak != null) {
            if (hiStreak < streak) {
                hiStreak = streak;
            }
            histreak.setText("Hi Streak " + hiStreak);
        }
        if (toplev != null) {
            if (hiLevel < level) {
                hiLevel = level;
            }
            toplev.setText("Hi Level " + hiLevel);
        }
        ImageView thumbShow = (ImageView) findViewById(R.id.challenge);
        if (thumbShow != null) {
            thumbShow.setImageResource(R.drawable.beatsclown);
        }


    }

    public void displayInfo(View view) {
        String[] infoString = new String[set1.length];
        int[] pics = new int[set1.length];

        for (int i = 0; i < set1.length; i++) {

            infoString[i] = set1[i].toString();
            pics[i] = set1[i].getThumb();
        }


        Intent i = new Intent(this, inform.class);

        Bundle bundle = new Bundle();

//Add your data to bundle
        // bundle.putStringArray("Stuff", infoString);
        // bundle.putIntArray("pics",pics);
        bundle.putSerializable("stuff", set1);


//Add the bundle to the intent
        i.putExtras(bundle);


        startActivity(i);

    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(String rocky, int identity, int imint, int thumb) {
        TextView rockBut = (TextView) findViewById(identity);
        ImageView rockIm = (ImageView) findViewById(imint);
       /*
        Button sciBut = (Button) findViewById(
                R.id.scissors_button);*/
        if (rockBut != null) {
            rockBut.setTextSize(76);
        }
        if (rockBut != null) {
            rockBut.setText(rocky);
        }
        if (rockIm != null) {
            rockIm.setImageResource(thumb);
        }




        /*papBut.setText(papery);
        sciBut.setText(scissorsy);*/
    }

    @Override
    public void onStart() {
        super.onStart();
        if (musicWanted = true) {
            muzak = MediaPlayer.create(this, R.raw.wavmuzakshort);


        }
        doStarting();
        if (muzak != null) {
            muzak.start();
        }

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://qwerky.rockpaperscissors/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();
        // We need an Editor object to make preference changes.
        // All objects are from android.context.Context

        SharedPreferences scores = getSharedPreferences("scores", MODE_PRIVATE);
        SharedPreferences.Editor editor = scores.edit();
        editor.putInt("toplevel", hiLevel);
        editor.putInt("histreak", hiStreak);
        // Commit the edits!
        editor.commit();

        releaseMediaPlayer(muzak);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://qwerky.rockpaperscissors/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

    public String inPlay(Thingy rock, Thingy paper, Thingy scissors, Cpu computer) {
        String name = "bollcks";


        String cpu;
        String result = "dna";
        String verb = "likes";
        int thumb = R.mipmap.ic_launcher;


        cpu = computer.cpuChoice(rock, paper, scissors);
        ImageView thumbShow = (ImageView) findViewById(
                R.id.challenge);
        TextView mess = (TextView) findViewById(
                R.id.rock_button);

        if (playerChoice == 1) {
            name = rock.getName();
            verb = rock.getVerb();
            thumb = rock.getThumb();

            mess = (TextView) findViewById(
                    R.id.rock_button);
            outcomePic = rock.getBeatsPic();
            result = rock.compete(cpu);
        }
        if (playerChoice == 2) {
            name = paper.getName();
            verb = paper.getVerb();
            thumb = paper.getThumb();
            mess = (TextView) findViewById(
                    R.id.paper_button);
            outcomePic = paper.getBeatsPic();
            result = paper.compete(cpu);
        }
        if (playerChoice == 3) {
            name = scissors.getName();
            verb = scissors.getVerb();
            thumb = scissors.getThumb();
            outcomePic = scissors.getBeatsPic();
            mess = (TextView) findViewById(
                    R.id.scissors_button);

            result = scissors.compete(cpu);

        }


        if (result.equals("lose")) {
            streak = 0;

            if (chances > 0) {
                result = "draw";
                Toast toast = Toast.makeText(this, "You used your like", Toast.LENGTH_SHORT);
                toast.show();
                chances = Math.max(0, chances - 1);
                TextView cha = (TextView) findViewById(
                        R.id.chancez);

                if (cha != null) {
                    cha.setText("Likes " + chances);
                }

            }
        }


        if (result.equals("win")) {
            level += 1;
            streak += 1;
            if (mess != null) {
                mess.setBackgroundColor(Color.rgb(0, 255, 0));
                mess.setTextSize(30);
                mess.setText("You chose " + name + ".\nThe computer chose " + cpu + ".\nYou win.\n"
                        + name + " " + verb + " " + cpu + ".");
                if (thumbShow != null) {
                    thumbShow.setImageResource(thumb);
                }
                if (streak % 3 == 0) {
                    Toast toast = Toast.makeText(this, "3 wins in a row gets you an extra like",
                            Toast.LENGTH_SHORT);
                    toast.show();

                    chances += 1;
                    TextView cha = (TextView) findViewById(
                            R.id.chancez);

                    if (cha != null) {
                        cha.setText("Likes " + chances);
                    }
                }
            }
        }

        if (result.equals("draw")) {
            streak = 0;
            mess.setBackgroundColor(Color.rgb(255, 165, 0));
            if (!name.equals(cpu)) {

                mess.setBackgroundColor(Color.rgb(218, 112, 214));
            }

            mess.setTextSize(30);

            mess.setText("You chose " + name + ".\nThe computer chose " + cpu + ".\nA draw.\n" +
                    name + " likes " + cpu + ".");
        }
        yourTurn = false;
        return result;

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
        }
    }
}