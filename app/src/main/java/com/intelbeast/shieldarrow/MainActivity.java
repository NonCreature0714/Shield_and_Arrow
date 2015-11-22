package com.intelbeast.shieldarrow;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends Activity implements OnClickListener {
    private static final String TAG = "ShieldAndArrow";

    /*******************************************
     * Called when MainActivity is created,    *
     * onCreate() sets the screen to the the   *
     * main menu and displays two clickable    *
     * buttons.                                *
     *******************************************/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create the buttons from XML parameters,
        // clicks inside those parameters pass the
        // view object to the onclick listener.
        View newButton = findViewById(R.id.new_button);
        newButton.setOnClickListener(this);

        View aboutButton = findViewById(R.id.about_button);
        newButton.setOnClickListener(this);
    }

    /*****************************************
     * onClick is a member function, called  *
     * when clicks are detected. (I think.)  *
     * If a button is clicked, start an      *
     * activity with specified intent.       *
     *****************************************/
    public void onClick(View v){
        switch (v.getId()){
        case R.id.new_button:
            //Set Intent to game activity.
            Intent n = new Intent(this, ShieldAndArrowActivity.class);
            startActivity(n);
            break;
        case R.id.about_button:
            //Set inten to about (more info) activity.
            Intent i = new Intent(this, About.class);
            startActivity(i);
            break;
        }
    }

    public void onConfigurationChanged(Configuration newConfig){
        super.onConfigurationChanged(newConfig);

        // Log the Configuration change.
        Log.d(TAG, "onConfigurationChanged " + newConfig.orientation);
    }
}
