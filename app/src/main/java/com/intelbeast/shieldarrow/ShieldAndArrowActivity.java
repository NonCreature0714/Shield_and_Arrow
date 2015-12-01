package com.intelbeast.shieldarrow;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by Bill on 11/18/15.
 */

 /*************************************************
  * ShieldAndArrowActivity extends and Activity   *
  * which creates a surface and a viewing area    *
  * for a player to interact with.                *
  *************************************************/
public class ShieldAndArrowActivity extends Activity {

  /****************************
   * Create a string variable *
   * for use in the log.      *
   ****************************/
  private static final String TAG = "Game Activity";


  /********************************
   *  Create a variable for the   *
   *  gameView, which will        *
   *  the player will interact    *
   *  with.                       *
   ********************************/
  private ShieldAndArrowView gameView;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);

    /****************************************************
     *  Create a log of onCreate being called.          *
     ****************************************************/
      Log.d(TAG, "onCreate");

    /****************************************************
     *  Use full screen without phone status icons.     *
     ****************************************************/
      requestWindowFeature(Window.FEATURE_NO_TITLE);
      getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

     /***************************************************
      * Dynamically allocate memory for the             *
      * ShieldAndArrowView class.                       *
      ***************************************************/
      gameView = new ShieldAndArrowView(this);
      setContentView(gameView);
      gameView.requestFocus();
  }



}
