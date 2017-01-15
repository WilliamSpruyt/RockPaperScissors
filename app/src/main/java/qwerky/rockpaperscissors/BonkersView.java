package qwerky.rockpaperscissors;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

/**
 * Created by Family on 13/12/2016.
 */

public class BonkersView extends View {

    float xpos;
    float ypos;
    int width;
    int height;
    double orbit = 0;
    double counter = 0;
    double increment;
    Canvas canvas;
    double tempxpos = 0;
    double deltaxpos = 0;
    Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Bitmap bmpicky = BitmapFactory.decodeResource(getResources(), R.drawable.beatslugthumb);
    Bitmap bmwicky = BitmapFactory.decodeResource(getResources(), R.drawable.beatsspoon);
    int bmwWidth = bmpicky.getWidth();
    int bmwHeight = bmpicky.getHeight();
    int bmpWidth = bmpicky.getWidth();
    int bmpHeight = bmpicky.getHeight();

    public BonkersView(Context context) {
        super(context);
    }


    public BonkersView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public BonkersView(Context context, AttributeSet attrs, int deafaultStyle) {
        super(context, attrs, deafaultStyle);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);

        canvas.drawBitmap(bmwicky, (width - bmwWidth) / 2, (height - bmwHeight) / 2, null);


        canvas.drawBitmap(bmpicky, (width - bmpWidth) / 2, 0, null);
        canvas.drawBitmap(bmpicky, 0, (height - bmpHeight) / 2, null);
        canvas.drawBitmap(bmpicky, (width - bmpWidth) / 2, (height - bmpHeight), null);
        canvas.drawBitmap(bmpicky, (width - bmpWidth), (height - bmpHeight) / 2, null);
        canvas.drawCircle(width / 2, height / 2, width / 3, mPaint);
        canvas.drawCircle(width / 2, height / 2, width / 6, mPaint);
        canvas.drawLine(width / 2, height / 2, (width / 2) - (width / 3), height / 2, mPaint);
        canvas.drawLine(width / 2, height / 2, width / 2, (height / 2) - (width / 3), mPaint);
        canvas.drawLine(width / 2, height / 2, (width / 2), (height / 2) + (width / 3), mPaint);
        canvas.drawLine(width / 2, height / 2, (width / 2) + (width / 3), height / 2, mPaint);


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int desiredWidth = 500;
        int desiredHeight = 500;

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);


        //Measure Width
        if (widthMode == MeasureSpec.EXACTLY) {
            //Must be this size
            width = widthSize;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            //Can't be bigger than...
            width = Math.min(desiredWidth, widthSize);
        } else {
            //Be whatever you want
            width = desiredWidth;
        }

        //Measure Height
        if (heightMode == MeasureSpec.EXACTLY) {
            //Must be this size
            height = heightSize;
        } else if (heightMode == MeasureSpec.AT_MOST) {
            //Can't be bigger than...
            height = Math.min(desiredHeight, heightSize);
        } else {
            //Be whatever you want
            height = desiredHeight;
        }
        orbit = 50;
        //MUST CALL THIS
        setMeasuredDimension(width, height);
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

    public void toaster(String message) {
        Context context = getContext();
        CharSequence text = "Hello toast!";
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(context, message, duration);
        toast.show();
    }

    void disco_ball(int width, int height) {

        orbit = Math.sqrt(Math.pow(xpos - width / 2, 2) + Math.pow(ypos - height / 2, 2));


        counter += 0.01;
        double ffs = width / 2 + (orbit * Math.sin(counter));
        double fFs = height / 2 + (orbit * Math.cos(counter));
        xpos = Float.valueOf(String.valueOf(ffs));
        tempxpos = xpos;
        ypos = Float.valueOf(String.valueOf(fFs));
        deltaxpos = ypos - tempxpos;


    }

}
