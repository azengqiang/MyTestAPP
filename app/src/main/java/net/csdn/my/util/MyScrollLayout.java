package net.csdn.my.util;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

import net.csdn.my.inf.OnViewChangeListener;

/**
 * 继承viewGroup的自定义滑动布局
 */
public class MyScrollLayout extends ViewGroup {
    private VelocityTracker mVelocityTracker;//速度追踪 用于判断甩动手势
    private static final int snap_welocity = 300;
    private Scroller mScroller;//滑动控制
    private int mCurrentScreen;//当前页
    private int mDefaultScreen = 0;//起始页
    private float mLastMotionX;//上一个动作的x坐标
    private OnViewChangeListener mOnViewChangeListener;

    public MyScrollLayout(Context context) {
        super(context);
        init(context);
    }

    public MyScrollLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MyScrollLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    /**
     * 初始化引导页
     *
     * @param context
     */
    public void init(Context context) {
        mCurrentScreen = mDefaultScreen;
        mScroller = new Scroller(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // TODO 给定孩子view的大小
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        final int width = MeasureSpec.getSize(widthMeasureSpec);
        final int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        final int count = getChildCount();
        for (int i = 0; i < count; i++) {
            getChildAt(i).measure(widthMeasureSpec, heightMeasureSpec);
        }
        //滑动到指定位置
        scrollTo(mCurrentScreen * width, 0);
    }

    /*
     * 父亲初始左边的宽度为0。得到孩子的个数  for循环 得到每个孩子的view
     * 如果可见 得到孩子view的宽度  将该view放到合适位置 左 上 右 下 左边宽度加。。
     * */
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        // TODO 给定的viewgroup的布局
        if (changed) {
            int childLeft = 0;
            final int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                final View childView = getChildAt(i);
                if (childView.getVisibility() != View.GONE) {
                    final int childWidth = childView.getMeasuredWidth();
                    childView.layout(childLeft, 0, childLeft + childWidth,
                            childView.getMeasuredHeight());
                    childLeft += childWidth;
                }
            }
        }

    }

    //手指松开屏幕后的处理
    public void snapToDestination() {
        final int screenWidth = getWidth();
        //滑到一半自动完成未完成的动作 到下一个孩子view
        final int destScreen = (getScrollX() + screenWidth / 2) / screenWidth;
        snapToScreen(destScreen);
    }

    //根据指定的屏幕号 切换到该屏幕
    public void snapToScreen(int whichScreen) {
        whichScreen = Math.max(0, Math.min(whichScreen, getChildCount() - 1));
        if (getScrollX() != (whichScreen * getWidth())) {
            final int delta = whichScreen * getWidth() - getScrollX();
            mScroller.startScroll(getScrollX(), 0, delta, 0, Math.abs(delta * 2));
            mCurrentScreen = whichScreen;
            invalidate();
            if (mOnViewChangeListener != null) {
                mOnViewChangeListener.OnViewChange(mCurrentScreen);
            }
        }
    }

    //计算移动屏幕的位移和重新绘制屏幕
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            postInvalidate();
        }
    }

    public boolean onTouchEvent(MotionEvent e) {
        final int action = e.getAction();//得到触控事件
        final float x = e.getX();
        final float y = e.getY();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                if (mVelocityTracker == null) {
                    //当你要使用VelocityTracker跟踪一个touch事件速率的时候，
                    //使用obtain()方法得到这个类的实例
                    mVelocityTracker = VelocityTracker.obtain();
                    //用addMovement(MotionEvent)函数将你接受到的motion
                    //event加入到VelocityTracker类实例中
                    mVelocityTracker.addMovement(e);
                }
                if (!mScroller.isFinished()) {
                    mScroller.abortAnimation();//停止滑动
                }
                mLastMotionX = x;
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaX = (int) (mLastMotionX - x);
                if (isCanMove(deltaX)) {
                    if (mVelocityTracker != null) {
                        mVelocityTracker.addMovement(e);
                    }
                    mLastMotionX = x;
                    scrollBy(deltaX, 0);
                }
                break;
            case MotionEvent.ACTION_UP:
                int velocityX = 0;
                if (mVelocityTracker != null) {
                    mVelocityTracker.addMovement(e);
                    //1s中运动了多少像素
                    mVelocityTracker.computeCurrentVelocity(1000);
                    //当你使用到速率时，使用computeCurrentVelocity(int)初始化速率的单位，
                    //并获得当前的事件的速率，然后使用getXVelocity() 或getXVelocity()获得横向和竖向的速率。
                    velocityX = (int) mVelocityTracker.getXVelocity();
                }
                if (velocityX > snap_welocity && mCurrentScreen > 0) {
                    snapToScreen(mCurrentScreen - 1);
                } else if (velocityX < -snap_welocity &&
                        mCurrentScreen < getChildCount() - 1) {
                    snapToScreen(mCurrentScreen + 1);
                } else {
                    snapToDestination();
                }
                if (mVelocityTracker != null) {
                    mVelocityTracker.recycle();
                    mVelocityTracker = null;
                }
                break;
        }
        return true;
    }

    //判断引导页能不能移动
    public boolean isCanMove(int deltaX) {
        if (getScrollX() <= 0 && deltaX < 0) {
            return false;
        }
        if (getScrollX() >= (getChildCount() - 1) * getWidth() && deltaX > 0) {
            return false;
        }
        return true;
    }

    /**
     * 引导页的滑动监听接口的回调方法
     *
     * @param listener
     */
    public void SetOnViewChangeListener(OnViewChangeListener listener) {
        mOnViewChangeListener = listener;
    }

}
