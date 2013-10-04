package blak.android.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseExpandableAdapter<Group, Child, GroupHolder, ChildHolder> extends BaseExpandableListAdapter {
    private final Context mContext;
    private final LayoutInflater mInflater;
    private List<? extends Group> mGroups;

    protected BaseExpandableAdapter(Context context) {
        this(context, null);
    }

    protected BaseExpandableAdapter(Context context, List<? extends Group> groups) {
        mGroups = (groups != null) ? groups : new ArrayList<Group>();
        mContext = context;

        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    protected Context getContext() {
        return mContext;
    }

    public LayoutInflater getInflater() {
        return mInflater;
    }

    public void setGroups(List<? extends Group> groups) {
        mGroups = groups;
    }

    @Override
    public int getGroupCount() {
        return mGroups.size();
    }

    @Override
    public Group getGroup(int groupPosition) {
        return mGroups.get(groupPosition);
    }

    @Override
    public abstract Child getChild(int groupPosition, int childPosition);

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupHolder holder;

        if (convertView == null) {
            convertView = getInflater().inflate(getGroupResourceId(), parent, false);
            holder = createGroupHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (GroupHolder) convertView.getTag();
        }

        Group item = getGroup(groupPosition);
        initGroupView(convertView, holder, item, groupPosition, isExpanded);

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildHolder holder;

        if (convertView == null) {
            convertView = getInflater().inflate(getChildResourceId(), parent, false);
            holder = createChildHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ChildHolder) convertView.getTag();
        }

        Child item = getChild(groupPosition, childPosition);
        initChildView(convertView, holder, item, groupPosition, childPosition, isLastChild);

        return convertView;
    }

    protected abstract int getGroupResourceId();
    protected abstract GroupHolder createGroupHolder(View view);
    protected abstract void initGroupView(View view, GroupHolder holder, Group item, int groupPosition, boolean isExpanded);

    protected abstract int getChildResourceId();
    protected abstract ChildHolder createChildHolder(View view);
    protected abstract void initChildView(View view, ChildHolder holder, Child item, int groupPosition, int childPosition, boolean isLastChild);
}
