package com.mic.demo.viewwy.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * 变换操作
 */
public class CanvasTransformView extends View {

    private Paint mPaint;

    public CanvasTransformView(Context context) {
        this(context, null);
    }

    public CanvasTransformView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CanvasTransformView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(4);
        mPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        //1，平移操作
//        canvas.drawRect(0,0, 400, 400, mPaint);
          //平移后的原理定义到了(50.50)
//        canvas.translate(50, 50);
//        mPaint.setColor(Color.GRAY);
//        canvas.drawRect(0,0, 400, 400, mPaint);
//        canvas.drawLine(0, 0, 600,600, mPaint);

//        //缩放操作
//        canvas.drawRect(200,200, 700,700, mPaint);
////        canvas.scale(0.5f, 0.5f);
//        //先translate(px, py)后面两个参数决定,再scale(sx, sy),再反向translate ，这行代码等同于如下三行
//        //canvas.scale(0.5f, 0.5f, 200,200);
//
          //这三行与上面一行功能相同
//        canvas.translate(200, 200);
//        canvas.scale(0.5f, 0.5f);
//        canvas.translate(-200, -200);
//
//        mPaint.setColor(Color.GRAY);
//        canvas.drawRect(200,200, 700,700, mPaint);
//        canvas.drawLine(0,0, 400, 600, mPaint);

//        //旋转操作
//        canvas.translate(50,50);
//        canvas.drawRect(0,0, 700,700, mPaint);
          //按顺时针旋转
//        canvas.rotate(45);
//        mPaint.setColor(Color.GRAY);
//        canvas.drawRect(0,0, 700,700, mPaint);

//        canvas.drawRect(400, 400, 900, 900, mPaint);
//        canvas.rotate(45, 650, 650); //px, py表示旋转中心的坐标
//        mPaint.setColor(Color.GRAY);
//        canvas.drawRect(400, 400, 900, 900, mPaint);

//        //倾斜操作
//        canvas.drawRect(0,0, 400, 400, mPaint);
////        canvas.skew(1, 0); //在X方向倾斜45度,Y轴逆时针旋转45
//        canvas.skew(0, 1); //在y方向倾斜45度， X轴顺时针旋转45
//        mPaint.setColor(Color.GRAY);
//        canvas.drawRect(0, 0, 400, 400, mPaint);

        //切割
//        canvas.drawRect(200, 200,700, 700, mPaint);
//        mPaint.setColor(Color.GRAY);
//        canvas.drawRect(200, 800,700, 1300, mPaint);
//        canvas.clipRect(200, 200,700, 700); //画布被裁剪
//        canvas.drawCircle(100,100, 100,mPaint); //坐标超出裁剪区域，无法绘制
//        canvas.drawCircle(300, 300, 100, mPaint); //坐标区域在裁剪范围内，绘制成功

//        canvas.drawRect(200, 200,700, 700, mPaint);
//        mPaint.setColor(Color.GRAY);
//        canvas.drawRect(200, 800,700, 1300, mPaint);
//        canvas.clipOutRect(200,200,700,700); //画布裁剪外的区域
//        canvas.drawCircle(100,100,100,mPaint); //坐标区域在裁剪范围内，绘制成功
//        canvas.drawCircle(300, 300, 100, mPaint);//坐标超出裁剪区域，无法绘制

        //矩阵
        canvas.drawRect(0,0,700,700, mPaint);
        Matrix matrix = new Matrix();
//        matrix.setTranslate(50,50);
//        matrix.setRotate(45);
        matrix.setScale(0.5f, 0.5f);
        canvas.setMatrix(matrix);
        mPaint.setColor(Color.GRAY);
        canvas.drawRect(0,0,700,700, mPaint);

    }

}
