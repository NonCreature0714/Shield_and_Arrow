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


    private final ShieldAndArrowActivity gameActivity;

    private int width;  //Screen width
    private int height; //Screen height

    private boolean newGame = true;

    class ShieldAndArrowThread extends Thread {


        /************************************************
         *  update() function updates all variables,    *
         *  such as physics, Canvas draw points, score  *
         *  life, etc.. It is called before draw.       *
         ************************************************/




        // Draw




    }

}
