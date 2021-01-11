package com.assignment.adpter;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.assignment.R;
import com.assignment.model.Item;
import com.assignment.model.ListModel;
import com.assignment.model.SectionItem;
import com.assignment.utils.EventInjectManager;

import java.util.ArrayList;
import java.util.List;


//region LIST ADAPTER
public class EntryAdapter extends ArrayAdapter<Item> implements EventInjectManager.EventInjectListener {

    private static final String TAG = "Search Activity" ;
    private Context context;
    private ArrayList<Item> items;
    private LayoutInflater vi;


    public EntryAdapter(Context context, ArrayList<Item> items) {
        super(context,0, items);
        this.context = context;
        this.items = items;
        vi = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        EventInjectManager.getInstance().registerForEvent(EventInjectManager.SUGARBOX_DISCONNECT_REMOVE_ICON, this);

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        final Item i = items.get(position);
        if (i != null) {
            if(i.isSection()){
                SectionItem si = (SectionItem)i;
                v = vi.inflate(R.layout.search_list_item_section, null);

                v.setOnClickListener(null);
                v.setOnLongClickListener(null);
                v.setLongClickable(false);

                final TextView sectionView = (TextView) v.findViewById(R.id.list_item_section_text);
                final ImageView list_arrow = (ImageView) v.findViewById(R.id.list_arrow);
                sectionView.setText(si.getTitle().toUpperCase());
                list_arrow.setOnClickListener(view -> EventInjectManager.getInstance().injectEvent(EventInjectManager.EVENT_TYPE_SEARCH, si.getTitle().toUpperCase()));

            }

        }
        return v;
    }


    @Override
    public void eventReceived(int eventType, Object data) {
        if (eventType == EventInjectManager.SUGARBOX_DISCONNECT_REMOVE_ICON) {
            notifyDataSetChanged();
        }
    }

    public void unregisterEventListener() {
        EventInjectManager.getInstance().unRegisterForEvent(EventInjectManager.HOME_PAGE_REFRESH, this);
        EventInjectManager.getInstance().unRegisterForEvent(EventInjectManager.SUGARBOX_DISCONNECT_REMOVE_ICON, this);
    }
}
    //endregion
