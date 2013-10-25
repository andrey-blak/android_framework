package blak.android.example.adapterseparated;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import blak.android.adapters.HolderListAdapter;
import blak.android.example.R;

import java.util.List;

public class FriendsAdapter extends HolderListAdapter<Friend, FriendsAdapter.ViewHolder> {
    public FriendsAdapter(Context context, List<? extends Friend> invites) {
        super(context, invites);
    }

    @Override
    protected int getResourceId() {
        return R.layout.afs__item_text_2;
    }

    @Override
    protected ViewHolder createViewHolder(View view) {
        ViewHolder holder = new ViewHolder();
        holder.mNameTextView = (TextView) view.findViewById(R.id.afs__item_name);
        return holder;
    }

    @Override
    protected void initView(View view, ViewHolder holder, Friend friend, int position) {
        holder.mNameTextView.setText(friend.getName());
    }

    public static class ViewHolder {
        TextView mNameTextView;
    }
}
