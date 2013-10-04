package blak.android.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public abstract class DropDownAdapter<Item, Holder> extends BaseListAdapter<Item> {
    private final View mView;

    public DropDownAdapter(Context context, List<? extends Item> items, View view) {
        super(context, items);
        mView = view;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return mView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        Holder holder;

        if (convertView == null) {
            int resourceId = getResourceDropDownId();
            convertView = getInflater().inflate(resourceId, parent, false);

            holder = createDropDownHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        Item item = getItem(position);
        initDropDownView(convertView, holder, item, position);

        return convertView;
    }

    protected abstract int getResourceDropDownId();
    protected abstract Holder createDropDownHolder(View view);
    protected abstract void initDropDownView(View view, Holder holder, Item item, int position);

    public View getView() {
        return mView;
    }
}
