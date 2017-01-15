package qwerky.rockpaperscissors;

/**
 * Created by Family on 29/11/2016.
 */

public abstract class ThingyArsenal {

    private static Thingy rock = new Thingy("Rock", "Scissors", R.drawable.beatsrock, R.drawable.smashesscisorsthumb, "smashes");
    private static Thingy paper = new Thingy("Paper", "Rock", R.drawable.cutspaper, R.drawable.beatsrockthumb, "wraps");
    private static Thingy scissors = new Thingy("Scissors", "Paper", R.drawable.smashesscisors, R.drawable.cutspaperthumb, "cut");

    private static Thingy monkey = new Thingy("Monkey", "Banana", R.drawable.beatsmonkey, R.drawable.eatsbananathumb, "eats");
    private static Thingy clown = new Thingy("Clown", "Monkey", R.drawable.slipsclown, R.drawable.beatsmonkeythumb, "shoots");
    private static Thingy banana = new Thingy("Banana", "Clown", R.drawable.eatsbanana, R.drawable.slipsclownthumb, "slips up");

    private static Thingy knife = new Thingy("Knife", "Fork", R.drawable.beatsknife, R.drawable.beatsforkthumb, "stabs");
    private static Thingy spoon = new Thingy("Spoon", "Knife", R.drawable.beatsspoon, R.drawable.beatsknifethumb, "reflects and shrinks");
    private static Thingy fork = new Thingy("Fork", "Spoon", R.drawable.beatsfork, R.drawable.beatsspoonthumb, "forks");

    private static Thingy humanity = new Thingy("Humanity", "DDT", R.drawable.beatshumanity, R.drawable.beatsddtthumb, "outlaws");
    private static Thingy mosquito = new Thingy("Mosquito", "Humanity", R.drawable.beastmosquito, R.drawable.beatshumanitythumb, "plagues");
    private static Thingy ddt = new Thingy("DDT", "Mosquito", R.drawable.beatsddt, R.drawable.beastmosquitothumb, "exterminates");

    private static Thingy fire = new Thingy("Fire", "Air", R.drawable.beatsfire2, R.drawable.beatsairthumb, "consumes");
    private static Thingy water = new Thingy("Water", "Fire", R.drawable.beatswater, R.drawable.beatsfire2thumb, "extinguishes");
    private static Thingy air = new Thingy("Air", "Water", R.drawable.beatsair, R.drawable.beatswaterthumb, "evaporates");

    private static Thingy slug = new Thingy("Slug", "Snake", R.drawable.beatslug2, R.drawable.beatsssnakethumb, "slimes");
    private static Thingy frog = new Thingy("Frog", "Slug", R.drawable.beatsfrog, R.drawable.beatslugthumb, "tongue-grabs");
    private static Thingy snake = new Thingy("Snake", "Frog", R.drawable.beatsssnake, R.drawable.beatsfrogthumb, "swallows whole");

    private static Thingy fox = new Thingy("Fox", "Chief", R.drawable.beatsfox, R.drawable.beatschiefthumb, "outsmarts");
    private static Thingy hunter = new Thingy("Hunter", "Fox", R.drawable.beatshunter, R.drawable.beatsfoxthumb, "guts");
    private static Thingy chief = new Thingy("Chief", "Hunter", R.drawable.beatschief, R.drawable.beatshunterthumb, "banishes");

    private static Thingy red = new Thingy("Red", "Yellow", R.drawable.beatsred, R.drawable.beatsyellowthumb, "is more dangerous than");
    private static Thingy blue = new Thingy("Blue", "Red", R.drawable.beatsblue, R.drawable.beatsredthumb, "depresses");
    private static Thingy yellow = new Thingy("Yellow", "Blue", R.drawable.beatsyellow, R.drawable.beatsbluethumb, "greens up");



    private static Thingy beer = new Thingy("Beer", "Spirits", R.drawable.beatsbeer,R.drawable.beatsspiritsthumb, "raises");
    private static Thingy wines = new Thingy("Wines", "Beer", R.drawable.beatswine, R.drawable.beatsbeerthumb, "make you feel queer after");
    private static Thingy spirits = new Thingy("Spirits", "Wines", R.drawable.beatsspirits,R.drawable.beatswinethumb, "are stronger than");

    private static Thingy mummy = new Thingy("Mummy", "Baby", R.drawable.oppressesmummy,R.drawable.looksafterbabythumb , "takes care of");
    private static Thingy daddy = new Thingy("Daddy", "Mummy", R.drawable.beatsdaddy,R.drawable.oppressesmummythumb , "oppresses");
    private static Thingy baby = new Thingy("Baby", "Daddy", R.drawable.looksafterbaby, R.drawable.beatsdaddy, "outscreams");

    private static Thingy plants = new Thingy("Plants", "Funghi", R.drawable.beatsplants, R.drawable.beats_fungi_thumb, "are prettier than");
    private static Thingy animals = new Thingy("Animals", "Plants", R.drawable.beats_animal, R.drawable.beatsplants_thumb, "eat");
    private static Thingy funghi = new Thingy("Fungi", "Animals", R.drawable.beats_fungi, R.drawable.beats_animal_thumb, "decompose");

     static Thingy[] ALL_THINGS = new Thingy[]{rock, paper, scissors, humanity, mosquito, ddt,mummy,daddy,baby, monkey, clown, banana,plants,animals,funghi,red,blue,yellow, knife,
            spoon, fork, slug, frog, snake, beer, wines, spirits, fire, water, air, fox, hunter, chief};

    {}

    public Thingy[] getAllThings(){return ALL_THINGS;}
}
