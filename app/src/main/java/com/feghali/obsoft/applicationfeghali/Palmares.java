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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class Palmares extends Fragment {

    View rootView=null;
    Context context=null;
    ArrayList<Palmares_class> arraypalmares=null;
    ListView listpalmares=null;
    List_palmares_adapter adapter_palmares=null;
    ImageView facebook_image=null;
    ImageView insta_image=null;

    private ProgressDialog pDialog=null;
    JSONArray array_json=null;
    private static String url = "http://192.168.0.67/lebanon/mobile-palmares";
        Palmares_class palmares_objet=null;
        JSONObject object_i=null;

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
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {

        rootView=inflater.inflate(R.layout.palmares_layout,container,false);
        context=rootView.getContext();
        listpalmares=(ListView)rootView.findViewById(R.id.list_palmares);
        arraypalmares=new ArrayList<Palmares_class>();
        // arraypalmares=Ini2(arraypalmares);
        new GetPalmares(context).execute() ;


   //     adapter_palmares=new List_palmares_adapter(getActivity() , arraypalmares);

   //     listpalmares.setAdapter(adapter_palmares);

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


        return rootView;
    }

    public ArrayList<Palmares_class> Ini2 (ArrayList<Palmares_class> arraypalmares)
    {
        Palmares_class palmares_object= new Palmares_class();
        palmares_object.setCar_text("Lancer Evo 9 & KTM Xbow & Radical");
        palmares_object.setChampionship_text("MERC");
        palmares_object.setCo_diver_text("Giovanni Bernachi");
        palmares_object.setComment_text("5th overall");
        palmares_object.setEvent_text("Dubai International Rally");
        palmares_object.setUrl_Car_pict("http://www.mapsofworld.com/images/world-countries-flags/france-flag.gif");
        palmares_object.setUrl_Co_diver_pict("http://www.mapsofworld.com/images/world-countries-flags/france-flag.gif");
        palmares_object.setUrl_Event_pict("http://www.mapsofworld.com/images/world-countries-flags/france-flag.gif");
        palmares_object.setYear_palmares("2008");

        for(int i=0;i<12;++i)
        {
            arraypalmares.add(palmares_object);
        }

        return arraypalmares;
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
                4);
    }


    private class GetPalmares extends AsyncTask<Void, Void, Void> {

        Context context;

        public  GetPalmares(Context context)
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
                     array_json= jsonObj.getJSONArray("data");

                    for(int i=0;i<array_json.length();++i)
                    {
                        object_i=array_json.getJSONObject(i);

                        palmares_objet=new Palmares_class();

                        palmares_objet.setYear_palmares(object_i.getString("year"));
                        palmares_objet.setUrl_Event_pict("http://192.168.0.67/lebanon/"+object_i.getString("event_country"));
                        palmares_objet.setUrl_Co_diver_pict("http://192.168.0.67/lebanon/"+object_i.getString("codriver_country"));
                        palmares_objet.setUrl_Car_pict("http://192.168.0.67/lebanon/"+object_i.getString("car_logo"));
                        palmares_objet.setCar_text(object_i.getString("car"));
                        palmares_objet.setChampionship_text(object_i.getString("championship"));
                        palmares_objet.setComment_text(object_i.getString("comment"));
                        palmares_objet.setEvent_text(object_i.getString("event"));
                        palmares_objet.setCo_diver_text(object_i.getString("codriver"));

                        arraypalmares.add(palmares_objet);

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

            adapter_palmares=new List_palmares_adapter(context , arraypalmares);
            listpalmares.setAdapter(adapter_palmares);


        }
    }
}
