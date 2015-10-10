package com.example.aquachamber;

import android.app.Activity;
import android.os.Bundle;
import android.view.VelocityTracker;
import android.view.Window;
import android.view.WindowManager;

public class level1Activity extends Activity {
    private int xDelta;
    private int yDelta;
    private VelocityTracker mVelocityTracker = null;
    private static final String DEBUG_TAG = "Velocity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // turn title off
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(new GamePanel(this));

//        ImageView gameBall = (ImageView) findViewById(R.id.gameBall);
//
//        Bitmap bitmap = ((BitmapDrawable) gameBall.getDrawable()).getBitmap();
//        Log.d("demo", "game ball layout params width: " + bitmap.getWidth() + " height: " + bitmap.getHeight());
//
//        gameBall.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent event) {
//                final int X = (int) event.getRawX();
//                final int Y = (int) event.getRawY();
//                int index = event.getActionIndex();
//                int action = event.getActionMasked();
//                int pointerId = event.getPointerId(index);
//
//                switch(action) {
//                    case MotionEvent.ACTION_DOWN:
//                        if(mVelocityTracker == null) {
//                            // Retrieve a new VelocityTracker object to watch the velocity of a motion.
//                            mVelocityTracker = VelocityTracker.obtain();
//                        }
//                        else {
//                            // Reset the velocity tracker back to its initial state.
//                            mVelocityTracker.clear();
//                        }
//                        // Add a user's movement to the tracker.
//                        mVelocityTracker.addMovement(event);
//                        xDelta = (int) (X - view.getTranslationX());
//                        yDelta = (int) (Y - view.getTranslationY());
//                        break;
//                    case MotionEvent.ACTION_MOVE:
//                        mVelocityTracker.addMovement(event);
//                        // When you want to determine the velocity, call
//                        // computeCurrentVelocity(). Then call getXVelocity()
//                        // and getYVelocity() to retrieve the velocity for each pointer ID.
//                        mVelocityTracker.computeCurrentVelocity(1000);
//                        // Log velocity of pixels per second
//                        // Best practice to use VelocityTrackerCompat where possible.
//                        Log.d("velocity", "X velocity: " +
//                            VelocityTrackerCompat.getXVelocity(mVelocityTracker,
//                                pointerId));
//                        Log.d("velocity", "Y velocity: " +
//                            VelocityTrackerCompat.getYVelocity(mVelocityTracker,
//                                pointerId));
//                        view.setTranslationX(X-xDelta);
//                        view.setTranslationY(Y-yDelta);
//                        break;
//                    case MotionEvent.ACTION_UP:
//                    case MotionEvent.ACTION_CANCEL:
//                        // Return a VelocityTracker object back to be re-used by others.
//                        mVelocityTracker.recycle();
//                        break;
//                }
//                return true;
//            }
//        });
    }
}
