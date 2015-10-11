package com.example.aquachamber;

import android.content.Context;
import android.graphics.Canvas;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.util.Log;
import android.view.SurfaceHolder;
import android.widget.Toast;

import com.example.aquachamber.GamePanel;

public class MainThread extends Thread
{
    private int FPS = 30;
    private double averageFPS;
    private SurfaceHolder surfaceHolder;
    private GamePanel gamePanel;
    private boolean running;
    public static Canvas canvas;

    //gyroscope hardware variables
    private static SensorManager gravManager;
    private Sensor grav;

    public MainThread(SurfaceHolder surfaceHolder, GamePanel gamePanel, Context context)
    {
        super();
        this.surfaceHolder = surfaceHolder;
        this.gamePanel = gamePanel;
        gravManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        grav = gravManager.getDefaultSensor(Sensor.TYPE_GRAVITY);

        if (grav != null) {
            gravManager.registerListener(mySensorEventListener, grav,
                    SensorManager.SENSOR_DELAY_NORMAL);
            Log.i("Main Thread", "Registerered for GRAVITY Sensor");
        } else {
            Log.e("Main Thread", "Registerered for GRAVITY Sensor");
            Log.d("Main Thread", "GRAVITY Sensor not found");
            Toast.makeText(context, "GRAVITY Sensor not found", Toast.LENGTH_LONG);
        }
    }
    @Override
    public void run()
    {
        Log.d("demo", "inside of on thread run");
        long startTime;
        long timeMillis;
        long waitTime;
        long totalTime = 0;
        int frameCount =0;
        long targetTime = 1000/FPS;

        while(running) {
            startTime = System.nanoTime();
            canvas = null;

            //try locking the canvas for pixel editing
            try {
                canvas = this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {
                    this.gamePanel.update();
                    this.gamePanel.draw(canvas);

                }
            } catch (Exception e) {
            }
            finally{
                if(canvas!=null)
                {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    }
                    catch(Exception e){e.printStackTrace();}
                }
            }

            timeMillis = (System.nanoTime() - startTime) / 1000000;
            waitTime = targetTime-timeMillis;

            try{
                this.sleep(waitTime);
            } catch(Exception e){}

            totalTime += System.nanoTime()-startTime;
            frameCount++;
            if(frameCount == FPS)
            {
                averageFPS = 1000/((totalTime/frameCount)/1000000);
                frameCount =0;
                totalTime = 0;
                Log.d("demo", "averageFPS: " + averageFPS);
            }
        }
    }
    public void setRunning(boolean b)
    {
        running=b;
    }

    private SensorEventListener mySensorEventListener = new SensorEventListener() {

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }

        @Override
        public void onSensorChanged(SensorEvent event) {
            Log.d("GYROSCOPE", "X Value: " + event.values[0]);
            Log.d("GYROSCOPE", "Y Value: " + event.values[1]);
            Log.d("GYROSCOPE", "Z Value: " + event.values[2]);
        }
    };
}