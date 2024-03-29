package com.example.simple.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Paint.FontMetricsInt;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;

import com.example.simple.R;

import static android.view.View.MeasureSpec.AT_MOST;
import static android.view.View.MeasureSpec.EXACTLY;
import static android.view.View.MeasureSpec.UNSPECIFIED;


/**
 * Created by guozhk on 2018/9/20.
 */


public class CircleView extends View {
    /**
     * 进度条最大值，默认为100
     */
    private int maxValue = 100;

    /**
     * 当前进度值
     */
    private int currentValue = 0;

    /**
     * 每次扫过的角度，用来设置进度条圆弧所对应的圆心角，alphaAngle=(currentValue/maxValue)*360
     */
    private float alphaAngle;

    /**
     * 底部圆弧的颜色，默认为Color.LTGRAY
     */
    private int firstColor;

    /**
     * 进度条圆弧块的颜色
     */
    private int secondColor;

    /**
     * 圆环的宽度
     */
    private int circleWidth;

    /**
     * 画圆弧的画笔
     */
    private Paint circlePaint;

    /**
     * 画文字的画笔
     */
    private Paint textPaint;

    /**
     * 渐变圆周颜色数组
     */
    private int[] colorArray = new int[]{Color.parseColor("#27B197"), Color.parseColor("#00A6D5")};//


    //中心
    // private int center;

    private int mCenterX;
    private int mCenterY;
    //半径
    private int radius;

    /**
     * 通过代码创建时才使用
     *
     * @param context
     */
    public CircleView(Context context) {
        this(context, null);
    }

    /**
     * 当从xml中加载view的时候，这个构造器才会被调用。其第二个参数中就包含自定义的属性。
     *
     * @param context 上下文
     * @param attrs   自定义属性
     */
    public CircleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /**
     * 从xml加载时执行和应用一个特定的风格。这里有两种方式，一是从theme中获得，二是从style中获得。        
     * 第三个参数官方有这样的说明： defStyle - The default style to apply to this view. If 0,
     * no style will be applied (beyond what is included in the theme). This may
     * either be an attribute resource, whose value will be retrieved from the
     * current theme, or an explicit style resource.
     * 默认的风格会被应用到这个view上。如果是0，没有风格将会被应用
     * （除了被包含在主题中）。这个也许是一个属性的资源，它的值是从当前的主题中检索，或者是一个明确的风格资源。
     *
     * @param context      上下文
     * @param attrs        自定义的属性
     * @param defStyleAttr 自定义风格
     */
    public CircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray ta = context.getTheme().obtainStyledAttributes(attrs, R.styleable.circleProgressBar,
                defStyleAttr, 0);
        int n = ta.getIndexCount();

        for (int i = 0; i < n; i++) {
            int attr = ta.getIndex(i);
            switch (attr) {
                case R.styleable.circleProgressBar_firstColor:
                    firstColor = ta.getColor(attr, Color.LTGRAY); // 默认底色为亮灰色
                    break;
                case R.styleable.circleProgressBar_secondColor:
                    secondColor = ta.getColor(attr, Color.BLUE); // 默认进度条颜色为蓝色
                    break;
                case R.styleable.circleProgressBar_circleWidth:
                    circleWidth = ta.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP, 6, getResources().getDisplayMetrics())); // 默认圆弧宽度为6dp
                    break;
                default:
                    break;
            }
        }
        ta.recycle();

        circlePaint = new Paint();
        circlePaint.setAntiAlias(true); // 抗锯齿
        circlePaint.setDither(true); // 防抖动
        circlePaint.setStrokeWidth(circleWidth);

        textPaint = new Paint();
        textPaint.setAntiAlias(true);
        textPaint.setDither(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // 获取宽-测量规则的模式和大小
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        // 获取高-测量规则的模式和大小
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        // 设置wrap_content的默认宽 / 高值
        // 默认宽/高的设定并无固定依据,根据需要灵活设置
        // 类似TextView,ImageView等针对wrap_content均在onMeasure()对设置默认宽 / 高值有特殊处理,具体读者可以自行查看
        int mWidth = 400;
        int mHeight = 400;

        switch (widthMode) {
            case UNSPECIFIED:
                break;
            case EXACTLY:
                break;
            case AT_MOST:
                widthSize=mWidth;
                break;
            default:
                break;
        }

        switch (heightMode) {
            case UNSPECIFIED:
                break;
            case EXACTLY:
                break;
            case AT_MOST:
                heightSize=mHeight;
                break;
            default:
                break;
        }

        setMeasuredDimension(widthSize, heightSize);

        int pLeft = getPaddingLeft();
        int pRight = getPaddingRight();
        int pTop = getPaddingTop();
        int pBottom = getPaddingBottom();


        int radiusX = this.getWidth() - pLeft - pRight;
        int radiusY = this.getHeight() - pTop - pBottom;

        radius = Math.min(radiusX, radiusY) / 2 - circleWidth / 2;
        mCenterX = this.getWidth() / 2;
        mCenterY = this.getHeight() / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawCircle(canvas, mCenterX, mCenterY, radius); // 绘制进度圆弧
        drawText(canvas, mCenterX, mCenterY);
    }

    /**
     * 绘制进度圆弧
     *
     * @param canvas 画布对象
     * @param x      圆心的x和y坐标
     * @param y      圆心的x和y坐标
     * @param radius 圆的半径
     */
    private void drawCircle(Canvas canvas, int x, int y, int radius) {
        circlePaint.setShader(null); // 清除上一次的shader
        circlePaint.setColor(firstColor); // 设置底部圆环的颜色，这里使用第一种颜色
        circlePaint.setStyle(Paint.Style.STROKE); // 设置绘制的圆为空心
        canvas.drawCircle(x, y, radius, circlePaint); // 画底部的空心圆
        RectF oval = new RectF(x - radius, y - radius,
                x + radius, y + radius); // 圆的外接正方形

        // 绘制颜色渐变圆环
        // shader类是Android在图形变换中非常重要的一个类。Shader在三维软件中我们称之为着色器，其作用是来给图像着色。
        LinearGradient linearGradient = new LinearGradient(circleWidth, circleWidth, getMeasuredWidth()
                - circleWidth, getMeasuredHeight() - circleWidth, colorArray, null, Shader.TileMode.MIRROR);
        circlePaint.setShader(linearGradient);
        circlePaint.setShadowLayer(10, 10, 10, Color.RED);
        circlePaint.setColor(secondColor); // 设置圆弧的颜色
        circlePaint.setStrokeCap(Paint.Cap.ROUND); // 把每段圆弧改成圆角的

        alphaAngle = currentValue * 360.0f / maxValue * 1.0f; // 计算每次画圆弧时扫过的角度，这里计算要注意分母要转为float类型，否则alphaAngle永远为0
        canvas.drawArc(oval, -90, alphaAngle, false, circlePaint);
    }

    /**
     * 绘制文字
     *
     * @param canvas 画布对象
     * @param x      圆心的x和y坐标
     * @param y      圆心的x和y坐标
     */
    private void drawText(Canvas canvas, int x, int y) {
        float result = (currentValue * 100.0f / maxValue * 1.0f); // 计算进度
        String percent = String.format("%.1f", result) + "%";

        textPaint.setTextAlign(Paint.Align.CENTER); // 设置文字居中，文字的x坐标要注意
        textPaint.setColor(Color.BLACK); // 设置文字颜色
        textPaint.setTextSize(40); // 设置要绘制的文字大小
        textPaint.setStrokeWidth(0); // 注意此处一定要重新设置宽度为0,否则绘制的文字会重叠
        Rect bounds = new Rect(); // 文字边框
        textPaint.getTextBounds(percent, 0, percent.length(), bounds); // 获得绘制文字的边界矩形
        FontMetricsInt fontMetrics = textPaint.getFontMetricsInt(); // 获取绘制Text时的四条线
        // int baseline = center + (fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.bottom; // 计算文字的基线,方法见http://blog.csdn.net/harvic880925/article/details/50423762
        // canvas.drawText(percent, center, baseline, textPaint); // 绘制表示进度的文字

        int baseline = y + (fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.bottom; // 计算文字的基线,方法见http://blog.csdn.net/harvic880925/article/details/50423762
        canvas.drawText(percent, x, baseline, textPaint); // 绘制表示进度的文字
    }

    /**
     * 设置圆环的宽度
     *
     * @param width
     */
    public void setCircleWidth(int width) {
        this.circleWidth = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, width, getResources()
                .getDisplayMetrics());
        circlePaint.setStrokeWidth(circleWidth);
        invalidate();
    }

    /**
     * 设置圆环的底色，默认为亮灰色LTGRAY
     *
     * @param color
     */
    public void setFirstColor(int color) {
        this.firstColor = color;
        circlePaint.setColor(firstColor);
        invalidate();
    }

    /**
     * 设置进度条的颜色，默认为蓝色<br>
     *
     * @param color
     */
    public void setSecondColor(int color) {
        this.secondColor = color;
        circlePaint.setColor(secondColor);
        invalidate();
    }

    /**
     * 设置进度条渐变色颜色数组
     *
     * @param colors 颜色数组，类型为int[]
     */
    public void setColorArray(int[] colors) {
        this.colorArray = colors;
        invalidate();
    }

    /**
     * 按进度显示百分比
     *
     * @param progress 进度，值通常为0到100
     */
    public void setProgress(int progress) {
        int percent = progress * maxValue / 100;
        if (percent < 0) {
            percent = 0;
        }
        if (percent > 100) {
            percent = 100;
        }
        this.currentValue = percent;
        invalidate();
    }

    /**
     * 按进度显示百分比，可选择是否启用数字动画
     *
     * @param progress     进度，值通常为0到100
     * @param useAnimation 是否启用动画，true为启用
     */
    public void setProgress(int progress, boolean useAnimation) {
        int percent = progress * maxValue / 100;
        if (percent < 0) {
            percent = 0;
        }
        if (percent > 100) {
            percent = 100;
        }
        if (useAnimation) // 使用动画
        {
            ValueAnimator animator = ValueAnimator.ofInt(0, percent);
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    currentValue = (int) animation.getAnimatedValue();
                    invalidate();
                }
            });
            //animator.setInterpolator(new OvershootInterpolator());
            animator.setInterpolator(new LinearInterpolator());
            animator.setDuration(10 * 1000L);
            animator.start();
        } else {
            setProgress(progress);
        }
    }
}


