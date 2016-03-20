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
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class About extends Fragment {

    View rootView=null;
    private ProgressDialog pDialog=null;
    TextView biography_text=null;
    ImageView image_biography=null;

    ImageView facebook_image=null;
    ImageView insta_image=null;

    Context context=null;

    private static String url = "http://192.168.0.67/lebanon/mobile-biography";

    private static final String TAG_title = "title";
    private static final String TAG_object = "data";
    private static final String TAG_pict = "image";
    private static final String TAG_text = "text";
    JSONArray about = null;
    ArrayList<HashMap<String, String>> aboutList;


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

        rootView = inflater.inflate(R.layout.about_layout, container, false);
        aboutList = new ArrayList<HashMap<String, String>>();

        biography_text = (TextView) rootView.findViewById(R.id.text_biography);
        image_biography = (ImageView) rootView.findViewById(R.id.image_biography);
        context = rootView.getContext();
        //  Picasso.with(rootView.getContext()).load(getUrl_Biographi()).into(image_biography);
        facebook_image = (ImageView) rootView.findViewById(R.id.facebook_icon);
        insta_image = (ImageView) rootView.findViewById(R.id.insta_icon);
        facebook_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent facebookIntent = getOpenFacebookIntent(context);
                startActivity(facebookIntent);
            }
        });
        insta_image = (ImageView) rootView.findViewById(R.id.insta_icon);
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


        new GetAbout(biography_text,image_biography,context).execute();
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
                6);
    }

    String getBiography() {
        String bio;
        bio = "About feghali  About feghali About feghaliAbout feghali About feghaliAbout feghali  About feghaliAbout feghaliAbout " +
                "feghaliAbout feghaliAbout feghali About feghali About feghaliAbout feghali About feghal" +
                "i  About feghalivAbout feghaliAbout feghali";

        return bio;

    }


    String getUrl_Biographi() {

        String url;
        url = "http://www.poeticapublishing.com/86998626746360336318.jpg";
        return url;
    }


    private class GetAbout extends AsyncTask<Void, Void, Void> {

        TextView about_view;
        ImageView image_view;
        String text_bio="";
        String url_bio="";
        Context context;
      public  GetAbout(TextView about, ImageView image,Context context)
        {
            about_view=about;
            image_view=image;
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
                    about= jsonObj.getJSONArray(TAG_object);

                        JSONObject c = about.getJSONObject(0);

                        text_bio = c.getString(TAG_text);
                        String image_total= c.getString("image");

                    JSONObject J=new JSONObject(image_total);
                    url_bio=J.getString("image_intro");


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

            about_view.setText(text_bio);
            Picasso.with(context).load("http://192.168.0.67/lebanon/"+url_bio).into(image_view);
    }
}



}


