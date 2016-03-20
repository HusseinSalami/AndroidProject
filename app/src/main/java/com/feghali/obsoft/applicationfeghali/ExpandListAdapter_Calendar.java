package com.feghali.obsoft.applicationfeghali;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by leilalelou on 8/13/2015.
 */
public class ExpandListAdapter_Calendar extends BaseExpandableListAdapter {

    private Context context=null;
    private ArrayList<ClassGroup> groups=null;
    View row=null;
    ButtonListenercalendar buttonListenercalendar=null;

    public void setButtonListenercalendar(ButtonListenercalendar buttonListenercalendar) {
        this.buttonListenercalendar = buttonListenercalendar;
    }


    public ExpandListAdapter_Calendar(Context context, ArrayList<ClassGroup> groups) {
        this.context = context;
        this.groups = groups;
    }

    @Override
    public ClassChild getChild(int groupPosition, int childPosition) {
        ArrayList<ClassChild> chList = groups.get(groupPosition).getitems();
        return chList.get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        ClassChild child = (ClassChild) getChild(groupPosition, childPosition);
        if (convertView == null) {
            LayoutInflater inflateInflater = (LayoutInflater) context
                    .getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inflateInflater.inflate(R.layout.child_item_event, null);
        }
        TextView description = (TextView) convertView.findViewById(R.id.Event_Description);
        description.setText(child.getEvent_Description().toString());
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        ArrayList<ClassChild> chList = groups.get(groupPosition).getitems();
        return chList.size();
    }

    @Override
    public ClassGroup getGroup(int groupPosition) {
        return groups.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return groups.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        row=convertView;
        ClassGroup group = (ClassGroup) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater inf = (LayoutInflater) context
                    .getSystemService(context.LAYOUT_INFLATER_SERVICE);
            row = inf.inflate(R.layout.group_item_event, null);
        }
        TextView title = (TextView) row.findViewById(R.id.lbl_Event_Title);
        TextView Date= (TextView) row.findViewById(R.id.lbl_Events_Date_hour);
        ImageView event_image = (ImageView) row.findViewById(R.id.img_Event);
        ImageView event_location=(ImageView) row.findViewById(R.id.img_location_Events);


        title.setText(group.getTitle());
        Date.setText(group.getDate_hour());
        Picasso.with(context).load(group.getEvent_picture_URL()).transform(new CircleTransform()).into(event_image);
        event_location.setImageResource(group.getRes_location());
        event_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                buttonListenercalendar.clickedcalendar(row, groupPosition);

            }
        });


        return row;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition)
    {
        return true;
    }


    public interface ButtonListenercalendar
    {
        public void clickedcalendar(View view,int position);
    }

}
