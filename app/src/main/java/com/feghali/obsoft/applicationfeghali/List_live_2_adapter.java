package com.feghali.obsoft.applicationfeghali;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by leilalelou on 8/14/2015.
 */
public class List_live_2_adapter extends BaseAdapter

{
    List<List<String>> list_stages=null;
    List<TextView> textViews=null;
    LayoutInflater inflater=null;
    int width_nom;
    int height_nom;

    public List_live_2_adapter(LayoutInflater inflater, List<List<String>> list_stages, int width_nom, int height_nom) {
        this.width_nom = width_nom;
        this.height_nom = height_nom;
        this.list_stages = list_stages;
        this.inflater = inflater;

    }


    public void removeItem(int position) {
        list_stages.remove(position);
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list_stages.size();
    }


    @Override
    public List<String> getItem(int position) {
        return list_stages.get(position);
    }

    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolderLive2 viewholder;
        Context context;

        if (convertView == null) {

            List<String> stages = getItem(position);
            convertView = this.inflater.inflate(R.layout.list_live_2, parent, false);
            context = convertView.getContext();
            viewholder = new ViewHolderLive2();
            viewholder.linearlayout = (LinearLayout) convertView.findViewById(R.id.layout_stages);
            viewholder.Ini(stages, context);
            convertView.setTag(viewholder);

        } else

        {
            viewholder = (ViewHolderLive2) convertView.getTag();
        }

        List<String> stages = getItem(position);
        viewholder.setText_view(convertView, stages);

        return convertView;


    }
}
