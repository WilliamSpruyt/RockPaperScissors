package qwerky.rockpaperscissors;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Family on 05/08/2016.
 */
public class WordAdapter extends ArrayAdapter<Thingy> {

    public WordAdapter(Activity context, ArrayList<Thingy> word) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, word);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.my_list, parent, false);
        }

        // Get the {@link AndroidFlavor} object located at this position in the list
        Thingy currentWord = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView nameTextView = (TextView) listItemView.findViewById(R.id.beats);
        // Get the version name from the current AndroidFlavor object and
        // set this text on the name TextView
        nameTextView.setText(currentWord.toString());

        // Find the TextView in the list_item.xml layout with the ID version_number
        //TextView numberTextView = (TextView) listItemView.findViewById(R.id.default_text_view);
        // Get the version number from the current AndroidFlavor object and
        // set this text on the number TextView
        //numberTextView.setText(currentWord.defaultTranslation());
        // Find the ImageView in the list_item.xml layout with the ID list_item_icon
             ImageView iconView = (ImageView) listItemView.findViewById(R.id.image);
               // Get the image resource ID from the current AndroidFlavor object and
                       // set the image to iconView
                              iconView.setImageResource(currentWord.getThumb());





        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView
        return listItemView;
    }





}