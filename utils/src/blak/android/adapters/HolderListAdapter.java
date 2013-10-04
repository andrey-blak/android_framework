package blak.android.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public abstract class HolderListAdapter<Item, HolderType> extends BaseListAdapter<Item> {
    public HolderListAdapter(Context context) {
        super(context);
    }

    public HolderListAdapter(Context context, List<? extends Item> items) {
        super(context, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HolderType holder;

        if (convertView == null) {
            int resourceId = getResourceId();
            convertView = getInflater().inflate(resourceId, parent, false);

            holder = createViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (HolderType) convertView.getTag();
        }

        Item item = getItem(position);
        initView(convertView, holder, item, position);

        return convertView;
    }

    protected abstract int getResourceId();
    protected abstract HolderType createViewHolder(View view);
    protected abstract void initView(View view, HolderType holder, Item item, int position);
}
