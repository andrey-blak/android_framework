package blak.android.example.adapter_simple;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import blak.android.adapters.HolderListAdapter;
import blak.android.example.R;

import java.util.List;

public class SimpleHolderAdapter extends HolderListAdapter<SimpleItem, SimpleHolderAdapter.SimpleHolder> {
    public SimpleHolderAdapter(Context context, List<? extends SimpleItem> items) {
        super(context, items);
    }

    @Override
    protected int getResourceId() {
        return R.layout.afs__item_text_2;
    }

    @Override
    protected SimpleHolder createViewHolder(View view) {
        SimpleHolder holder = new SimpleHolder();
        holder.fileNameView = (TextView) view.findViewById(R.id.afs__item_name);
        holder.descriptionView = (TextView) view.findViewById(R.id.afs__item_description);
        return holder;
    }

    @Override
    protected void initView(View view, SimpleHolder holder, SimpleItem item, int position) {
        holder.fileNameView.setText(item.name);
        holder.descriptionView.setText(item.description);
    }

    public static class SimpleHolder {
        TextView fileNameView;
        TextView descriptionView;
    }
}
