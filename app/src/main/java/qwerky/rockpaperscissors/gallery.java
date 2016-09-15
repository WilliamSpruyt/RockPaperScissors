package qwerky.rockpaperscissors;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class gallery extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        Bundle bundle = getIntent().getExtras();

//Extract the data…

        int picky =bundle.getInt("Stuff");
        ImageView image =(ImageView) findViewById(R.id.picture);
        image.setImageResource(picky);
    }
}
