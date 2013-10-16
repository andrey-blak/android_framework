package blak.android.adapters;

import android.view.View;

import blak.android.Listeners;

public class ItemSubviewClickListener implements View.OnClickListener {
    private ItemClickListener mItemClickListener;

    public ItemSubviewClickListener() {
    }

    public ItemSubviewClickListener(ItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    public ItemClickListener getItemClickListener() {
        return mItemClickListener;
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        mItemClickListener = (itemClickListener != null)? itemClickListener : Listeners.NULL_ITEM_CLICK_LISTENER;
    }

    @Override
    public void onClick(View v) {
        int position = (Integer) v.getTag();
        mItemClickListener.onClick(v, position);
    }
}
