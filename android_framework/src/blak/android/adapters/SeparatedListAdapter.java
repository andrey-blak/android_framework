package blak.android.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

public class SeparatedListAdapter extends BaseAdapter {
    public static final int TYPE_SECTION_HEADER = 0;

    private final List<Section> mSections = new ArrayList<Section>();

    // TODO extract creation of mHeaderAdapter
    public final ArrayAdapter<String> mHeaderAdapter;

    public SeparatedListAdapter(Context context, int headerId) {
        mHeaderAdapter = new ArrayAdapter<String>(context, headerId);
    }

    public void addSection(String header, Adapter adapter) {
        mHeaderAdapter.add(header);
        mSections.add(new Section(header, adapter));
    }

    @Override
    public int getCount() {
        // total together all sections, plus one for each section header  
        int total = 0;
        for (Section section : mSections) {
            Adapter adapter = section.mAdapter;
            total += adapter.getCount() + 1;
        }
        return total;
    }

    @Override
    public int getViewTypeCount() {
        // assume that headers count as one, then total all sections  
        int total = 1;
        for (Section section : mSections) {
            Adapter adapter = section.mAdapter;
            total += adapter.getViewTypeCount();
        }
        return total;
    }

    @Override
    public int getItemViewType(int position) {
        int type = 1;
        for (Section section : mSections) {
            Adapter adapter = section.mAdapter;
            int size = adapter.getCount() + 1;

            // check if position inside this section   
            if (position == 0) {
                return TYPE_SECTION_HEADER;
            }
            if (position < size) {
                return type + adapter.getItemViewType(position - 1);
            }

            // otherwise jump into next section  
            position -= size;
            type += adapter.getViewTypeCount();
        }
        return -1;
    }

    @Override
    public Object getItem(int position) {
        for (Section section : mSections) {
            Adapter adapter = section.mAdapter;
            int size = adapter.getCount() + 1;

            // check if position inside this section
            if (position == 0) {
                return section.mHeader;
            }
            if (position < size) {
                return adapter.getItem(position - 1);
            }

            // otherwise jump into next section
            position -= size;
        }
        return null;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int position) {
        return getItemViewType(position) != TYPE_SECTION_HEADER;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int sectionNum = 0;
        for (Section section : mSections) {
            Adapter adapter = section.mAdapter;
            int size = adapter.getCount() + 1;

            // check if position inside this section   
            if (position == 0) {
                return mHeaderAdapter.getView(sectionNum, convertView, parent);
            }
            if (position < size) {
                return adapter.getView(position - 1, convertView, parent);
            }

            // otherwise jump into next section  
            position -= size;
            sectionNum++;
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private static class Section {
        public String mHeader;
        public Adapter mAdapter;

        Section(String header, Adapter adapter) {
            mHeader = header;
            mAdapter = adapter;
        }
    }
}
