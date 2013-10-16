package blak.android.adapters;

import android.view.View;

public class ClickItemListener implements View.OnClickListener {
    private ItemClickListener mItemClickListener;

    public ClickItemListener() {
    }

    public ClickItemListener(ItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    public ItemClickListener getItemClickListener() {
        return mItemClickListener;
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v) {
        int position = (Integer) v.getTag();
        mItemClickListener.onClick(v, position);
    }
}
