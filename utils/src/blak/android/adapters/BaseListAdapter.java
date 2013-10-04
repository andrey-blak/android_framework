package blak.android.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseListAdapter<T> extends BaseAdapter {
    private final Context mContext;
    private final LayoutInflater mInflater;
    private List<? extends T> mItems;

    public BaseListAdapter(Context context) {
        this(context, null);
    }

    protected BaseListAdapter(Context context, List<? extends T> items) {
        mItems = (items != null) ? items : new ArrayList<T>();
        mContext = context;

        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public T getItem(int position) {
        return mItems.get(position);
    }

    protected List<? extends T> getItems() {
        return mItems;
    }

    protected void setItems(List<? extends T> items) {
        mItems = items;
    }

    protected void removeItem(int position) {
        mItems.remove(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    protected View createViewFromResource(View convertView, ViewGroup parent, int resource) {
        View view;

        if (convertView == null) {
            view = mInflater.inflate(resource, parent, false);
        } else {
            view = convertView;
        }

        return view;
    }

    protected Context getContext() {
        return mContext;
    }

    public LayoutInflater getInflater() {
        return mInflater;
    }
}
