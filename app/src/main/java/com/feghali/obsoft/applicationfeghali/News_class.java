package com.feghali.obsoft.applicationfeghali;

import android.content.Context;

/**
 * Created by leilalelou on 8/10/2015.
 */
public class News_class {


    private String url;
    private String title;
    private String description;
    private Context context=null;



    public News_class(String url,String title,String description)
    {
        super();
        this.url=url;
        this.title=title;
        this.description=description;

    }

    public News_class() {

    }

    public void setUrl(String url)
    {
        this.url=url;
    }

    public String getUrl()
    {

        return url;
    }

    public void setTitle(String title)
    {
        this.title=title;
    }

    public String getTitle()
    {
        return title;
    }


    public void setDescription(String description)
    {

        this.description=description;
    }

    public String getDescription()
    {
        return description;
    }


    public Context getcontext()
    {return context;}
}
