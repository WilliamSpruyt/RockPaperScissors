package qwerky.rockpaperscissors;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {
    // counter for objects in array ThingyArsenal.ALL_THINGS
    int thingCount = 0;
    Random choose=new Random();

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
     
    private MediaPlayer muzak;

    private Cpu computer = new Cpu();
    //records player choice;
    private int playerChoice = 7;
    private boolean yourTurn = true;
    private boolean bonkers=false;
    private String result = "draw";
    private int numberOfThings=(ThingyArsenal.ALL_THINGS.length);

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
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        bonkers=sp.getBoolean("Warped",false);
        musicWanted=sp.getBoolean("Music",true);
        // Restore preferences
        SharedPreferences scores = getSharedPreferences("scores", MODE_PRIVATE);
        hiLevel = scores.getInt("toplevel", 0);
        hiStreak = scores.getInt("histreak", 0);

        final CircleImageView rockpic=(CircleImageView) findViewById(R.id.rock_image);
        final CircleImageView paperpic=(CircleImageView) findViewById(R.id.paper_image);
        final CircleImageView scissorspic=(CircleImageView) findViewById(R.id.scissors_image);


        final TextView rockView = (TextView) findViewById(R.id.rock_button);
        final TextView paperView = (TextView) findViewById(R.id.paper_button);
        final TextView scissorsView = (TextView) findViewById(R.id.scissors_button);
        final TextView theI = (TextView) findViewById(R.id.infomazione);
        doStarting();

        if (rockView != null) {
            rockView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                     rockpic.setBorderWidth(10);rockMme(v,1);
                }
            });
        }
        if (paperView != null) {
            paperView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {paperpic.setBorderWidth(10);
                    rockMme(v,2);;
                }
            });
        }
        if (scissorsView != null) {
            scissorsView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {scissorspic.setBorderWidth(10);
                    rockMme(v,3);
                }
            });
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        return true;

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;}

        return super.onOptionsItemSelected(item);
    }


    public void rockMme(View view,int playerChoice) {
        if (yourTurn) {
            TextView pressedView = (TextView) findViewById(R.id.rock_button);




            result = inPlay(ThingyArsenal.ALL_THINGS[thingCount], ThingyArsenal.ALL_THINGS[thingCount + 1], ThingyArsenal.ALL_THINGS[thingCount + 2], computer);
            if (result.equals("win")) {
                final MediaPlayer ding = MediaPlayer.create(this, R.raw.ding);
                ding.start();
                outcomePic = ThingyArsenal.ALL_THINGS[thingCount].beatsPic;
                thingCount += 3;
                if(bonkers){thingCount=(choose.nextInt(numberOfThings/3))*3;}

            } else if (result.equals("lose")) {
                Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(this, R.anim.plummet);
                pressedView.startAnimation(hyperspaceJumpAnimation);

                if (hiStreak < streak) {
                    hiStreak = streak;
                }
                if (hiLevel < level) {
                    hiLevel = level;
                }
                releaseMediaPlayer(muzak);
                chances = Math.max(0, chances - 1);
                level = 1;


                setSender("You chose " + ThingyArsenal.ALL_THINGS[thingCount].getName() + ".\nThe computer chose " + ThingyArsenal.ALL_THINGS[thingCount + 1].getName() + ".\nYou lose.\n"
                        + ThingyArsenal.ALL_THINGS[thingCount + 1].getName() + " " + ThingyArsenal.ALL_THINGS[thingCount + 1].getVerb() + " " + ThingyArsenal.ALL_THINGS[thingCount].getName() + ".");

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

            playerChoice = 2;
            result = inPlay(ThingyArsenal.ALL_THINGS[thingCount], ThingyArsenal.ALL_THINGS[thingCount + 1], ThingyArsenal.ALL_THINGS[thingCount + 2], computer);
            if (result.equals("win")) {
                final MediaPlayer ding = MediaPlayer.create(this, R.raw.ding);
                ding.start();
                outcomePic = ThingyArsenal.ALL_THINGS[thingCount + 1].beatsPic;
                thingCount += 3;
                if(bonkers){thingCount=(choose.nextInt(numberOfThings/3))*3;}

            } else if (result.equals("lose")) {
                Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(this, R.anim.plummet);
                pressedView.startAnimation(hyperspaceJumpAnimation);
                if (hiStreak < streak) {
                    hiStreak = streak;
                }
                if (hiLevel < level) {
                    hiLevel = level;
                }
                releaseMediaPlayer(muzak);
                chances = Math.max(0, chances - 1);
                level = 1;


                setSender("You chose " + ThingyArsenal.ALL_THINGS[thingCount + 1].getName() + ".\nThe computer chose " + ThingyArsenal.ALL_THINGS[thingCount + 2].getName() + ".\nYou lose.\n"
                        + ThingyArsenal.ALL_THINGS[thingCount + 2].getName() + " " + ThingyArsenal.ALL_THINGS[thingCount + 2].getVerb() + " " + ThingyArsenal.ALL_THINGS[thingCount + 1].getName() + ".");

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

            playerChoice = 3;
            result = inPlay(ThingyArsenal.ALL_THINGS[thingCount], ThingyArsenal.ALL_THINGS[thingCount + 1], ThingyArsenal.ALL_THINGS[thingCount + 2], computer);
            if (result.equals("win")) {
                final MediaPlayer ding = MediaPlayer.create(this, R.raw.ding);
                ding.start();
                outcomePic = ThingyArsenal.ALL_THINGS[thingCount + 2].beatsPic;
                thingCount += 3;
                if(bonkers){thingCount=(choose.nextInt(numberOfThings/3))*3;}

            } else if (result.equals("lose")) {
                Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(this, R.anim.plummet);
                pressedView.startAnimation(hyperspaceJumpAnimation);
                if (hiStreak < streak) {
                    hiStreak = streak;
                }
                if (hiLevel < level) {
                    hiLevel = level;
                }
                releaseMediaPlayer(muzak);
                chances = Math.max(0, chances - 1);
                level = 1;


                setSender("You chose " + ThingyArsenal.ALL_THINGS[thingCount + 2].getName() + ".\nThe computer chose " + ThingyArsenal.ALL_THINGS[thingCount].getName() + ".\nYou lose.\n"
                        + ThingyArsenal.ALL_THINGS[thingCount].getName() + " " + ThingyArsenal.ALL_THINGS[thingCount].getVerb() + " " + ThingyArsenal.ALL_THINGS[thingCount + 2].getName() + ".");

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
        if (thingCount > ThingyArsenal.ALL_THINGS.length - 1) {
            thingCount = 0;
        }
        final CircleImageView rockpic=(CircleImageView) findViewById(R.id.rock_image);
        final CircleImageView paperpic=(CircleImageView) findViewById(R.id.paper_image);
        final CircleImageView scissorspic=(CircleImageView) findViewById(R.id.scissors_image);
        scissorspic.setBorderWidth(0);
        rockpic.setBorderWidth(0);
        paperpic.setBorderWidth(0);
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


        display(ThingyArsenal.ALL_THINGS[thingCount].getName(), R.id.rock_button, R.id.rock_image, ThingyArsenal.ALL_THINGS[thingCount].getThumb());
        int p=1;
        int s=2;
        if(bonkers){p=(choose.nextInt(numberOfThings/3)*3)+1-thingCount;
                    s=(choose.nextInt(numberOfThings/3)*3)+2-thingCount;}
        display(ThingyArsenal.ALL_THINGS[thingCount + p].getName(), R.id.paper_button, R.id.paper_image, ThingyArsenal.ALL_THINGS[thingCount + p].getThumb());
        display(ThingyArsenal.ALL_THINGS[thingCount + s].getName(), R.id.scissors_button, R.id.scissors_image, ThingyArsenal.ALL_THINGS[thingCount + s].getThumb());
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
        String[] infoString = new String[ThingyArsenal.ALL_THINGS.length];
        int[] pics = new int[ThingyArsenal.ALL_THINGS.length];

        for (int i = 0; i < ThingyArsenal.ALL_THINGS.length; i++) {

            infoString[i] = ThingyArsenal.ALL_THINGS[i].toString();
            pics[i] = ThingyArsenal.ALL_THINGS[i].getThumb();
        }


        Intent i = new Intent(this, inform.class);

        Bundle bundle = new Bundle();

//Add your data to bundlef
        // bundle.putStringArray("Stuff", infoString);
        // bundle.putIntArray("pics",pics);
        bundle.putSerializable("stuff", ThingyArsenal.ALL_THINGS);


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

        if (rockBut != null)  {

            Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(this, R.anim.tweener);
            rockBut.startAnimation(hyperspaceJumpAnimation);
            rockBut.setText(rocky);
            rockBut.setTextSize(70);


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

        if (muzak != null||musicWanted) {
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
        int computerInt=0;


        cpu = computer.cpuChoice(rock, paper, scissors);
        if(bonkers){
        if (cpu.equals(rock.getName())){computerInt=1;}
        if (cpu.equals(paper.getName())){computerInt=2;}
        if (cpu.equals(scissors.getName())){computerInt=3;}
        }

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
        if(bonkers)result=crazyPlay(playerChoice,computerInt);


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
                String messText="You chose " + name + ".\nThe computer chose " + cpu + ". You win!\n"
                        + name + " " + verb + " " + cpu + ".";
                if(messText.length()>80){mess.setTextSize(25);}
                mess.setText(messText);
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
    private String crazyPlay(int playerChoice,int cpuChoice)
    {   toaster("p "+playerChoice+"c "+cpuChoice);

        if (playerChoice==cpuChoice)return"draw";
        if (playerChoice==0&&cpuChoice==2||playerChoice==1&&cpuChoice==0||playerChoice==2&&cpuChoice==1)return"win";

        return"lose";

    }
    public void toaster(String message) {
        Context context = getApplicationContext();
        CharSequence text = "Hello toast!";
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(context, message, duration);
        toast.show();
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