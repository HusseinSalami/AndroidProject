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
 * Created by leilalelou on 8/10/2015.
 */
public class ListNewsAdapter extends BaseAdapter {

    Context context=null;
    protected List<News_class> listNews=null;
    LayoutInflater inflater=null;
    View row=null;

    public ListNewsAdapter(Context context, List<News_class> listNews) {
        this.listNews = listNews;
        this.context = context;
        this.inflater = LayoutInflater.from(context);

    }

    public void removeItem(int position) {
        listNews.remove(position);
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return listNews.size();
    }

    @Override
    public News_class getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolderNews holder;
        row = convertView;
        if (convertView == null) {
            holder = new ViewHolderNews();
            row = this.inflater.inflate(R.layout.list_news, parent, false);
            holder.newsImage=(ImageView) row.findViewById(R.id.img_News);
            holder.newsTitle = (TextView) row.findViewById(R.id.lbl_News_Title);
            holder.newsDescription = (TextView) row.findViewById(R.id.lbl_News_description);
            holder.flecheImage=(ImageView)row.findViewById(R.id.img_fleche);
            row.setTag(holder);

        } else

        {

            holder = (ViewHolderNews) row.getTag();

        }

        News_class news = listNews.get(position);
        holder.newsTitle.setText(news.getTitle());
        holder.newsDescription.setText(news.getDescription());
        holder.flecheImage.setImageResource(R.drawable.ic_arrow);
        Picasso.with(context).load(news.getUrl()).transform(new CircleTransform()).into(holder.newsImage);

        return row;
    }


}
