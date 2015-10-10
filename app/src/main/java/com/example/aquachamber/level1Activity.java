package com.example.aquachamber;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class level1Activity extends AppCompatActivity {

    private int xDelta;
    private int yDelta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level1);

        ImageView gameBall = (ImageView) findViewById(R.id.gameBall);



        Bitmap bitmap = ((BitmapDrawable) gameBall.getDrawable()).getBitmap();
        Log.d("demo", "game ball layout params width: " + bitmap.getWidth() + " height: " + bitmap.getHeight());


        Bitmap newGameBitmap = getCroppedBitmap(bitmap, (bitmap.getWidth() / 2));

        gameBall.setImageBitmap(newGameBitmap);

        gameBall.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                final int X = (int) event.getRawX();
                final int Y = (int) event.getRawY();
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();

//                Log.d("demo", "getRaw X " + X);
//                Log.d("demo", "getRaw Y " + Y);
                ImageView userBall = (ImageView)findViewById(R.id.gameBall);

                Display display = getWindowManager().getDefaultDisplay();
                Point size = new Point();
                display.getSize(size);
                int width = size.x;
                int height = size.y;
                Log.d("demo", "action down");

                switch (event.getAction() & MotionEvent.ACTION_MASK) {
                    case MotionEvent.ACTION_DOWN:
                        Log.d("demo", "action down");
                        xDelta = (int) (X - userBall.getTranslationX());
                        yDelta = (int) (Y - userBall.getTranslationY());
                        break;
                    case MotionEvent.ACTION_MOVE:
//                        int newX = (int) userBall.getTranslationX() - X;
//                        int newY = (int) userBall.getTranslationY() - Y;
                        Log.d("demo", "action down xDelta: " + (X-xDelta) + " width: " + width);
                        Log.d("demo", "action down yDelta: " + (Y - yDelta) + "height: " + height);
                        if ( (xDelta <= 0 || (X - xDelta) >= width) ||
                            (yDelta <= 0 || (Y - yDelta) >= height) )
                            break;
                        Log.d("demo", "set translation");
                        userBall.setTranslationX(X - xDelta);
                        userBall.setTranslationY(Y - yDelta);
                        break;
                }

                return true;
            }
        });
    }

    public static Bitmap getCroppedBitmap(Bitmap bmp, int radius) {
        Bitmap sbmp;
        if(bmp.getWidth() != radius || bmp.getHeight() != radius)
            sbmp = Bitmap.createScaledBitmap(bmp, radius, radius, false);
        else
            sbmp = bmp;
        Bitmap output = Bitmap.createBitmap(sbmp.getWidth(),
            sbmp.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xffa19774;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, sbmp.getWidth(), sbmp.getHeight());

        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setDither(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(Color.parseColor("#BAB399"));
        canvas.drawCircle(sbmp.getWidth() / 2+0.7f, sbmp.getHeight() / 2+0.7f,
            sbmp.getWidth() / 2+0.1f, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(sbmp, rect, rect, paint);


        return output;
    }
}
