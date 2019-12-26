package com.mic.customview.lockpatternview
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

/**
 * description: 九宫格自定义控件
 */


class LockPatternView : View {
    // 是否初始化，确保只初始化一次
    private var mIsInit = false

    // 二维数组初始化，int[3][3]
    private var mPoints: Array<Array<Point?>> = Array(3) { Array<Point?>(3, { null }) }

    // 外圆的半径
    private var mDotRadius = 0

    // 画笔
    private lateinit var mLinePaint: Paint
    private lateinit var mPressedPaint: Paint
    private lateinit var mErrorPaint: Paint
    private lateinit var mNormalPaint: Paint
    private lateinit var mArrowPaint: Paint
    // 颜色
    private val mOuterPressedColor = 0xff8cbad8.toInt()
    private val mInnerPressedColor = 0xff0596f6.toInt()
    private val mOuterNormalColor = 0xffd9d9d9.toInt()
    private val mInnerNormalColor = 0xff929292.toInt()
    private val mOuterErrorColor = 0xff901032.toInt()
    private val mInnerErrorColor = 0xffea0945.toInt()

    // 按下的时候是否是按在一个点上
    private var mIsTouchPoint = false
    // 选中的所有点
    private var mSelectPoints = ArrayList<Point>()

    // 构造函数
    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)


    override fun onDraw(canvas: Canvas) {
        // 初始化九个宫格，onDraw 回调用多次
        if (!mIsInit) {
            initDot()
            initPaint()
            mIsInit = true
        }

        // 绘制九个宫格
        drawShow(canvas)
    }

    /**
     * 绘制九个宫格显示
     */
    private fun drawShow(canvas: Canvas) {
        for (i in 0..2) {
            for (point in mPoints[i]) {
                if (point!!.statusIsNormal()) {
                    // 先绘制外圆
                    mNormalPaint.color = mOuterNormalColor
                    canvas.drawCircle(point.centerX.toFloat(), point.centerY.toFloat(),
                            mDotRadius.toFloat(), mNormalPaint)
                    // 后绘制内圆
                    mNormalPaint.color = mInnerNormalColor
                    canvas.drawCircle(point.centerX.toFloat(), point.centerY.toFloat(),
                            mDotRadius / 6.toFloat(), mNormalPaint)
                }

                if (point!!.statusIsPressed()) {
                    // 先绘制外圆
                    mPressedPaint.color = mOuterPressedColor
                    canvas.drawCircle(point.centerX.toFloat(), point.centerY.toFloat(),
                            mDotRadius.toFloat(), mPressedPaint)
                    // 后绘制内圆
                    mPressedPaint.color = mInnerPressedColor
                    canvas.drawCircle(point.centerX.toFloat(), point.centerY.toFloat(),
                            mDotRadius / 6.toFloat(), mPressedPaint)
                }

                if (point!!.statusIsError()) {
                    // 先绘制外圆
                    mErrorPaint.color = mOuterErrorColor
                    canvas.drawCircle(point.centerX.toFloat(), point.centerY.toFloat(),
                            mDotRadius.toFloat(), mErrorPaint)
                    // 后绘制内圆
                    mErrorPaint.color = mInnerErrorColor
                    canvas.drawCircle(point.centerX.toFloat(), point.centerY.toFloat(),
                            mDotRadius / 6.toFloat(), mErrorPaint)
                }
            }
        }

        //绘制两个点之间的连线以及箭头
        drawLine(canvas)
    }

    /**
     * 绘制两个点之间的连线以及箭头
     */
    private fun drawLine(canvas: Canvas) {
        if (mSelectPoints.size >= 1) {
            // 两个点之间需要绘制一条线和箭头
            var lastPoint = mSelectPoints[0]

            // if (mSelectPoints.size != 1) {
            // for(int i=1;i<mSelectPoints.size-1;i++)
            for (index in 1..mSelectPoints.size - 1) {
                // 两个点之间绘制一条线 sin cos 简单讲解 数学知识 Unity 物理学 光学
                // 贝塞尔曲线 - 讲一次数学课
                drawLine(lastPoint, mSelectPoints[index], canvas, mLinePaint)
                // 两个点之间绘制一个箭头
                drawArrow(canvas, mArrowPaint!!, lastPoint, mSelectPoints[index], (mDotRadius / 5).toFloat(), 38)
                lastPoint = mSelectPoints[index]
            }
            //}


            // 绘制最后一个点到手指当前位置的连线
            // 如果手指在内圆里面就不要绘制
            var isInnerPoint = MathUtil.checkInRound(lastPoint.centerX.toFloat(), lastPoint.centerY.toFloat(),
                    mDotRadius.toFloat() / 4, mMovingX, mMovingY)
            if (!isInnerPoint && mIsTouchPoint) {
                drawLine(lastPoint, Point(mMovingX.toInt(), mMovingY.toInt(), -1), canvas, mLinePaint)
            }
        }
    }

    /**
     * 画箭头
     */
    private fun drawArrow(canvas: Canvas, paint: Paint, start: Point, end: Point, arrowHeight: Float, angle: Int) {
        val d = MathUtil.distance(start.centerX.toDouble(), start.centerY.toDouble(), end.centerX.toDouble(), end.centerY.toDouble())
        val sin_B = ((end.centerX - start.centerX) / d).toFloat()
        val cos_B = ((end.centerY - start.centerY) / d).toFloat()
        val tan_A = Math.tan(Math.toRadians(angle.toDouble())).toFloat()
        val h = (d - arrowHeight.toDouble() - mDotRadius * 1.1).toFloat()
        val l = arrowHeight * tan_A
        val a = l * sin_B
        val b = l * cos_B
        val x0 = h * sin_B
        val y0 = h * cos_B
        val x1 = start.centerX + (h + arrowHeight) * sin_B
        val y1 = start.centerY + (h + arrowHeight) * cos_B
        val x2 = start.centerX + x0 - b
        val y2 = start.centerY.toFloat() + y0 + a
        val x3 = start.centerX.toFloat() + x0 + b
        val y3 = start.centerY + y0 - a
        val path = Path()
        path.moveTo(x1, y1)
        path.lineTo(x2, y2)
        path.lineTo(x3, y3)
        path.close()
        canvas.drawPath(path, paint)
    }


    /**
     * 画线
     */
    private fun drawLine(start: Point, end: Point, canvas: Canvas, paint: Paint) {
        val pointDistance = MathUtil.distance(start.centerX.toDouble(), start.centerY.toDouble(),
                end.centerX.toDouble(), end.centerY.toDouble())

        var dx = end.centerX - start.centerX
        var dy = end.centerY - start.centerY

        val rx = (dx / pointDistance * (mDotRadius / 6.0)).toFloat()
        val ry = (dy / pointDistance * (mDotRadius / 6.0)).toFloat()
        canvas.drawLine(start.centerX + rx, start.centerY + ry, end.centerX - rx, end.centerY - ry, paint)
    }


    /**
     * 初始化画笔
     * 3个点状态的画笔，线的画笔，箭头的画笔
     */
    private fun initPaint() {
        // new Paint 对象 ，设置 paint 颜色
        // 线的画笔
        mLinePaint = Paint()
        mLinePaint.color = mInnerPressedColor
        mLinePaint.style = Paint.Style.STROKE
        mLinePaint.isAntiAlias = true
        mLinePaint.strokeWidth = (mDotRadius / 9).toFloat()
        // 按下的画笔
        mPressedPaint = Paint()
        mPressedPaint.style = Paint.Style.STROKE
        mPressedPaint.isAntiAlias = true
        mPressedPaint.strokeWidth = (mDotRadius / 6).toFloat()
        // 错误的画笔
        mErrorPaint = Paint()
        mErrorPaint.style = Paint.Style.STROKE
        mErrorPaint.isAntiAlias = true
        mErrorPaint.strokeWidth = (mDotRadius / 6).toFloat()
        // 默认的画笔
        mNormalPaint = Paint()
        mNormalPaint.style = Paint.Style.STROKE
        mNormalPaint.isAntiAlias = true
        mNormalPaint.strokeWidth = (mDotRadius / 9).toFloat()
        // 箭头的画笔
        mArrowPaint = Paint()
        mArrowPaint.color = mInnerPressedColor
        mArrowPaint.style = Paint.Style.FILL
        mArrowPaint.isAntiAlias = true
    }

    /**
     * 初始化点
     */
    private fun initDot() {
        // 九个宫格，存到集合  Point[3][3]
        // 不断绘制的时候这几个点都有状态，而且后面肯定需要回调密码点都有下标 点肯定是一个对象
        // 计算中心位置
        var width = this.width
        var height = this.height

        // 兼容横竖屏
        var offsetX = 0
        var offsetY = 0
        if (height > width) {
            offsetY = (height - width) / 2
            height = width
        } else {
            offsetX = (width - height) / 2
            width = height
        }

        var squareWidth = width / 3

        // 外圆的大小，根据宽度来
        mDotRadius = width / 12

        // 计算和指定点的中心点位置
        mPoints[0][0] = Point(offsetX + squareWidth / 2, offsetY + squareWidth / 2, 0)
        mPoints[0][1] = Point(offsetX + squareWidth * 3 / 2, offsetY + squareWidth / 2, 1)
        mPoints[0][2] = Point(offsetX + squareWidth * 5 / 2, offsetY + squareWidth / 2, 2)
        mPoints[1][0] = Point(offsetX + squareWidth / 2, offsetY + squareWidth * 3 / 2, 3)
        mPoints[1][1] = Point(offsetX + squareWidth * 3 / 2, offsetY + squareWidth * 3 / 2, 4)
        mPoints[1][2] = Point(offsetX + squareWidth * 5 / 2, offsetY + squareWidth * 3 / 2, 5)
        mPoints[2][0] = Point(offsetX + squareWidth / 2, offsetY + squareWidth * 5 / 2, 6)
        mPoints[2][1] = Point(offsetX + squareWidth * 3 / 2, offsetY + squareWidth * 5 / 2, 7)
        mPoints[2][2] = Point(offsetX + squareWidth * 5 / 2, offsetY + squareWidth * 5 / 2, 8)
    }

    // 手指触摸的位置
    private var mMovingX = 0f
    private var mMovingY = 0f
    override fun onTouchEvent(event: MotionEvent): Boolean {
        mMovingX = event.x
        mMovingY = event.y

        // 你可以自己用Java ，后面会专门讲 kotlin 的语法
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                // 判断手指是不是按在一个 宫格上面
                // 如何判断一个点在圆里面   点到圆心的距离 < 半径
                var point = point
                if (point != null) {
                    mIsTouchPoint = true
                    mSelectPoints.add(point)
                    // 改变当前点的状态
                    point.setStatusPressed()
                }
            }

            MotionEvent.ACTION_MOVE -> {
                if (mIsTouchPoint) {
                    // 按下的时候一定要在一个点上，不断触摸的时候不断去判断新的点
                    var point = point
                    if (point != null) {
                        if (!mSelectPoints.contains(point)) {
                            mSelectPoints.add(point)
                        }
                        // 改变当前点的状态
                        point.setStatusPressed()
                    }
                }
            }

            MotionEvent.ACTION_UP -> {
                mIsTouchPoint = false
                // 回调密码获取监听 今晚8点再讲  显示错误，错误显示完之后要清空恢复默认
            }
        }

        invalidate()
        return true
    }

    /**
     * 获取按下的点
     * @return 当前按下的点
     */
    private val point: Point?
        get() {
            for (i in 0..2) {
                for (point in mPoints[i]) {
                    // for 循环九个点，判断手指位置是否在这个九个点里面
                    if (MathUtil.checkInRound(point!!.centerX.toFloat(), point.centerY.toFloat(),
                                    mDotRadius.toFloat(), mMovingX, mMovingY)) {
                        return point
                    }
                }
            }
            return null
        }


    /**
     * 宫格的类
     */
    class Point(var centerX: Int, var centerY: Int, var index: Int) {

        private val STATUS_NORMAL = 1
        private val STATUS_PRESSED = 2
        private val STATUS_ERROR = 3
        // 当前点的状态 有三种状态
        private var status = STATUS_NORMAL


        fun setStatusPressed() {
            status = STATUS_PRESSED
        }

        fun setStatusNormal() {
            status = STATUS_NORMAL
        }

        fun setStatusError() {
            status = STATUS_ERROR
        }

        fun statusIsPressed(): Boolean {
            return status == STATUS_PRESSED
        }

        fun statusIsError(): Boolean {
            return status == STATUS_ERROR
        }

        fun statusIsNormal(): Boolean {
            return status == STATUS_NORMAL
        }
    }

}


