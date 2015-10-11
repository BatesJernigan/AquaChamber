package com.example.aquachamber;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.VelocityTracker;
import android.view.Window;
import android.view.WindowManager;

public class level1Activity extends Activity {
    private int xDelta;
    private int yDelta;
    private VelocityTracker mVelocityTracker = null;
    private static final String DEBUG_TAG = "Velocity";
    //private SensorManager mSensorManager;
    //private Sensor gyroscope;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("demo", "inside of on create");
        super.onCreate(savedInstanceState);
        // turn title off
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        //sets ContentView
        setContentView(new GamePanel(this));

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}
