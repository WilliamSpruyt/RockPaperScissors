package qwerky.rockpaperscissors;

/**
 * Created by Family on 23/07/2016.
 */
import java.util.Random;
public class Cpu {
    Thingy rock,paper,scissors;
    public Cpu(){

    }
    public String cpuChoice(Thingy rock,Thingy paper,Thingy scissors){
        Random choose=new Random();
        int a=choose.nextInt(3);

        if (a==0){return rock.name;}
        else if (a==1){return paper.name;}
        else if (a==2){return scissors.name;}
        else return "fuck this shitty game";
    }
}