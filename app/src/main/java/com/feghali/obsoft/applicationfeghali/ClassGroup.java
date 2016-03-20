package com.feghali.obsoft.applicationfeghali;

import java.util.ArrayList;

/**
 * Created by leilalelou on 8/13/2015.
 */
public class  ClassGroup {

    private String Event_picture_URL;
    private int Res_location;
    private String Title;
    private String Date_hour;
    private ArrayList<ClassChild> items=null;

    public String getEvent_picture_URL ()
    {return Event_picture_URL;}

    public void setEvent_URL(String URL)
    {this.Event_picture_URL=URL;}

    public void setRes_location(int Res_location)
    {this.Res_location=Res_location;}

    public int getRes_location()
    {return Res_location;}

    public void setTitle(String Title)
    {this.Title=Title;}

    public String getTitle()
    {return Title;}

    public void setDate_hour(String Date_hour)
    {
        this.Date_hour=Date_hour;
    }

    public String getDate_hour()
    {
        return Date_hour;
    }

    public void setitems(ArrayList<ClassChild>items)
    {
        this.items=items;
    }

    public ArrayList<ClassChild> getitems()
    {
        return items;
    }

}
