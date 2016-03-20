package com.feghali.obsoft.applicationfeghali;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;


public class Results extends Fragment {

    View rootView=null;
    Context context=null;
    ArrayList<List_live_1> array_live1=new ArrayList<List_live_1>();
    ListView listview1=null;
    List_live_1_adapter adapter1=null;
    List<List<String>> array_live2=new ArrayList<List<String>>();
    List_live_2_adapter adapter2=null;
    ListView listview2=null;
    int textview_nom_width;
    int textview_nom_height;
    private Dictionary<Integer, Integer> listViewItemHeights = new Hashtable<Integer, Integer>();
    ImageView facebook_image=null;
    ImageView insta_image=null;
    LinearLayout results_header2=null;
    HorizontalScrollView results_scroll=null;
    HorizontalScrollView header_scroll=null;
    int scrollx, scrolly;
    LayoutInflater inflater=null ;


    String overall_server;
    private ProgressDialog pDialog=null;
    JSONObject object_json=null;
    private static String url = "http://192.168.0.67/lebanon/mobile-live-results";
    JSONObject object_i=null;
    JSONObject overall_object=null;
    List<String> array_stage=null;

    public static Intent getOpenFacebookIntent(Context context){
        try{
            context.getPackageManager().getPackageInfo("com.facebook.katana",0);
            return new Intent(Intent.ACTION_VIEW, Uri.parse("fb://profile/39467698622"));
        }
        catch (Exception e)
        {
            return new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.facebook.com/pages/Roger-Feghali-The-Official-Page/39467698622"));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {

        rootView=inflater.inflate(R.layout.results_layout,container,false);
        context=rootView.getContext();
        this.inflater=inflater;
        array_stage=new ArrayList<String>();

        listview1=(ListView)rootView.findViewById(R.id.list_view_live_results_1);
        listview2=(ListView)rootView.findViewById(R.id.list_view_live_results_2);


        facebook_image=(ImageView)rootView.findViewById(R.id.facebook_icon);
        insta_image=(ImageView)rootView.findViewById(R.id.insta_icon);
        facebook_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent facebookIntent=getOpenFacebookIntent(context);
                startActivity(facebookIntent);
            }
        });
        insta_image=(ImageView)rootView.findViewById(R.id.insta_icon);
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


        if(array_live1.isEmpty() || array_live2.isEmpty())
        new GetResults(context).execute() ;
        else
        {
            this.setRetainInstance(true);

            listview1.setAdapter(adapter1);
            listview2.setAdapter(adapter2);

        }



        return rootView;
    }

    public String[] stages={"stage1","stage2","stage3","stage4","stage5","stage6"};


    public String[] Initialise_table_stage(String[] s,JSONObject object)
    {
        int taille;
        String element="";
        taille=object.length()-1;
        s=new String[taille];
        for(int i=0;i<taille;++i)
        {
            try {
                JSONObject object_child= object.getJSONObject(Integer.toString(i));

                element=object_child.getString("Time");
                s[i]=element;

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return s;

    }
    public LinearLayout Ini_header_linear2(List_live_2_adapter adapter2,LinearLayout linear2,Context context,View view,JSONObject objet)
    {
        linear2=new LinearLayout(context);
        linear2=(LinearLayout)view.findViewById(R.id.results_header2);

        LinearLayout.LayoutParams layoutparams = new LinearLayout.LayoutParams(76,90);

        layoutparams.setMargins(0,0,12,0);


        for(int j=0;j<(objet.length()-1);++j)
        {
            try {
                JSONObject object_child= objet.getJSONObject(Integer.toString(j));
                TextView textView =new TextView (context);
                textView.setId(j);
                textView.setText(object_child.getString("StageName"));
                textView.setGravity(Gravity.CENTER);
                textView.setLayoutParams(layoutparams);
                linear2.addView(textView);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return linear2;
    }

    public  List<List<String>> Ini2(List<List <String>> list,JSONObject objet2,int i)
    {
        String[] essai_oci=new String[objet2.length()];

            essai_oci=Initialise_table_stage(essai_oci,objet2);

           List<String> list_2=new ArrayList<String>();
            list_2.addAll(Arrays.asList(essai_oci));

        list.add(i,list_2);
        return list;
    }


    public ArrayList<List_live_1> Ini(ArrayList<List_live_1> n)
    {String player1=" Roger Feghali Roger ";
        String player2="Elie Roukoz";
        String duree1=1+"h"+10+"m"+12+"s";
        String duree2=1+"h"+25+"m"+12+"s";
        List_live_1 LIVE1= new List_live_1() ;
        LIVE1.setDuree(duree1);
        LIVE1.setParticipant(player1);
        LIVE1.setPosition(1);
        n.add(LIVE1);
        for(int i=0;i<11;i++)
        {
            List_live_1 LIVE2= new List_live_1() ;
            LIVE2.setDuree(duree2);
            LIVE2.setParticipant(player2);
            LIVE2.setPosition(i + 2);
            n.add(LIVE2);
        }
        return n;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
                5);
    }


    private class GetResults extends AsyncTask<Void, Void, Void> {

        Context context;

        public  GetResults(Context context)
        {
            this.context=context;

        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(rootView.getContext());
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            // Creating service handler class instance
            ServiceHandler sh = new ServiceHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url, ServiceHandler.GET);



            Log.d("Response: ", "> " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    object_json=jsonObj.getJSONObject("data");

                    for(int i=0;i<object_json.length();++i)
                    {
                        array_stage.clear();
                        object_i=object_json.getJSONObject(Integer.toString(i));
                        List_live_1 live1= new List_live_1();

                        live1.setParticipant(object_i.getString("Name"));
                        live1.setPosition(i + 1);

                        overall_object=object_i.getJSONObject("Score");

                        overall_server=overall_object.getString("Overall");
                        live1.setDuree(overall_object.getString("Overall"));

                        array_live1.add(live1);

                        Ini2(array_live2,overall_object,i);
                    }



                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Log.e("ServiceHandler", "Couldn't get any data from the url");
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            if (pDialog.isShowing())
                pDialog.dismiss();

            adapter1=new List_live_1_adapter(context,array_live1);
            listview1.setAdapter(adapter1);

            adapter2=new List_live_2_adapter(inflater,array_live2,textview_nom_width,textview_nom_height);

            Ini_header_linear2(adapter2,results_header2,context,rootView,overall_object);

            listview2.setAdapter(adapter2);

           listview1.setOnScrollListener(new AbsListView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(AbsListView view, int scrollState) {

                }

                @Override
                public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                    int index=listview1.getFirstVisiblePosition();

                    View v=listview1.getChildAt(0);
                    int top= (v == null) ? 0 : (v.getTop() - listview1.getPaddingTop());
                    adapter1.notifyDataSetChanged();
                    listview2.setSelectionFromTop(index,top);
                }
            });

            listview2.setOnScrollListener(new AbsListView.OnScrollListener() {

                @Override
                public void onScrollStateChanged(AbsListView view, int scrollState) {

                }

                @Override
                public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                    adapter1.notifyDataSetChanged();
                    int index=listview2.getFirstVisiblePosition();
                    View v=listview2.getChildAt(0);
                    int top= (v == null) ? 0 : (v.getTop() - listview2.getPaddingTop());
                    listview1.setSelectionFromTop(index,top);
                }
            });

            listview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(context, "position" + position, Toast.LENGTH_LONG).show();
                }

            });

            header_scroll=(HorizontalScrollView)rootView.findViewById(R.id.horizontal_scroll_results_header);
            results_scroll=(HorizontalScrollView)rootView.findViewById(R.id.horizontal_scroll_live_results);


            header_scroll.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {

                    scrollx=v.getScrollX();
                    scrolly=v.getScrollY();
                    results_scroll.scrollTo(scrollx,scrolly);

                    return false;
                }
            });
            results_scroll.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {

                    scrollx=v.getScrollX();
                    scrolly=v.getScrollY();
                    header_scroll.scrollTo(scrollx,scrolly);



                    return false;
                }
            });





        }
    }

}

