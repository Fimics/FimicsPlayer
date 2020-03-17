package com.mic.view;

import android.animation.TypeEvaluator;
import android.graphics.PointF;

/**
 * Created by hcDarren on 2017/8/6.
 * 自定义路径属性动画
 */

public class LoveTypeEvaluator implements TypeEvaluator<PointF>{
    private PointF point1,point2;
    public LoveTypeEvaluator(PointF point1,PointF point2){
        this.point1 = point1;
        this.point2  =point2;
    }

    @Override
    public PointF evaluate(float t, PointF point0, PointF point3) {
        // t 是 [0,1]  开始套公式 公式有四个点 还有两个点从哪里来（构造函数中来）
        PointF pointF = new PointF();

        pointF.x = point0.x*(1-t)*(1-t)*(1-t)
                + 3*point1.x*t*(1-t)*(1-t)
                + 3*point2.x*t*t*(1-t)
                + point3.x*t*t*t;

        pointF.y = point0.y*(1-t)*(1-t)*(1-t)
                + 3*point1.y*t*(1-t)*(1-t)
                + 3*point2.y*t*t*(1-t)
                + point3.y*t*t*t;

        return pointF;
    }
}
