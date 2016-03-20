package com.feghali.obsoft.applicationfeghali;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

public class Home extends Fragment {

View rootView=null;
Button news_button=null;
Button calendar_button=null;
Button results_button=null;
Button about_button=null;
Button palmares_button=null;
FragmentManager manager_fragment=null;
MainActivity main=null;
ImageView facebook_image=null;
ImageView insta_image=null;
Context context=null;

    public static Intent getOpenFacebookIntent(Context context){
    try{
        context.getPackageManager().getPackageInfo("com.facebook.katana",0);
        return new Intent(Intent.ACTION_VIEW,Uri.parse("fb://profile/39467698622"));
    }
    catch (Exception e)
    {
        return new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.facebook.com/pages/Roger-Feghali-The-Official-Page/39467698622"));
    }
}

 @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

       rootView=inflater.inflate(R.layout.home_layout,container,false);
       context=rootView.getContext();
       news_button=(Button)rootView.findViewById(R.id.news_button);
       calendar_button=(Button)rootView.findViewById(R.id.calendar_button);
       results_button=(Button)rootView.findViewById(R.id.results_button);
       about_button=(Button)rootView.findViewById(R.id.about_button);
       palmares_button=(Button)rootView.findViewById(R.id.Palmares_button);
       main=(MainActivity) getActivity();
       manager_fragment= getActivity().getSupportFragmentManager();
       news_button.setOnClickListener(new View.OnClickListener() {
           @Override

           public void onClick(View v) {
               manager_fragment.popBackStack(null,FragmentManager.POP_BACK_STACK_INCLUSIVE);
               main.onNavigationDrawerItemSelected(2);
               main.onSectionAttached(2);
               main.restoreActionBar();
             }
       });

       calendar_button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               manager_fragment.popBackStack(null,FragmentManager.POP_BACK_STACK_INCLUSIVE);
               main.onNavigationDrawerItemSelected(3);
               main.onSectionAttached(3);
               main.restoreActionBar();
      }
       });

       results_button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               manager_fragment.popBackStack(null,FragmentManager.POP_BACK_STACK_INCLUSIVE);
               main.onNavigationDrawerItemSelected(5);
               main.onSectionAttached(5);
               main.restoreActionBar();
     }
       });

       about_button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               manager_fragment.popBackStack(null,FragmentManager.POP_BACK_STACK_INCLUSIVE);
               main.onNavigationDrawerItemSelected(6);
               main.onSectionAttached(6);
               main.restoreActionBar();
   }
       });

       palmares_button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               manager_fragment.popBackStack(null,FragmentManager.POP_BACK_STACK_INCLUSIVE);
               main.onNavigationDrawerItemSelected(4);
               main.onSectionAttached(4);
               main.restoreActionBar();
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
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
                1);
    }
}
