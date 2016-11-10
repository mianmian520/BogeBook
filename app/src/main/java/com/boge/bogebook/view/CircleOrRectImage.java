package com.boge.bogebook.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.boge.bogebook.R;


/**
 * Description:圆形和圆角图片
 * Created by boge on 2016/8/28.
 */
public class CircleOrRectImage extends ImageView {

    private Context context;
    /***画笔*/
    private Paint mPaint;
    /***3x3 矩阵，主要用于缩小放大*/
    private Matrix mMatrix;
    /***圆的半径*/
    private int mRadius;
    /***控件宽度*/
    private int mWidht;
    /**
     * 图像渲染
     * BitmapShader的作用是使用一张位图作为纹理来对某一区域进行填充。可以想象成在一块区域内铺瓷砖，只是这里的瓷砖是一张张位图而已
     */
    private BitmapShader bitmapShader;
    /***矩形*/
    private RectF mRoundRect;
    public CircleOrRectImage(Context context) {
        super(context);
    }

    public CircleOrRectImage(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init(attrs);
    }

    public CircleOrRectImage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.parseColor("#FFFFFF"));

        TypedArray typedArray = context.obtainStyledAttributes(attrs , R.styleable.CircleOrRectImage);
        isCricle = typedArray.getBoolean(R.styleable.CircleOrRectImage_isCircle,true);
        orderRadius = (int) typedArray.getDimension(R.styleable.CircleOrRectImage_orderRadius,dip2px(10));
        typedArray.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if(isCricle){
            mWidht = Math.min(getMeasuredWidth(),getMeasuredHeight());
            mRadius = mWidht/2;
            setMeasuredDimension(mWidht,mWidht);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(getDrawable() == null){
            return;
        }
        setImage();
        if(isCricle){
            canvas.drawCircle(mRadius,mRadius,mRadius,mPaint);
        }else{
            canvas.drawRoundRect(mRoundRect , orderRadius , orderRadius , mPaint);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if(!isCricle){
            mRoundRect = new RectF(0 , 0 , w , h);
        }
    }

    private void setImage() {
        Drawable drawable = getDrawable();
        if(drawable == null){
            return;
        }
        Bitmap bitmap = drawableToBitamp(drawable);
        /**
         * 参数bitmap表示用来作为纹理填充的位图
         * 后两个 表示在位图X方向上位图衔接形式；表示在位图Y方向上位图衔接形式。
         * Shader.TileMode有3种参数可供选择，分别为CLAMP、REPEAT和MIRROR。
         * CLAMP的作用是如果渲染器超出原始边界范围，则会复制边缘颜色对超出范围的区域进行着色。
         * REPEAT的作用是在横向和纵向上以平铺的形式重复渲染位图。
         * MIRROR的作用是在横向和纵向上以镜像的方式重复渲染位图。
         */
        bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP , Shader.TileMode.CLAMP);
        float scale = 1.0f;
        if(isCricle){
            int size = Math.min(bitmap.getWidth(), bitmap.getHeight());
            scale = mWidht * 1.0f / size;
        } else {
            if (!(bitmap.getWidth() == getWidth() && bitmap.getHeight() == getHeight())) {
                // 如果图片的宽或者高与view的宽高不匹配，计算出需要缩放的比例；缩放后的图片的宽高，一定要大于我们view的宽高；所以我们这里取大值；
                scale = Math.max(getWidth() * 1.0f / bitmap.getWidth(),getHeight() * 1.0f / bitmap.getHeight());
            }
        }

        mMatrix = new Matrix();
        // shader的变换矩阵，我们这里主要用于放大或者缩小
        // scale * scale 的矩阵
        mMatrix.setScale(scale , scale);
        // 设置变换矩阵
        bitmapShader.setLocalMatrix(mMatrix);
        mPaint.setShader(bitmapShader);
    }

    /***
     * drawable转化为BitMap
     * @param drawable
     * @return
     */
    public Bitmap drawableToBitamp(Drawable drawable){
        if(drawable instanceof BitmapDrawable){
            BitmapDrawable bd = (BitmapDrawable) drawable;
            return bd.getBitmap();
        }
        //如果没有绘图，创建一个空白图片
        //得到宽和高
        int widht = drawable.getIntrinsicWidth();
        int height = drawable.getIntrinsicHeight();
        //创建bitmap
        Bitmap bitmap = Bitmap.createBitmap(widht, height, Bitmap.Config.ARGB_8888);
        //添加画板
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0 , 0 , widht , height);
        drawable.draw(canvas);
        return bitmap;
    }

    /***
     * 得到手机密度
     * @param dpValue
     * @return
     */
    public int dip2px(float dpValue) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /***是否是圆形*/
    private boolean isCricle;
    /***圆角半径*/
    private int orderRadius;

    public int getOrderRadius() {
        return orderRadius;
    }

    public void setOrderRadius(int orderRadius) {
        int v = dip2px(orderRadius);
        if(orderRadius != v){
            this.orderRadius = orderRadius;
            invalidate();
        }
    }

    public boolean isCricle() {
        return isCricle;
    }

    public void setCricle(boolean cricle) {
        if(isCricle != cricle){
            isCricle = cricle;
            requestLayout();
        }
    }
}
