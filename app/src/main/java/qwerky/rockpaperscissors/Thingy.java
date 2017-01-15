package qwerky.rockpaperscissors;

import android.content.Context;
import android.widget.Toast;

import java.io.Serializable;

/**
 * Created by Family on 23/07/2016.
 */
public class Thingy implements Serializable {
    String name;
    int beatsPic;
    int thumbPic;
    String beats;
    String verb;
    public Thingy(String Name, String Beats, int BeatsPic, int ThumbPic, String Verb){
        name=Name;
        beatsPic=BeatsPic;
        thumbPic=ThumbPic;
        beats=Beats;
        verb=Verb;
    }
    public String compete(String enemy){
        String result="lose";

        if(enemy.equals(beats)){result="win";}
        if(enemy.equals(name)){result="draw";}
        //toaster("ene "+enemy + " " + beats + "you " + name + " "+ result,context);
        return result;}

    public String getName()
    {return name;}

    public int getThumb()
    {return thumbPic;}

    public int getBeatsPic()
    {return beatsPic;}

    public String getVerb()
    {return verb;}

    public String toString()
    {return name+" "+verb+" "+beats+"!";}

    public void toaster(String message, Context context) {
        CharSequence text = "Hello toast!";
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(context, message, duration);
        toast.show();
    }

}





