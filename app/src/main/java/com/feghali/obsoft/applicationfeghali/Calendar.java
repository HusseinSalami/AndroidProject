package com.feghali.obsoft.applicationfeghali;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by leilalelou on 8/4/2015.
 */
public class Calendar extends Fragment {

    private ExpandListAdapter_Calendar ExpAdapter=null;
    private ArrayList<ClassGroup> ExpListItems=null;
    private ExpandableListView ExpandList=null;
    View rootView=null;
    Context context=null;
    Fragment_map map=null;
    Bundle bundle_latlong=null;
    ImageView facebook_image=null;
    ImageView insta_image=null;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.calendar_layout, container, false);
        context=rootView.getContext();
        ExpandList = (ExpandableListView) rootView.findViewById(R.id.expandable_Event);
        ExpListItems = new ArrayList<ClassGroup>();
        ExpListItems = IniExpListView();
        ExpAdapter = new ExpandListAdapter_Calendar(getActivity(), ExpListItems);
        ExpAdapter.setButtonListenercalendar(new ExpandListAdapter_Calendar.ButtonListenercalendar() {
            @Override
            public void clickedcalendar(View view, int position)
            {

                map=new Fragment_map();
                bundle_latlong=new Bundle();
                bundle_latlong.putDouble("long",get_longitude(longitude,position));
                bundle_latlong.putDouble("lat",get_latitude(latitude,position));
                bundle_latlong.putString("title_location",location[position]);
                map.setArguments(bundle_latlong);

                FragmentManager manager_map=getActivity().getSupportFragmentManager();
                FragmentTransaction transaction_map=manager_map.beginTransaction();
                transaction_map.replace(R.id.container,map).addToBackStack(null).commit();

            }
        });
        ExpandList.setAdapter(ExpAdapter);
        ExpandList.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            int previousGroup = -1;
            @Override
            public void onGroupExpand(int groupPosition) {
                if (groupPosition != previousGroup)
                    ExpandList.collapseGroup(previousGroup);
                previousGroup = groupPosition;
            }
        });

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


    public Double get_latitude(Double latitude[],int position)
    {
        return  latitude[position];

    }

   public  Double get_longitude(Double longitude[],int position )
   {
       return longitude[position];

   }


    String location[]={"jbeil","jnoub","bekaa","Beirut"};
    Double longitude[]={35.6519282, 35.333333, 36.1762615, 35.4733996};
    Double latitude[]={34.1230022, 33.416667, 34.0156218, 33.8989467};

    public ArrayList<ClassGroup> IniExpListView() {

        String group_Event_URL[] = {"https://t1.ftcdn.net/jpg/00/32/44/38/400_F_32443801_WaMMe6Pb2JFJAaxEGASKCiscXowoQavn.jpg", "https://t1.ftcdn.net/jpg/00/32/44/38/400_F_32443801_WaMMe6Pb2JFJAaxEGASKCiscXowoQavn.jpg", "https://t1.ftcdn.net/jpg/00/32/44/38/400_F_32443801_WaMMe6Pb2JFJAaxEGASKCiscXowoQavn.jpg", "https://t1.ftcdn.net/jpg/00/32/44/38/400_F_32443801_WaMMe6Pb2JFJAaxEGASKCiscXowoQavn.jpg"};
        int group_Images_Res[] = {R.drawable.ic_location,R.drawable.ic_location,R.drawable.ic_location,R.drawable.ic_location};
        String group_Event_Title[] = location;
        String group_Event_Date[] = {"1/1/2005   22h10", "1/1/2005   22h10", "1/1/2005   22h10", "1/1/2005   22h10"};
        String child_Event_Desciption[] = {"RALLY JBEIL", "RALLY JNOUB", "RALLY CHMEL", "RALLY B2A3" };
        String child_Event_Description2[]={"JBEIL","JNOUB","CHMEL","B2A3"};
        ArrayList<ClassGroup> list = new ArrayList<ClassGroup>();
        ArrayList<ClassChild> chld;
        int size_group = 4;
        int size_child = 1;
        for (int i = 0; i < size_group; i++) {
            ClassGroup gru = new ClassGroup();
            gru.setTitle(group_Event_Title[i]);
            gru.setRes_location(group_Images_Res[i]);
            gru.setDate_hour(group_Event_Date[i]);
            gru.setEvent_URL(group_Event_URL[i]);
            chld = new ArrayList<ClassChild>();
            ClassChild ch = new ClassChild();
            ch.setEvent_Description(child_Event_Desciption[i]);

            chld.add(ch);
            ClassChild chi = new ClassChild();
            chi.setEvent_Description(child_Event_Description2[i]);
            chld.add(chi);

            gru.setitems(chld);
            list.add(gru);

        }
        return list;
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
                3);
    }


}
