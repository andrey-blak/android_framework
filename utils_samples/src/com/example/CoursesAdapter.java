package com.example;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import biz.mobidev.golf.R;
import biz.mobidev.golf.adapters.HolderListAdapter;
import biz.mobidev.golf.network.model.dto.teetimes.search.GolfCoursesType;
import biz.mobidev.golf.network.model.dto.teetimes.search.TeeTimesType;

import java.util.List;

public class CoursesAdapter extends HolderListAdapter<GolfCoursesType, CoursesAdapter.Holder> {
    private static final int GRID_VIEW_NUM_COLUMNS = 3;

    public interface OnCourseItemClickListener {
        public void onTeeTimePressed(int position, GolfCoursesType course, int teeTimePosition);

        public void onPlusPressed(int position, GolfCoursesType course);
    }

    private OnCourseItemClickListener mOnCourseItemClickListener;

    public CoursesAdapter(Context context, OnCourseItemClickListener onCourseItemClickListener) {
        super(context);
        mOnCourseItemClickListener = onCourseItemClickListener;
    }

    public void setListener(OnCourseItemClickListener listener) {
        mOnCourseItemClickListener = listener;
    }

    @Override
    protected int getResourceId() {
        return R.layout.gc__course_item;
    }

    @Override
    protected Holder createViewHolder(View view) {
        Holder holder = new Holder();
        holder.mCourseName = (TextView) view.findViewById(R.id.goflcenter_find_tee_times_course_name);
        holder.mContainer = view.findViewById(R.id.container_tee_times);
        holder.mCourseDistanceAway = (TextView) view.findViewById(R.id.goflcenter_find_tee_times_btn_pinch);
        holder.mCourseShowCourseInfo = view.findViewById(R.id.goflcenter_find_tee_times_btn_show_course_info);
        holder.mCourseTeeTimes = (GridView) view.findViewById(R.id.goflcenter_find_tee_times_show_part_tee_time);
        holder.mCourseEmptyPhone = (TextView) view.findViewById(R.id.goflcenter_find_tee_times_show_empty_course_phone);
        holder.mCourseEmptySite = (TextView) view.findViewById(R.id.goflcenter_find_tee_times_show_empty_course_website);
        return holder;
    }

    @Override
    protected void initView(View view, Holder holder, GolfCoursesType course, int position) {
        holder.position = position;

        adjustGridView(holder.mCourseTeeTimes);
        holder.mCourseTeeTimes.setAdapter(new TeeTimesAdapter(getContext()));

        holder.mCourseShowCourseInfo.setOnClickListener(mOnClickListener);
        holder.mCourseShowCourseInfo.setTag(position);
        holder.mCourseTeeTimes.setOnItemClickListener(mOnItemClickListener);
        holder.mCourseTeeTimes.setTag(position);

        String name = ("null".equals(course.getName())) ? "" : course.getName();
        holder.mCourseName.setText(name); // temporarily

        holder.mCourseDistanceAway.setText(course.getDistanceAway());
        List<TeeTimesType> allTeeTimes = course.getTeeTimes();
        if (!allTeeTimes.isEmpty()) {
            int toIndex = Math.min(GRID_VIEW_NUM_COLUMNS, allTeeTimes.size());
            ((TeeTimesAdapter) holder.mCourseTeeTimes.getAdapter()).setData(allTeeTimes.subList(0, toIndex));
            holder.mCourseTeeTimes.setVisibility(View.VISIBLE);
            holder.mCourseEmptySite.setVisibility(View.GONE);
            holder.mCourseEmptyPhone.setVisibility(View.GONE);
            holder.mContainer.setBackgroundColor(Color.TRANSPARENT);
        } else {
            holder.mContainer.setBackgroundResource(R.color.gc__white);
            holder.mCourseTeeTimes.setVisibility(View.GONE);
            holder.mCourseEmptySite.setVisibility(View.VISIBLE);
            holder.mCourseEmptyPhone.setVisibility(View.VISIBLE);

            String site = course.getWebSite();
            if (!site.equals("null")) {
                if (!site.contains("http://")) {
                    site = "http://" + site;
                }
                holder.mCourseEmptySite.setText(site);
            }
            if (!course.getPhoneNumber().equals("null")) {
                holder.mCourseEmptyPhone.setText(course.getPhoneNumber());
            }
        }
    }

    public static void adjustGridView(GridView gridView) {
        gridView.setNumColumns(GRID_VIEW_NUM_COLUMNS);
        gridView.setCacheColorHint(0);
        gridView.setDrawingCacheBackgroundColor(0);
    }

    public static class Holder {
        TextView mCourseName;
        TextView mCourseDistanceAway;
        View mCourseShowCourseInfo;
        GridView mCourseTeeTimes;
        TextView mCourseEmptyPhone;
        TextView mCourseEmptySite;
        View mContainer;
        int position;
    }

    private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int position = (Integer) v.getTag();
            GolfCoursesType course = getItem(position);
            mOnCourseItemClickListener.onPlusPressed(position, course);
        }
    };

    private final AdapterView.OnItemClickListener mOnItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            int coursePosition = (Integer) parent.getTag();
            GolfCoursesType course = getItem(coursePosition);
            mOnCourseItemClickListener.onTeeTimePressed(coursePosition, course, position);
        }
    };
}
