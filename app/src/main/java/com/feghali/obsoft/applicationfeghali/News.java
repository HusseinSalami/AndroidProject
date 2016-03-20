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
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class News extends Fragment  {

    View rootView=null;
    Context context=null;
    ArrayList<News_class> arrayNews=new ArrayList<News_class>();
    ListView listViewNews=null;
    Fragmentclass_news frag=null;
    Bundle bundle=null;
    TextView description=null;
    String Description;
    String image_url;
    ListNewsAdapter adapter=null;
    ImageView facebook_image=null;
    ImageView insta_image=null;

    private ProgressDialog pDialog=null;
    JSONArray array_json=null;
    private static String url = "http://192.168.0.67/lebanon/mobile-news-1";
    News_class news_objet=null;
    JSONObject object_i=null;
    JSONArray array_slide=null;
    ArrayList<String> slide_array=null;




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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        rootView=inflater.inflate(R.layout.news_layout,container,false);
        context = rootView.getContext();
        listViewNews= (ListView) rootView.findViewById(R.id.list_News);



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

        if(arrayNews.isEmpty())

        new GetNews(context).execute() ;

        else {
            this.setRetainInstance(true);

            listViewNews.setAdapter(adapter);

        }
        listViewNews.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                frag = new Fragmentclass_news();


                FragmentManager manager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();

                description=(TextView) view.findViewById(R.id.lbl_News_description);
                image_url=arrayNews.get(position).getUrl();
                Description=description.getText().toString();
                bundle.putString("description",Description);
                bundle.putString("url",image_url);
                bundle.putInt("position",position);


                frag.setArguments(bundle);
                transaction.replace(R.id.container, frag).addToBackStack(null).commit();

            }
        });

 return rootView;

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
                2);
    }

    public ArrayList<News_class> Ini(ArrayList<News_class> n)
    {String titre=" title";
        String description="descriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescription" +
                "descriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescription" +
                "descriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescription" +
                "descriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescription" +
                "descriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescription" +
                "descriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescription" +
                "descriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescription" +
                "descriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescription" +
                "descriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescription" +
                "descriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescription" +
                "descriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescription" +
                "descriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescription" +
                "descriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescription" +
                "descriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescription" +
                "descriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescription" +
                "descriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescription" +
                "descriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescription" +
                "descriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescription" +
                "descriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescription" +
                "descriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescription" +
                "descriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescription" +
                "descriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescription" +
                "descriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescription" +
                "descriptiondescriptiondescription alloooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        News_class news= new News_class() ;
        for(int i=0;i<12;i++)
        {
            news.setTitle(titre+i);
            news.setDescription(description+i);
            news.setUrl("https://t1.ftcdn.net/jpg/00/32/44/38/400_F_32443801_WaMMe6Pb2JFJAaxEGASKCiscXowoQavn.jpg");
            n.add(news);
        }
        return n;
    }



    public ArrayList<String> Ini_array_slide(JSONArray jarray)
    {
        String url_pic="http://192.168.0.67/lebanon/";
        ArrayList<String> array_pic=new ArrayList<String>();
        for(int i=0;i<jarray.length();++i)
        {
            try {
                array_pic.add(i,url_pic+jarray.getString(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
return array_pic;

    }
    private class GetNews extends AsyncTask<Void, Void, Void> {

        Context context;

        public  GetNews(Context context)
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
                    bundle = new Bundle();
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    array_json= jsonObj.getJSONArray("data");
                    slide_array=new ArrayList<String>();

                    for(int i=0;i<array_json.length();++i)
                    {
                        String test1;
                        object_i=array_json.getJSONObject(i);

                        array_slide=object_i.getJSONArray("article_image");

          //              slide_object=slide_data(array_slide,slide_object,i);

                        String contenu_image=object_i.getString("image");
                        JSONObject object_image= new JSONObject(contenu_image);

                        array_slide=object_i.getJSONArray("article_image");

                        news_objet=new News_class();
                        news_objet.setDescription(object_i.getString("text"));
                        news_objet.setTitle(object_i.getString("title"));
                        news_objet.setUrl("http://192.168.0.67/lebanon/"+object_image.getString("image_intro"));


                        bundle.putStringArrayList("slide_pic_json_url"+Integer.toString(i),Ini_array_slide(array_slide));

                        arrayNews.add(news_objet);


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

            adapter = new ListNewsAdapter( context , arrayNews);
            listViewNews.setAdapter(adapter);




        }
    }

}
