package com.feghali.obsoft.applicationfeghali;

import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by leilalelou on 8/10/2015.
 */
public class Fragmentclass_news extends Fragment {

    View rootview=null;
    String description_news;
    Context context=null;
    LinearLayout linear_layout_news_scrollview=null;
    ImageView selected=null;
    String url_initial;
    ImageView facebook_image=null;
    ImageView insta_image=null;
    int position;

    ArrayList<String> table = new ArrayList<String>();
    private ProgressDialog pDialog=null;
    JSONArray array_json=null;
    private static String url = "http://192.168.0.67/lebanon/mobile-news-1";
    JSONObject object_i=null;
    ArrayList<String> array_bundle_slide=null;

    public static Intent getOpenFacebookIntent(Context context) {
        try {
            context.getPackageManager().getPackageInfo("com.facebook.katana", 0);
            return new Intent(Intent.ACTION_VIEW, Uri.parse("fb://profile/39467698622"));
        } catch (Exception e) {
            return new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/pages/Roger-Feghali-The-Official-Page/39467698622"));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.fragmentextend_layout_news, container, false);
        context = rootview.getContext();

        description_news=getArguments().getString("description");
        position=getArguments().getInt("position");

        array_bundle_slide=getArguments().getStringArrayList("slide_pic_json_url"+Integer.toString(position));
        TextView description=(TextView) rootview.findViewById(R.id.description_news);

        context=container.getContext();
        description.setText(description_news);
        linear_layout_news_scrollview=(LinearLayout)rootview.findViewById(R.id.linear_scroll_news);
        selected=(ImageView)rootview.findViewById(R.id.picture_news_extended);
        url_initial=getArguments().getString("url");
        Picasso.with(rootview.getContext()).setLoggingEnabled(true);
        Picasso.with(rootview.getContext()).load(url_initial).into(selected);

        set_scroll_news(linear_layout_news_scrollview,array_bundle_slide,context,selected);
        selected.getDrawingCache(true);


        facebook_image = (ImageView) rootview.findViewById(R.id.facebook_icon);
        insta_image = (ImageView) rootview.findViewById(R.id.insta_icon);
        facebook_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent facebookIntent = getOpenFacebookIntent(context);
                startActivity(facebookIntent);
            }
        });
        insta_image = (ImageView) rootview.findViewById(R.id.insta_icon);
        insta_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri uri = Uri.parse("http://instagram.com/_u/roger_feghali/");
                Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);
                likeIng.setPackage("com.instagram.android");
                try {
                    startActivity(likeIng);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://instagram.com/roger_feghali/")));
                }
            }
        });


        return rootview;
    }

    public Fragmentclass_news() {

    }


    public ArrayList<String> tableau_url() {
        ArrayList<String> table = new ArrayList<String>();
        table.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQMSC1eA0KCNwtm4qoETraXOAUeySnQgMgVOQU_nWLZBRJxg7GeXQ");
        table.add("https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcQK-AUADBnMVS29vrkBUCLfXRuaTypx8Il0N46Jla-aqWZQQJ0m");
        table.add("https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcSg1tvQglFj_QRiWS4gxJN1EJbAM2pwyua49Ibskbme9kCm0W3S");
        table.add("https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcSg1tvQglFj_QRiWS4gxJN1EJbAM2pwyua49Ibskbme9kCm0W3S");
        table.add("https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcSg1tvQglFj_QRiWS4gxJN1EJbAM2pwyua49Ibskbme9kCm0W3S");
        table.add("https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcSg1tvQglFj_QRiWS4gxJN1EJbAM2pwyua49Ibskbme9kCm0W3S");
        table.add("https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcSg1tvQglFj_QRiWS4gxJN1EJbAM2pwyua49Ibskbme9kCm0W3S");
        table.add("https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcSg1tvQglFj_QRiWS4gxJN1EJbAM2pwyua49Ibskbme9kCm0W3S");
        table.add("https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcSg1tvQglFj_QRiWS4gxJN1EJbAM2pwyua49Ibskbme9kCm0W3S");
        return table;
    }

    public void set_scroll_news(LinearLayout layout, final ArrayList<String> table_url, final Context context, final ImageView select)
    {
        ImageView image;
        int i;
        final int ressource;
        final int j;

        for (i = 0; i < table_url.size(); i++) {
            image = new ImageView(context);
            LinearLayout.LayoutParams layoutparams = new LinearLayout.LayoutParams(170, 140);
            image.setLayoutParams(layoutparams);

            Picasso.with(context).setLoggingEnabled(true);

            Picasso.with(context).load(table_url.get(i)).transform(new CircleTransform()).into(image);
            image.setId(i);

            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Picasso.with(context).load(table_url.get(v.getId())).into(select);

                }
            });

            layout.addView(image);
        }
    }



}