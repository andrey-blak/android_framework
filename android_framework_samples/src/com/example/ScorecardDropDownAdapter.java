//package com.example;
//
//import android.content.Context;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.AdapterView;
//import android.widget.PopupMenu;
//import android.widget.TextView;
//import biz.mobidev.golf.R;
//import blak.android.adapters.DropDownAdapter;
//
//import java.util.List;
//
//public class ScorecardDropDownAdapter extends DropDownAdapter<String, Object> {
//    private AdapterView.OnItemClickListener mOnItemClickListener;
//    View mSpinner;
//
//    public ScorecardDropDownAdapter(Context context, View view, View spinner) {
//        super(context, null, view);
//        mSpinner = spinner;
//    }
//
//    public ScorecardDropDownAdapter(Context context, List<String> items, View view) {
//        super(context, items, view);
//    }
//
//    @Override
//    protected int getResourceDropDownId() {
//        return R.layout.gc__player_dropdown_item;
//    }
//
//    @Override
//    protected Object createDropDownHolder(View view) {
//        return null;
//    }
//
//    @Override
//    protected void initDropDownView(View view, Object viewHolder, String item, int position) {
//        ((TextView) view).setText(item);
//        view.setTag(position);
//        view.setOnClickListener(mOnClickListener);
//    }
//
//    public AdapterView.OnItemClickListener getOnItemClickListener() {
//        return mOnItemClickListener;
//    }
//
//    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
//        mOnItemClickListener = onItemClickListener;
//    }
//
//    private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            int position = (Integer) v.getTag();
//            if (mOnItemClickListener != null) {
//                mOnItemClickListener.onItemClick((AdapterView) v.getParent(), v, position, position);
//            }
//            AdapterView spinner = (AdapterView) v.getParent();
//            spinner.getOnItemClickListener().onItemClick((AdapterView) v.getParent(), v, position, position);
//        }
//    };
//
//}
