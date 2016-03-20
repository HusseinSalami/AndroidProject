package com.feghali.obsoft.applicationfeghali;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by leilalelou on 8/14/2015.
 */
public class List_live_1_adapter extends BaseAdapter{

    Context context=null;
    List<List_live_1> list_live_1=null;
    LayoutInflater inflater=null;


    List_live_1_adapter(Context context,List<List_live_1>list_live_1)
    {
        this.list_live_1=list_live_1;
        this.context=context;
        this.inflater=LayoutInflater.from(context);

    }

    public void removeItem(int position)
    {
        list_live_1.remove(position);
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount(){return list_live_1.size();}

    @Override
    public List_live_1 getItem(int position)  {return null;}

    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position,View convertView,ViewGroup parent)
    {
        ViewHolderLive1 viewholder;

        if(convertView==null)
        {

            viewholder=new ViewHolderLive1();
            convertView=this.inflater.inflate(R.layout.list_live_1,parent,false);
            viewholder.duree_total_view=(TextView)convertView.findViewById(R.id.duree_text);
            viewholder.participant_view=(TextView)convertView.findViewById(R.id.parcipant_view);
            viewholder.position_view=(TextView)convertView.findViewById(R.id.position_text);
            convertView.setTag(viewholder);


        }
        else {
            viewholder = (ViewHolderLive1) convertView.getTag();
        }
        List_live_1 list1 = list_live_1.get(position);
        TextView position_holder=(TextView) convertView.findViewById(R.id.position_text);
        TextView duree_holder=(TextView)convertView.findViewById(R.id.duree_text);
        TextView participant_holder=(TextView)convertView.findViewById(R.id.parcipant_view);

        position_holder.setText(Integer.toString(list1.getPosition()));
        duree_holder.setText(list1.getDuree());
        participant_holder.setText(list1.getParticipant());

        return convertView;
    }
}
