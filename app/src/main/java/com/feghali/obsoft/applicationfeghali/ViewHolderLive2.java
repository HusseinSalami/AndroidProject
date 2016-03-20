package com.feghali.obsoft.applicationfeghali;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.List;

public class ViewHolderLive2 {

    LinearLayout linearlayout;


public void  Ini(List<String> stages, Context context)
    {
        LinearLayout.LayoutParams layoutparams = new LinearLayout.LayoutParams(76,120);

        layoutparams.setMargins(12,0,0,0);
        for(int j=0;j<stages.size();++j)
        {
            TextView textView =new TextView (context);
            textView.setId(j);
            textView.setGravity(Gravity.CENTER_HORIZONTAL);
            textView.setLayoutParams(layoutparams);
            linearlayout.addView(textView);
        }
    }

    public void setText_view(View convertview,List<String> stages)
    {
        TextView textview;

        for(int i=0;i<stages.size();++i)
        {

           textview= (TextView) convertview.findViewById(i);
           textview.setText(stages.get(i));

        }
    }
}
