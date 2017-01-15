package qwerky.rockpaperscissors;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by Family on 16/12/2016.
 */

public class BonkersSurfaceView extends SurfaceView {
    boolean swiping = false;
    int bitMapNo = 0;
    float pi = 0;
    int steer = 0;
    float xPos = 0;
    float yPos = 0;
    float deltaX = 5;
    float deltaY = 5;
    int iconWidth;
    int turner = -1;
    int iconHeight;
    float counter = 0;
    Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Bitmap bmpicky = BitmapFactory.decodeResource(getResources(), R.drawable.beatslugthumb);
    Bitmap bmwicky = BitmapFactory.decodeResource(getResources(), R.drawable.beatsspoon);
    int bmwWidth = bmpicky.getWidth();
    int bmwHeight = bmpicky.getHeight();
    int bmpWidth = bmpicky.getWidth();
    int bmpHeight = bmpicky.getHeight();
    private SurfaceHolder surfaceHolder;
    private Bitmap bmpIcon;
    private MyThread myThread;


    public BonkersSurfaceView(Context context) {
        super(context);
        init();
    }

    public BonkersSurfaceView(Context context,
                              AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BonkersSurfaceView(Context context,
                              AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {

        myThread = new MyThread(this);

        surfaceHolder = getHolder();
        bmpIcon = BitmapFactory.decodeResource(getResources(),
                R.drawable.beatslugthumb);

        iconWidth = bmpIcon.getWidth();
        iconHeight = bmpIcon.getHeight();

        surfaceHolder.addCallback(new SurfaceHolder.Callback() {

            @Override
            public void surfaceCreated(SurfaceHolder holder) {

                myThread.setRunning(true);
                myThread.start();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder,
                                       int format, int width, int height) {
                // TODO Auto-generated method stub

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                boolean retry = true;
                myThread.setRunning(false);
                while (retry) {
                    try {
                        myThread.join();
                        retry = false;
                    } catch (InterruptedException e) {
                    }
                }
            }
        });
    }

    protected void drawSomething(Canvas canvas) {
        canvas.drawColor(Color.BLACK);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setTextSize(30);
        int width = getWidth();
        int height = getHeight();


        canvas.drawBitmap(bmwicky, (getWidth() - bmwWidth) / 2, (getHeight() - bmwHeight) / 2, null);
        canvas.drawCircle(width / 2, height / 2, width / 3, mPaint);
        canvas.drawCircle(width / 2, height / 2, width / 6, mPaint);
        canvas.drawLine(width / 2, height / 2, (width / 2) - (width / 3), height / 2, mPaint);
        canvas.drawLine(width / 2, height / 2, width / 2, (height / 2) - (width / 3), mPaint);
        canvas.drawLine(width / 2, height / 2, (width / 2), (height / 2) + (width / 3), mPaint);
        canvas.drawLine(width / 2, height / 2, (width / 2) + (width / 3), height / 2, mPaint);
        if (!swiping) {

            for (float pi = 0; pi < 2.2; pi += 0.2) {
                disco_ball(getWidth(), getHeight(), (getHeight() / 3), Math.PI * pi);
                canvas.drawBitmap(bmpicky,
                        xPos - (bmpWidth / 2), yPos - (bmpHeight / 2), null);
            }


        }
        if (swiping)

        {
            for (float pi = 0; pi < 2.2; pi += 0.2) {
                fling(getWidth(), getHeight(), (getHeight() / 3), Math.PI * pi);

                canvas.drawBitmap(bmpicky,
                        xPos - (bmpWidth / 2), yPos - (bmpHeight / 2), null);
            }

        }


    }

    void disco_ball(int width, int height, int orbit, double placer) {

        //orbit = Math.sqrt(Math.pow(xPos - width / 2, 2) + Math.pow(yPos - height / 2, 2));


        counter += 0.01;

        double ffs = width / 2 + (orbit * Math.sin(counter + placer));
        double fFs = height / 2 + (orbit * Math.cos(counter + placer * turner));
        xPos = Float.valueOf(String.valueOf(ffs));
        //tempxpos=xPos;
        yPos = Float.valueOf(String.valueOf(fFs));


    }

    void fling(int width, int height, int orbit, double placer) {

        //orbit = Math.sqrt(Math.pow(xPos - width / 2, 2) + Math.pow(yPos - height / 2, 2));


        counter += 0.01;


        double fFs = height / 2 + (orbit * Math.cos(counter + placer * turner));
        xPos += steer;
        //tempxpos=xPos;
        yPos = Float.valueOf(String.valueOf(fFs));


    }

    public void setBm(int bittter, int wide, int high) {
        Bitmap bmpocky = BitmapFactory.decodeResource(getResources(), bittter);
        bmpicky = Bitmap.createScaledBitmap(bmpocky, wide, high, false);
        bmpWidth = bmpicky.getWidth();
        bmpHeight = bmpicky.getHeight();
    }

    public void setBmWicky(int bittter) {
        bmwicky = BitmapFactory.decodeResource(getResources(), bittter);
        bmwWidth = bmwicky.getWidth();
        bmwHeight = bmwicky.getHeight();
    }

    public void setSwipeDirection(String dir) {

        swiping = true;
        {
            switch (dir) {
                case ("left"):
                    steer = -10;
                    break;
                case ("right"):
                    steer = 5;
                    break;
            }
        }

    }

}

