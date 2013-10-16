package blak.android;

import android.view.View;
import android.widget.AdapterView;

public class Listeners {
    public static final View.OnClickListener NULL_CLICK_LISTENER = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        }
    };

    public static final AdapterView.OnItemClickListener NULL_ITEM_CLICK_LISTENER = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        }
    };
}
