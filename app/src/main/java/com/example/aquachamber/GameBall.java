package com.example.aquachamber;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Log;


public class GameBall extends GameObject{
    private int score;
    private double dya;
    private boolean up;
    private boolean playing;
    private Animation animation = new Animation();
    private long startTime;

    public GameBall(Bitmap res, int w, int h, int numFrames) {

        x = 100;
        y = GamePanel.HEIGHT / 2;
        dy = 0;
        score = 0;
        height = h;
        width = w;

        Bitmap[] images = {res};

        animation.setFrames(images);
        animation.setDelay(10);
        startTime = System.nanoTime();

    }

    public void setUp(boolean b){up = b;}

    public void update()
    {
        long elapsed = (System.nanoTime()-startTime)/1000000;
        if(elapsed>100)
        {
            score++;
            startTime = System.nanoTime();
        }
        animation.update();

        if(up) {
            dy = (int)(dya -= 1.1);
        } else {
            dy = (int)(dya += 1.1);
        }

        Log.d("height", "gamepanel height: " + GamePanel.HEIGHT);
        Log.d("height", "y value " + y);

        if(y >= GamePanel.HEIGHT || y < GamePanel.HEIGHT) {
            Log.d("height", "dy should change now");
            dy *= -1;
        }

        if(dy > 2) {
            dy = 2;
        }
        if(dy <= -2) {
            dy = -2;
        }
        y += dy*3;
        Log.d("height", "value of y after increment " + y);
        dy = 0;
    }

    public void draw(Canvas canvas)
    {
        canvas.drawBitmap(animation.getImage(),x,y,null);
    }
    public int getScore(){return score;}
    public boolean getPlaying(){return playing;}
    public void setPlaying(boolean b){playing = b;}
    public void resetDYA(){dya = 0;}
    public void resetScore(){score = 0;}
}