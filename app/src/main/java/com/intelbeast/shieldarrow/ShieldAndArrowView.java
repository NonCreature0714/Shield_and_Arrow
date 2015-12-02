package com.intelbeast.shieldarrow;

import java.util.Vector;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.animation.AnimationUtils;


/**
 * Created by Bill on 11/23/15.
 */


public class ShieldAndArrowView extends SurfaceView implements SurfaceHolder.Callback {

    private ShieldAndArrowThread _thread;

    private boolean previouslyRunning = false;


    private ShieldAndArrowActivity gameActivity; //TODO: resolve if this statement needs to be 'final'

    private int width;  //Screen width
    private int height; //Screen height

    private boolean newGame = true;

    class ShieldAndArrowThread extends Thread {


        /************************************************
         *  update() function updates all variables,    *
         *  such as physics, Canvas draw points, score  *
         *  life, etc.. It is called before draw.       *
         ************************************************/


/****************************
 *   Private functions      *
 ****************************/

        // TODO: private update function

        // TODO: private draw function


/****************************
 *   Public functions       *
 ****************************/

        // TODO: public touch x

        //TODO: public touch y

        //TODO: public run()

/*******************************/
/********************************
 * End of ShieldAndArrowThread  *
 ********************************/
/********************************/
    }

//TODO: ShieldAndArrowView() function, NOT class
  public ShieldAndArrowView(Context context){
    super(context);
    getHolder().addCallback(this);
    this.gameActivity = (ShieldAndArrowActivity) context;
    _thread = new ShieldAndArrowThread(getHolder(), this);
    setFocusable(true);
    setFocusableInTouchMode(true);
  }

    //TODO: create contructor

    //TODO: create destructor

    //TODO: create override functions
        //TODO: touch detection
        //TODO: onPause
}
