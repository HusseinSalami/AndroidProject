package com.feghali.obsoft.applicationfeghali;


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

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by leilalelou on 8/13/2015.
 */
public class Fragment_map  extends Fragment {

    View rootView=null;
    MapView  mMapview=null;
    private GoogleMap map=null;
    double latitude ;
    double longitude ;
    String title_location;
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
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState)
    {


     rootView=inflater.inflate(R.layout.map_fragment,container,false);

     mMapview= (MapView)rootView.findViewById(R.id.mapView);
     latitude= getArguments().getDouble("lat");
     longitude=getArguments().getDouble("long");
     title_location=getArguments().getString("title_location");
     context=rootView.getContext();
     mMapview.onCreate(savedInstanceState);
     mMapview.onResume();//needed to get the map to display immediatelly
     MapsInitializer.initialize(getActivity().getApplicationContext());
     map=mMapview.getMap();

        MarkerOptions marker=new MarkerOptions().position(new LatLng(latitude,longitude)).title(title_location);

        marker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        map.addMarker(marker);
        CameraPosition cameraPosition=new CameraPosition.Builder().target(new LatLng(latitude,longitude)).zoom(12).build();
        map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

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

}
