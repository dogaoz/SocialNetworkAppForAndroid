package com.dogaozkaraca.izunetwork.CustomViews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class lobster extends TextView {


    public lobster(Context context) {
      super(context);
      if(!isInEditMode())
      {
      Typeface face=Typeface.createFromAsset(context.getAssets(), "lobster.ttf");

      this.setTypeface(face);
      }
    }

    public lobster(Context context, AttributeSet attrs) {
        super(context, attrs);
        if(!isInEditMode())
        {
     Typeface face=Typeface.createFromAsset(context.getAssets(), "lobster.ttf");
  this.setTypeface(face);
        }
    }

    public lobster(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        if(!isInEditMode())
        {
     Typeface face=Typeface.createFromAsset(context.getAssets(), "lobster.ttf");
  this.setTypeface(face); 
        }
    }

    @Override
	protected void onDraw (Canvas canvas) {
        super.onDraw(canvas);
        
       
    }

}