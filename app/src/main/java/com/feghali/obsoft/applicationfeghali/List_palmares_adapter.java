package com.feghali.obsoft.applicationfeghali;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by leilalelou on 8/17/2015.
 */
public class List_palmares_adapter extends BaseAdapter
{

    Context context=null;
    List<Palmares_class> list_palmares=null;
    LayoutInflater inflater=null;


    List_palmares_adapter(Context context,List<Palmares_class>list_palmares)
    {
        this.list_palmares=list_palmares;
        this.context=context;
        this.inflater=LayoutInflater.from(context);

    }

    public void removeItem(int position)
    {
        list_palmares.remove(position);
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount(){return list_palmares.size();}

    @Override
    public Palmares_class getItem(int position)  {return null;}

    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position,View convertView,ViewGroup parent)
    {
        View_Holder_palmares viewholder;

        if(convertView==null)
        {

            viewholder=new View_Holder_palmares();
            convertView=this.inflater.inflate(R.layout.palmares_listview_layout,parent,false);
            viewholder.year=(TextView)convertView.findViewById(R.id.year_palmares);
            viewholder.event_image=(ImageView)convertView.findViewById(R.id.Event_image_palmares);
            viewholder.event_text=(TextView)convertView.findViewById(R.id.Event_text_palmares);
            viewholder.championship_text=(TextView)convertView.findViewById(R.id.championship_palmares);
            viewholder.co_driver_image=(ImageView)convertView.findViewById(R.id.Co_driver_image);
            viewholder.co_driver_text=(TextView)convertView.findViewById(R.id.Co_driver_text);
            viewholder.car_image=(ImageView)convertView.findViewById(R.id.Car_image);
            viewholder.car_text=(TextView)convertView.findViewById(R.id.Car_text);
            viewholder.comment_text=(TextView)convertView.findViewById(R.id.Comment_text);
            convertView.setTag(viewholder);


        }
        else {
            viewholder = (View_Holder_palmares) convertView.getTag();
        }

        Palmares_class palmares = list_palmares.get(position);
        viewholder.year.setText(palmares.getYear_palmares());
        Picasso.with(context).load(palmares.getUrl_Event_pict()).into(viewholder.event_image);
        viewholder.event_text.setText(palmares.getEvent_text());
        viewholder.championship_text.setText(palmares.getChampionship_text());
        Picasso.with(context).load(palmares.getUrl_Co_diver_pict()).into(viewholder.co_driver_image);
        viewholder.co_driver_text.setText(palmares.getCo_diver_text());
        Picasso.with(context).load(palmares.getUrl_Car_pict()).into(viewholder.car_image);
        viewholder.car_text.setText(palmares.getCar_text());
        viewholder.comment_text.setText(palmares.getComment_text());

        return convertView;

    }
}
