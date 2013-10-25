package blak.android.example.adapterseparated;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import blak.android.adapters.HolderListAdapter;
import blak.android.example.R;

import java.util.List;

public class invitesAdapter extends HolderListAdapter<Invite, invitesAdapter.ViewHolder> {
    public invitesAdapter(Context context, List<? extends Invite> invites) {
        super(context, invites);
    }

    @Override
    protected int getResourceId() {
        return R.layout.afs__item_name;
    }

    @Override
    protected ViewHolder createViewHolder(View view) {
        ViewHolder holder = new ViewHolder();
        holder.mNameTextView = (TextView) view.findViewById(R.id.afs__item_name);
        return holder;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return super.getView(position, convertView, parent);
    }

    @Override
    protected void initView(View view, ViewHolder holder, Invite invite, int position) {
        String name = invite.getName();
        if (TextUtils.isEmpty(name)) {
            name = invite.getContact();
        }
        holder.mNameTextView.setText(name);
    }

    public static class ViewHolder {
        TextView mNameTextView;
    }
}
