package blak.android.adapters;

import android.content.Context;
import android.view.LayoutInflater;
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
    public long getItemId(int position) {
        return position;
    }

    @Override
    public T getItem(int position) {
        return mItems.get(position);
    }

    public List<? extends T> getItems() {
        return mItems;
    }

    public void setItems(List<? extends T> items) {
        mItems = (items != null) ? items : new ArrayList<T>();
    }

    protected Context getContext() {
        return mContext;
    }

    protected LayoutInflater getInflater() {
        return mInflater;
    }
}
